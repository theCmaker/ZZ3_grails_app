<div class="form-group" role="main">

    <g:form action="save" class="form-group">

        <!--Answer content-->
        <label for="answerContent">${message(code: 'default.answer.label.content', default: 'Content')}</label>
        <f:widget id="answerContent" class="form-control" bean="answer" property="content" />

        <!--Hidden fields-->
        <div class="hidden">
            <f:field bean="answer" property="question" />
            <f:field bean="answer" property="user" />
            <f:field bean="answer" property="date" />
        </div>

        <!--Save the answer-->
        <g:submitButton name="create" class="btn btn-success" value="${message(code: 'default.answer.answer', default: 'Create')}" />
    </g:form>
</div>