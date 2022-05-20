<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix ="c" uri ="http://java.sun.com/jsp/jstl/core" %>
    <c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<%@include file="./templates/head.jsp" %>
 <script>
 
 $(document).ready(function(){
	 console.log("hello");
	 
		$("#login-btn").click(function() {
			let info = JSON.stringify({
				"id" : $("#id").val(), 
				"password" : $("#password").val(), 
				
			   });
			console.log(info);
			console.log('${root}/user/login');
			$.ajax({
				url:'${root}/user/login',  
				type:'POST',
				contentType:'application/json;charset=utf-8',
				dataType:'json',
				data: info,
				success:function(user) {
					$(location).attr("href","${root}${root}")
				},
				error:function(xhr,status,msg){
					console.log("상태값 : " + status + " Http에러메시지 : "+msg);
				}
			});
		});
	 
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
              <h2>Login</h2>
              <ol class="breadcrumb list-inline text-center">
                <li class="list-inline-item">
                  <a href="index.html">
                    <i class="ion-ios-home"></i>
                    Home &nbsp; &nbsp;/
                  </a>
                </li>
                <li class="active list-inline-item">Login</li>
              </ol>
            </div>
          </div>
        </div>
      </div>
    </section>
  
   <section class="works service-page">
      <div class="container">
        <div class="row">
          <div class="login-page">
            <div class="form">
              <form name="login-form" class="login-form" method='post' action="${root}/user/login ">
                <input id="id" name="id" type="text" placeholder="ID" />
                
                <input
                  id="password"
                  name="password"
                  type="password"
                  placeholder="password"/>
                <button class="login-btn"  id="login-btn" type="button"> 로그인 </button>
           
              </form>
            </div>
          </div>
        </div>
      </div>
    </section>
  
  
   <%@ include file="./templates/footer.jsp" %>
</body>
</html>