package fr.isima.zzoverflow

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

import grails.plugin.springsecurity.*
import grails.plugin.springsecurity.annotation.*

class FeatureController {
    static responseFormats = [
        'json',
        'html'
    ]

	def springSecurityService

    static scaffold = Feature

    @Secured(['ROLE_ADMIN'])
    def index() {
         withFormat {
            'html' { respond Feature.list(params), view: 'index' }
            'json' { respond Feature.list(params) }
        }
    }

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def list() {
        respond Feature.list()
    }

    @Transactional
    @Secured(['ROLE_ADMIN'])
    def enable(Feature feature) {

        feature.enabled = true

        feature.save flush: true

        redirect action:'index', controller:'feature', method: 'GET'
    }

    @Transactional
    // @Secured(['ROLE_ADMIN'])
    def disable(Feature feature) {

        feature.enabled = false

        feature.save flush: true

        redirect action:'index', controller:'feature', method: 'GET'
    }


}
