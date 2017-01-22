package fr.isima.zzoverflow

class Badge {

	String name

	static belongsTo = [ user: User ]

    static constraints = {
        name blank: false
    }
}
