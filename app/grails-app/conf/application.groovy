

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'fr.isima.zzoverflow.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'fr.isima.zzoverflow.UserRole'
grails.plugin.springsecurity.authority.className = 'fr.isima.zzoverflow.Role'
grails.plugin.springsecurity.authority.groupAuthorityNameField = 'authorities'
grails.plugin.springsecurity.useRoleGroups = true
grails.plugin.springsecurity.logout.postOnly = false
grails.plugin.springsecurity.successHandler.useReferer = true

grails.plugin.springsecurity.rest.login.active = true
grails.plugin.springsecurity.rest.login.endpointUrl = '/api/login' // /api/login
grails.plugin.springsecurity.rest.login.failureStatusCode=401
grails.plugin.springsecurity.rest.token.validation.enableAnonymousAccess = true

grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/unavailable',    access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']],
	[pattern: '/logout', 		 access: ['permitAll']],
	[pattern: '/logout/**', 	 access: ['permitAll']],
	[pattern: '/login', 		 access: ['permitAll']],
	[pattern: '/login/**', 		 access: ['permitAll']],
	[pattern: '/dbconsole', 	 access: ['permitAll']],
	[pattern: '/dbconsole/**', 	 access: ['permitAll']],
	[pattern: '/fonts',			 access: ['permitAll']],
	[pattern: '/fonts/**',		 access: ['permitAll']],
	[pattern: '/health',		 access: ['permitAll']],
	[pattern: '/feature',		 access: ['permitAll']],
	[pattern: '/feature/**',	 access: ['permitAll']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
	// Stateless
	[
		pattern: '/**',
		filters: 'JOINED_FILTERS'
	]
	//,-authenticationProcessingFilter,-exceptionTranslationFilter,-securityContextPersistenceFilter,-rememberMeAuthenticationFilter'
]