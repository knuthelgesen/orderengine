var WebsocketClient = function(url, wsToken) {
	
	var self = this;
	var socket = new WebSocket(url);
	var ready = false;
	
	socket.onopen = function(event) {
		var authenticateMessage = new AuthenticateMessage(wsToken);
		socket.send(JSON.stringify(authenticateMessage));
	};
	socket.onclose = function(event) {
		console.log('Closed: ' + socket);
		ready = false;
	};
	socket.onmessage = function(event) {
		console.log('Received message: ' + event.data);
		var message = JSON.parse(event.data);
		if (message.messageType == 'authenticateResponse') {
			if (message.accepted) {
				ready = true;
				console.log('Client authenticated');
				self.onReady();
			}
		}
		self.handleMessage(message);
	}
	
	this.onReady = function() {
		console.log('No onReady function registered.');
	}
	
	this.handleMessage = function(message) {
		console.log('No message handler registered. Moving message to /dev/null');
	}
	
	this.sendMessage = function(message) {
		if (!ready) {throw 'Client not ready'}
		console.log('Sending message: ' + JSON.stringify(message));
		socket.send(JSON.stringify(message));
	}
	
}