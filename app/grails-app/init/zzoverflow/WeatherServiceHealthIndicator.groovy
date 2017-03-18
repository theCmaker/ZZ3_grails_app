package zzoverflow
 
import org.springframework.boot.actuate.health.AbstractHealthIndicator
import org.springframework.boot.actuate.health.Health
 
class WeatherServiceHealthIndicator extends AbstractHealthIndicator {
 
    private final String url
 
    private final int timeout
 
    WeatherServiceHealthIndicator(final String url, final int timeout = 10 * 1000) {
        this.url = url
        this.timeout = timeout
    }
 
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        final HttpURLConnection urlConnection =
                (HttpURLConnection) url.toURL().openConnection()
 
        final int responseCode =
                urlConnection.with {
                    requestMethod = 'GET'
                    readTimeout = timeout
                    connectTimeout = timeout
                    connect()
                    responseCode
                }
 
        // If code in 200 to 399 range everything is fine.
        responseCode in (200..399) ?
                builder.up() :
                builder.down(
                        new Exception(
                                "Invalid responseCode '${responseCode}' checking '${url}'."))
    }
}