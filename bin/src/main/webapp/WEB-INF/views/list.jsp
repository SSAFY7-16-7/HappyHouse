<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<%@include file="./templates/head.jsp" %>
<script>
 
 $(document).ready(function(){
		//회원 목록
		$.ajax({
			url:'${root}/user/user',  
			type:'GET',
			contentType:'application/json;charset=utf-8',
			dataType:'json',
			success:function(users) {
				console.log(users);
				makeList(users);
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
		
		$(document).on("click", ".modiBtn", function() {
			let mid = $(this).parents("tr").attr("data-id");
			console.log(mid);
			let user = JSON.stringify({
				"id" : mid,
				"password" : $("#userpwd"+mid).val(),
				"name" : $("#username"+mid).val(),
				"email" : $("#useremail"+mid).val(),
				"age" : $("#userage"+mid).val(),
					   }); 
			console.log(user);
			$.ajax({
				url : '${root}/admin/update',
				type : 'PUT',
				contentType : 'application/json;charset=utf-8',
				dataType : 'json',
				data : user,
				success : function(user) {
				console.log(user);
					alert("정보 수정 완료 !");
					location.reload(); 
					
				},
				error : function(xhr, status, msg) {
					console.log("상태값 : " + status + " Http에러메시지 : " + msg);
				}
			});
		});

	
		// 회워 탈퇴.
		$(document).on("click", ".delBtn", function() {
				let id = $(this).parents("tr").attr("data-id");
				$.ajax({
					url:'${root}/admin/remove', 
					type:'DELETE',
					contentType : 'application/text;charset=utf-8',
					dataType : 'text',
					data : id,
					success:function(users) {
						alert("회원 삭제 완료 !");
						location.reload();
					},
					error:function(xhr,status,msg){
						console.log("상태값 : " + status + " Http에러메시지 : "+msg);
					}
				});
		});
		
		function makeList(users){
			$("#userlist").empty();
			$(users).each(function(index, user) {
				if(user.id=='admin') return true;
				let str = `
					<tr id="user_${'${user.id}'}" class="view" data-id="${'${user.id}'}">
						<td >${'${user.id}'}</td>
						<td ><input id="userpwd${'${user.id}'}" value="${'${user.password}'}" size=10/></td>
						<td ><input id="username${'${user.id}'}" value="${'${user.name}'}" size=10/></td>
						<td ><input id="useremail${'${user.id}'}" value="${'${user.email}'}" size=10/></td>
						<td ><input id="userage${'${user.id}'}" value="${'${user.age}'}" size=10/></td>
						<td >
							<button type="button" class="modiBtn btn btn-outline-primary btn-sm">수정</button>
							<button type="button" class="delBtn btn btn-outline-danger btn-sm">삭제</button>
						</td>
					</tr>
		/* 			<tr id="mview_${'${user.id}'}" data-id="${'${user.id}'}" style="display: none;">
						<td>${'${user.id}'}</td>
						<td><input type="text" name="password" id="userpwd${'${user.id}'}" value="${'${user.password}'}"></td>
						<td>${'${user.name}'}</td>
						<td><input type="text" name="email" id="email${'${user.id}'}" value="${'${user.email}'}"></td>
						<td>${'${user.age}'}</td>
						<td>
							<button type="button" class="modifyBtn btn btn-primary btn-sm">수정</button>
							<button type="button" class="cancelBtn btn btn-danger btn-sm">취소</button>
						</td>
					</tr> */
					`;
					$("#userlist").append(str);
				});//each
			
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
              <h2>회원 관리</h2>
              <ol class="breadcrumb list-inline text-center">
                <li class="list-inline-item">
                  <a href="index.html">
                    <i class="ion-ios-home"></i>
                    Home &nbsp; &nbsp;/
                  </a>
                </li>
                <li class="active list-inline-item">회원 관리</li>
              </ol>
            </div>
          </div>
        </div>
      </div>
    </section>

	<section>
		<main class="container">
		 <div class="col-lg-12 mx-auto">
            <h2 class="p-3 mb-3 shadow bg-light"><mark class="pink">회원목록</mark></h2>
		  	<table class="table table-hover">
		  		<colgroup>
		            <col width="120">
		            <col width="120">
		            <col width="120">
		            <col width="170">
		            <col width="120">
		            <col width="130">
		        </colgroup>
		    	<thead>
			      	<tr>
			        	<th class="text-center">아이디</th>
			        	<th class="text-center">비밀번호</th>
			        	<th class="text-center">이름</th>
			        	<th class="text-center">이메일</th>
			        	<th class="text-center">나이</th>
			        	<th class="text-center">수정/삭제</th>
			      	</tr>
		    	</thead>
		    	<tbody id="userlist"></tbody>
			</table>
		</div>
		</main>
	</section>



	<%@ include file="./templates/footer.jsp"%>
</body>
</html>