<div class="panel ${feature.enabled ? 'panel-success' : 'panel-danger' }">
    
    <div class="panel-heading">
        <f:display bean="feature" property="feature" />
    </div>

    <div class="panel-body">

    

        <div class="row">
            <div class="col-xs-8">
                <f:display bean="feature" property="description" />
            </div>
            <div class="col-xs-4">
                <div class="btn-group">
                    <g:link class="btn btn-success" action="enable" controller="feature" ressource="${this.feature}" id="${this.feature.id}">
                        <i class="glyphicon glyphicon-ok"></i>
                        <g:message code="default.feature.enable" default="Enable" />
                    </g:link>

                    <g:link class="btn btn-danger" action="disable" controller="feature" ressource="${this.feature}" id="${this.feature.id}">
                        <i class="glyphicon glyphicon-ban-circle"></i>
                        <g:message code="default.feature.disable" default="Disable" />
                    </g:link>

                </div>

                
            </div>

        </div>

    </div>

</div>
