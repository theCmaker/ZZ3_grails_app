package fr.isima.zzoverflow

import grails.plugin.springsecurity.*
import grails.plugin.springsecurity.annotation.*

class FeatureController {

	def springSecurityService

    static scaffold = Feature

    @Secured(['ROLE_ADMIN'])
    def index() {

        respond Feature.list(params)
    }

}
