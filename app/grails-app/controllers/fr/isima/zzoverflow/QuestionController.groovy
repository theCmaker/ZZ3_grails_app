package fr.isima.zzoverflow

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

import grails.plugin.springsecurity.*
import grails.plugin.springsecurity.annotation.*

@Transactional(readOnly = true)
class QuestionController {
    static responseFormats = [
        'json',
        'xml'
    ]

    def springSecurityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Question.list(params), model:[questionCount: Question.count()], view: '_index'
    }

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def show(Question question) {
        def auth = springSecurityService.principal
          String username = auth.username
          def authorities = auth.authorities // a Collection of GrantedAuthority
        respond question
    }
    
    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    def create() {

        if(Feature.findByFeature(Features.QUESTION_CREATE).enabled) {
            def question = new Question()

            bindData(question, params, [include: ['title', 'content']])

            // We get the user now
            question.user = User.get(springSecurityService.currentUser.id)

            respond question, view: 'create'

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
                notFound()
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
        respond question
    }

    @Transactional
    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    def update(Question question) {
        if (question == null) {
            transactionStatus.setRollbackOnly()
            notFound()
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
            notFound()
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
            notFound()
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
                notFound()
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
                notFound()
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

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'question.label', default: 'Question'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
