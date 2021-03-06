package fr.isima.zzoverflow

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

import grails.plugin.springsecurity.*
import grails.plugin.springsecurity.annotation.*

@Transactional(readOnly = true)
class QuestionController {
    static responseFormats = [
        'json',
        'html'
    ]

    def springSecurityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        withFormat {
            'html' { respond Question.list(params), model:[questionCount: Question.count()], view: '_index' }
            'json' { render Question.list(params) }
        }
    }


    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def list() {
        respond Question.list(), model:[questions: Question.list()], view: '_summary'
    }

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def show(Question question) {
        def auth = springSecurityService.principal
          String username = auth.username
          def authorities = auth.authorities // a Collection of GrantedAuthority

        withFormat {
            'html' { respond question, view: 'show' }
            'json' { render question }
        }
        //respond question
    }
    
    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    def create() {

        if(Feature.findByFeature(Features.QUESTION_CREATE).enabled) {
            def question = new Question()

            bindData(question, params, [include: ['title', 'content']])

            // We get the user now
            question.user = User.get(springSecurityService.currentUser.id)

            withFormat {
                'html' { respond question, view: 'create' }
                'json' { render question }
            }

        } else {

            render status: SERVICE_UNAVAILABLE

        }

    }

    @Transactional
    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    def save(Question question) {

        if(Feature.findByFeature(Features.QUESTION_CREATE).enabled) {

            if (question == null) {
                transactionStatus.setRollbackOnly()
                render status: NOT_FOUND
                return
            }

            if (question.user.questions != null && question.user.questions.size() == 4) {
                def badge = new Badge(name: "curious", user: question.user).save()
            }

            // Adding the date on save
            question.date = new Date()

            if (!question.validate()) {
                transactionStatus.setRollbackOnly()
                respond question.errors, view:'create'
                return
            }

            question.save flush:true

            request.withFormat {
                form multipartForm {
                    flash.message = message(code: 'default.created.message', args: [message(code: 'question.label', default: 'Question'), question.id])
                    redirect question
                }
                '*' { respond question, [status: CREATED] }
            }
        } else {

            render status: SERVICE_UNAVAILABLE

        }
    }

    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    def edit(Question question) {
        withFormat {
            html { respond question }
            json { respond question }
        }
    }

    @Transactional
    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    def update(Question question) {
        if (question == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (question.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond question.errors, view:'edit'
            return
        }

        question.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'question.label', default: 'Question'), question.id])
                redirect question
            }
            '*'{ respond question, [status: OK] }
        }
    }

    @Transactional
    @Secured(['ROLE_ADMIN','ROLE_MODO'])
    def delete(Question question) {

        if (question == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        question.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'question.label', default: 'Question'), question.id])
                redirect uri:"/", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    @Transactional
    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    def vote(Question question) {

        if(null == question) {
            render status: NOT_FOUND
            return
        }

        if(Feature.findByFeature(Features.QUESTION_VOTE).enabled) {

            respond question, view: '_vote'

        } else {

            respond question, view: '_vote_disabled'

        }

    }


    @Transactional
    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    def upvote(Question question) {

        if(Feature.findByFeature(Features.QUESTION_VOTE).enabled) {

            if (question == null) {
                render status: NOT_FOUND
                return
            }

            // get the current user to manupulate him from the lists
            long currentUserId = springSecurityService.currentUser.id

            // Remove the user from the downVoters list to add it here
            question.downVoters -= currentUserId

            // Add the user in the upVoters list
            question.upVoters += currentUserId

            question.save flush:true

            // Redirect to show to update view and maybe ordering
            redirect uri:"/", method:"GET"
        } else {

            render status: SERVICE_UNAVAILABLE

        }
    }

    @Transactional
    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    def downvote(Question question) {

        if(Feature.findByFeature(Features.QUESTION_VOTE)) {

            if (question == null) {
                render status: NOT_FOUND
                return
            }

            // get the current user to manupulate him from the lists
            long currentUserId = springSecurityService.currentUser.id

            // Remove the user from the downVoters list to add it here
            question.upVoters.remove(currentUserId)

            // Add the user in the upVoters list
            question.downVoters.add(currentUserId)

            question.save flush:true

            redirect uri:"/", method:"GET"
        } else {

            render status: SERVICE_UNAVAILABLE

        }
    }

}
