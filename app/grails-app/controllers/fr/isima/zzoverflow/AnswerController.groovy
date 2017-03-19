package fr.isima.zzoverflow

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

import grails.plugin.springsecurity.*
import grails.plugin.springsecurity.annotation.*

@Transactional(readOnly = true)
class AnswerController {
    static responseFormats = [
        'json',
        'html'
    ]

    def springSecurityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def show(Answer answer) {
        respond answer, view: '_show'
    }

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def list() {
        respond Answer.list()
    }

    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    def create() {

        if(Feature.findByFeature(Features.ANSWER_CREATE).enabled) {

            // Create a new answer
            def answer = new Answer()

            // Initialize the answer fields with only the content 
            bindData(answer, params, [include: 'content'])

            // Get the question from the request param
            answer.question = Question.get(params.id)

            // Get the user from the current session
            answer.user = User.get(springSecurityService.currentUser.id)

            // Send to the _create view
            withFormat {
                "html" { respond answer, view: '_create' }
                "json" { respond answer }
            }
        } else {

            render status: SERVICE_UNAVAILABLE

        }

    }

    @Transactional
    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    def save() {

        if(Feature.findByFeature(Features.ANSWER_CREATE).enabled) {

            Answer answer = new Answer()

            bindData(answer, params, [include: 'content'])
            answer.question = Question.get(params.id)
            answer.user = User.get(springSecurityService.currentUser.id)
            
            if (answer == null) {
                transactionStatus.setRollbackOnly()
                render status: NOT_FOUND
                return
            }

            if (answer.user.answers != null && answer.user.answers.size() == 4) {
                def badge = new Badge(name: "talkative", user: answer.user).save()
            }

            // Setting the date when we save
            answer.date = new Date()

            if (!answer.validate()) {
                transactionStatus.setRollbackOnly()
                withFormat {
                    'html' { respond answer.errors, view:'_create' }                    
                    'json' { respond answer.errors }
                }
                return
            }

            // Save the new answer
            answer.save(flush:true, failOnError: true)
            
            // Redirect to the question show view
            // withFormat {
            //     'html' : { redirect action:'show', controller: 'question', method: 'GET', params: [id: answer.question.id] }
            // }
        } else {

            render status: SERVICE_UNAVAILABLE

        }
    }

    // Enables the user that created it to edit the content ONLY
    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    def edit(Answer answer) {

        withFormat {
            "html" { respond answer , view: "_edit" }
            "json" { respond answer }
        }
        
        
    }

    // Called to update an existing answer
    @Transactional
    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    def update(Answer answer) {
        if (answer == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (!answer.validate()) {
            transactionStatus.setRollbackOnly()
            respond answer.errors, view:'_edit'
            return
        }

        answer.save flush:true
        redirect action:'show', controller:'question', method: 'GET', params: [id: answer.question.id]
    }

    @Transactional
    @Secured(['ROLE_ADMIN','ROLE_MODO'])
    def delete(Answer answer) {

        if (answer == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        answer.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'answer.label', default: 'Answer'), answer.id])
                redirect action:'show', controller:'question', method:"GET", params: [id: answer.question.id]
            }
            '*'{ render status: NO_CONTENT }
        }
    }

        // Accept the answer in the question
    @Transactional
    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    def accept(Answer answer) {

        if (answer == null) {
            render status: NOT_FOUND
            return
        }

        // Check if there is already an accepted answer
        def alreadyAccepted = answer?.question?.answers?.find{ it.accepted == true }

        if(null != alreadyAccepted) {
            // There is one, we revoke it and accept the new one
            alreadyAccepted.accepted = false
            alreadyAccepted.save flush:true
        }

        answer.accepted = true
        answer.save flush:true
        

        redirect action:'show', controller:'question', method: 'GET', params: [id: answer.question.id]
    }

    @Transactional
    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    def revoke(Answer answer) {

        if (answer == null) {
            render status: NOT_FOUND
            return
        }

        answer.accepted = false
        answer.save flush:true

        redirect action:'show', controller:'question', method: 'GET', params: [id: answer.question.id]
    }

    @Transactional
    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    def vote(Answer answer) {

        if(null == answer) {
            render status: NOT_FOUND;
            return
        }

        if(Feature.findByFeature(Features.ANSWER_VOTE).enabled) {

            respond answer, view: '_vote'

        } else {

            respond answer, view: '_vote_disabled'

        }
    }

    @Transactional
    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    def upvote(Answer answer) {

        if(Feature.findByFeature(Features.ANSWER_VOTE).enabled) {

            if (answer == null) {
                render status: NOT_FOUND
                return
            }

            // get the current user to manupulate him from the lists
            long currentUserId = springSecurityService.currentUser.id

            // Remove the user from the downVoters list to add it here
            answer.downVoters -= currentUserId

            // Add the user in the upVoters list
            answer.upVoters += currentUserId

            answer.save flush:true

            // Redirect to show to update view and maybe ordering
            redirect action:'show', controller:'question', method: 'GET', params: [id: answer.question.id]
        } else {

            render status: SERVICE_UNAVAILABLE

        }
    }

    @Transactional
    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    def downvote(Answer answer) {

        if(Feature.findByFeature(Features.ANSWER_VOTE).enabled) {

            if (answer == null) {
                render status: NOT_FOUND
                return
            }

            // get the current user to manupulate him from the lists
            long currentUserId = springSecurityService.currentUser.id

            // Remove the user from the downVoters list to add it here
            answer.upVoters.remove(currentUserId)

            // Add the user in the upVoters list
            answer.downVoters.add(currentUserId)

            answer.save flush:true

            redirect action:'show', controller:'question', method: 'GET', params: [id: answer.question.id]
        } else {

            render status: SERVICE_UNAVAILABLE
            
        }
    }

}
