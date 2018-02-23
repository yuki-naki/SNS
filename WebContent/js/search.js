$(document).ready(function() {
	$('#follow').addClass("active");
	$('#top').removeClass("active");
});

$(function(){
	$('#grade, #departmentId, #test').change(function() {
		var dept =  $('#departmentId').val();
		var grade =  $('#grade').val();
		var search = new RegExp($('#test').val().trim());

		$('#table tbody tr').each(function(){
		//td:eq(0) の０番目
			var userDept = $(this).find("td:eq(3)").html();
			var userGrade = $(this).find("td:eq(2)").html();
			var username = $(this).find("td:eq(1)").html();

			if(userDept.match(dept) && grade === "default" && search ===""){
				$(this).show();
			}
			else if(userGrade.match(grade) && dept === "default" && search ===""){
				$(this).show();
			}
			else if(username.match(search) && dept === "default" && grade === "default"){
				$(this).show();
			}
			else if(userGrade.match(grade) && userDept.match(dept) && grade != "default" && dept != "default" && search ===""){
				$(this).show();
			}
			else if(userGrade.match(grade) && username.match(search) && grade != "default" && search !="" && dept === "default"){
				$(this).show();
			}
			else if(userDept.match(dept) && username.match(search) && dept != "default" && search !="" && grade === "default"){
				$(this).show();
			}
			else if(userGrade.match(grade) && username.match(search) && userDept.match(dept) &&
					grade != "default" && search !="" && dept != "default"){
				$(this).show();
			}
			else{
				$(this).hide();
			}
	     });
	});
})