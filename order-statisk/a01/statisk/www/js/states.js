var app = angular.module('OrderEngine');

app.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/');

    $stateProvider
    
    /* **********************************************************************************
     *  Main menu state 
     ************************************************************************************/
    .state('main_menu', {
    	url : '/',
    	templateUrl : '/partials/main_menu.html'
    })

    /* **********************************************************************************
     *  Profile page state 
     ************************************************************************************/
    .state('profile', {
    	url : '/profile',
    	templateUrl : '/partials/profile.html',
    	resolve : {
    		userDataservice : 'userDataservice'
    	},
    	controller : function($scope, userDataservice) {
    		$scope.changePassword = function() {
    			userDataservice.changePassword($scope.newPassword);
    		};
    		$scope.deleteUser = function() {
    			userDataservice.deleteUser();
    		};
    	}
    });
    
}]);