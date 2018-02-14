$(document).ready(function() {
	$('#myPage').addClass("active");
	$("#config").click(function(){
		$('#comment').attr('readonly',false);
		$('#config').attr("disabled",true);
	});
});