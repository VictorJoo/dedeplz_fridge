<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<script>
	var page =0;
 function showMore(count) {
	count = count+20;
	var pageNo = page+1;
	$("#showMore"+page).html("<input type='button' value='더보기' onclick='showMore("+count+")'>"
			+"<div id=showMore"+pageNo+"></div>");		
	page = page + 1;
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
	
	<!--요리 목록 -->
<c:set var="recipeCount" value="19" />
<c:set var="count" value="0"/>
<c:forEach var="path" items="${requestScope.fileLastNamePath}"	end="${recipeCount}">
	<div class="col-md-3 col-sm-6">
		<a href="#portfolioModal1" class="portfolio-box" data-toggle="modal" onclick="testAlert('${path.fileLastPath}')"> 
		<img src="${path.fileLastPath}" class="img-responsive" style="width: 350px; height: 220px;">
			<div class="portfolio-box-caption">
				<div class="portfolio-box-caption-content">
					<div class="project-category text-faded">
					${path.rvo.nick }님의	요리
					</div>
					<div class="project-name">${path.rvo.title }
					</div>
					조회수 : ${path.rvo.hits }
				</div>
			</div>
		</a> <br>
	</div>
	<c:set var="count" value="${path.count}"/>
</c:forEach>

<c:if test="${recipeCount < count}">
	<center>

	<input type="button" value="더보기" id="showMoreRecipe" onclick="showMore(${recipeCount})" >

	</center>
</c:if>
<div id="showMore0">

</div>


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

