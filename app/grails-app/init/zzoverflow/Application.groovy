package zzoverflow

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration

import org.springframework.boot.actuate.health.DiskSpaceHealthIndicatorProperties

class Application extends GrailsAutoConfiguration {
    static void main(String[] args) {
        GrailsApp.run(Application, args)
    }

    @Override
    Closure doWithSpring() {
        { ->
            // Create instance for URL health indicator.
            weatherServiceHealthCheck(WeatherServiceHealthIndicator, 'http://api.openweathermap.org/data/2.5/weather?q=Paris&APPID=b2f89b7079ce1606126d2693ff44af5a', 2000)
 
            diskSpaceHealthIndicatorProperties(DiskSpaceHealthIndicatorProperties) {
                threshold = 250 * 1024 * 1024
            }
        }
    }
}