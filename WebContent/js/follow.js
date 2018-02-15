$(document).ready(function() {
	$('#follow').addClass("active");
	$('#top').removeClass("active");
});


$(function(){
	$('#test').keyup(function() {
		
		//検索文字をre
		var re = new RegExp($('#test').val());
			$('#test2 tbody tr').each(function(){
			//td:eq(0) の０番目 
			var txt = $(this).find("td:eq(1)").html();
			if(txt.match(re) != null){
				$('#test2 tbody tr').show();
			}else{
				$('#test2 tbody tr').hide();
			}
		});
	}); 
});

var change = function(){
	$('#test').change( function() {
	
			
				var re = new RegExp($('.searchText').val());
						$('#result tbody tr').each(function(){
						//td:eq の０番目
						var txt = $(this).find("td:eq(1)").html();
						if(txt.match(re) != null){
							$(this).show();
						}else{
							$(this).hide();
						}
				});
		}); 
	}
	
