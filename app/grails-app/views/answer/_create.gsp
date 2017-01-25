<g:form action="save" class="form">

    <!--Hidden fields-->
    <div class="hidden">
        <f:field bean="answer" property="question" />
        <f:field bean="answer" property="user" />
        <f:field bean="answer" property="date" />
        <f:field bean="answer" property="upVoters" />
        <f:field bean="answer" property="downVoters" />
    </div>

    <!--Answer content-->
    <label for="answerContent">${message(code: 'default.answer.label.content', default: 'Content')}</label>
    <div class="form-group">
    <f:widget id="answerContent" class="form-control" bean="answer" property="content" />
    </div>

    <!--Save the answer-->
    <button name="create" class="btn btn-success">
        <i class="glyphicon glyphicon-send"></i> <g:message code="default.answer.answer" />
    </button>
</g:form>