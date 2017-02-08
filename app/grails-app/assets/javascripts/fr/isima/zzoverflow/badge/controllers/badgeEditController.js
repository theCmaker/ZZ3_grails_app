//= wrapped

angular
    .module("fr.isima.zzoverflow.badge")
    .controller("BadgeEditController", BadgeEditController);

function BadgeEditController(Badge, $stateParams, $state, User) {
    var vm = this;

    vm.userList = User.list();

    Badge.get({id: $stateParams.id}, function(data) {
        vm.badge = new Badge(data);
    }, function() {
        vm.errors = [{message: "Could not retrieve badge with ID " + $stateParams.id}];
    });

    vm.updateBadge = function() {
        vm.errors = undefined;
        vm.badge.$update(function() {
            $state.go('badge.show', {id: vm.badge.id});
        }, function(response) {
            var data = response.data;
            if (data.hasOwnProperty('message')) {
                vm.errors = [data];
            } else {
                vm.errors = data._embedded.errors;
            }
        });
    };
}
