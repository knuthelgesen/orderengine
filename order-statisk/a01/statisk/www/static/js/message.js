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

/* *********************************************************************************
 * IssueOrder message class
 ***********************************************************************************/
//Define constructor
function IssueOrderMessage() {
	Message.call(this, 'issueOrder');
};
//Authenticate prototype
IssueOrderMessage.prototype = Object.create(Message.prototype);
//Set constructor to point to "IssueOrderMessage"
IssueOrderMessage.prototype.constructor = IssueOrderMessage;
