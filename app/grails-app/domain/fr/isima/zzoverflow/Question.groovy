package fr.isima.zzoverflow

class Question implements Comparable {

    /// The Question main title
    String title

    /// The Question content
    String content

    /// The Question creation Date
    Date date

    /// Set containing the users id that voted up or down
    Set<Long> upVoters = []
    Set<Long> downVoters = []

    /// We store the answers in a sorted set to display them properly
    SortedSet answers

    // Many to many Tag and Answer
    static hasMany = [ tags: Keyword, answers: Answer ]

    static belongsTo = [ user: User ]

    /// The title or the content cannot be blank
    static constraints = {
        title blank: false
        content blank: false, widget: 'textarea'
    }

    int compareTo(other) {
        other.getScore() <=> getScore() ?: date <=> other.date
    }

    int getScore() {
        return upVoters.size() - downVoters.size()
    }

}
