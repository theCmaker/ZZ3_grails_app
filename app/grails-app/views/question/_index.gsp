<a href="#list-question" class="skip" tabindex="-1">
    <g:message code="default.link.skip.label" default="Skip to content&hellip;" />
</a>
<div class="nav" role="navigation">
    <ul>
        <li>
            <g:link class="create" action="create">
                <g:message code="default.question.new" /></g:link>
        </li>
    </ul>
</div>
<div id="list-question" class="content scaffold-list" role="main">
    
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    
    <!--<f:table collection="${questionList}" />-->

    <g:render template="/question/summary" model="[questions: questionList]" />

    </table>



    <div class="pagination">
        <g:paginate total="${questionCount ?: 0}" />
    </div>
</div>
