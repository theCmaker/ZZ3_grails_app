<!doctype html>
<html>

    <head>
        <meta name="layout" content="main" />
        <title>Welcome to Grails</title>

        <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
    </head>

    <body>

        <div id="content" role="main">
            <section class="row colset-2-its">
                <h1>Welcome to OverfloZZ</h1>

                <p>
                    You can ask everything you want! Except a million $...
                </p>

                <div id="askQuestion">
                    <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
                        <li class="controller">
                            <g:link controller="${c.logicalPropertyName}">${c}</g:link>
                        </li>
                    </g:each>
                </div>

                <div id="controllers" role="navigation">
                    <h2>Available Controllers:</h2>
                    <ul>
                        <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
                            <li class="controller">
                                <g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link>
                            </li>
                        </g:each>
                    </ul>
                </div>
            </section>
        </div>

    </body>

</html>