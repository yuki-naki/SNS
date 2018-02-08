var id;
var first_message = true;

$(function(){
    $(".heading-compose").click(function() {
      $(".side-two").css({
        "left": "0"
      });
    });

    $(".newMessage-back").click(function() {
      $(".side-two").css({
        "left": "-100%"
      });
    });
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

    Chat.socket.onopen = function () {
       // Console.selfMessage('Info: WebSocket connection opened.');
        document.getElementById('comment').onkeydown = function(event) {
            if (event.keyCode == 13) {
                Chat.sendMessage();
            }
        };
        $("#reply_btn").on("click",function(event) {
                Chat.sendMessage();
        });
    };

    Chat.socket.onclose = function () {
        document.getElementById('comment').onkeydown = null;
        //Console.selfMessage('Info: WebSocket closed.');
    };

    Chat.socket.onmessage = function(message) {
    	var json = JSON.parse(message.data);
    	if(first_message){
    		first_message = false;
    		id = json.id;
    	}
    	else {
    		if(id === json.id){
    			Console.selfMessage(json.message);
    		}
    		else {
    			Console.receiveMessage(json.message);
    		}
    	}
    	console.log(json);
    	console.log("jsonid:"+json.id+" id:"+id);
    };
});

Chat.initialize = function() {
    if (window.location.protocol == 'http:') {
        Chat.connect('ws://' + window.location.host + '/SNS/ws');
    } else {
        Chat.connect('wss://' + window.location.host + '/SNS/ws');
    }
};

Chat.sendMessage = (function() {
    var message = document.getElementById('comment').value;
    if (message.trim() != "" && message != null) {
    	//Console.selfMessage(message);
        Chat.socket.send(message);
        document.getElementById('comment').value = '';
    }
});

var Console = {};

Console.selfMessage = (function(message) {
    var console = document.getElementById('conversation');
    var p = document.createElement('p');
    p.className  = 'message-text';
    p.innerHTML = message;
    var span = document.createElement('span');
    span.className  = 'message-time pull-right';
    var date = new Date(Date.now());
    var hour = date.getHours();
    if(hour < 10){
    	hour = "0"+hour;
    }
    var min = date.getMinutes();
    if(min < 10){
    	min = "0"+min;
    }
    var now = hour + ":" + min;
    span.innerHTML = now;
    var div_sender = document.createElement('div');
    div_sender.className  = 'sender';
    div_sender.appendChild(p);
    div_sender.appendChild(span);
    var div_main_sender = document.createElement('div');
    div_main_sender.className  = 'col-sm-12 message-main-sender';
    div_main_sender.appendChild(div_sender);
    var div_main_message = document.createElement('div');
    div_main_message.className  = 'row message-body';
    div_main_message.appendChild(div_main_sender);
    console.appendChild(div_main_message);
    /*
    while (console.childNodes.length > 25) {
        console.removeChild(console.firstChild);
    }*/
    console.scrollTop = console.scrollHeight;
});

Console.receiveMessage = (function(message) {
    var console = document.getElementById('conversation');
    var p = document.createElement('p');
    p.className  = 'message-text';
    p.innerHTML = message;
    var span = document.createElement('span');
    span.className  = 'message-time pull-right';
    var date = new Date(Date.now());
    var hour = date.getHours();
    if(hour < 10){
    	hour = "0"+hour;
    }
    var min = date.getMinutes();
    if(min < 10){
    	min = "0"+min;
    }
    var now = hour + ":" + min;
    span.innerHTML = now;
    var div_sender = document.createElement('div');
    div_sender.className  = 'receiver';
    div_sender.appendChild(p);
    div_sender.appendChild(span);
    var div_sender_name = document.createElement('div');
    div_sender_name.className = 'row receiver-name';
    div_sender_name.innerHTML = "username";
    var div_main_receiver = document.createElement('div');
    div_main_receiver.className  = 'col-sm-12 message-main-receiver';
    div_main_receiver.appendChild(div_sender_name);
    div_main_receiver.appendChild(div_sender);
    var div_avatar = document.createElement('div');
    div_avatar.className  = 'avatar-icon';
    var img = document.createElement('img');
    img.setAttribute("src","https://bootdey.com/img/Content/avatar/avatar2.png");
    div_avatar.appendChild(img);
    var div_body_avatar = document.createElement('div');
    div_body_avatar.className = "col-sm-2 col-xs-2 messageBody-avatar";
    div_body_avatar.appendChild(div_avatar);
    var div_main_message = document.createElement('div');
    div_main_message.className  = 'row message-body';
    div_main_message.appendChild(div_body_avatar);
    div_main_message.appendChild(div_main_receiver);
    console.appendChild(div_main_message);
    /*
    while (console.childNodes.length > 25) {
        console.removeChild(console.firstChild);
    }*/
    console.scrollTop = console.scrollHeight;
});

Chat.initialize();

document.addEventListener("DOMContentLoaded", function() {
    // Remove elements with "noscript" class - <noscript> is not allowed in XHTML
    var noscripts = document.getElementsByClassName("noscript");
    for (var i = 0; i < noscripts.length; i++) {
        noscripts[i].parentNode.removeChild(noscripts[i]);
    }
}, false);