//= wrapped

angular
    .module("fr.isima.zzoverflow.badge")
    .controller("BadgeCreateController", BadgeCreateController);

function BadgeCreateController(Badge, $state, $stateParams, User) {

    var vm = this;
    vm.userList = User.list();
    vm.badge = new Badge();
    
    if ($stateParams.userId) {
        vm.badge.user = {id: $stateParams.userId};
    }
    
    vm.saveBadge = function() {
        vm.errors = undefined;
        vm.badge.$save({}, function() {
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
