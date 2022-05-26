<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
  <%@include file="./templates/head.jsp" %>
  <script>
    $(document).ready(function () {
      // 회원 가입
      $("#registerBtn").click(function () {
        let user = JSON.stringify({
          id: $("#id").val(),
          password: $("#password").val(),
          name: $("#name").val(),
          email: $("#email").val(),
          age: $("#age").val(),
        });
        //console.log('${root}/user/regist');
        $.ajax({
          url: "${root}/user/regist",
          type: "POST",
          contentType: "application/json;charset=utf-8",
          dataType: "json",
          data: user,
          success: function (user) {
            //console.log(user)
            alert("가입 완료 !");
            $(location).attr("href", "${root}/loginPage");
          },
          error: function (xhr, status, msg) {
            //console.log("상태값 : " + status + " Http에러메시지 : " + msg);
          },
        });
      });
    });
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
              <h2>Sign In</h2>
              <ol class="breadcrumb list-inline text-center">
                <li class="list-inline-item">
                  <a href="index.html">
                    <i class="ion-ios-home"></i>
                    Home &nbsp; &nbsp;/
                  </a>
                </li>
                <li class="active list-inline-item">Sign in</li>
              </ol>
            </div>
          </div>
        </div>
      </div>
    </section>

    <section>
      <main class="container">
        <div class="login-page">
          <div class="form">
            <form
              name="register-form"
              class="register-form"
              method="post"
              action="${root}/user"
            >
              <input type="hidden" name="act" id="act" value="register" />
              <input id="id" name="id" type="text" placeholder="id" />
              <input
                id="password"
                name="password"
                type="password"
                placeholder="password"
              />
              <input
                id="email"
                name="email"
                type="email"
                placeholder="email address"
              />
              <input id="name" name="name" type="text" placeholder="name" />
              <input id="age" name="age" type="number" placeholder="age" />
              <button id="registerBtn" type="button">회원 등록</button>
            </form>
          </div>
        </div>
      </main>
    </section>

    <%@ include file="./templates/footer.jsp"%>
  </body>
</html>
