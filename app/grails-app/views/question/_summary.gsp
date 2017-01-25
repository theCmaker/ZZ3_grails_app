<div class="summary question">

    <table class="table table-striped">
        <thead>
            <tr>
                <th>${message(code: 'default.question.title', default: 'Title')}</th>
                <th>${message(code: 'default.question.user', default: 'User')}</th>
                <th>${message(code: 'default.question.date', default: 'Date')}</th>
                <th>${message(code: 'default.question.vote', default: 'Votes')}</th>
            </tr>
        </thead>
        <tbody>
        <g:each in="${questions}" var="question">
            <tr>
                <td>
                    <g:link controller="question" action="show" params="[id: question.id]" >
                        <f:display bean="${question}" property="title" />
                        <g:if test="${question.answers.any{ it -> it.accepted } }">
                            <i class="glyphicon glyphicon-ok"></i>
                        </g:if>
                    </g:link>
                </td>

                <td>
                    <f:display bean="${question}" property="user.username" />
                </td>

                <td>
                    <g:formatDate formatName="default.date.format" date="${question.date}" />
                </td>

                <td>
                    <div class="text-center btn-group">
                        <sec:ifLoggedIn>
                            <g:link class="btn btn-primary" action="upvote" controller="question" ressource="${question}" id="${question.id}">
                                <i class="glyphicon glyphicon-thumbs-up"></i>
                            </g:link>
                        </sec:ifLoggedIn>

                        <div class="btn disabled value">${question.getScore()}</div>

                        <sec:ifLoggedIn>
                            <g:link class="btn btn-primary" action="downvote" controller="question" ressource="${question}" id="${question.id}">
                                <i class="glyphicon glyphicon-thumbs-down"></i>
                            </g:link>
                        </sec:ifLoggedIn>
                    </div>
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>
</div>