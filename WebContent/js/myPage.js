$(document).ready(function() {
	$('#myPage').addClass("active");
	$('#top').removeClass("active");
	$("#config").click(function(){
		$('#comment').attr('readonly',false);
		$('#config').attr("disabled",true);
	});
});