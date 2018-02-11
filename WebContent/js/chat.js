$(function() {
	$(".heading-compose").click(function() {
		$(".side-two").css({
			"left" : "0"
		});
	});

	$(".newMessage-back").click(function() {
		$(".side-two").css({
			"left" : "-100%"
		});
	});
	var console = document.getElementById('conversation');
	console.scrollTop = console.scrollHeight;
})

"use strict";

var Chat = {};

Chat.socket = null;

Chat.connect = (function(host) {
	if ('WebSocket' in window) {
		Chat.socket = new WebSocket(host);
	} else if ('MozWebSocket' in window) {
		Chat.socket = new MozWebSocket(host);
	} else {
		Console.selfMessage('Error: WebSocket is not supported by this browser.');
		return;
	}

	Chat.socket.onopen = function() {
		// Console.selfMessage('Info: WebSocket connection opened.');
		document.getElementById('comment').onkeydown = function(event) {
			if (event.keyCode == 13) {
				Chat.sendMessage();
			}
		};
		$("#reply_btn").on("click", function(event) {
			Chat.sendMessage();
		});
	};

	Chat.socket.onclose = function() {
		document.getElementById('comment').onkeydown = null;
	};

	Chat.socket.onmessage = function(message) {
		var jsonMessage = JSON.parse(message.data);
		var JsonUser = $("#reply").attr("data-user");
		var user = JSON.parse(JsonUser);
		if(jsonMessage.user.userId === user.userId) {
			Console.selfMessage(jsonMessage);
		}
		else {
			Console.receiveMessage(jsonMessage);
		}
	};
});

Chat.initialize = function() {
	var groupId = $("#headingGroupname").attr("data-groupId");
	if(typeof groupId != "undefined"){
		if (window.location.protocol == 'http:') {
			Chat.connect('ws://' + window.location.host + '/SNS/ws/'+groupId);
		} else {
			Chat.connect('wss://' + window.location.host + '/SNS/ws/'+groupId);
		}
	}
};

Chat.sendMessage = (function() {
	var message = document.getElementById('comment').value;
	if (message.trim() != "" && message != null) {
		var JsonUser = $("#reply").attr("data-user");
		var user = JSON.parse(JsonUser);
		const JsonMessage = {"content":message,"user":user};
		Chat.socket.send(JSON.stringify(JsonMessage));
		document.getElementById('comment').value = '';
	}
});

Chat.initialize();

var Console = {};

Console.selfMessage = (function(jsonMessage) {
	var console = document.getElementById('conversation');
	var div = document.createElement('div');
	div.className = 'message-text';
	div.innerHTML = jsonMessage.content;
	var span = document.createElement('span');
	span.className = 'message-time pull-right';
	span.innerHTML = jsonMessage.date;
	var div_sender = document.createElement('div');
	div_sender.className = 'sender';
	div_sender.appendChild(div);
	div_sender.appendChild(span);
	var div_main_sender = document.createElement('div');
	div_main_sender.className = 'col-xs-12 message-main-sender';
	div_main_sender.appendChild(div_sender);
	var div_main_message = document.createElement('div');
	div_main_message.className = 'row message-body';
	div_main_message.appendChild(div_main_sender);
	console.appendChild(div_main_message);

	$("#sideGroupname[data-groupId="+jsonMessage.groupId+"] + #sideDate > span").text(jsonMessage.date);

	console.scrollTop = console.scrollHeight;
});

Console.receiveMessage = (function(jsonMessage) {
	var console = document.getElementById('conversation');
	var div = document.createElement('div');
	div.className = 'message-text';
	div.innerHTML = jsonMessage.content;
	var span = document.createElement('span');
	span.className = 'message-time pull-right';
	span.innerHTML = jsonMessage.date;
	var div_sender = document.createElement('div');
	div_sender.className = 'receiver';
	div_sender.appendChild(div);
	div_sender.appendChild(span);
	var div_sender_name = document.createElement('div');
	div_sender_name.className = 'row receiver-name';
	div_sender_name.innerHTML = jsonMessage.user.username;
	var div_main_receiver = document.createElement('div');
	div_main_receiver.className = 'col-xs-11 message-main-receiver';
	div_main_receiver.appendChild(div_sender_name);
	div_main_receiver.appendChild(div_sender);
	var div_avatar = document.createElement('div');
	div_avatar.className = 'avatar-icon';
	var img = document.createElement('img');
	img.setAttribute("src","https://bootdey.com/img/Content/avatar/avatar2.png");
	div_avatar.appendChild(img);
	var div_body_avatar = document.createElement('div');
	div_body_avatar.className = "col-xs-1 messageBody-avatar";
	div_body_avatar.appendChild(div_avatar);
	var div_main_message = document.createElement('div');
	div_main_message.className = 'row message-body';
	div_main_message.appendChild(div_body_avatar);
	div_main_message.appendChild(div_main_receiver);
	console.appendChild(div_main_message);

	$("#sideGroupname[data-groupId="+jsonMessage.groupId+"] + #sideDate > span").text(jsonMessage.date);
	/*
	 * while (console.childNodes.length > 25) {
	 * console.removeChild(console.firstChild); }
	 */
	console.scrollTop = console.scrollHeight;
});

document.addEventListener("DOMContentLoaded", function() {
	// Remove elements with "noscript" class - <noscript> is not allowed in
	// XHTML
	var noscripts = document.getElementsByClassName("noscript");
	for (var i = 0; i < noscripts.length; i++) {
		noscripts[i].parentNode.removeChild(noscripts[i]);
	}
}, false);