<div class="panel ${this.answer?.accepted ? 'panel-success':'panel-info'}" role="main">

    <div class="panel-heading">
        <f:display bean="answer" property="content" />
    </div>

    <div class="panel-footer row">

        <div class="col-xs-4 text-left">
            <f:display bean="answer" property="user.username" />
        </div>

        <div class="col-xs-4 btn-group">
            <sec:ifLoggedIn>
                <g:form class="btn-group" resource="${this.answer}" method="DELETE">
                    <!--Acces only of creator or admin-->
                    <sec:access expression="hasRole('ROLE_ADMIN') || principal.id == ${this.answer.user.id}">
                        <g:link class="btn btn-primary" action="edit" resource="${this.answer}">
                            <g:message code="default.button.edit.label" default="Edit" />
                        </g:link>
                    </sec:access>

                    <!--Only an admin or the question author can accept / reject-->
                    <sec:access expression="hasRole('ROLE_ADMIN') || principal.id == ${this.answer.question.user.id}">
                    <!--Accept / reject button-->
                        <g:if test="${answer.accepted}">
                            <g:link class="btn btn-danger" action="revoke" controller="answer" ressource="${this.answer}" id="${this.answer.id}">
                                <g:message code="default.answer.revoke" default="Bad" />
                            </g:link>
                        </g:if>
                        <g:else>
                            <g:link class="btn btn-success" action="accept" controller="answer" ressource="${this.answer}" id="${this.answer.id}">
                                <g:message code="default.answer.accept" default="Good" />
                            </g:link>
                        </g:else>
                    </sec:access>

                    <!--Acces only of creator or admin-->
                    <sec:access expression="hasRole('ROLE_ADMIN') || principal.id == ${this.answer.user.id}">
                        <input class="btn btn-danger" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                    </sec:access>
                </g:form>
            </sec:ifLoggedIn>
        </div>

        <div class="col-xs-4 text-right">
            <g:formatDate formatName="default.date.format" date="${answer.date}" />
        </div>
    </div>

</div>