package fr.isima.zzoverflow

class Question {

    // TODO:validator for tag in list of all tags or none

    String title

    String content

    // Many to many keywords
    static hasMany = [ tags: Keyword, answers: Answer ]

    static belongsTo = [ user: User ]

    static constraints = {
        title blank: false
        content blank: false
    }
}
