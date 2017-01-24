<g:set var="entityName" value="${message(code: 'badge.label', default: 'Badge')}" />
<div id="show-badge" class="content scaffold-show" role="main">
    <f:display bean="badge" />
    <g:form resource="${this.badge}" method="DELETE">
        <fieldset class="buttons">
            <g:link class="edit" action="edit" resource="${this.badge}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
            <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
        </fieldset>
    </g:form>
</div>