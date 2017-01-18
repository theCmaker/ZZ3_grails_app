package zzoverflow

import fr.isima.zzoverflow.*;

class BootStrap {

    def init = { servletContext ->
        def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
        def userRole = new Role(authority: 'ROLE_USER').save(flush: true)

        def adminUser = new User(username: 'admin', enabled: true, password: 'admin')
        adminUser.save(flush: true)

        UserRole.create adminUser, adminRole

        def normalUser = new User(username: 'toto', enabled: true, password: 'toto')
        normalUser.save(flush: true)

        UserRole.create normalUser, userRole

        assert User.count() == 2
        assert Role.count() == 2

        def question = new Question(title:'This is a question', content:'To be or not to be?', user: normalUser)
        question.save(flush: true)

    }
    def destroy = {
    }
}
