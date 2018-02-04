var file_id = 1;
var img_src;
var input;
var filename;
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
	plugins: ["textcolor, autoresize, image, lists advlist"],
	autoresize_bottom_margin:20,
	autoresize_min_height: 200,
	autoresize_max_height: 10000,
	toolbar: "undo redo | fontsizeselect | forecolor backcolor | bold italic | " +
		"alignleft aligncenter alignright alignjustify | bullist numlist | outdent indent | myImage",
	branding: false,
	file_picker_types: 'image',
	file_browser_callback_types: 'image',

	init_instance_callback: function (ed) {
		$('body').on('click', '#insertImage', function () {
			$("#insertImage").addClass("mce-active");
		});

		ed.on('click', function (e) {
			if(e.target.nodeName==="IMG"){
				$("#insertImage").addClass("mce-active");
			}
			else {
				$("#insertImage").removeClass("mce-active");
			}
		});
	},

	setup : function(ed) {
		ed.addButton('myImage', {
			title : 'Insert/edit image',
			icon : 'image',
			id: 'insertImage',

			onclick : function() {

				ed.windowManager.open({
					title: 'Insert/edit image',
					body: [{
							type: 'filepicker',
							id:'filepicker',
							filetype:'image',
							label: 'Source',
					}],
					buttons: [
						{
							text: 'OK',
							id:'ok_btn',
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
				            		ed.insertContent('<img id='+(file_id-1)+' src="' + img_src + '" alt="" name="image" data-filename='+filename+' >');
				                    flag_input = false;
				                    window.scrollTo(0,document.getElementById("form").scrollHeight);
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
						}
					],

					onPostRender: function() {
			        	$("#filepicker-l").css("font-size","16px");
			        	$("#filepicker-action").css("height","20px");
			        	$("#filepicker-inp").attr("readonly","readonly");
			        	if(ed.selection.getNode().nodeName==="IMG"){
			        		$("#filepicker-inp").val(ed.selection.getNode().getAttribute("data-filename"));
			        	}
					},

			        onclose: function() {
			        	if(flag_input){
			            	input.remove();
			            	flag_input = false;
			            }
			        	$("#insertImage").removeClass("mce-active");
			        }
				});
			}
		});
	},

	file_picker_callback: function(cb, value, meta) {

		if(flag_input){
			input.remove();
			flag_input = false;
		}

		input = createInput();
		input.click();
		flag_input = true;

		input.onchange = function() {

			var file = this.files[0];
			filename = file.name;
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
					img_src = blobInfo.blobUri();
				};
				reader.readAsDataURL(file);

				$("#ok_btn").focus();
			};
		}
	}
});

$(document).ready(function() {

	$('form').submit(function() {
		$("#content").text(tinymce.activeEditor.getContent());
		var content = $("#content").text();
		content = replaceSrc(content);
		$("#content2").text(content);
		var images = tinymce.activeEditor.getDoc().getElementsByName("image");
		var files = document.getElementsByClassName("file");

		if(images.length != files.length){
			for(var i = 0; i < files.length; i++){
				var file_to_remove = true;
				var file_id = files[i].getAttribute("id");

				for(var j = 0; j < images.length; j++){
					var image_id = images[j].getAttribute("id");

					if(file_id === image_id){
						file_to_remove = false;
						break;
					}
				}
				if(file_to_remove){
					files[i].remove();
					if(i <= (files.length - 1)){
						i--;
					}
				}
			}
		}
		$("#content").remove();
	});
});

function replaceSrc(content){
	var indexImg = content.substr(0).indexOf("<img");
	if(indexImg === -1){
		return content;
	}
	else {
		var beforeImg = content.substr(0,indexImg);
		var afterImg = content.substr(indexImg);
		var indexSrc = afterImg.indexOf("src");
		var beforeSrc = afterImg.substr(0,indexSrc);
		var afterSrc = afterImg.substr(indexSrc);

		afterSrc = afterSrc.replace(/src="[^\s]+"/, 'src="" width="100%" height="100%"');
		content = afterSrc;
		return beforeImg+beforeSrc+replaceSrc(content);
	}
}

function createInput(){
	var form = document.getElementById('form');
	var input = document.createElement("input");
	input.setAttribute("type", "file");
	input.setAttribute('accept', 'image/*');
	input.setAttribute("class", "hidden file");
	input.setAttribute("name", "file-"+file_id);
	input.setAttribute("id", ""+file_id);
	form.appendChild(input);
	file_id++;
	return input;
}