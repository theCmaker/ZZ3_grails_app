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
                <f:field bean="feature" property="enabled" />
            </div>

        </div>


    </div>

</div>
