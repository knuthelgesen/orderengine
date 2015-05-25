var app = angular.module("OrderEngine").factory('userDataservice', userDataservice);

userDataservice.$inject = ["$http"];

function userDataservice($http) {
	var self = this;
	self.functions = {
			isLoggedIn : isLoggedIn,
			resolveLoggedIn : resolveLoggedIn,
			createUser : createUser,
			logIn : logIn,
			logOut : logOut,
			changePassword : changePassword,
			deleteUser : deleteUser,
			loggedIn : true
		};
	
	resolveLoggedIn();
	
	return self.functions;
	
	function isLoggedIn() {
		return this.loggedIn;
	};
	
	function resolveLoggedIn() {
    	return $http.get('/order-front/rest/users/loggedIn').success(function(data) {
    		self.functions.loggedIn = true;
    	}).error(function(response) {
    		self.functions.loggedIn = false;
    	});
	}
		
	function createUser(newUser) {
    	return $http.put('/order-front/rest/users/', newUser).success(function(data) {
            return;
    	}).error(function(response) {
    		return;
    	});
	}

	function logIn(logIn) {
    	return $http.post('/order-front/rest/users/logIn', logIn).success(function(data) {
    		self.functions.resolveLoggedIn();
    	}).error(function(response) {
    		self.functions.resolveLoggedIn();
    	});
	}
	
	function logOut() {
    	return $http.post('/order-front/rest/users/logOut').success(function(data) {
    		self.functions.resolveLoggedIn();
    	}).error(function(response) {
    		self.functions.resolveLoggedIn();
    	});
	}

	function changePassword(newPassword) {
    	return $http.post('/order-front/rest/users', newPassword).success(function(data) {
    		return;
    	}).error(function(response) {
    		return;
    	});
	}
	
	function deleteUser() {
    	return $http.delete('/order-front/rest/users').success(function(data) {
    		self.functions.resolveLoggedIn();
    	}).error(function(response) {
    		self.functions.resolveLoggedIn();
    	});
	}
};