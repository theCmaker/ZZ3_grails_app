package fr.isima.zzoverflow

class Answer {

    /// The answer is accepted
    boolean accepted = false

    String content

    static belongsTo = [ question: Question, user: User ]

    static constraints = {
        content blank: false
    }
}
