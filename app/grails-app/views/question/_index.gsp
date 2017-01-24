<a href="#list-question" class="skip" tabindex="-1">
    <g:message code="default.link.skip.label" default="Skip to content&hellip;" />
</a>
<div class="nav" role="navigation">
    <g:link class="create" action="create">
        <g:message code="default.question.new" />
    </g:link>
</div>
<div id="list-question" class="content scaffold-list" role="main">
    
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <g:render template="/question/summary" model="[questions: questionList.sort()]" />


    <div class="pagination">
        <g:paginate total="${questionCount ?: 0}" />
    </div>
</div>
