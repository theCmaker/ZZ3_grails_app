<!doctype html>
<html>

    <head>
        <meta name="layout" content="main" />
        <title>Manage features</title>
        <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
    </head>

    <body>

        <div id="content" role="main">                

            <g:each in="${featureList}" var="feature">
        
                <g:render template="/feature/show" model='[feature: feature]' />

            </g:each>

        </div>

    </body>

</html>