<!DOCTYPE html>
<html>

    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'question.label', default: 'Question')}" />
        <title>
            <g:message code="default.show.label" args="[entityName]" />
        </title>
    </head>

    <body>
        <a href="#show-question" class="skip" tabindex="-1">
            <g:message code="default.link.skip.label" default="Skip to content&hellip;" />
        </a>
        <div class="nav" role="navigation">
            <ul>
                <li>
                    <a class="home" href="${createLink(uri: '/')}">
                        <g:message code="default.home.label" />
                    </a>
                </li>
            </ul>
        </div>

        <div id="show-question" class="content scaffold-show" role="main">

            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <!--<f:display bean="question" />-->

            <div class="title question">
                <f:display bean="question" property="title" />
            </div>

            <div class="content question">
                <f:display bean="question" property="content" />

            </div>

            <div class="answers question">

                <g:each in="${question.answers}" var="ans">
                    <div class="answer">
                        <g:render template="/answer/show" model="[answer: ans]" />
                    </div>
                </g:each>

            </div>

            <!--This is an empty question to answer-->
            <sec:ifLoggedIn>
                <div class="create answer question">
                    <div class="answer">
                        <g:include controller="answer" action="create" />
                    </div>
                </div>
            </sec:ifLoggedIn>

<sec:ifAnyGranted roles='ROLE_ADMIN'>
                    TPTPAR
                </sec:ifAnyGranted>

            <sec:ifLoggedIn>
                    <g:form resource="${this.question}" method="DELETE">
                        <fieldset class="buttons">
                            <g:link class="edit" action="edit" resource="${this.question}"> <g:message code="default.button.edit.label" default="Edit" /> </g:link>
                <sec:ifAnyGranted roles='ROLE_ADMIN'>
                    TPTPAR
                </sec:ifAnyGranted>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
                </g:form>
            </sec:ifLoggedIn>
</div>
</body>

</html>