/**
 *
 */
$(function(){
 $(".modal-open").click(function(){

 //add();
 //$("#puni").load("/SNS/WebContent/WEB-INF/jsp/myPage.jsp");


 //キーボード操作などにより、オーバーレイが多重起動するのを防止する
 $( this ).blur() ; //ボタンからフォーカスを外す
 if( $( "#modal-overlay" )[0] ) return false ; //新しくモーダルウィンドウを起動しない (防止策1)
 //if($("#modal-overlay")[0]) $("#modal-overlay").remove() ;		//現在のモーダルウィンドウを削除して新しく起動する (防止策2)

 //オーバーレイを出現させる
 $( "body" ).append( '<div id="modal-overlay"></div>' ) ;
 $( "#modal-overlay" ).fadeIn( "slow" ) ;

 //コンテンツをセンタリングする
 centeringModalSyncer() ;

 //コンテンツをフェードインする
 $( "#modal-content" ).fadeIn( "slow" ) ;

 //[#modal-overlay]、または[#modal-close]をクリックしたら…
 $( "#modal-overlay,#modal-close" ).unbind().click( function(){

 //[#modal-content]と[#modal-overlay]をフェードアウトした後に…
 $( "#modal-content,#modal-overlay" ).fadeOut( "slow" , function(){

 //[#modal-overlay]を削除する
 $('#modal-overlay').remove() ;

 } ) ;

 } ) ;

 } ) ;

 //リサイズされたら、センタリングをする関数[centeringModalSyncer()]を実行する
 $( window ).resize( centeringModalSyncer ) ;

 //センタリングを実行する関数
 function centeringModalSyncer() {

 //画面(ウィンドウ)の幅、高さを取得
 var w = $( window ).width() ;
 var h = $( window ).height() ;

 // コンテンツ(#modal-content)の幅、高さを取得
 // jQueryのバージョンによっては、引数[{margin:true}]を指定した時、不具合を起こします。
 var cw = $( "#modal-content" ).outerWidth( {margin:true} );
 var ch = $( "#modal-content" ).outerHeight( {margin:true} );
 //var cw = $( "#modal-content" ).outerWidth();
 //var ch = $( "#modal-content" ).outerHeight();

 //センタリングを実行する
 console.log(cw+","+ch);
 $( "#modal-content" ).css( {"left": ((w - cw)/2) + "px","top": ((h - ch)/2) + "px"} ) ;

 }

} ) ;





function add()
{
    var div_element = document.createElement("div");
    /*div_element.innerHTML = '<div id="modal-content"><div id="modal-content-innar"><jsp:include page="myPage.jsp" flush="true" /></div></div>';*/
	/*div_element.innerHTML = '<div id="modal-content"><div id="modal-content-innar"><div id="puni"></div></div></div>';*/
    /*div_element.innerHTML = '<div id="puni"><jsp:include page="myPage.jsp" flush="true" /></div>';*/
    /*div_element.innerHTML = '<div id="modal-content"><div id="modal-content-innar">aaa</div></div>';*/
    /*div_element.innerHTML = '<div id="modal-content"><div id="modal-content-innar"><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"><html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1"><link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"><link rel="stylesheet" href="css/myPage.css"><script src="js/jquery.min.js"></script><script src="js/bootstrap.min.js"></script><title>MyProfile</title></head><body><%@include file="header.jsp" %><div class="container-fluid text-center"><div class="row justify-content-center"><div class="col-sm-8 col-sm-offset-2" id="profile"><form method="post" action="IconTestServlet" enctype="multipart/form-data"><c:forEach var="result" items="${result}"><div class="col"><h1>MyProfile</h1></div><div class="col-sm-3 col-sm-offset-1 content-center" id="iconwrap"><img name="icon" class="img-circle" src="loadIcon?userId=${result.userId}" id="icon"></div><div class="col-sm-5 col-sm-offset-2"><h3>名前:${result.username }</h3><h3>学年:${result.admissionYear}年</h3><h3>学科:${result.departmentName }</h3></div><div class="col-sm-12"><h3>自己紹介文</h3><textarea name="comment" class="form-control" rows="8" id="comment" readonly>${result.userIntroduction}</textarea></div><div class="col-sm-12 text-right"><input type="file" id="upload" name="iconimg"><button type="button" class="btn btn-success btn-md" id="config" >編集</button><a href="myPageSetup"><button type="submit" class="btn btn-success btn-md" >保存</button></a></div></c:forEach></form></div></div></div><script src="js/myPage.js"></script></body></html></div></div>';*/
    /*div_element.innerHTML = '<div id="modal-content"><div id="modal-content-innar"><div class="col"><h1>UserProfile</h1></div><div class="col-sm-3 col-sm-offset-1 content-center" id="iconwrap"><img name="icon" class="img-circle" src="https://bootdey.com/img/Content/avatar/avatar1.png" id="icon"></div><div class="col-sm-5 col-sm-offset-2"><c:forEach var="result" items="${result}"><h3>名前:${result.userName }</h3><h3>学年:${result.schoolYear }年</h3><h3>学科:${result.departmentName }</h3></div><div class="col-sm-12"><h3>自己紹介文</h3><textarea name="comment" class="form-control" rows="8" id="comment" readonly>${result.userIntroduction}</textarea></div></c:forEach></div></div>';*/
    div_element.innerHTML = '<div id="modal-content"><div id="modal-content-innar"><div><h1>UserProfile</h1></div><div id="iconwrap"><img name="icon" class="img-circle" src="https://bootdey.com/img/Content/avatar/avatar1.png" id="icon"></div><div><c:forEach var="result" items="${result}"><h3>名前:${result.userName }</h3><h3>学年:${result.schoolYear }年</h3><h3>学科:${result.departmentName }</h3></div><h3>自己紹介文</h3><textarea name="comment" class="form-control"  id="comment" readonly>${result.userIntroduction}</textarea></c:forEach></div></div>';
    var parent_object = document.getElementById("piyo");
	var textNode = document.createTextNode(div_element);
    parent_object.appendChild(div_element);
}