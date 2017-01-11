        <div id="create-answer" class="content scaffold-create answer" role="main">
            <g:form action="save">

                <!--Answer content-->
                <f:widget class="content" bean="answer" property="content" />

                <!--Hidden fields-->
                <div style="display:none;">
                <f:field bean="answer" property="question" />
                <f:field bean="answer" property="user" />
        </div>

            <!--Save the answer-->
            <g:submitButton name="create" class="save" value="${message(code: 'default.answer.answer', default: 'Create')}" />
            </g:form>
        </div>
