<div class="text-center vote-section">

    <sec:ifLoggedIn>
        <g:link class="btn btn-block btn-primary" action="upvote" controller="answer" ressource="${this.answer}" id="${this.answer.id}">
            <i class="glyphicon glyphicon-thumbs-up"></i>
        </g:link>
    </sec:ifLoggedIn>

    <div class="value">${this.answer.getScore()}</div>

    <sec:ifLoggedIn>
        <g:link class="btn btn-block btn-primary" action="downvote" controller="answer" ressource="${this.answer}" id="${this.answer.id}">
            <i class="glyphicon glyphicon-thumbs-down"></i>
        </g:link>
    </sec:ifLoggedIn>

</div>