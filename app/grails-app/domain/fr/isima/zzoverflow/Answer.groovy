package fr.isima.zzoverflow

class Answer implements Comparable {

    /// The answer is accepted or not
    boolean accepted = false

    /// The content of the answer
    String content

    /// The date the answer was created
    Date date

    /// Set containing the users id that voted. Used to calculate the score
    Set<Long> upVoters = []
    Set<Long> downVoters = []

    /// An answer is related to a Question and is given by a User
    static belongsTo = [ question: Question, user: User ]

    /// The question content cannot be blank
    static constraints = {
        content blank: false, widget: 'textarea'
    }

    /// Override of Comparable to order the answers
    int compareTo(other) {
        other.accepted <=> accepted ?: other.getScore() <=> getScore() ?: date <=> other.date
    }

    /// Return the score of the answer calculated from users that up and down voted
    int getScore() {
        return upVoters.size() - downVoters.size()
    }

}
