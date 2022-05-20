<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
						<h2 class="res_info">검색 결과: 서울시 동대문구 휘경동</h2>
						<button class="res_btn">관심 지역 등록</button>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-8 order-1 order-lg-1">
					<div id="map" style="width: 500px; height: 500px;"></div>
				</div>
				<div class="search_apt_list col-lg-4 order-2 order-lg-2">
					<div class="block">
						<c:if test="${!empty deals}">
							<c:forEach var="deal" items="${deals}">
								<div class="row service-parts">
									<div class="col-md-12 info-card">
										<div class="info" data-wow-duration="400ms"
											data-wow-delay="600ms">
														<h4 class='apt_title'>${deal.aptName}</h4>
											<div class='price'>거래 금액:${deal.dealAmout}</div>
											<div class='apt_size'>면적 : ${deal.dealArea}</div>
											<div class='apt_deal_date'>거래 날짜 :
												${deal.dealYear}.${deal.dealMonth}.${deal.dealDay}</div>
									
										</div>
									</div>
								</div>
							</c:forEach>
						</c:if>
					</div>
				</div>
			</div>
		</div>

	</section>
	<%@ include file="./templates/footer.jsp"%>
</body>
</html>