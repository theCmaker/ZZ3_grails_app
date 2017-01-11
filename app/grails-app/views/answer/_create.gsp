<!--<!DOCTYPE html>
<html>

    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'answer.label', default: 'Answer')}" />
        <title>
            <g:message code="default.create.label" args="[entityName]" />
        </title>
    </head>

    <body>-->

        <div id="create-answer" class="content scaffold-create answer" role="main">
            <g:form action="save">

                <!--Answer content-->
                <f:field bean="answer" property="content" />

                <!--Hidden fields-->
                <div style="display:none;">
                <f:field bean="answer" property="question" />
                <f:field bean="answer" property="user" />
        </div>

            <!--Save the answer-->
            <g:submitButton name="create" class="save" value="${message(code: 'default.answer.answer', default: 'Create')}" />
            </g:form>
        </div>
<!--</body>-->