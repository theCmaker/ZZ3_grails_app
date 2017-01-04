package fr.isima.zzoverflow

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes='name')
@ToString(includes='name', includeNames=true, includePackage=false)
class Group implements Serializable {

	private static final long serialVersionUID = 1

	String name

	Set<Role> getAuthorities() {
		GroupRole.findAllByGroup(this)*.role
	}

	static constraints = {
		name blank: false, unique: true
	}

	static mapping = {
		cache true
	}
}
