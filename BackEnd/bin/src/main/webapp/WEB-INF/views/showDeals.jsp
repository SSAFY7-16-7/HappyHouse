<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<%@include file="./templates/head.jsp"%>


<body>
	<%@include file="./templates/header.jsp"%>

	<%@  include file="./searchBar.jsp"%>
	<section id="service-page" class="pages service-page">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="block search_res">
						<div id="searchInfoDiv"></div>
					</div>
				</div>
			</div>
			<div class="row">
				 <div class="col-lg-8 order-1 order-lg-1">
					<div id="map" style="width:100%;height:500px;"></div>
					<script type="text/javascript" src="${root}/js/map.js"></script>
				</div> 
				<div class="search_apt_list col-lg-4 order-2 order-lg-2">
					<div class="block" id="dealsDiv">
						<c:if test="${!empty deals}">
							<c:forEach var="deal" items="${deals}">
								<div class="row service-parts">
									<div class="col-md-12 info-card">
											<div class="info" data-wow-duration="400ms" data-wow-delay="600ms">
												<input type="hidden" name="aptCode" value="${deal.aptCode}"/>
												<h5 class='apt_title'>${deal.aptName}</h5>
												<div class='apt_size'> ${deal.area}평 / ${deal.floor}층 </div>
												<div class='price'>거래 금액:${deal.dealAmount} 만원</div>
												<div class='apt_size'>위치 : ${deal.dongName} ${deal.jibun}</div>
												<div class='apt_deal_date'>거래 날짜 : ${deal.dealYear}.${deal.dealMonth}.${deal.dealDay}</div>
											</div>
										</div>
								</div>
							</c:forEach>

						</c:if>
					</div>
			</div>
		</div>
		</div>
		<div id="pagination-container">${pageBean.pageLink}</div>
	</section>
	<%@ include file="./templates/footer.jsp"%>
</body>
</html>