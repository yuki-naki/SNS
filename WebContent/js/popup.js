//ポップアップ
$(function() {

	$('#show').click(function(e) {// show popupボタンクリック時の処理
	    $('#popup, #layer').show();
	});

	// レイヤー/ポップアップのcloseボタンクリック時の処理
	$('#close, #layer').click(function(e) {
	    $('#popup, #layer').hide();
	});
});