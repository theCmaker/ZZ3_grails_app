package fr.isima

class Question {

    // TODO:validator for tag in list of all tags or none

    String title

    // Many to many keywords
    static hasMany = [ tags: Keyword[] ]

    // One to one Topic
    // static belongsTo = [ topic: Topic ]

    static constraints = {
    }
}
