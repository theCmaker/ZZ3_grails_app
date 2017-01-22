package fr.isima.zzoverflow

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

import grails.plugin.springsecurity.*
import grails.plugin.springsecurity.annotation.*

@Transactional(readOnly = true)
class AnswerController {

    def springSecurityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def show(Answer answer) {
        respond answer, view: '_show'
    }

    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    def create() {
        // Create a new answer
        def answer = new Answer()

        // Initialize the answer fields with only the content 
        bindData(answer, params, [include: 'content'])

        // Get the question from the request param
        answer.question = Question.get(params.id)

        // Get the user from the current session
        answer.user = User.get(springSecurityService.currentUser.id)

        // Send to the _create view
        respond answer, view: '_create'
    }

    @Transactional
    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    def save(Answer answer) {

        if (answer == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        // Setting the date when we save
        answer.date = new Date()

        if (!answer.validate()) {
            transactionStatus.setRollbackOnly()
            respond answer.errors, view:'_create'
            return
        }

        // Save the new answer
        answer.save(flush:true, failOnError: true)
        
        // Redirect to the question show view
        redirect action:'show', controller: 'question', method: 'GET', params: [id: answer.question.id]
    }

    // Enables the user that created it to edit the content ONLY
    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    def edit(Answer answer) {
        respond answer, view: '_edit'
    }

    // Called to update an existing answer
    @Transactional
    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    def update(Answer answer) {
        if (answer == null) {
            transactionStatus.setRollbackOnly()
            notFound()
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
            notFound()
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
            notFound()
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
            notFound()
            return
        }

        answer.accepted = false
        answer.save flush:true

        redirect action:'show', controller:'question', method: 'GET', params: [id: answer.question.id]
    }

    @Transactional
    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    def upvote(Answer answer) {

        if (answer == null) {
            notFound()
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
    }

    @Transactional
    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    def downvote(Answer answer) {

        if (answer == null) {
            notFound()
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
    }

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'answer.label', default: 'Answer'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
