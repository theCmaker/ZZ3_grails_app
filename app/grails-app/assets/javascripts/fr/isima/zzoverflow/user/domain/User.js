//= wrapped
//= require /angular/angular-resource

angular
    .module("fr.isima.zzoverflow.user")
    .factory("User", User);

function User($resource) {
    var User = $resource(
        "user/:id",
        {"id": "@id"},
        {"update": {method: "PUT"},
         "query": {method: "GET", isArray: true},
         "get": {method: 'GET'}}
    );

    User.list = User.query;

    User.prototype.toString = function() {
        return 'fr.isima.zzoverflow.User : ' + (this.id ? this.id : '(unsaved)');
    };

    return User;
}
