package zzoverflow

import fr.isima.zzoverflow.*;

class BootStrap {

    def init = { servletContext ->
        def adminGroup = new Group(name: 'Administrators').save(flush: true)
        def usersGroup = new Group(name: 'Users').save(flush: true)
        // def modosGroup = new Group(name: 'Moderators').save(flush: true)

        def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
        def userRole = new Role(authority: 'ROLE_USER').save(flush: true)
        // def modoRole = new Role(authority: 'ROLE_MODO').save(flush: true)

        def adminUser = new User(username: 'admin', enabled: true, password: 'admin')
        adminUser.save(flush: true)
        def normalUser = new User(username: 'toto', enabled: true, password: 'toto')
        normalUser.save(flush: true)
        def benjiUser = new User(username: 'benji', enabled: true, password: 'benji')
        benjiUser.save(flush: true)

        UserGroup.create adminUser, adminGroup
        UserGroup.create normalUser, usersGroup
        
        GroupRole.create adminGroup, adminRole
        GroupRole.create usersGroup, userRole
        // GroupRole.create modosGroup, Role.findByAuthority('ROLE_MODO')


        // assert User.count() == 2
        // assert Role.count() == 3
        // assert Group.count() == 3
        // assert GroupRole.count() == 3
        // assert UserGroup.count() == 2

        def question = new Question(title:'This is a question', content:'To be or not to be?', user: normalUser, date: new Date())
        question.save(flush: true)


    }
    def destroy = {
    }
}
