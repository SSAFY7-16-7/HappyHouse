<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<%@include file="./templates/head.jsp" %>
<script>
 
 $(document).ready(function(){
	 let userid = "${userInfo.id}";
		//관심 아파트 목록 
		$.ajax({
			url:'${root}/user/likeList/'+userid,  
			type:'GET',
			contentType:'application/json;charset=utf-8',
			dataType:'json',
			success:function(data) {
				makeAptList(data);
			},
			error:function(xhr, status, error){
				console.log("상태값 : " + xhr.status + "\tHttp 에러메시지 : " + xhr.responseText);
			},
			statusCode: {
				500: function() {
					alert("서버에러.");
					// location.href = "/error/e500";
				},
				404: function() {
					alert("페이지없다.");
					// location.href = "/error/e404";
				}
			}	
		});

		// 관심 목록에서 삭제 
		$(document).on("click", ".delBtn", function() {
				let aptCode = $(this).parents("tr").attr("id");
				let info = JSON.stringify({
					"user_id": "${userInfo.id}",
					"apt_code": aptCode
				})
		
				$.ajax({
					url:'${root}/user/likeList/'+aptCode, 
					type:'DELETE',

				
					success:function() {
						alert(" 삭제 완료 !");
						location.reload();
					},
					error:function(xhr,status,msg){
						console.log("상태값 : " + status + " Http에러메시지 : "+msg);
					}
				}); 
		});
		
		function makeAptList(list){
			$("#aptlist").empty();
			$(list).each(function(index,apt){
				let str = `
					<tr id="${'${apt.aptCode}'}" class="view" >
						<td >${'${apt.aptName}'}</td>
						<td >${'${apt.dongName}'}</td>
						<td >${'${apt.jibun}'}</td>
						<td >${'${apt.buildYear}'}</td>
						<td >
							<button type="button" class="delBtn btn btn-outline-danger btn-sm">삭제</button>
						</td>
					</tr>
	
					`;
					$("#aptlist").append(str); 
				
			});

			
		}
 })
 
 </script>
 
<body>
  <%@include file="./templates/header.jsp" %>
  

    <!-- 
        ================================================== 
            Global Page Section Start
        ================================================== -->
    <section class="global-page-header">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <div class="block">
              <h2>관심 아파트</h2>
              <ol class="breadcrumb list-inline text-center">
                <li class="list-inline-item">
                  <a href="index.html">
                    <i class="ion-ios-home"></i>
                    Home &nbsp; &nbsp;/
                  </a>
                </li>
                <li class="active list-inline-item">관심 아파트</li>
              </ol>
            </div>
          </div>
        </div>
      </div>
    </section>

	<section>
		<main class="container">
		 <div class="col-lg-12 mx-auto">
            <h2 class="p-3 mb-3 shadow bg-light"><mark class="pink">관심 아파트 목록 </mark></h2>
		  	<table class="table table-hover">
		  		<colgroup>
		            <col width="120">
		            <col width="120">
		            <col width="120">
		            <col width="120">
		            <col width="130">
		        </colgroup>
		    	<thead>
			      	<tr>
			        	<th class="text-center">아파트 이름</th>
			        	<th class="text-center">동</th>
			        	<th class="text-center">지번</th>
			        	<th class="text-center">건축 연도</th>
			        	<th class="text-center">삭제</th>
			      	</tr>
		    	</thead>
		    	<tbody id="aptlist"></tbody>
			</table>
		</div>
		</main>
	</section>



	<%@ include file="./templates/footer.jsp"%>
</body>
</html>