//= wrapped

angular
    .module("fr.isima.zzoverflow.badge")
    .controller("BadgeShowController", BadgeShowController);

function BadgeShowController(Badge, $stateParams, $state) {
    var vm = this;

    Badge.get({id: $stateParams.id}, function(data) {
        vm.badge = new Badge(data);
    }, function() {
        $state.go('badge.list');
    });

    vm.delete = function() {
        vm.badge.$delete(function() {
            $state.go('badge.list');
        }, function() {
            //on error
        });
    };

}
