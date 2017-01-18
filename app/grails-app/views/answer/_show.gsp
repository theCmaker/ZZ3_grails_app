<div class="panel ${answer?.accepted ? 'panel-success':'panel-info'} panel-info" role="main">

    <div class="panel-body">
        <f:display bean="answer" property="content" />
    </div>

    <div class="panel-footer row">

        <div class="col-xs-4 text-left">
            <f:display bean="answer" property="user.username" />
        </div>

        <div class="col-xs-4 text-center btn-group">
            <sec:ifLoggedIn>
                <g:link class="edit btn btn-primary" action="edit" resource="${this.answer}">
                    <g:message code="default.button.edit.label" default="Edit" />
                </g:link>

                <!--If the answer is accepted, display revoke-->
                <g:if test="${answer.accepted}">
                    <button class="btn btn-danger">
                        ${message(code: 'default.answer.revoke', default: 'Bad')}
                    </button>
                </g:if>
                <g:else>
                    <button class="btn btn-success">
                        ${message(code: 'default.answer.accept', default: 'Good')}
                    </button>
                </g:else>



                <g:form resource="${this.answer}" method="DELETE">
                    <sec:ifAnyGranted roles="ROLE_ADMIN">
                        <input class="delete btn btn-danger" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                    </sec:ifAnyGranted>
                </g:form>
            </sec:ifLoggedIn>
        </div>

        <div class="col-xs-4 text-right">
            <g:formatDate format="yyyy-MM-dd HH:mm" date="${answer.date}" />
        </div>

        <div class="row">


        </div>
    </div>

</div>