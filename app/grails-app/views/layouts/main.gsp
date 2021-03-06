<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Overflozz"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <asset:stylesheet src="application.css"/>

    <g:layoutHead/>
</head>
<body>
    <div class="navbar navbar-default navbar-static-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/">
                    <i class="fa grails-icon">
                        <asset:image src="overflozz-logo.png"/>
                    </i> ${message(code: 'default.site.name')}
                </a>
            </div>
            <div class="navbar-collapse collapse" aria-expanded="false" style="height: 0.8px;">
                <ul class="nav navbar-nav navbar-right">
                    <g:pageProperty name="page.nav" />
                    <sec:ifLoggedIn>

                        <sec:ifAnyGranted roles="ROLE_ADMIN" >
                            <li>
                                <g:link controller="feature" action="index">
                                    <i class="glyphicon glyphicon-tasks"></i>
                                    <g:message code="default.feature.button" default="Features" />
                                </g:link>
                            </li>
                        </sec:ifAnyGranted>

                        <li class="login">
                            <g:link controller="user" action="show" params="[id: sec.loggedInUserInfo(field:'id')]" >
                                <i class="glyphicon glyphicon-user"></i> ${message(code: 'default.loggin.greeting', default: 'You are')} <sec:username/>
                            </g:link>
                        </li>
                        <li>
                            <a class="login" href="${createLink(uri: '/logout')}">
                                <i class="glyphicon glyphicon-log-out"></i> <g:message code="default.loggin.logout" />
                            </a>
                        </li>
                    </sec:ifLoggedIn>
                    <sec:ifNotLoggedIn>
                        <li>
                            <g:link controller="user" action="create">
                                <i class="glyphicon glyphicon-edit"></i>
                                <g:message code="default.loggin.sign" default="Sign up" />
                            </g:link>
                        </li>
                        <li>
                            <a class="login" href="${createLink(uri: '/login')}">
                                <i class="glyphicon glyphicon-log-in"></i> <g:message code="default.loggin.login" />
                            </a>
                        </li>
                    </sec:ifNotLoggedIn>
                </ul>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <g:layoutBody/>
        </div>
    </div>
    <div class="footer" role="contentinfo"></div>

    <div id="spinner" class="spinner" style="display:none;">
        <g:message code="spinner.alt" default="Loading&hellip;"/>
    </div>

    <asset:javascript src="application.js"/>

</body>
</html>
