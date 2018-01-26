var webSocket = new WebSocket("ws://localhost:8080/SNS/ws");
var messages = document.getElementById("textarea");

webSocket.onopen = function(message){processOpen(message);};
webSocket.onmessage = function(message){processMessage(message);};
webSocket.onclose = function(message){processClose(message);};
webSocket.onerror = function(message){processError(message);};

function processOpen(message){
	messages.value = "Connected to the server\n"
}

function processMessage(message){
	messages.value += "Received from the server: "+message.data+"\n"
}

function send(){
	if(messageinput != "close"){
 	webSocket.send(messageinput.value);
 	messages.value += "Send to server => "+messageinput.value+"\n";
 	messageinput.value = "";
	}
	else{
		webSocket.close();
	}
}

function processClose(message){
	webSocket.send("client disconnected");
	messages.value += "Connexion closed\n"
}

function processError(message){
	messages.value += "Error\n"
}
