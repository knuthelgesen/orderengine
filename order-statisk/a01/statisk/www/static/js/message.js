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
 * EnterGame message class
 ***********************************************************************************/
//Define constructor
function EnterGameMessage(gameId) {
	Message.call(this, 'enterGame');
	
	this.gameId = gameId;
};
//EnterGame prototype
EnterGameMessage.prototype = Object.create(Message.prototype);
//Set constructor to point to "EnterGameMessage"
EnterGameMessage.prototype.constructor = EnterGameMessage;

/* *********************************************************************************
 * IssueOrder message class
 ***********************************************************************************/
//Define constructor
function IssueOrderMessage() {
	Message.call(this, 'issueOrder');
};
//IssueOrder prototype
IssueOrderMessage.prototype = Object.create(Message.prototype);
//Set constructor to point to "IssueOrderMessage"
IssueOrderMessage.prototype.constructor = IssueOrderMessage;
