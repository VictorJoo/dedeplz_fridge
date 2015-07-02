<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<script>
var count= 20;
var maxCount=40;
 function show(){
	 if("${total.recipeNoList < maxCount}"==true){
		 maxCount = "${total.recipeNoList}";
	 }else{
		 maxCount = count+20;
		 if("${total.recipeNoList}" < maxCount){
			var test = document.getElementById("showBtn");
			test.innerHTML=" ";
		 }
	 }
	 
	 for (var i = count; i < maxCount; i++) {
	     var recipe = document.getElementById("recipe"+i);
	     recipe.style.display='';
		}
	 count = count+20;

   }
</script>


<form action="searchRecipe.do">
	<div class="form-group">
		<div class="input-group">
			<input type="text" class="form-control" placeholder="Enter your items" name="items"  style="background-color: #e2e2e2;"> 
			<span class="input-group-btn"> 
			<button class="btn btn-primary" type="submit">
			<i class="fa fa-fw fa-spoon"></i>search</button>
			</span>
		</div>
	</div>
</form>

	<c:if test="${total.recipeNoList==0}">
	<div class="cover">
		<div class="cover-image"></div>
		<div class="container">
			<div class="row">
				<div class="col-md-12 text-center">
					<br>				<br>				<br>				<br>
					<h2><strong>선택하신 메뉴에 대한 RECIPE가 없습니다.</strong></h2>
					<br>
					<h4>
						<br>자신만의 새로운 RECIPE를 만들어 보세요!
					</h4>
					<br> 
						<c:choose>
								<c:when test="${sessionScope.mvo==null}">
								 <a class="btn btn-lg btn-success" href="#"  onclick="loginCheck();return false;">YOURs NEW RECIPE!</a>
								</c:when>
								<c:otherwise>
								<a class="btn btn-lg btn-success" href="${initParam.root}registerRecipeForm.do">YOURs NEW RECIPE!</a>
								</c:otherwise>
							</c:choose>
						<br><br><br><br><br><br><br>
				</div>
			</div>
		</div>
	</div>

	</c:if>
	<!-- 인기도 top3 요리 목록 -->
	<c:set var="topCount" value="0"/>
	<c:forEach var="topPath" items="${requestScope.total.topFileInfo}">
	<div class="col-md-4 col-sm-6" style=" padding: 0px; height: auto;">
		<a href="#portfolioModal1" class="portfolio-box" data-toggle="modal" onclick="testAlert('${topPath.fileLastPath}')" > 
		<img src="${topPath.fileLastPath}" class="img-responsive" style="width: 500px; height: 300px;">
		<c:set var="topCount" value="${topCount+1 }"/>
			<div class="portfolio-best-caption">
				 <img src="img/best${topCount }.jpg" width="80"/> 
			</div>
				<div class="portfolio-box-caption">
					<div class="portfolio-box-caption-content">
						<div class="project-category text-faded">
						${topCount }위 <br>
						${topPath.rvo.nick }님의 recipe
						</div>
						<div class="project-name">${topPath.rvo.title}
						</div>
						<div>
						<br>
						 <img src="img/comment.jpg"> ${topPath.commentCount}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 <img src="img/love.jpg"> ${topPath.goodPoint}
						
					</div>
				</div>
			</div>
		</a> 
	</div>
</c:forEach>

	<!--요리 목록 -->
<c:set var="recipeCount" value="19" />
<c:set var="count" value="0"/>
<c:forEach var="path" items="${requestScope.total.filePath}">
	<c:choose>
		<c:when test="${count<20}">
			<div class="col-md-3 col-sm-6" id="recipe${count}" style="padding: 0px;">
		</c:when>
		<c:otherwise>
			<div class="col-md-3 col-sm-6" id="recipe${count}" style="display: none; padding: 0px">
		</c:otherwise>
	</c:choose>
	

	<c:set var="count" value="${count+1}"/>
		<a href="#portfolioModal1" class="portfolio-box" data-toggle="modal" onclick="testAlert('${path.fileLastPath}')" > 
		<img src="${path.fileLastPath}" class="img-responsive" style="width: 350px; height: 220px;">
			<div class="portfolio-box-caption">
				<div class="portfolio-box-caption-content">
					<div class="project-category text-faded">
					${path.rvo.nick }님의	recipe
					</div>
					<div class="project-name">${path.rvo.title}
					</div>
					<div>
					<br>
					<img src="img/comment.jpg"> ${path.commentCount} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<img src="img/love.jpg"> ${path.goodPoint}
					</div>
				</div>
			</div>
		</a> 
	</div>
</c:forEach>

<c:if test="${recipeCount < count}">
	<center>
		<div class="row" >
          <div class="col-md-12" id= "showBtn">
          <br><br><br>
          <input type="image" src="img/showMore.jpg" onclick="show(${recipeCount})">		
          <br><br><br>	
          </div>
        </div>
	</center>
</c:if>



<!-- showcontent -->
<div class="portfolio-modal modal fade" id="portfolioModal1" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-content">
		<div class="close-modal" data-dismiss="modal">
                <div class="lr">
                    <div class="rl">
                    </div>
                </div>
		</div>
		
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-lg-offset-2">
					<div class="modal-body" id="gogo">
						
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

