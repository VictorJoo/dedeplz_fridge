<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
    <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css"
    rel="stylesheet" type="text/css">
    <link href="http://pingendo.github.io/pingendo-bootstrap/themes/default/bootstrap.css"
    rel="stylesheet" type="text/css">

<title>Insert title here</title>
   <script src="js/jquery.js"></script>
<script type="text/javascript" >

$(document).ready(function(){   

      var commentNoList=new Array();
         <c:forEach items="${requestScope.clvo }" var="commentInfo">
            commentNoList.push("${commentInfo.commentNo}");
         </c:forEach>
     
         
         
         $(document).on("click",":input[name=recommentInsertBtn]",function(){
           $("#registerCommentForm").hide();
           var commentNum=$(this).val();
           for(var i=0;i<commentNoList.length;i++){
               $("#update"+commentNoList[i]).html($("#contents"+commentNoList[i]).val());
               $("#recommentUpdateBtn"+commentNoList[i]).show();
            }
          $("#recommentUpdateBtn"+commentNum).hide();          
           var insertRecommentForm="<form action='${initParam.root}registerRecomment.do' method='post' id='registerRecommentForm'>";
           insertRecommentForm+= "<strong>@"+$("#commentNick"+commentNum).val()+"</strong> &nbsp; &nbsp; ";
           insertRecommentForm+="<input type='text' name='commentContents' id='recommentContents'> <button type='button' class='btn btn-link btn-xs'  id='registerRecomment'>등록</button>";
           insertRecommentForm+="<button type='button' class='btn btn-link btn-xs' id='cancelRecomment'>취소</button>";
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
               $("#registerCommentForm").show();
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
          $("#registerCommentForm").hide();
           var commentNum=$(this).val();
          // if(confirm("수정하시겠습니까?")){
             //alert(commentNum);
             
           for(var i=0;i<commentNoList.length;i++){
                $("#update"+commentNoList[i]).html($("#contents"+commentNoList[i]).val());
                $("#recommentUpdateBtn"+commentNoList[i]).show();
             }
            $(".recommentForm").html("");
           $("#recommentUpdateBtn"+commentNum).hide();
          // alert(commentNum);
           var updateRecommentForm="<form action='${initParam.root}updateRecomment.do' method='post' id='updateRecommentForm'>";
           updateRecommentForm+="<input type='text' name='commentContents' id='updateRecommentContents'  value='"+$("#commentContents"+commentNum).val()+"'>";
           updateRecommentForm+="    <button type='button' class='btn btn-link btn-xs' id='updateRecomment'>수정</button>";
           updateRecommentForm+="<button type='button' class='btn btn-link btn-xs' id='cancelUpdateRecomment'>취소</button>";
           updateRecommentForm+="<input type='hidden' value='"+$("#commentNo"+commentNum).val()+"' name='commentNo'>";
           updateRecommentForm+="<input type='hidden' value='${requestScope.recipeNo}' name='commentRecipeNo'>";
           updateRecommentForm+= "</form>";           
            // $(".updateRecommentForm").html("");
            $("#"+"update"+commentNum).html(updateRecommentForm);
          //}
           });//수정 폼
        $(document).on("click","#updateRecomment",function(){
            if($("#updateRecommentContents").val()==""){
               alert("내용입력");
               return;
            }
           $("#updateRecommentForm").submit();
        }); 
           $(document).on("click","#cancelUpdateRecomment",function(){
              $("#registerCommentForm").show();
               for(var i=0;i<commentNoList.length;i++){
                    $("#update"+commentNoList[i]).html($("#contents"+commentNoList[i]).val());
                    $("#recommentUpdateBtn"+commentNoList[i]).show();
                 }
           });
   });//ready   


   </script>
   </head>
   <body>
     <div class="alert alert-dismissable alert-default" contenteditable="true">
              <strong>${requestScope.recipeNo}번 레시피 댓글</strong></div>
          </div>
   
   
    <table >
    <c:forEach items="${requestScope.clvo }" var="commentList" varStatus="status">
         <tr>
             <td>
                       <c:if test="${commentList.commentLevel==0}" ><hr></c:if>                     
               <strong>${commentList.commentNick}</strong>        ${commentList.commentTime}
             <button type="button" class="btn btn-link btn-xs" name="recommentInsertBtn" value="${commentList.commentNo}">답글</button>
             <%--   <button type="button"  name="recommentInsertBtn" value="${commentList.commentNo}">답글</button>  --%>
           <%--   <a href="#" onclick="recommentInsertBtn()" class="${commentList.commentNo}"><i class="fa fa-fw fa-level-up fa-lg fa-rotate-90 text-primary"></i></a> --%>  
                   <%--     <button type="button"  name="recommentInsertBtn" value="${commentList.commentNo}">답글</button>      --%>       
                          <c:if test="${sessionScope.mvo.nick == commentList.commentNick}" >
                           
                           <button type="button" class="btn btn-link btn-xs" name="recommentDeleteBtn" value="${commentList.commentNo}">삭제</button>
                              <%--  <button type="button"  name="recommentDeleteBtn" value="${commentList.commentNo}">삭제</button> --%>
                               <button type="button" class="btn btn-link btn-xs"  id="recommentUpdateBtn${commentList.commentNo}" name="recommentUpdateBtn"  value="${commentList.commentNo}">수정</button>
                               <%-- <button type="button"  id="recommentUpdateBtn${commentList.commentNo}" name="recommentUpdateBtn"  value="${commentList.commentNo}">수정</button> --%>
                               <input type="hidden" value="${commentList.commentContents}" id="commentContents${commentList.commentNo}">  
                          </c:if>
                     
                      <br>   <c:if test="${commentList.commentLevel==1}" >      
                         <p class="text-danger">  @ ${commentList.commentRefNick}</p>  
                      </c:if>
                       <div id="update${commentList.commentNo}" class="updateRecommentForm">  ${commentList.commentContents}</div>
                       <input type="hidden" value="${commentList.commentContents}" id="contents${commentList.commentNo}" >
                                   
                      <div id="${commentList.commentNo}" class="recommentForm"></div>
                      <input type="hidden" value="${commentList.commentNo}" id="commentNo${commentList.commentNo}">
                      <input type="hidden" value="${commentList.commentNick}" id="commentNick${commentList.commentNo}">
                      <input type="hidden" value="${commentList.commentGroup}" id="commentGroup${commentList.commentNo}">
             </td>     
         </tr>
         </div>
      </c:forEach>   
      </table>
      <br><br>
      <div class="col-sm-2">
      <div class="alert alert-dismissable alert-info" contenteditable="true">
      <form action="registerComment.do" method="post" id="registerCommentForm">
     <a href="#" ><i class="fa fa-3x fa-fw fa-user"  style="margin-bottom: 55px" ></i></a>&nbsp;&nbsp;&nbsp;<textarea rows="2.8" cols="20" name="commentContents" id="recipeCommentContents" ></textarea>
       &nbsp; <button type="submit" class="btn btn-info btn-lg"  id="registerCommentBtn" style="margin-bottom: 38px">등록</button>
   <input type="hidden" value="${requestScope.recipeNo }" name="commentRecipeNo">
   </form>
      </div>
       </div>
   </body>

   </html>