package fr.isima

class User {

    String mailAddress
    String userName
    String password

    String firstName
    String lastName
    Date birthDate

    static constraints = {
        mailAddress blank: false, email:true
        userName blank: false, size: 5..15, unique: true
        password blank: false, password: true

        firstName blank: false
        lastName blank: false
        birthDate blank: false, max: new Date()
    }
}
