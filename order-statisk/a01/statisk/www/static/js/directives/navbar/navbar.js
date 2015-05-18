var app = angular.module('OrderEngine');

app.directive('navbar', navbar);
navbar.$inject = ['userDataservice'];

function navbar(userDataservice) {
    var directive = {};
	directive.restrict = 'E';
    directive.templateUrl = '/static/js/directives/navbar/navbar.html';

    directive.controller = ['$scope', function ($scope) {
    	this.logOut = function() {
    		userDataservice.logOut();
    	};
    }];
    directive.controllerAs = 'navbarCtrl';
    
    return directive;
};
