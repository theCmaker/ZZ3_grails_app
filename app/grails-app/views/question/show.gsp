<!DOCTYPE html>
<html>

    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'question.label', default: 'Question')}" />
        <title>
            <g:message code="default.show.label" args="[entityName]" />
        </title>

    </head>

    <body>
        <a href="#show-question" class="skip" tabindex="-1">
            <g:message code="default.link.skip.label" default="Skip to content&hellip;" />
        </a>

        <div class="panel panel-primary" role="main">

            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>

            <!--Display the question title and content-->
            <div class="panel-heading row">

                <div class="col-xs-9">
                    <f:display bean="question" property="title" />
                </div>

                <div class="col-xs-3 align-right">
                    <sec:ifLoggedIn>
                        <sec:access expression="hasRole('ROLE_ADMIN') || principal.id == ${this.question.user.id}">
                            <g:form class="btn-group" resource="${this.question}" method="DELETE">
                                <g:link class="btn btn-primary" action="edit" resource="${this.question}">
                                    <g:message code="default.button.edit.label" default="Edit" />
                                </g:link>

                                <sec:ifAnyGranted roles="ROLE_ADMIN" >
                                    <input class="btn btn-danger" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                                </sec:ifAnyGranted>
                            </g:form>
                        </sec:access>
                    </sec:ifLoggedIn>
                </div>
            </div>

            <div class="panel-body">
                <f:display bean="question" property="content" />
            </div>

            <div class="panel-footer row">
                    <div class="col-xs-6">
                        <f:display bean="question" property="user.username" />
                    </div>

                    <div class="col-xs-6 text-right">
                        <g:formatDate formatName="default.date.format" date="${question.date}" />
                    </div>
            </div>
        </div>


            <!--Answers-->
            <div class="answers question">
                <g:each in="${question.answers}" var="ans">
                    <div class="answer">
                        <g:render template="/answer/show" model="[answer: ans]" />
                    </div>
                </g:each>
            </div>

            <!--This is an empty question to answer-->
            <sec:ifLoggedIn>
                <div class="create answer question">
                    <div class="answer">
                        <g:include controller="answer" action="create" params="[id: question.id]" />
                    </div>
                </div>
            </sec:ifLoggedIn>

            </body>

            </html>