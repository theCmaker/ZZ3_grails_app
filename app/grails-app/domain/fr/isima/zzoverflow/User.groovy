package fr.isima.zzoverflow

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class User implements Serializable {

	private static final long serialVersionUID = 1

	transient springSecurityService

	String username
	String password
	int points = 0
	boolean enabled = true
	boolean accountExpired = false
	boolean accountLocked = false
	boolean passwordExpired = false

	static hasMany = [ answers: Answer, questions: Question, badges: Badge ]

	Set<Group> getAuthorities() {
		UserGroup.findAllByUser(this)*.group
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}

	static transients = ['springSecurityService']

	static constraints = {
		password blank: false, password: true
		username blank: false, unique: true

		
	}

	static mapping = {
		password column: '`password`'
	}
}
