
$(document).ready(function() {
	$('#top').removeClass("active");
});

$(function(){
	$('#grade1, #departmentId1, #test1').change(function() {
		var dept =  $('#departmentId1').val();
		var grade =  $('#grade1').val();
		var search = new RegExp($('#test1').val().trim());

		$('#table1 tbody tr').each(function(){
		//td:eq(0) の０番目
			var userDept = $(this).find("td:eq(4)").html();
			var userGrade = $(this).find("td:eq(3)").html();
			var username = $(this).find("td:eq(2)").html();

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

$(function(){
	$('#grade2, #departmentId2, #test2').change(function() {
		var dept =  $('#departmentId2').val();
		var grade =  $('#grade2').val();
		var search = new RegExp($('#test2').val().trim());

		$('#table2 tbody tr').each(function(){
		//td:eq(0) の０番目
			var userDept = $(this).find("td:eq(4)").html();
			var userGrade = $(this).find("td:eq(3)").html();
			var username = $(this).find("td:eq(2)").html();

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