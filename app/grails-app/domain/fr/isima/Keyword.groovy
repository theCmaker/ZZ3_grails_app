package fr.isima

class Keyword {

    String value

    // Many to many with a question
    static hasMany = [ questions : Question ]
    static belongsTo = Question

    static constraints = {
    }
}
