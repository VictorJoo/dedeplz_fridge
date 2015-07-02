<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
   <script src="js/jquery.js"></script>
<script type="text/javascript" >
$(document).ready(function(){   
   var commentNoList=new Array();
      <c:forEach items="${requestScope.clvo }" var="commentInfo">
         commentNoList.push("${commentInfo.commentNo}");
      </c:forEach>
     $(":input[name=recommentInsertBtn]").click(function(){
        var commentNum=$(this).val();
        for(var i=0;i<commentNoList.length;i++){
            $("#update"+commentNoList[i]).html($("#contents"+commentNoList[i]).val());
            $("#recommentUpdateBtn"+commentNoList[i]).show();
         }
       $("#recommentUpdateBtn"+commentNum).hide();
       
        var insertRecommentForm="<form action='${initParam.root}registerRecomment.do' method='post' id='registerRecommentForm'>";
        insertRecommentForm+= "@"+$("#commentNick"+commentNum).val()+" ";
        insertRecommentForm+="<input type='text' name='commentContents' id='recommentContents'><input type='button' value='등록' id='registerRecomment'>";
        insertRecommentForm+="<input type='button' value='취소' id='cancelRecomment'>";
        insertRecommentForm+="<input type='hidden' value='"+$("#commentNo"+commentNum).val()+"' name='commentRef'>";
        insertRecommentForm+="<input type='hidden' value='"+$("#commentNick"+commentNum).val()+"' name='commentRefNick'>";
        insertRecommentForm+="<input type='hidden' value='"+$("#commentNo"+commentNum).val()+"' name='commentNo'>";
        insertRecommentForm+="<input type='hidden' value='"+$("#commentGroup"+commentNum).val()+"' name='commentGroup'>";
        insertRecommentForm+="<input type='hidden' value='${requestScope.recipeNo}' name='commentRecipeNo'>";
        insertRecommentForm+= "</form>";
        $(".recommentForm").html("");
        $("#"+commentNum).html(insertRecommentForm);
     });
         $(document).on("click","#registerRecomment",function(){
            if($("#recommentContents").val()==""){
               alert("내용입력");
               return;
            }
           $("#registerRecommentForm").submit();
        }); 
         
         $(document).on("click","#cancelRecomment",function(){
             if($("#recommentContents").val()==""){
            	 for(var i=0;i<commentNoList.length;i++){
                     $("#update"+commentNoList[i]).html($("#contents"+commentNoList[i]).val());
                     $(".recommentForm").html("");
                     $("#recommentUpdateBtn"+commentNoList[i]).show();
                  }
             }
         }); 
            
         $("#registerCommentBtn").click(function(){
           if($("#recipeCommentContents").val()==""){
              alert("내용입력");
              return false;
           } 
         });
     $(":input[name=recommentDeleteBtn]").click(function(){
        if(confirm("삭제하시겠습니까?")){
          location.href="${initParam.root}deleteComment.do?commentNo="+$(this).val()+"&commentRecipeNo=${requestScope.recipeNo}";
        }
     });
     
     $(":input[name=recommentUpdateBtn]").click(function(){
        var commentNum=$(this).val();        
        for(var i=0;i<commentNoList.length;i++){
             $("#update"+commentNoList[i]).html($("#contents"+commentNoList[i]).val());
             $("#recommentUpdateBtn"+commentNoList[i]).show();
          }
        $(".recommentForm").html("");
        $("#recommentUpdateBtn"+commentNum).hide();
        var updateRecommentForm="<form action='${initParam.root}updateRecomment.do' method='post' id='updateRecommentForm'>";
        updateRecommentForm+="<input type='text' name='commentContents' id='updateRecommentContents'  value='"+$("#commentContents"+commentNum).val()+"'>";
        updateRecommentForm+="    <input type='button' value='수정' id='updateRecomment'>";
        updateRecommentForm+="<input type='button' value='취소' id='cancelUpdateRecomment'>";
        updateRecommentForm+="<input type='hidden' value='"+$("#commentNo"+commentNum).val()+"' name='commentNo'>";
        updateRecommentForm+="<input type='hidden' value='${requestScope.recipeNo}' name='commentRecipeNo'>";
        updateRecommentForm+= "</form>";           
         $("#"+"update"+commentNum).html(updateRecommentForm);
        });//수정 폼
     $(document).on("click","#updateRecomment",function(){
         if($("#updateRecommentContents").val()==""){
            alert("내용입력");
            return;
         }
        $("#updateRecommentForm").submit();
     }); 
        $(document).on("click","#cancelUpdateRecomment",function(){
        	 for(var i=0;i<commentNoList.length;i++){
                 $("#update"+commentNoList[i]).html($("#contents"+commentNoList[i]).val());
                 $("#recommentUpdateBtn"+commentNoList[i]).show();
              }
        });     
});//ready   


</script>
</head>
<body>
	<P>${requestScope.recipeNo}번레시피 댓글</P>
	<table>
		<c:forEach items="${requestScope.clvo }" var="commentList"
			varStatus="status">
			<tr>
				<td>
				<c:if test="${commentList.commentLevel==0}">
						<hr>
				</c:if> ${commentList.commentNick} ${commentList.commentTime}
				
					<button type="button" name="recommentInsertBtn" value="${commentList.commentNo}">답글</button>
					 <c:if test="${sessionScope.mvo.nick == commentList.commentNick}">
						<button type="button" name="recommentDeleteBtn" value="${commentList.commentNo}">삭제</button>
						<button type="button" id="recommentUpdateBtn${commentList.commentNo}"
						name="recommentUpdateBtn" value="${commentList.commentNo}">수정</button>
						<input type="hidden" value="${commentList.commentContents}"
							id="commentContents${commentList.commentNo}">
					</c:if> <br> <c:if test="${commentList.commentLevel==1}">      
                         @ ${commentList.commentRefNick}
                   </c:if>
					<div id="update${commentList.commentNo}" class="updateRecommentForm">${commentList.commentContents}</div> 
					<input type="hidden" value="${commentList.commentContents}"
					id="contents${commentList.commentNo}">
					<div id="${commentList.commentNo}" class="recommentForm"></div> 
					<input type="hidden" value="${commentList.commentNo}" id="commentNo${commentList.commentNo}">
					<input type="hidden" value="${commentList.commentNick}" id="commentNick${commentList.commentNo}">
					<input type="hidden" value="${commentList.commentGroup}" id="commentGroup${commentList.commentNo}"></td>
			</tr>
		</c:forEach>
	</table>
	<form action="registerComment.do" method="post">
		${sessionScope.mvo.id }님 : <input type="text" name="commentContents"
			id="recipeCommentContents"><input type="submit" value="등록"
			id="registerCommentBtn"> <input type="hidden"
			value="${requestScope.recipeNo }" name="commentRecipeNo">
	</form>
</body>

</html>