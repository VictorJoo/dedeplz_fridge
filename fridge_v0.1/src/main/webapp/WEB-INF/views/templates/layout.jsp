<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
   <title>Creative - Start Bootstrap Theme</title>

    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">

    <!-- Custom Fonts -->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="font-awesome/css/font-awesome.min.css" type="text/css">

    <!-- Plugin CSS -->
    <link rel="stylesheet" href="css/animate.min.css" type="text/css">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="css/creative.css" type="text/css">
    <link href="css/agency.css" rel="stylesheet">


<title>Tiles Layout</title>

  



<link rel="stylesheet" type="text/css"
	href="${initParam.root}/css/home.css" />
</head>
<body id="page-top">
<nav id="mainNav" class="navbar navbar-default navbar-fixed-top">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand page-scroll" href="#page-top">Top</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<c:choose>
						<c:when test="${sessionScope.mvo==null}">
							<li><a class="page-scroll"
								href="${initParam.root}member_joinclause_view.do">join</a></li>
							<li><a class="page-scroll" data-toggle="modal" href="#"
								data-target="#exampleModal" data-whatever="@getbootstrap">login</a>
							</li>
							<li><a class="page-scroll" href="#portfolio">Search</a></li>
						</c:when>
						<c:otherwise>
							<li><a class="page-scroll" href="#">${sessionScope.mvo.nick}님
									접속중</a></li>
							<li><a class="page-scroll"
								href="${initParam.root}member_mypage.do">MyPage</a></li>
							<li><a class="page-scroll"
								href="${initParam.root}registerRecipeForm.do">New Recipe</a></li>
							<li><a class="page-scroll"
								href="${initParam.root}member_logout.do">logout</a></li>
							<c:choose>
								<c:when test="${sessionScope.mvo.level=='6' }">
									<li><a class="page-scroll"
										href="${initParam.root}admin_adminpage.do">관리자페이지</a></li>
								</c:when>
							</c:choose>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>
    <div id="header" class="fluid"><tiles:insertAttribute name="header" /></div>
	<div id="container">
	<section class="no-padding" id="portfolio">
	<div id="main"><tiles:insertAttribute name="main" /></div>
	</section>
	<%-- <div id="footer"><tiles:insertAttribute name="footer" /></div> --%>
