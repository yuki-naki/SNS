var file_id = 1;
var img_src;
var input;
var flag_input=false;

tinymce.init({
      selector: "#content",
	  menubar: false,
	  forced_root_block : false,
	  image_description: false,
	  object_resizing : false,
	  image_dimensions: false,
	  language: 'ja',
	  content_style: 'img {max-width: 100%; max-height: 400px;}',
	  setup : function(ed) {

		  ed.addButton('myImage', {
	            title : 'Insert/edit image',
	            icon : 'image',
	            onclick : function() {

	                ed.windowManager.open({
	                    title: 'Insert/edit image',
	                    body: [
	                        {
		                        type: 'filepicker',
		                        id:'filepicker',
		                        filetype:'image',
		                        label: 'Source',
		                        onclick: function(e) {
		                        }
	                    }],
	                    buttons: [{
	                        text: 'OK',
	                        subtype: 'primary',
	                        onclick: function() {
	                        	if($("#filepicker-inp").val().trim()===""){
	                        		alert("画像を選んでください");
	                        		if(flag_input){
		                            	input.remove();
		                            	flag_input = false;
		                            }
	                        	}
	                        	else {
	                        		ed.insertContent('<img src="' + img_src + '" alt="" /> ');
	                        		//ed.execCommand('mceAutoResize');
			                        flag_input = false;
			                        (this).parent().parent().close();
	                        	}
	                        }
	                    },
	                    {
	                        text: 'Cancel',
	                        onclick: function() {
	                            (this).parent().parent().close();
	                            if(flag_input){
	                            	input.remove();
	                            	flag_input = false;
	                            }
	                        }
	                    }],
	                    onPostRender: function() {
	                    	$("#filepicker-l").css("font-size","16px");
	    	            	$("#filepicker-action").css("height","20px");
	    	            	$("#filepicker-inp").attr("readonly","readonly");
	                     },
	                    onclose: function() {
	                    	if(flag_input){
                            	input.remove();
                            	flag_input = false;
                            }
	                    	//ed.focus();
	                    	//ed.click();
	                        window.scrollTo(0,document.getElementById("form").scrollHeight);
	                    }
	                });
	            }
	        });

          ed.on('change', function(e) {
        	  //$("#content").text(ed.getContent());
        	  //ed.save();
          });

     },
      plugins: ["textcolor, autoresize, image, lists advlist"],
      autoresize_bottom_margin:20,
      autoresize_min_height: 150,
      autoresize_max_height: 10000,
      toolbar: "undo redo | fontsizeselect | forecolor backcolor | bold italic | " +
      		"alignleft aligncenter alignright alignjustify | bullist numlist | outdent indent | myImage",
	  branding: false,
	  file_picker_types: 'image',
	  file_browser_callback_types: 'image',

	  file_picker_callback: function(cb, value, meta) {
		  input = createInput();
		  input.click();
		  flag_input = true;

		input.onchange = function() {
		  var file = this.files[0];
		  $("#filepicker-inp").val(file.name);
		  if(! file.type.startsWith("image")){
			  alert("画像じゃない");
			  input.remove();
			  flag_input = false;
		  }
		  else {
			  var reader = new FileReader();

			 reader.onload = function () {
				var id = 'blobid' + (new Date()).getTime();
				var blobCache =  tinymce.activeEditor.editorUpload.blobCache;
				var base64 = reader.result.split(',')[1];
				var blobInfo = blobCache.create(id, file, base64);
				blobCache.add(blobInfo);
				//cb(blobInfo.blobUri(), { title: file.name });
				img_src = blobInfo.blobUri();
			  };
			  reader.readAsDataURL(file);
		  };
		}
	  }

  });

$(document).ready(function() {
	$('form').submit(function() {
		$("#content").text(ed.getContent());
  	  var content = $("#content").text();
  	/*var count = (content.match(/<img src="/g)).length;
  	if(count >= 2){
  		alert("画像が2つ以上ある");
  		return false;
  	}
  	else {*/
  			var number = 1;
			content = content.replace(/<img\ssrc="[^\s]+"\salt=""/g, function() {
				return "<img src=\""+(number++)+"\" alt=\"\"";
			});
			$("#content").text(content);
			$("#content2").text(content);
		$("#content").remove();
		 // return true;
  //	}
		});
});

function createInput(){
	var form = document.getElementById('form');
	var input = document.createElement("input");
	input.setAttribute("type", "file");
	input.setAttribute('accept', 'image/*');
	input.setAttribute("class", "");
	input.setAttribute("name", "file-"+file_id);
	input.setAttribute("id", ""+file_id);
	form.appendChild(input);
	file_id++;
	return input;
}

function checkRemovedImage(){
	$("#content").text(ed.getContent());
	 var content = $("#content").text();
}

/*
var number = 1;
var text = "<img src=\"12fgdfgd++/f13\" alt=\"\" /> test <img src=\"12f/gdfg++df13\" alt=\"\" />";
text = text.replace(/<img\ssrc="[^\s]+"\salt=""/g, function() {
	return "<img src=\""+(number++)+"\" alt=\"\"";
});

alert(text);*/

/*
(function($) {
    'use strict';

    $(document).ready(function() {
        $(document).on( 'click', '#mce-modal-block', function() {
            tinyMCE.activeEditor.windowManager.close();
        });
    });

})(jQuery);*/