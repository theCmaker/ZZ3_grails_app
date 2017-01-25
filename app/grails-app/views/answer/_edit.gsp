<!DOCTYPE html>
<html>

    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'answer.label', default: 'Answer')}" />
        <title>
            <g:message code="default.edit.label" args="[entityName]" />
        </title>
    </head>

    <body>

        <div id="edit-answer" class="content scaffold-edit answer" role="main">

            <g:form resource="${this.answer}" method="PUT">
                <g:hiddenField name="version" value="${this.answer?.version}" />

                <div class="form-group">
                    <!--Answer content to change-->
                    <f:field bean="answer" property="content"/>
                </div>

                <input class="btn btn-success" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
            </g:form>
        </div>
    </body>

</html>