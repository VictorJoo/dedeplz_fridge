<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
   <script src="js/jquery.js"></script>
<script type="text/javascript"  >
$(document).ready(function(){
     $(":input[name=recommentBtn]").click(function(){
    	 var valueee=$(this).val();
    	 var insertRecommentForm="<form action='${initParam.root}registerRecomment.do' method='post' id='fsddsfsfds'>";
    	 insertRecommentForm+= $("#commentNick"+valueee).val()+"에게";
    	 insertRecommentForm+="<input type='text' name='commentContents' id='recommentContents'><input type='button' value='등록' id='registerRecomment'>";
    	 insertRecommentForm+="<input type='hidden' value='"+$("#commentNo"+valueee).val()+"' name='commentRef'>";
    	 insertRecommentForm+="<input type='hidden' value='"+$("#commentNick"+valueee).val()+"' name='commentRefNick'>";
    	 insertRecommentForm+="<input type='hidden' value='${requestScope.recipeNo}' name='commentRecipeNo'>";
    	 insertRecommentForm+= "</form>";
    	 $(".recommnetForm").html("");
    	 $("#"+valueee).html(insertRecommentForm);
     });
     	 $(document).on("click","#registerRecomment",function(){
     		 if($("#recommentContents").val()==""){
     			 alert("내용입력");
     			 return;
     		 }
     		$("#fsddsfsfds").submit();
     	}); 
     	 $("#registerCommentBtn").click(function(){
     		if($("#recipeCommentContents").val()==""){
     			alert("내용입력");
     			return false;
     		} 
     	 });
});//ready

</script>
</head>
<body>
<P>${requestScope.recipeNo}번 레시피 댓글</P><hr>
 <table >
 <c:forEach items="${requestScope.clvo }" var="commentList" varStatus="status">
      <tr>
          <td>${commentList.commentNick}  |  ${commentList.commentTime}  
           <button type="button"  name="recommentBtn" value="${commentList.commentNo}">답글</button><br> 
              ${commentList.commentContents}<hr>
    		<div id="${commentList.commentNo}" class="recommnetForm"></div>
    		<input type="hidden" value="${commentList.commentNo}" id="commentNo${commentList.commentNo}">
    		<input type="hidden" value="${commentList.commentNick}" id="commentNick${commentList.commentNo}">
          </td>       
      </tr>      
   </c:forEach>   
   </table>
<form action="registerComment.do" method="post">
${sessionScope.mvo.id }님 : <input type="text" name="commentContents" id="recipeCommentContents" ><input type="submit"  value="등록" id="registerCommentBtn">
<input type="hidden" value="${requestScope.recipeNo }" name="commentRecipeNo">
</form>
</body>

</html>