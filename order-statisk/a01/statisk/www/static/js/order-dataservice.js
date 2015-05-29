var app = angular.module("OrderEngine").factory('orderDataservice', orderDataservice);

orderDataservice.$inject = ['$http'];

function orderDataservice($http) {
	var self = this;
	self.functions = {
			getWSToken : getWSToken
		};
	return self.functions;
	
	function getWSToken() {
    	return $http.get('/order-front/rest/tokens/ws').success(function(data) {
    		return data;
    	}).error(function(response) {
    		return;
    	});
	};
	
};