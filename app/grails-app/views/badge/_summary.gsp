<div class="summary">
    <g:each in="${badges}" var="badge">
        <div class="col-xs-4 col-md-2">
        	<div class="thumbnail">
            	<asset:image src="badge/badge_${badge.name}.png" class="img-circle" title="${badge.name}"/>
            </div>
        </div>
    </g:each>
</div>