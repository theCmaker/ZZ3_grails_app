//= wrapped

angular
    .module("fr.isima.zzoverflow.badge")
    .controller("BadgeListController", BadgeListController);

function BadgeListController(Badge) {
    var vm = this;

    var max = 10, offset = 0;

    Badge.list({max: max, offset: offset}, function(data) {
        vm.badgeList = data;
    });
}
