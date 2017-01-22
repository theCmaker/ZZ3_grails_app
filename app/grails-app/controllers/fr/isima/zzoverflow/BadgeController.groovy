package fr.isima.zzoverflow

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

import grails.plugin.springsecurity.*
import grails.plugin.springsecurity.annotation.*

@Transactional(readOnly = true)
class BadgeController {

    def springSecurityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def show(Badge badge) {
        respond badge, view: '_show'
    }

    @Secured(['ROLE_MODO', 'ROLE_ADMIN'])
    def create() {
        // Create a new answer
        def badge = new Badge()

        // Initialize the answer fields with only the content 
        bindData(badge, params, [include: 'user.id'])

        // Send to the _create view
        respond badge, view: 'create'
    }

    @Transactional
    @Secured(['ROLE_MODO', 'ROLE_ADMIN'])
    def save(Badge badge) {

        if (badge == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (!badge.validate()) {
            transactionStatus.setRollbackOnly()
            respond badge.errors, view:'create'
            return
        }

        // Save the new badge
        badge.save(flush:true, failOnError: true)
        
        // Redirect to the question show view
        //redirect action:'show', controller: 'question', method: 'GET', params: [id: answer.question.id]
    }

    @Secured(['ROLE_MODO','ROLE_ADMIN'])
    def edit(Badge badge) {
        respond badge, view: 'edit'
    }

    // Called to update an existing badge
    @Transactional
    @Secured(['ROLE_MODO', 'ROLE_ADMIN'])
    def update(Badge badge) {
        if (badge == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (!badge.validate()) {
            transactionStatus.setRollbackOnly()
            respond badge.errors, view:'edit'
            return
        }

        badge.save flush:true
        //redirect action:'show', controller:'question', method: 'GET', params: [id: answer.question.id]
    }

    @Transactional
    @Secured(['ROLE_ADMIN','ROLE_MODO'])
    def delete(Badge badge) {

        if (badge == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        badge.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.badge', args: [message(code: 'badge.label', default: 'Badge'), badge.id])
                //redirect action:'show', controller:'question', method:"GET", params: [id: answer.question.id]
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'badge.label', default: 'Badge'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
