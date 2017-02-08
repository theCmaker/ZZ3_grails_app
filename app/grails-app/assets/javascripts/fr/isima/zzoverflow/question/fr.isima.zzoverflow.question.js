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

angular.module("fr.isima.zzoverflow.question", ["ui.router", "ngResource", "fr.isima.zzoverflow.core"]).config(config);

function config($stateProvider) {
    $stateProvider
        .state('question', {
            url: "/question",
            abstract: true,
            template: "<div ui-view></div>"
        })
        .state('question.list', {
            url: "",
            templateUrl: "/fr/isima/zzoverflow/question/list.html",
            controller: "QuestionListController as vm"
        })
        .state('question.create', {
            url: "/create",
            params: {"userId":null},
            templateUrl: "/fr/isima/zzoverflow/question/create.html",
            controller: "QuestionCreateController as vm"
        })
        .state('question.edit', {
            url: "/edit/:id",
            templateUrl: "/fr/isima/zzoverflow/question/edit.html",
            controller: "QuestionEditController as vm"
        })
        .state('question.show', {
            url: "/show/:id",
            templateUrl: "/fr/isima/zzoverflow/question/show.html",
            controller: "QuestionShowController as vm"
        });
}
