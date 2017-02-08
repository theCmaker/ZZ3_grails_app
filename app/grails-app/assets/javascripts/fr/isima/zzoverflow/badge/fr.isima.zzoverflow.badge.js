//= wrapped
//= require /angular/angular 
//= require /angular/angular-ui-router
//= require /angular/angular-resource
//= require /fr/isima/zzoverflow/core/fr.isima.zzoverflow.core
//= require /fr/isima/zzoverflow/user/fr.isima.zzoverflow.user
//= require_self
//= require_tree services
//= require_tree controllers
//= require_tree directives
//= require_tree domain
//= require_tree templates

angular.module("fr.isima.zzoverflow.badge", [
    "ui.router",
    "ngResource",
    "fr.isima.zzoverflow.core",
    "fr.isima.zzoverflow.user"
]).config(config);

function config($stateProvider) {
    $stateProvider
        .state('badge', {
            url: "/badge",
            abstract: true,
            template: "<div ui-view></div>"
        })
        .state('badge.list', {
            url: "",
            templateUrl: "/fr/isima/zzoverflow/badge/list.html",
            controller: "BadgeListController as vm"
        })
        .state('badge.create', {
            url: "/create",
            params: {"userId":null},
            templateUrl: "/fr/isima/zzoverflow/badge/create.html",
            controller: "BadgeCreateController as vm"
        })
        .state('badge.edit', {
            url: "/edit/:id",
            templateUrl: "/fr/isima/zzoverflow/badge/edit.html",
            controller: "BadgeEditController as vm"
        })
        .state('badge.show', {
            url: "/show/:id",
            templateUrl: "/fr/isima/zzoverflow/badge/show.html",
            controller: "BadgeShowController as vm"
        });
}
