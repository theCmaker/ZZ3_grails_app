<div class="panel ${this.answer?.accepted ? 'panel-success':'panel-info'}" role="main">

    <div class="panel-heading row">
        <div class="col-xs-11">
            <f:display bean="answer" property="content" />
        </div>

            <div class="col-xs-1">

                <g:include controller="answer" action="vote" params="[id: answer.id]" />

            </div>
    </div>

    <div class="panel-footer row">

        <div class="col-xs-4 text-left">
            <i class="glyphicon glyphicon-user"></i>
            <g:link controller="user" action="show" id="${answer.user.id}">
                <f:display bean="answer" property="user.username" />
            </g:link>
        </div>

        <div class="col-xs-4 text-center">
            <sec:ifLoggedIn>
                <g:form resource="${this.answer}" method="DELETE">
                    <div class="btn-group">
                    <!--Acces only of creator or admin-->
                    <sec:access expression="hasRole('ROLE_ADMIN') || principal.id == ${this.answer.user.id}">
                        <g:link class="btn btn-primary" action="edit" resource="${this.answer}">
                            <i class="glyphicon glyphicon-pencil"></i>
                            <g:message code="default.button.edit.label" default="Edit" />
                        </g:link>
                    </sec:access>

                    <!--Only an admin or the question author can accept / reject-->
                    <sec:access expression="hasRole('ROLE_ADMIN') || principal.id == ${this.answer.question.user.id}">
                    <!--Accept / reject button-->
                        <g:if test="${answer.accepted}">
                            <g:link class="btn btn-danger" action="revoke" controller="answer" ressource="${this.answer}" id="${this.answer.id}">
                                <i class="glyphicon glyphicon-remove"></i>
                                <g:message code="default.answer.revoke" default="Bad" />
                            </g:link>
                        </g:if>
                        <g:else>
                            <g:link class="btn btn-success" action="accept" controller="answer" ressource="${this.answer}" id="${this.answer.id}">
                                <i class="glyphicon glyphicon-ok"></i>
                                <g:message code="default.answer.accept" default="Good" />
                            </g:link>
                        </g:else>
                    </sec:access>

                    <!--Acces only of creator or admin-->
                    <sec:access expression="hasRole('ROLE_ADMIN') || principal.id == ${this.answer.user.id}">
                        <button class="btn btn-danger" type="submit" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
                            <i class="glyphicon glyphicon-trash"></i> <g:message code="default.button.delete.label" /> 
                        </button>
                    </sec:access>
                    </div>
                </g:form>
            </sec:ifLoggedIn>
        </div>

        <div class="col-xs-4 text-right">
            <i class="glyphicon glyphicon-calendar"></i>
            <g:formatDate formatName="default.date.format" date="${answer.date}" />
        </div>
    </div>

</div>