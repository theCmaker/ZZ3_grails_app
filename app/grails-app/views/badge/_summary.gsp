<div class="summary">
    <g:each in="${badges}" var="badge">
        <div class="col-xs-4 col-md-2">
            <g:link controller="badge" action="show" params="[id: badge.id]" class="thumbnail">
                <asset:image src="badge/badge_${badge.name}.png" class="img-circle" title="${badge.name}"/>
            </g:link>
        </div>
    </g:each>
</div>