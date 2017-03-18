//= wrapped
//= require /angular/angular-resource

angular
    .module("fr.isima.zzoverflow.badge")
    .factory("Badge", Badge);

function Badge($resource, domainListConversion, domainConversion) {
    var Badge = $resource(
        "badge/:id",
        {"id": "@id"},
        {"update": {method: "PUT"},
         "query": {method: "GET", isArray: true, transformResponse: [angular.fromJson, domainListConversion("User", "user", "domainConversion")]},
         "get": {method: 'GET', transformResponse: [angular.fromJson, domainConversion("User", "user")]}}
    );

    Badge.list = Badge.query;

    Badge.prototype.toString = function() {
        return 'fr.isima.zzoverflow.Badge : ' + (this.id ? this.id : '(unsaved)');
    };

    return Badge;
}
