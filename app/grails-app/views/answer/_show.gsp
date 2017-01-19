<div class="panel ${answer?.accepted ? 'panel-success':'panel-info'} panel-info" role="main">

    <div class="panel-body">
        <f:display bean="answer" property="content" />
    </div>

    <div class="panel-footer row">

        <div class="col-xs-4 text-left">
            <f:display bean="answer" property="user.username" />
        </div>

        <div class="col-xs-4 btn-group">
        <sec:ifLoggedIn>
            <g:form class="btn-group" resource="${this.answer}" method="DELETE">
                <g:link class="btn btn-primary" action="edit" resource="${this.answer}">
                    <g:message code="default.button.edit.label" default="Edit" />
                </g:link>

                <g:if test="${answer.accepted}">
                    <g:link class="btn btn-danger" action="revoke" ressource="${this.answer}">
                        <g:message code="default.answer.revoke" default="Bad" />
                    </g:link>
                </g:if>
                <g:else>
                    <g:link class="btn btn-success" action="accept" ressource="${this.answer}" id="${this.answer.id}">
                        <g:message code="default.answer.accept" default="Good" />
                    </g:link>
                </g:else>

                <!--<g:link class="btn btn-danger" action="delete" ressource="${this.answer}">
                    <g:message code="default.button.delete.label" default="Delete" />
                </g:link>-->
                <sec:ifAnyGranted roles="ROLE_ADMIN">
                    <input class="btn btn-danger" type="submit" value="${message(code: '', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </sec:ifAnyGranted>
            </g:form>
        </sec:ifLoggedIn>
        </div>

        <div class="col-xs-4 text-right">
            <g:formatDate format="yyyy-MM-dd HH:mm" date="${answer.date}" />
        </div>
    </div>

</div>