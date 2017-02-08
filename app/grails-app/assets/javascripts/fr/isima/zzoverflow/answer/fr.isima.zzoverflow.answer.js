//= wrapped
//= require /angular/angular 
//= require /angular/angular-ui-router
//= require /angular/angular-resource
//= require /fr/isima/zzoverflow/core/fr.isima.zzoverflow.core
//= require_self
//= require_tree services
//= require_tree controllers
//= require_tree directives
//= require_tree domain
//= require_tree templates

angular.module("fr.isima.zzoverflow.answer", ["ui.router", "ngResource", "fr.isima.zzoverflow.core"]).config(config);

function config($stateProvider) {
    $stateProvider
        .state('answer', {
            url: "/answer",
            abstract: true,
            template: "<div ui-view></div>"
        })
        .state('answer.list', {
            url: "",
            templateUrl: "/fr/isima/zzoverflow/answer/list.html",
            controller: "AnswerListController as vm"
        })
        .state('answer.create', {
            url: "/create",
            params: {"questionId":null,"userId":null},
            templateUrl: "/fr/isima/zzoverflow/answer/create.html",
            controller: "AnswerCreateController as vm"
        })
        .state('answer.edit', {
            url: "/edit/:id",
            templateUrl: "/fr/isima/zzoverflow/answer/edit.html",
            controller: "AnswerEditController as vm"
        })
        .state('answer.show', {
            url: "/show/:id",
            templateUrl: "/fr/isima/zzoverflow/answer/show.html",
            controller: "AnswerShowController as vm"
        });
}
