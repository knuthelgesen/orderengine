var app = angular.module('OrderEngine');

app.directive('login', login);
login.$inject = ['userDataservice'];

function login(userDataservice) {
    var directive = {};
	directive.restrict = 'E';
    directive.templateUrl = '/js/directives/login/login.html';

    directive.controller = ['$scope', function ($scope) {
    	this.isLogInFormValid = function() {
    		return $scope.logIn && $scope.logIn.userName && $scope.logIn.password;
    	};
    	
    	this.logIn = function() {
    		userDataservice.logIn($scope.logIn);
    	};

    	this.isCreateFormValid = function() {
    		return $scope.newUser && $scope.newUser.userName && $scope.newUser.password 
    			&& $scope.newUser.passwordRepeat && $scope.newUser.password == $scope.newUser.passwordRepeat;
    	};
    	
    	this.createUser = function() {
    		userDataservice.createUser($scope.newUser);
    	};
    }];
    directive.controllerAs = 'loginCtrl';
    
    return directive;
};
