<!DOCTYPE html>
<html>

    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title>
            <g:message code="default.show.label" args="[entityName]" />
        </title>
    </head>

    <body>
        <a href="#show-user" class="skip" tabindex="-1">
            <g:message code="default.link.skip.label" default="Skip to content&hellip;" />
        </a>

        <div id="show-user" class="content scaffold-show" role="main">
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>


            <h1>
                <i class="glyphicon glyphicon-user"></i> <f:display bean="user" property="username" label="TOTOTOT"/>
            </h1>

            <div class="row">
                <h2>
                    <i class="glyphicon glyphicon-certificate"></i> <g:message code="default.user.show.badge" default="Badges" />
                </h2>
                <g:render template="/badge/summary" model="[badges: this.user.badges]" />
            </div>
            
            <sec:ifAnyGranted  roles="ROLE_ADMIN">
                <div class="row">
                    <f:display bean="user" property="enabled" />
                    <f:display bean="user" property="accountExpired" />
                    <f:display bean="user" property="accountLocked" />
                    <f:display bean="user" property="passwordExpired" />
                </div>
            </sec:ifAnyGranted>

            <div class="row">
                <h2>
                    <i class="glyphicon glyphicon-question-sign"></i> <g:message code="default.user.show.question" default="Questions" />
                </h2>
                <g:render template="/question/summary" model="[questions: this.user.questions]" />
            </div>

            <div class="row">
                <h2>
                    <i class="glyphicon glyphicon-share"></i> <g:message code="default.user.show.answer" default="Answers" />
                </h2>
                <g:render template="/answer/summary" model="[answers: this.user.answers]" />
            </div>

            <sec:ifLoggedIn>
                <sec:access expression="hasRole('ROLE_ADMIN') || principal.id == ${this.user.id}">
                    <g:form resource="${this.user}" method="DELETE" class="btn-group">
                        <g:link class="btn btn-primary" action="edit" resource="${this.user}">
                            <g:message code="default.button.edit.label" default="Edit" />
                        </g:link>
                        
                        <input class="btn btn-danger" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                    </g:form>
                </sec:access>
            </sec:ifLoggedIn>
</div>
</body>

</html>