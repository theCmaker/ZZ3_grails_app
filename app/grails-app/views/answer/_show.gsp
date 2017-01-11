<div id="show-answer" class="content scaffold-show answer" role="main">
    <div class="content">
        <f:display bean="answer" property="content" />
    </div>
    <div class="accepted">
        <f:display bean="answer" property="accepted" />
    </div>
    <div class="username">
        <f:display bean="answer" property="user.username" />
    </div>

    <sec:ifLoggedIn>
        <g:form resource="${this.answer}" method="DELETE">
            
            <g:link class="edit" action="edit" resource="${this.answer}"><g:message code="default.button.edit.label" default="Edit" /></g:link>

            <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />

        </g:form>
    </sec:ifLoggedIn>
</div>