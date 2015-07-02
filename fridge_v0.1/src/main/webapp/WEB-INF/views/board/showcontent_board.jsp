<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- <script>
function updateComment(contents, num) {
   alert(num);
   document.getElementById("updateCon"+num).innerHTML = "<textarea rows='2' style='width: 80%;' id='updateBoardComment"+num+"' name='commentContents'></textarea>";
   document.getElementById("updateBoardComment"+num).value=contents;
}
 function deleteComment(num){
   alert(num);
   location.href="deleteBoardComment.do?commentNo="+num;
}
</script> -->
<br><br>
<div class="section">
      <div class="container">
<div class="col-md-8 " style="position:relative; top:0px; left:200px;">
            <table class="table table-bordered" >
              <tbody>
                <tr>
                  <td colspan="4" class="info text-center">[${vo.category}] ${requestScope.vo.title}</td>
                </tr>
                <tr>
                  <td>글번호: ${requestScope.vo.boardNo}</td>
                  <td>글쓴이: ${requestScope.vo.nick}</td>
                  <td>조회수: ${requestScope.vo.hits }</td>
                  <td>${requestScope.vo.postDate}</td>
                </tr>
                <tr>
                   <td colspan="4">${requestScope.vo.contents }</td>
                </tr>
              </tbody>
              
            </table>
         <c:set var="commentNo" value="0"/>
         <form id="boardCommentForm" >
            <div id="commentList">
               <c:forEach items="${requestScope.bcvo}" var="i">
                  <center>
                  <div class="col-md-10 " style="padding:0px; height:auto; width:600px; margin-left: 50px" id="boardComment" >                          
                     <table class="table " cellpadding="10"  style="table-layout:fixed" >
                     
                        <tbody>
                              <tr>
                                 <c:set var="commentNo" value="${commentNo+1 }" />
                                 <td colspan="2" id="${i.commentNo}CommentContents"><font
                                    size="2">${i.commentContents }</font></td>
                                 <td><c:choose>
                                       <c:when test="${sessionScope.mvo.nick==i.commentNick}">
                                          <button type="button" a class="btn btn-primary"
                                             value="${i.commentNo }" id="updateBoardCommentBtn"
                                             name="updateBoardCommentBtn">수정</button>
                                          <button type="button" a class="btn btn-primary"
                                             value="${i.commentNo }" id="deleteBoardCommentBtn"
                                             name="deleteBoardCommentBtn">삭제</button>
                                       </c:when>
                                       <c:when test="${sessionScope.mvo.level=='6' }">
                                          <button type="button" a class="btn btn-primary"
                                             value="${i.commentNo }" id="deleteBoardCommentBtn"
                                             name="deleteBoardCommentBtn">삭제</button>
                                       </c:when>
                                    </c:choose>                                     
                                 </td>
                              </tr>
                           </tbody>
                         <thead>
                           <tr>
                              <th colspan="2"><b>작성자 ${i.commentNick }</b></th>
                              <th><b>${i.commentTime}</b></th>
                           </tr>
                        </thead>
                     </table>
                  </div>
                     </center>
                <input type="hidden" id="commentNo" name="commentNo" value="${i.commentNo }">
               <input type="hidden" value="${i.commentBoardNo }"  id="commentBoardNo"> 
               <input type="hidden" value="${i.commentContents }" id="commentContents">
               </c:forEach>
            </div>
             <input type="hidden" value="${sessionScope.mvo.nick}" name="commentNick">
             <input type="hidden" value="${sessionScope.mvo.nick}" name="commentRefNick"> 
             <input type="hidden" value="${requestScope.vo.boardNo}"   name="commentBoardNo">
         <c:choose>
            <c:when test="${sessionScope.mvo.nick!=null }">
            <div class="text-center" >
               <textarea rows="2" style="width: 80%; " id="boardComment" name="commentContents"></textarea>
               <input type="button" a class="btn btn-success" value="등록" id="boardCommentBtn" style="margin-bottom: 38px">
            </div>
            </c:when>
</c:choose>
         </form>
         <div class="col-md-12 text-center">
            <a class="btn btn-primary" href="${initParam.root}BoardList.do">목록</a>
  <c:choose>
            <c:when test="${sessionScope.mvo.nick==requestScope.vo.nick }">
       <a class="btn btn-danger" href="updateBoardForm.do?BoardNo=${requestScope.vo.boardNo}">수정 </a>
          <a class="btn btn-danger" href="deleteBoard.do?boardNo=${requestScope.vo.boardNo}">삭제</a>
           </c:when>
            <c:when test="${sessionScope.mvo.level=='6' }">
       <a class="btn btn-danger" href="updateBoardForm.do?BoardNo=${requestScope.vo.boardNo}">수정 </a>
          <a class="btn btn-danger" href="deleteBoard.do?boardNo=${requestScope.vo.boardNo}">삭제</a>
           </c:when>
      </c:choose>
      </div>
          </div></div></div>