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
    
    /* **********************************************************************************
     *  Ingame TicTacToe state 
     ************************************************************************************/
    .state('ingame', {
    	url : '/ingame/:gameId',
    	templateUrl : '/static/partials/ingame.html',
    	resolve : {
    		gameDataservice : 'gameDataservice',
    		orderDataservice : 'orderDataservice'
    	},
    	controller : function($scope, $stateParams, gameDataservice, orderDataservice) {
    		var self = this;
    		var client;

    		orderDataservice.getWSToken().then(function(data) {
    			var wsToken = data.data;
    			
    			client = new WebsocketClient('ws://192.168.33.102:7101/order-front/ws/order', wsToken);

    			client.onReady = function() {
    				client.sendMessage(new EnterGameMessage($stateParams.gameId));
    			};
    			client.handleMessage = function(message) {
    				switch (message.messageType) {
    				case 'enterGameResponse':
    					console.log(message.view);
    					$scope.$apply($scope.gameState = message.view);
    					break;
    				default:
        				console.log(message);
    					break;
    				}
    			};
    		});

    		$scope.boardClick = function(index) {
    			if ($scope.gameState && !$scope.gameState.board[index]) {
        			client.sendMessage(new IssueOrderMessage({square: index}));
    			}
    		};
    		
    		$scope.resolveBoardColor = function(index) {
    			if (!$scope.gameState) {
    				return '';
    			}
    			if (!$scope.gameState.board[index]) {
        			return '/static/img/element/tictactoe.svg#blank';
    			}
    			if ($scope.gameState.board[index].value == 'blue') {
        			return '/static/img/element/tictactoe.svg#blue';
    			}
    			if ($scope.gameState.board[index].value == 'red') {
        			return '/static/img/element/tictactoe.svg#red';
    			}
    		};
    	},
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