<div class="summary answer">

    <table class="table table-striped">
        <thead>
            <tr>
                <th>${message(code: 'default.answer.question.refer', default: 'Question')}</th>
                <th>${message(code: 'default.answer.date', default: 'Date')}</th>
            </tr>
        </thead>
        <tbody>
        <g:each in="${answers}" var="answer">
            <tr>
                <td>
                    <g:link controller="question" action="show" params="[id: answer.question.id]" >
                        <f:display bean="${answer.question}" property="title" />
                    </g:link>
                </td>

                <td>
                    <g:formatDate formatName="default.date.format" date="${answer.date}" />
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>
</div>