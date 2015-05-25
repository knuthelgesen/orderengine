var app = angular.module("OrderEngine").factory('gameDataservice', gameDataservice);

gameDataservice.$inject = ['$http'];

function gameDataservice($http) {
	var self = this;
	self.functions = {
			getGames : getGames,
			getGame : getGame,
			createGame : createGame
		};
	return self.functions;
	
	function getGames() {
    	return $http.get('/order-front/rest/games').success(function(data) {
    		return data;
    	}).error(function(response) {
    		return;
    	});
	};

	function getGame(gameId) {
    	return $http.get('/order-front/rest/games/' + gameId).success(function(data) {
    		return data;
    	}).error(function(response) {
    		return;
    	});
	};
	
	function createGame(newGame) {
    	return $http.put('/order-front/rest/games/', newGame).success(function(data) {
            return;
    	}).error(function(response) {
    		return;
    	});
	};
	
};