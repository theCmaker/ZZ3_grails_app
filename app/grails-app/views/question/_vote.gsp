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