var app = angular.module('OrderEngine');

app.directive('navbar', navbar);
navbar.$inject = ['$window', 'userDataservice'];

function navbar($window, userDataservice) {
    var directive = {};
	directive.restrict = 'E';
    directive.templateUrl = '/static/js/directives/navbar/navbar.html';

    directive.controller = ['$scope', function ($scope) {
    	this.logOut = function() {
    		userDataservice.logOut().then(function() {$window.location.reload();});
    	};
    }];
    directive.controllerAs = 'navbarCtrl';
    
    return directive;
};
