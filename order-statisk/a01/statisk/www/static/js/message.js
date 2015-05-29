/* *********************************************************************************
 * Message base class
 ***********************************************************************************/
var Message = function(messageType) {
	this.messageType = messageType;
};

/* *********************************************************************************
 * Authenticate message class
 ***********************************************************************************/
//Define constructor
function AuthenticateMessage(wsToken) {
	Message.call(this, 'authenticate');
	
	this.wsToken = wsToken;
};
//Authenticate prototype
AuthenticateMessage.prototype = Object.create(Message.prototype);
//Set constructor to point to "AuthenticateMessage"
AuthenticateMessage.prototype.constructor = AuthenticateMessage;