</div>
 <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Plugin JavaScript -->
    <script src="js/jquery.easing.min.js"></script>
    <script src="js/jquery.fittext.js"></script>
    <script src="js/wow.min.js"></script>

    <!-- Custom Theme JavaScript -->    
    <script src="js/creative.js"></script>
	

   <script type="text/javascript">
	$(document).ready(function(){
		$("#findMyId").click(function() {	
	         $.ajax({
	            type:"POST",
	            url:"member_findMyId.do",
	            data:$("#findMyIdForm").serialize(),   
	            success:function(data){                  
	               if(data=="fail"){
	                  $("#findMyIdView").html("id에 해당하는 정보가 없음");
	               }else{                  
	                  $("#findMyIdView").html("회원님의 아디는 '"+data+"' 입니다");      
	               }               
	            }//callback         
	         });//ajax
	      });//keyup
		$("#findMyPassBtn").click(function(){			
			$.ajax({
				type:"post",
				url:"member_findMyPassword.do",
				data:$("#findMyPasswordForm").serialize(),
				success:function(data){                  
		               if(data=="fail"){
		                  $("#findMyPassView").html("password에 해당하는 정보가 없음");
		               }else{                  
		                  $("#findMyPassView").html("회원님의 비밀번호 '"+data+"' 입니다");      
		               }               
		            }//callback      
			});//ajax
		});//findMyPassBtn
		
		$("#loginBtn").click(function() {
			$("#loginForm").submit();
		});
		
	//회원가입
		$("#id").keyup(function(){
	         var id=$("#id").val().trim();
	         $("#id_error").html("");
	         if(id.length<4 || id.length>10){
	            checkResultId="";
	            return;
	         }
	         
	         $.ajax({
	            type:"POST",
	            url:"member_memberIdCheck.do",
	            data:"id="+id,   
	            success:function(data){                  
	               if(data=="fail"){
	               $("#idCheckView").html(id+" 는(은) 사용하실 수 없습니다.");
	                  checkResultId="";
	               }else{                  
	                  $("#idCheckView").html(id+" 는(은) 사용할 수 있습니다.");      
	                  checkResultId=id;
	               }               
	            }//callback         
	         });//ajax
	      });//keyup
	      
	      //닉넴 중복체크
	      $("#nick").keyup(function(){
	         var nick=$("#nick").val().trim();
	         $("#nick_error").html("");
	         if(nick.length<2 || nick.length>6){
	            $("#nickCheckView").html("닉네임은 2글자 이상 6글자 이하여야 합니다.");
	            checkResultNick="";
	            return;
	         }
	         
	         $.ajax({
	            type:"POST",
	            url:"member_memberNickCheck.do",
	            data:"nick="+nick,   
	            success:function(data){                  
	               if(data=="fail"){
	               $("#nickCheckView").html(nick+"는(은) 사용하실 수 없습니다.");
	                  checkResultNick="";
	               }else{                  
	                  $("#nickCheckView").html(nick+"는(은) 사용가능합니다.");      
	                  checkResultNick=nick;
	               }               
	            }//callback         
	         });//ajax
	      });//keyup
	      
	      
	      $("#pass").keyup(function(){
	         $("#pass_error").html("");
	          var password= $("#pass").val();
	          if(password.length<4 || password.length>10){
	                  $("#passwordSizeView").html("비밀번호는 4자이상 10자이하여야 합니다.");
	               }else{
	                  $("#passwordSizeView").html("");
	               }
	      });
	      $("#password2").keyup(function(){
	             $("#pass_error").html("");
	             var password = $("#pass").val();
	            var password2 = $("#password2").val();
	           /*  alert("1 : "+password +" "+ "2 : "+password2); */
	            if(password2.length<4 || password2.length>10){
	               $("#passwordCheckView").html("");
	            }else if (password2!=password) {
	               $("#passwordCheckView").html("비밀번호가 서로 같지 않습니다ssssssss.");
	            }else if(password2==password){
	               $("#passwordCheckView").html("확인 되었습니다.");
	            }
	         }); 
	      $("#cancelBtn").click(function(){
	         location.href="${initParam.root}home.do";
	      });
	      $("#regForm").submit(function(){
	         var password = $("#pass").val();
	         var password2 = $("#password2").val();
	            if(password!=password2){
	               alert("패스워드를 다시 확인 해주십시요.");
	               return false;
	            }
	      });
	      $("#submitBtn").click(function() {
	    			$("#regForm").submit();
		});
	      
	      $("#cancelBtn").click(function(){
		         location.href="${initParam.root}home.do";
		      });
		   $("#updateBtn").click(function(){
				location.href = "${initParam.root}member_updateMemberForm.do";
			});
			$("#deleteBtn").click(function(){
		         location.href="${initParam.root}member_deleteMemberForm.do";
		      });
			$("#gobackBtn").click(function() {
				location.href="${initParam.root}home.do";
			});
			
			//recipe 등록
			$("#register").click(function(){
				 obj.getById["contents"].exec("UPDATE_CONTENTS_FIELD", []); 
				 var info = $("#contents").val().indexOf("<img");
		         if($("#title").val()==""){
		            alert("제목을 입력하세요");
		            return false;
		         }else if($("#cookingTime").val()==""){
		            alert("요리시간을 입력하세요");
		            return false;
		         }else if (info == -1) {
		             alert("레시피 사진을 하나 이상 입력하세요!!!");
		             return false; 
		         }else if($("#items").val()==""){
		            alert("태그를 입력하세요");
		            return false;
		         }
		         $("#registerForm").submit();
		       
			 });//submit
			 //수정폼
			$("#updateRecipeBtn").click(function(){
			      if($("#cookingTime").val()==""){
			         alert("요리시간을 선택하세요!");
			         return false;
			      }
			      obj.getById["contents"].exec("UPDATE_CONTENTS_FIELD", []);
			      $("#updateForm").submit();
			   });
			   $("#cancleBtn").click(function(){
			      location.href="home.do";
			   });
				$("#password3").keyup(function() {
					$("#pass_error").html("");
					var password = $("#password3").val();
					if (password.length<4 || password.length>10) {
						$("#passwordSizeUpdateView").html("비밀번호 4자이상 10자이하");
					} else {
						$("#passwordSizeUpdateView").html("");
					}
				});//keyup Password1
				$("#password4").keyup(function() {
					$("#pass_error").html("");
					var password = $("#password4").val();
					var password2 = $("#password3").val();
					if (password.length<4 || password.length>10) {
						$("#passwordCheckUdpateView").html("");
					} else if (password2 != password) {
						$("#passwordCheckUdpateView").html("비밀번호가 서로 같지 않습니다.");
					} else if (password2 == password) {
						$("#passwordCheckUdpateView").html("일치합니다.");
					}
				});

				$("#resetBtn").click(function() {
					$("#password3").val("");
					$("#password4").val("");
					$("#name").val("${mvo.name}");
					$("#nick").val("${mvo.nick}");
					$("#email").val("${mvo.email}");
					$("#answer").val("${mvo.answer}");
				});
				$("#updateFinishBtn").click(function() {
					var password = $("#password3").val();
					var password2 = $("#password4").val();
					if (password != password2) {
						alert("패스워드를 확인 해주시길 바랍니다.");
						return false;
					}
					$("#updForm").submit();
				});   

	});//ready
</script>
<script type="text/javascript">
//modal shocontents
function testAlert(path) {
	$.ajax({
		type:"POST",
        url:"showRecipe.do",
        data:"filePath="+path,   
        dataType : 'json',
        success:function(data){            
        
        if(data.rvo.memberId=="${sessionScope.mvo.id}"){
        	$("#gogo").html(" <h2 class='heading'>"+data.rvo.title+"</h2><p>"+data.tag+"<br>요리시간 : "+data.rvo.cookingTime+"분</p><br>"
           		 +data.rvo.contents
         			+"<a class='btn btn-danger' href='updateForm.do?recipeNo="+data.rvo.recipeNo+"'>수정하기</a>  "
                   +"<a class='btn btn-danger' href='deleteRecipe.do?recipeNo="+data.rvo.recipeNo+"'>삭제하기</a>  "
           		  +"<button type='button' class='btn btn-primary' data-dismiss='modal'>"
         			+"<i class='fa fa-times'></i> Close</button>");
        }else{
        	$("#gogo").html(" <h2 class='heading'>"+data.rvo.title+"</h2><p>"+data.tag+"<br>요리시간 : "+data.rvo.cookingTime+"분</p><br>"
           		 +data.rvo.contents
           		  +"<button type='button' class='btn btn-primary' data-dismiss='modal'>"
         			+"<i class='fa fa-times'></i> Close</button>");
        }
          
        }//callback         
     });//ajax 
	
}
</script>



</body>
</html>