<!DOCTYPE html>
<html>

    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'question.label', default: 'Question')}" />
        <title>
            <g:message code="default.create.label" args="[entityName]" />
        </title>
    </head>

    <body>
        <a href="#create-question" class="skip" tabindex="-1">
            <g:message code="default.link.skip.label" default="Skip to content&hellip;" />
        </a>

        <div class="content scaffold-create" role="main">
            <h1>
                <g:message code="default.create.label" args="[entityName]" />
            </h1>
            
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.question}">
                <ul class="errors" role="alert">
                    <g:eachError bean="${this.question}" var="error">
                        <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>>
                            <g:message error="${error}" />
                        </li>
                    </g:eachError>
                </ul>
            </g:hasErrors>

            <g:form class="form-group" action="save">

                <!--Accessible fields-->
                <f:field bean="question" property="title" />
                <f:field bean="question" property="content" />

                  <!--Hidden fields-->
                <div class="hidden">
                    <f:field bean="question" property="date" />
                    <f:field bean="question" property="tags" />
                    <f:field bean="question" property="answers" />
                    <f:field bean="question" property="user" />
                </div>

                
                <button name="create" class="btn btn-success">
                    <i class="glyphicon glyphicon-send"></i> <g:message code="default.question.create"/>
                </button>
            </g:form>
        </div>
    </body>

</html>