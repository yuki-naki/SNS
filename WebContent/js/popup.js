// ポップアップscript
//createGroupポップアップ
$(function() {
	$('#groupIcon').click(function(e) {// show popupボタンクリック時の処理
	    $('#createGroupPopup, #createGroupLayer').show();
	});
	// レイヤー/ポップアップのcloseボタンクリック時の処理
	$('#closeCreateGroup, #layer').click(function(e) {
	    $('#createGroupPopup, #createGroupLayer').hide();
	});
});
$(function() {
	$("input[group='selectedMemberGroup']").click(function(e){
		if(($("input[group='selectedMemberGroup']:checked").length > 0) && ($("#newGroupName").val() != "")){
	        $("#createGroupButton").attr('disabled',false);
	    } else {
	    	$("#createGroupButton").attr('disabled',true);
	    }
	});
	$("#newGroupName").change(function(e){
		if(($("input[group='selectedMemberGroup']:checked").length > 0) && ($("#newGroupName").val() != "")){
	        $("#createGroupButton").attr('disabled',false);
	    } else {
	    	$("#createGroupButton").attr('disabled',true);
	    }
	});
});

//addMemberポップアップ
$(function() {
	$('#addMember').click(function(e) {// show popupボタンクリック時の処理
	    $('#addMemberPopup, #addMemberLayer').show();
	});

	// レイヤー/ポップアップのcloseボタンクリック時の処理
	$('#closeAddMember, #layer').click(function(e) {
	    $('#addMemberPopup, #addMemberLayer').hide();
	});
});
$(function() {
	$("input[group='addMemberGroup']").click(function(e){
		if(($("input[group='addMemberGroup']:checked").length > 0)){
	        $("#addMemberButton").attr('disabled',false);
	    } else {
	    	$("#addMemberButton").attr('disabled',true);
	    }
	});
});

//groupEditポップアップ
$(function() {
	$('#headingGroupname').click(function(e) {// show popupボタンクリック時の処理
	    $('#groupEditPopup, #groupEditLayer').show();
	});

	// レイヤー/ポップアップのcloseボタンクリック時の処理
	$('#closeGroupEdit, #layer').click(function(e) {
	    $('#groupEditPopup, #groupEditLayer').hide();
	});
});
$(function(){
	$("#upload" ).change(function() {
		var fileList = this.files ;
		for( var i=0,l=fileList.length; l>i; i++ ) {
			var blobUrl = window.URL.createObjectURL( fileList[i] ) ;
		}

		$("#editIcon").attr("src",blobUrl);
	});
});
$(function(){
	$('#editIcon').click(function(){
		document.getElementById("upload").click();
	});
});
