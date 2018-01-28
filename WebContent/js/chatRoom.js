var id;

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
        Console.log('Error: WebSocket is not supported by this browser.');
        return;
    }

    Chat.socket.onopen = function () {
       // Console.log('Info: WebSocket connection opened.');
        document.getElementById('comment').onkeydown = function(event) {
            if (event.keyCode == 13) {
                Chat.sendMessage();
            }
        };
    };

    Chat.socket.onclose = function () {
        document.getElementById('comment').onkeydown = null;
        //Console.log('Info: WebSocket closed.');
    };

    Chat.socket.onmessage = function(message) {
        Console.log(message.data);
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
        Chat.socket.send(message);
        document.getElementById('comment').value = '';
    }
});

var Console = {};

Console.log = (function(message) {
    var console = document.getElementById('conversation');
    var p = document.createElement('p');
    p.className  = 'message-text';
    p.innerHTML = message;
    var span = document.createElement('span');
    span.className  = 'message-time pull-right';
    var date = new Date(Date.now());
    var now = date.getHours() + ":" + date.getMinutes();
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

Chat.initialize();


document.addEventListener("DOMContentLoaded", function() {
    // Remove elements with "noscript" class - <noscript> is not allowed in XHTML
    var noscripts = document.getElementsByClassName("noscript");
    for (var i = 0; i < noscripts.length; i++) {
        noscripts[i].parentNode.removeChild(noscripts[i]);
    }
}, false);