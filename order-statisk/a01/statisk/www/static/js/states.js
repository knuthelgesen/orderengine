var app = angular.module('OrderEngine');

app.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/');

    $stateProvider
    
    /* **********************************************************************************
     *  Main menu state 
     ************************************************************************************/
    .state('main_menu', {
    	url : '/',
    	templateUrl : '/static/partials/main_menu.html'
    })

    /* **********************************************************************************
     *  New game menu state 
     ************************************************************************************/
    .state('new_game', {
    	url : '/newgame',
    	templateUrl : '/static/partials/new_game_menu.html'
    })
    
    /* **********************************************************************************
     *  New TicTacToe game menu state
     ************************************************************************************/
    .state('new_game.ttt', {
    	url : '/ttt',
		templateUrl : '/static/partials/new_game_ttt.html',
    	resolve : {
    		gameDataservice : 'gameDataservice'
    	},
    	controller : function($scope, gameDataservice) {
    		$scope.newGame = {
    			gameType : 'tic_tac_toe',
    			gameData : {
    				userColor : 'blue'
    			}
    		};
    		
    		$scope.setUserColor = function(userColor) {
				$scope.newGame.gameData.userColor = userColor;
    		};
    		
    		$scope.createGame = function() {
    			gameDataservice.createGame($scope.newGame);
    		};
    	}
    })

    /* **********************************************************************************
     *  Load game menu state 
     ************************************************************************************/
    .state('load_game', {
    	url : '/loadgame',
    	templateUrl : '/static/partials/load_game_menu.html',
    	resolve : {
    		gameDataservice : 'gameDataservice'
    	},
    	controller : function($scope, gameDataservice) {
    		gameDataservice.getGames().then(function(data) {
    			$scope.gameList = data.data;
    		});
    	}    	
    })
    
    .state('ingame', {
    	url : '/ingame/:gameId',
    	templateUrl : '/static/partials/ingame.html',
    	resolve : {
    		gameDataservice : 'gameDataservice'
    	},
    	controller : function($scope, $stateParams, gameDataservice) {
    		gameDataservice.getGame($stateParams.gameId).then(function(data) {
    			$scope.game = data.data;
    		});
    	}    	
    })
    
    /* **********************************************************************************
     *  Profile page state 
     ************************************************************************************/
    .state('profile', {
    	url : '/profile',
    	templateUrl : '/static/partials/profile.html',
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