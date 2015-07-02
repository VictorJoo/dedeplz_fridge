<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<h3>${sessionScope.mvo.nick} 님의 favoriteList</h3>
${requestScope.result }
<c:choose>
<c:when test="${requestScope.lvo.list.size()!=0}">
<div id="favorite">
<table border="1" style="width: 100%">
      <tr align="center">
         <td width="5%"></td>
         <td width="15%"><b>번호</b></td>
         <td width="80%"><b>레시피</b></td>
         <!-- <td width="15%"></td> -->
      </tr>
   <tbody id="favoriteView" align="center">   
   <c:forEach items="${requestScope.lvo.list }" var="frList" varStatus="status">
      <tr>
         <td><input type="checkbox" id="chkBox" name="chkBox" value="${frList.recipeNo }"></td>
         <td>${status.count }</td>
         <td>${frList.recipeTitle}</td>
         <!-- <td><input type="button"  id="delBtn"  value="삭제"></td> -->
      </tr>

   </c:forEach>
   </tbody>
</table>
<ul class="pagination">

					<c:if test="${requestScope.lvo.pagingBean.previousPageGroup}">
						<li><a
							href="${initParam.root}favoriteRecipeList.do?pageNo=${requestScope.lvo.pagingBean.startPageOfPageGroup-1}">Prev</a>
						</li>
					</c:if>
					<c:forEach var="i"
						begin="${requestScope.lvo.pagingBean.startPageOfPageGroup}"
						end="${requestScope.lvo.pagingBean.endPageOfPageGroup}">
						<c:choose>
							<c:when test="${requestScope.lvo.pagingBean.nowPage!=i}">
								<li><a
									href="${initParam.root}favoriteRecipeList.do?pageNo=${i}">${i}</a>
								</li>
							</c:when>
							<c:otherwise>
								<li><a href="#">${i}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${requestScope.lvo.pagingBean.nextPageGroup}">
						<li><a
							href="favoriteRecipeList.do?pageNo=${requestScope.lvo.pagingBean.endPageOfPageGroup+1}">Next</a>
						</li>
					</c:if>
				</ul>
<br>
<div style="float:right;">
<input type="button" id="allCheckBtn" value="전체선택">
<input type="button" id="resetBtn" value="초기화">
<input type="button" id="delBtn" value="삭제">
</div>
</div>
</c:when>
<c:otherwise>
<br><br>
	<center><h3>등록된 즐겨찾기가 없습니다. ^^</h3></center>
</c:otherwise>
</c:choose>
<br><br><br><br><br>
	<center><img src='${initParam.root}/img/house.JPG' id='houseImg'width='50' height='50'></center>
<br>	<br>