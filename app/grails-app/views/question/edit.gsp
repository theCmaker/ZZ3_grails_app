<!DOCTYPE html>
<html>

    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'question.label', default: 'Question')}" />
        <title>
            <g:message code="default.edit.label" args="[entityName]" />
        </title>
    </head>

    <body>
        <a href="#edit-question" class="skip" tabindex="-1">
            <g:message code="default.link.skip.label" default="Skip to content&hellip;" />
        </a>
        <div id="edit-question" class="content scaffold-edit" role="main">
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
            <g:form resource="${this.question}" method="PUT">
                <g:hiddenField name="version" value="${this.question?.version}" />

                <!--Accessible fields-->
                <h1>
                    <f:display bean="question" property="title" />
                </h1>

                <div class="form-group">
                    <f:field bean="question" property="content" />
                </div>

                <!--Hidden fields-->
                <div class="hidden">
                    <f:field bean="question" property="date" />
                    <f:field bean="question" property="tags" />
                    <f:field bean="question" property="answers" />
                    <f:field bean="question" property="user" />
                </div>

                <button class="save btn btn-success" type="submit">
                    <g:message code="default.question.update"/>
                </button>
            </g:form>
        </div>
    </body>

</html>