package fr.isima

class Answer {

    /// The answer is accepted
    boolean accepted = false

    static belongsTo = [ question: Question, user: User ]
    
    static constraints = {
    }
}
