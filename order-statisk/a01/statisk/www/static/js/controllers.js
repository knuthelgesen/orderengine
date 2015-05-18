var app = angular.module('OrderEngine');

app.controller('OrderEngineController', orderEngineController);
orderEngineController.$inject = ['userDataservice'];
function orderEngineController(userDataservice) {
	var self = this;

	self.isLoggedIn = function() {
		return userDataservice.isLoggedIn();
	};

};
