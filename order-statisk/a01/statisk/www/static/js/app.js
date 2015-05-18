var app = angular.module('OrderEngine', ['ui.router']);

app.config(["$locationProvider", function($locationProvider) {
	  $locationProvider.html5Mode(true);
}]);