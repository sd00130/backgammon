var MINDGAMES = {
	pages: {},
	websockets: {},
	json: {},
	messages: {}
};

MINDGAMES.messages = {
		JoinMessage: function(who){
			this.type = "JoinMessage";
			this.who = who;
		},

		JoinMessageResult: function(message){
			Object.extend(this, message);
		},
		
		ChatMessage: function(who, body){
			this.type = "ChatMessage";
			this.who = who;
			this.body = body;
		},
		
		ChatMessageResult: function(message){
			Object.extend(this, message);
		},
		
		MoveMessage: function(from, to){
			this.type = "MoveMessage";
			this.from = from;
			this.to = to;
		},
		
		MoveMessageResult: function(message){
			Object.extend(this, message);
		}
};

MINDGAMES.messages.ChatMessageResult.prototype.process = function(){
	var from=this.who;
    var text=this.body;
    
    var chat=$("#chat-field")[0];
    var spanFrom = document.createElement("span");
    spanFrom.className="from";
    spanFrom.innerHTML=from+":&nbsp;";
    var spanText = document.createElement("span");
    spanText.className="text";
    spanText.innerHTML=text;
    var lineBreak = document.createElement("br");
    chat.appendChild(spanFrom);
    chat.appendChild(spanText);
    chat.appendChild(lineBreak);
    chat.scrollTop = chat.scrollHeight - chat.clientHeight;  
};

MINDGAMES.messages.JoinMessageResult.prototype.process = function(){
	var from=this.who;
    var text="has joined";
    
    var chat=$("#chat-field")[0];
    var spanFrom = document.createElement("span");
    spanFrom.className="from";
    spanFrom.innerHTML=from+":&nbsp;";
    var spanText = document.createElement("span");
    spanText.className="text";
    spanText.innerHTML=text;
    var lineBreak = document.createElement("br");
    chat.appendChild(spanFrom);
    chat.appendChild(spanText);
    chat.appendChild(lineBreak);
    chat.scrollTop = chat.scrollHeight - chat.clientHeight;  
};

MINDGAMES.messages.MoveMessageResult.prototype.process = function(){
	var from=this.from;
	var to=this.to;
	var movedChip = MINDGAMES.pages.move(from, to);
	MINDGAMES.pages.removeSelection(movedChip);
};

