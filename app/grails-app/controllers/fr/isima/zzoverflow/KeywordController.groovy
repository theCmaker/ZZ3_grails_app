package fr.isima.zzoverflow

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class KeywordController {
    static responseFormats = [
        'json',
        'xml'
    ]

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Keyword.list(params), model:[keywordCount: Keyword.count()]
    }

    def show(Keyword keyword) {
        respond keyword
    }

    def create() {
        respond new Keyword(params)
    }

    @Transactional
    def save(Keyword keyword) {
        if (keyword == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (keyword.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond keyword.errors, view:'create'
            return
        }

        keyword.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'keyword.label', default: 'Keyword'), keyword.id])
                redirect keyword
            }
            '*' { respond keyword, [status: CREATED] }
        }
    }

    def edit(Keyword keyword) {
        respond keyword
    }

    @Transactional
    def update(Keyword keyword) {
        if (keyword == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (keyword.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond keyword.errors, view:'edit'
            return
        }

        keyword.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'keyword.label', default: 'Keyword'), keyword.id])
                redirect keyword
            }
            '*'{ respond keyword, [status: OK] }
        }
    }

    @Transactional
    def delete(Keyword keyword) {

        if (keyword == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        keyword.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'keyword.label', default: 'Keyword'), keyword.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'keyword.label', default: 'Keyword'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
