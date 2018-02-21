$(document).ready(function() {
	$('#follow').addClass("active");
	$('#top').removeClass("active");
});

//自動検索
/*
$(function(){
	$('#test').keyup(function() {
		if(keyup == 13){
		//検索文字をre
			var re = new RegExp($('#test').val());
				$('#table tbody tr').each(function(){
				//td:eq(0) の０番目 
					var txt = $(this).find("td:eq(1)").html();
					if(txt.match(re) != null){
						$(this).show();
					}else{
						$(this).hide();
					}
			});
		}
	}); 
});*/

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
			console.log(username + " " + search);

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