MINDGAMES.pages = {	
	
	removeSelection: function (chip){
		chip.removeClass("on");
		chip.removeClass("selected");
	},
		
	getSelectedChip: function (){
		return $(".chip.selected");
	},	
		
	getProjectedHPos: function (projector){
		return projector.attr('id').substring(1);
	},	
	
	getSelectedChipHPos: function(){
		return MINDGAMES.pages.getChipHPos(MINDGAMES.pages.getSelectedChip());
	},
	
	getChipHPos: function (chip){
		return chip.attr('data-hpos');
	},
	
	getChipVPos: function (chip){
		return chip.attr('data-vpos');
	},
		
	getTopPos: function (hPos){
		var chipsInPos = $("[data-hpos='" + hPos + "']");
		var maxVPos = -1;
		chipsInPos.each(function(index) {
			if ($(this).attr('data-vpos') > maxVPos){
				maxVPos = parseInt($(this).attr('data-vpos'));
			}
		});
		return maxVPos;
	},	
	
	getChipFromTop: function (hPos){
		var chip = null;
		var maxVPos = -1;
		var chipsInPos = $("[data-hpos='" + hPos + "']");
		chipsInPos.each(function(index) {
			if ($(this).attr('data-vpos') > maxVPos){
				maxVPos = parseInt($(this).attr('data-vpos'));
				chip = $(this);
			}
		});
		return chip;
	},
	
	move: function(fromHPos, toHPos){
		var chip = MINDGAMES.pages.getChipFromTop(fromHPos);
		var nextVPos = MINDGAMES.pages.getTopPos(toHPos) + 1;
		var topOrBottom = toHPos < 13 ? "top" : "bottom";
		chip.removeClass("top");
		chip.removeClass("bottom");
		chip.addClass(topOrBottom);
		chip.attr('data-hpos', toHPos);
		chip.attr('data-vpos', nextVPos);
		return chip;
	},
		
	backgammon : function() {
	
		$("#chat-enable").click(function()
		{
			$("#chat-enable").addClass("hidden");
	        $("#chat-disable").removeClass("hidden");
	        
	        $("#join").removeClass("chat-hidden");
			$("#joined").removeClass("chat-hidden");
			
			$( "#chat-field" ).animate( { width: "290px", height: "270px" }, 100);
			$( "#chat" ).animate( { width: "290px", height: "270px" }, 100);
			$( "#input-field" ).animate( { width: "290px", height: "40px" }, 100);
			$( "#input" ).animate( { width: "290px" }, 100);
	        
		});
		
		$("#chat-disable").click(function()
		{
			$("#chat-disable").addClass("hidden");
			$("#chat-enable").removeClass("hidden");
			
			$("#join").addClass("chat-hidden");
			$("#joined").addClass("chat-hidden");
			 
			$( "#chat-field" ).animate( { width: "0px", height: "0px" }, 100);
			$( "#chat" ).animate( { width: "0px", height: "0px" }, 100);
			$( "#input-field" ).animate( { width: "0px", height: "0px" }, 100);
			$( "#input" ).animate( { width: "0px", height: "0px" }, 100);
			
			
		});
		
		$("#joinB").click(function()
		{
			MINDGAMES.websockets.createWebsocket($("#username").val());
		});
		
		$("#sendB").click(function()
		{
	        var phrase = $("#phrase").val();
	        var chatMessage = new MINDGAMES.messages.ChatMessage(MINDGAMES.websockets.username, phrase);
	        MINDGAMES.websockets.doSend(chatMessage);
		});
		
		$(".projector").mouseover(function(){
			$(this).css("background-image", "url('./img/projectors/" + $(this).attr('id') + ".png')" );
		}).mouseout(function(){
			$(this).css("background-image","none");
		}).click(function()
		{				
			$(this).css("background-image","none");
			
			var thisHPos = MINDGAMES.pages.getProjectedHPos($(this));	
			var fromHPos = MINDGAMES.pages.getSelectedChipHPos();	
			
			var moveMessage = new MINDGAMES.messages.MoveMessage(fromHPos, thisHPos);
	        MINDGAMES.websockets.doSend(moveMessage);			
		});
	
		$(".chip").mouseover(function(){
			var thisHPos = MINDGAMES.pages.getChipHPos($(this));
			var thisVPos = MINDGAMES.pages.getChipVPos($(this));
			var maxVPos = MINDGAMES.pages.getTopPos(thisHPos);
			if (thisVPos == maxVPos)
			{
				$(this).addClass("on");
			}
		}).mouseout(function(){
			$(this).removeClass("on");
		}).click(function(){
			if ($(this).hasClass("selected"))
			{
				$(this).removeClass("selected");
			}
			else
			{
				$(".chip").removeClass("selected");
				$(this).addClass("selected");
			}
		});	
	}
};


MINDGAMES.websockets = {
		wsUri: document.location.toString().replace('http://','ws://').replace('https://','wss://').replace('backgammon.html','ws'),
				
		doSend: function(message)
		{
			var messageString = Object.toJSON(message);
		    this.websocket.send(messageString);
		},
		
		onOpen: function()
		{
	        $("#join").addClass("hidden");
	        $("#joined").removeClass("hidden");
	        $("#phrase").focus();
	        var username = $("#username");
	        var joinMessage = new MINDGAMES.messages.JoinMessage(MINDGAMES.websockets.username);
	        MINDGAMES.websockets.doSend(joinMessage);
		},
		
		onClose: function()
		{
			
		},
		
		onMessage: function(m)
		{
			if (m.data){
				var message = jQuery.parseJSON(m.data);
				var type = message.type;

				if (type == "JoinMessage"){
					var messageToProcess  = new MINDGAMES.messages.JoinMessageResult(message);
					messageToProcess.process();
				}
				
				if (type == "ChatMessage"){
					var messageToProcess = new MINDGAMES.messages.ChatMessageResult(message);
					messageToProcess.process();
				}
				if (type == "MoveMessage"){
					var messageToProcess = new MINDGAMES.messages.MoveMessageResult(message);
					messageToProcess.process();
				}
			}
		},
		
		onError: function()
		{
			
		},
		
		createWebsocket: function (name)
		 {			
			this.username = name;
	        this.websocket = new WebSocket(this.wsUri);
	        this.websocket.onopen = this.onOpen;
		    this.websocket.onclose = this.onClose;
		    this.websocket.onmessage = this.onMessage;
		    this.websocket.onerror = this.onError;
		  }
};

