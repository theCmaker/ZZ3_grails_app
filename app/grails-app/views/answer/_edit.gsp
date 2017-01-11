<div id="edit-answer" class="content scaffold-edit answer" role="main">

    <g:form resource="${this.answer}" method="PUT">
        <g:hiddenField name="version" value="${this.answer?.version}" />

        <!--Answer content to change-->
        <f:field bean="answer" property="content" />

        <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
    </g:form>
</div>