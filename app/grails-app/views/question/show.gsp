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

        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
        
        <div class="panel panel-primary" role="main">

            <!--Display the question title and content-->
            <div class="panel-heading row">

                <div id="question-title">
                    <f:display bean="question" property="title" />

                    <g:if test="${this.question.answers.any{ it -> it.accepted } }">
                        <i class="glyphicon glyphicon-ok"></i>
                    </g:if>

                </div>
            </div>

            <div class="panel-body">
                <f:display bean="question" property="content" />
            </div>

            <div class="panel-footer row">
                    <div class="col-xs-4">
                        <i class="glyphicon glyphicon-user"></i>
                        <g:link controller="user" action="show" id="${question.user.id}">
                            <f:display bean="question" property="user.username" />
                        </g:link>
                    </div>

                    <div class="col-xs-4 text-center">
                        <sec:ifLoggedIn>
                            <sec:access expression="hasRole('ROLE_ADMIN') || principal.id == ${this.question.user.id}">
                                <g:form resource="${this.question}" method="DELETE">
                                    <div class="btn-group">
                                    <g:link class="btn btn-primary" action="edit" resource="${this.question}">
                                        <i class="glyphicon glyphicon-pencil"></i>
                                        <g:message code="default.button.edit.label" default="Edit" />
                                    </g:link>

                                    <sec:ifAnyGranted roles="ROLE_ADMIN" >
                                        <button class="btn btn-danger" type="submit" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
                                            <i class="glyphicon glyphicon-trash"></i> <g:message code="default.button.delete.label" />
                                        </button>
                                    </sec:ifAnyGranted>
                                    </div>
                                </g:form>
                            </sec:access>
                        </sec:ifLoggedIn>
                    </div>

                    <div class="col-xs-4 text-right">
                        <i class="glyphicon glyphicon-calendar"></i>
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