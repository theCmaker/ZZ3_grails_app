package fr.isima.zzoverflow

class Question {

    /// The Question main title
    String title

    /// The Question content
    String content

    /// The Question creation Date
    Date date

    /// We store the answers in a sorted set to display them properly
    SortedSet answers

    // Many to many Tag and Answer
    static hasMany = [ tags: Keyword, answers: Answer ]

    static belongsTo = [ user: User ]

    /// The title or the content cannot be blank
    static constraints = {
        title blank: false
        content blank: false
    }

}
