<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<center>
<br><br>
<h3>${sessionScope.mvo.nick} 님의 Favorite List</h3>
<br><br>

</center>
${requestScope.result }
<c:choose>
<c:when test="${requestScope.lvo.list.size()!=0}">
<div id="favorite">
<div class="section">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
          <center>
            <table class="table table-bordered table-condensed table-hover table-striped" style="width: 60%">
              <thead>
                <tr>
                  <th class="warning" width="10%">#</th>
                  <th class="warning" width="30%">레시피</th>
                  <th class="warning" width="15%">등록 일자</th>
                </tr>
              </thead>
              <tbody id="favoriteView" align="center">
                 <c:forEach items="${requestScope.lvo.list }" var="frList" varStatus="status">
				      <tr>
				         <td><input type="checkbox" id="chkBox" name="chkBox" value="${frList.recipeNo }"></td>
				         <td>${frList.recipeTitle}</td>
				         <td>${frList.insertDate }</td>
				      </tr>				
				   </c:forEach>
              </tbody>
            </table>
            </center>
          </div>
        </div>
      </div>
    </div>

<div class="col-md-12 text-center">
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
          </div>

<br>
<div class="section">
      <div class="container">
        <div class="row">
          <div class="col-md-12 text-right" >
            <a class="btn btn-primary" id="allCheckBtn">전체선택</a>
            <a class="btn btn-primary" id="resetBtn">초기화</a>
            <a class="btn btn-danger" id="delBtn">삭제</a>
          </div>
        </div>
      </div>
    </div>
</div>
</c:when>
<c:otherwise>
<br><br>
	<center><h3>등록된 즐겨찾기가 없습니다. ^^</h3></center>
</c:otherwise>
</c:choose>
<br><br><br><br><br>
	<%-- <center><img src='${initParam.root}/img/house.JPG' id='houseImg'width='50' height='50'></center> --%>
<br>	<br>