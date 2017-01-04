package fr.isima.zzoverflow

class Keyword {

    String name

    // Many to many with a question
    static hasMany = [ questions : Question ]
    static belongsTo = [ Question ]

    static constraints = {
        name blank: false, unique: true
    }
}
