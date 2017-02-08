<!doctype html>
<html>

    <head>
        <meta name="layout" content="main" />
        <title>Welcome to OverfloZZ</title>

        <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
    </head>

    <body>

        <div id="content" role="main">
            <section class="row colset-2-its">
                <h1>
                    <g:message code="default.feature.sorry" default="Hum..." />
                </h1>

                <p>
                    <g:message code="default.feature.sorry.long" default="It does not work ..."/>
                </p>

                <g:include controller="question" action="index" />

            </section>
        </div>

    </body>

</html>