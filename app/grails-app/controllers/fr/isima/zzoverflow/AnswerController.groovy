package fr.isima.zzoverflow

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

import grails.plugin.springsecurity.*

@Transactional(readOnly = true)
class AnswerController {

    def springSecurityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    /*
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Answer.list(params), model:[answerCount: Answer.count()]
    }
    */

    def show(Answer answer) {
        respond answer
    }

    def create() {

        println "Creating a new answer"

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
    def save(Answer answer) {

        println "Saving the answer"

        if (answer == null) {
            println "Answer is null"
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        // answer.accepted = answer.accepted

        answer.validate()

        println "User id  ${}"

        println "${answer.user} ${answer.content} ${answer.question.title} ${answer.accepted}"

        if (!answer.validate()) {
            println "Error with answer"

            transactionStatus.setRollbackOnly()
            respond answer.errors, view:'_create'
            return
        }

        answer.save(flush:true, failOnError: true)

        // request.withFormat {
        //     form multipartForm {
        //         flash.message = message(code: 'default.created.message', args: [message(code: 'answer.label', default: 'Answer'), answer.id])
        //         redirect answer
        //     }
        //     '*' { respond answer, [status: CREATED] }
        // }
    }

    def edit(Answer answer) {
        respond answer, view: '_edit'
    }

    @Transactional
    def update(Answer answer) {
        if (answer == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (answer.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond answer.errors, view:'edit'
            return
        }

        answer.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'answer.label', default: 'Answer'), answer.id])
                redirect answer
            }
            '*'{ respond answer, [status: OK] }
        }
    }

    @Transactional
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
