package fr.isima.zzoverflow

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

import grails.plugin.springsecurity.*
import grails.plugin.springsecurity.annotation.*

@Transactional(readOnly = true)
class UserController {
    static responseFormats = [
        'json',
        'html'
    ]

    def springSecurityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(['ROLE_ADMIN','ROLE_MODO'])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        withFormat {
            'html' {respond User.list(params), model:[userCount: User.count()], view: "index"}
            'json' {respond User.list(params)}
        }
    }

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def list() {
        respond User.list()
    }

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def show(User user) {
        withFormat {
            'html' { respond user, view: "show"}
            'json' { repond user }
            
        }
    }

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def create() {
        if(Feature.findByFeature(Features.USER_CREATE).enabled) {

            withFormat {
                'html' { respond new User(params), view: "create" }
                'json' { respond new User(params) }
            }

        } else {
            
            render status: SERVICE_UNAVAILABLE

        }
    }

    @Transactional
    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def save(User user) {

        if(Feature.findByFeature(Features.USER_CREATE).enabled) {

            if (user == null) {
                transactionStatus.setRollbackOnly()
                render status: NOT_FOUND
                return
            } 

            if (user.hasErrors()) {
                transactionStatus.setRollbackOnly()
                respond user.errors, view:'create'
                return
            }

            user.save flush:true

            // Set default rights
            UserGroup.create user, Group.findByName('Users');

            request.withFormat {
                form multipartForm {
                    flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), user.id])
                    redirect(uri: "/login/auth")
                }
                '*' { respond user, [status: CREATED] }
            }
        } else {

            render status: SERVICE_UNAVAILABLE

        }
    }

    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    def edit(User user) {
        withFormat {
            'html' { respond user, view: "edit" }
            'json' { respond user }
        }
    }

    @Transactional
    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    def update(User user) {
        if (user == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (user.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond user.errors, view:'edit'
            return
        }

        user.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect user
            }
            '*'{ respond user, [status: OK] }
        }
    }

    @Transactional
    @Secured(['ROLE_ADMIN','ROLE_MODO'])
    def delete(User user) {

        if (user == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        user.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

}
