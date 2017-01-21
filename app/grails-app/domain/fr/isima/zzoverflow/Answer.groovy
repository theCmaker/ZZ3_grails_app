package fr.isima.zzoverflow

class Answer implements Comparable {

    /// The answer is accepted or not
    boolean accepted = false

    /// The content of the answer
    String content

    /// The date the answer was created
    Date date

    /// An answer is related to a Question and is given by a User
    static belongsTo = [ question: Question, user: User ]

    /// The question content cannot be blank
    static constraints = {
        content blank: false
    }

    int compareTo(other) {
        other.accepted <=> accepted ?: date <=> other.date
    }

}
