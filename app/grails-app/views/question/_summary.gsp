<div class="summary question">

    <table class="table table-striped">
        <thead>
            <tr>
                <th>${message(code: 'default.question.title', default: 'Title')}</th>
                <th>${message(code: 'default.question.user', default: 'User')}</th>
                <th>${message(code: 'default.question.date', default: 'Date')}</th>
                <th>${message(code: 'default.question.tags', default: 'Tags')}</th>
            </tr>
        </thead>
        <tbody>
        <g:each in="${questions}" var="question">
            <tr>
                <td>
                    <g:link controller="question" action="show" params="[id: question.id]" >
                        <f:display bean="${question}" property="title" />
                    </g:link>
                </td>

                <td>
                    <f:display bean="${question}" property="user.username" />
                </td>

                <td>
                    <g:formatDate format="yyyy-MM-dd HH:mm" date="${question.date}" />
                </td>

                <td>
                    <f:display bean="${question}" property="tags" />
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>
</div>