<!doctype html>
<html>

    <head>
        <meta name="layout" content="main" />
        <title>Manage features</title>
        <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
    </head>

    <body>

        <div id="content" role="main">                

            <g:form action="save" class="form">

                <g:each in="${featureList}" var="feature">
            
                    <g:render template="/feature/show" model='[feature: feature]' />

                </g:each>
    
                <button name="create" class="btn btn-success">
                    <i class="glyphicon glyphicon-send"></i> <g:message code="default.button.save" />
                </button>
            </g:form>

        </div>

    </body>

</html>