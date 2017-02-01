package fr.isima.zzoverflow

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

import grails.plugin.springsecurity.*
import grails.plugin.springsecurity.annotation.*

class FeatureController {

	def springSecurityService

    static scaffold = Feature

    @Secured(['ROLE_ADMIN'])
    def index() {

        respond Feature.list(params)
    }

    @Transactional
    @Secured(['ROLE_ADMIN'])
    def enable(Feature feature) {

        feature.enabled = true

        feature.save flush: true

        redirect action:'index', controller:'feature', method: 'GET'
    }

    @Transactional
    @Secured(['ROLE_ADMIN'])
    def disable(Feature feature) {

        feature.enabled = false

        feature.save flush: true

        redirect action:'index', controller:'feature', method: 'GET'
    }


}
