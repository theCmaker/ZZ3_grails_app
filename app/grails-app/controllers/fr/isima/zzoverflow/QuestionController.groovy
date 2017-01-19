package fr.isima.zzoverflow

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

import grails.plugin.springsecurity.*
import grails.plugin.springsecurity.annotation.*

@Transactional(readOnly = true)
class QuestionController {

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
        def question = new Question()

        bindData(question, params, [include: ['title', 'content']])

        // We get the user now
        question.user = User.get(springSecurityService.currentUser.id)

        respond question, view: 'create'
    }

    @Transactional
    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    def save(Question question) {
        if (question == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
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
    @Secured(['ROLE_ADMIN'])
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
