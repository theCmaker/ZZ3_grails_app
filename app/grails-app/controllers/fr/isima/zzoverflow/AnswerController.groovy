package fr.isima.zzoverflow

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

import grails.plugin.springsecurity.*
import grails.plugin.springsecurity.annotation.*

@Transactional(readOnly = true)
class AnswerController {

    def springSecurityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    // This method is used to view the answer and
    // - edit it for the user that created it
    // - delete only for admins
    def show(Answer answer) {
        respond answer, view: '_show'
    }

    @Secured(value=['ROLE_USER'])
    def create() {
        // Create a new answer
        def answer = new Answer()

        // Initialize the answer fields with only the content 
        bindData(answer, params, [include: 'content'])

        // Get the question from the request param
        answer.question = Question.get(params.id)

        // Get the user from the current session
        answer.user = User.get(springSecurityService.currentUser.id)

        println "${authenticatedUser.getAuthorities()}"

        // Send to the _create view
        respond answer, view: '_create'
    }

    @Transactional
    def save(Answer answer) {

        if (answer == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

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
    def edit(Answer answer) {
        respond answer, view: '_edit'
    }

    // Called to update an existing answer
    @Transactional
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
    @Secured(value=['ROLE_ADMIN'])
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
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

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
