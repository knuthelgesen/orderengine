var app = angular.module("OrderEngine").factory('gameDataservice', gameDataservice);

gameDataservice.$inject = ["$http"];

function gameDataservice($http) {
	var self = this;
	self.functions = {
			createGame : createGame
		};
	return self.functions;
	
	function createGame(newGame) {
    	return $http.put('/order-front/games/', newGame).success(function(data) {
            return;
    	}).error(function(response) {
    		return;
    	});
	}
};