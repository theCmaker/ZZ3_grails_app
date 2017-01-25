package fr.isima.zzoverflow

import grails.plugin.springsecurity.*
import grails.plugin.springsecurity.annotation.*

class FeatureController {

    static scaffold = Feature

    @Secured(['ROLE_ADMIN'])
    def index() {

        println "${Feature.list().size}"

        respond Feature.list(params)
    }

}
