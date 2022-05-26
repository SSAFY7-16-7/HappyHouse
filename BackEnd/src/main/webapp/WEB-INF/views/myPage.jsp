<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
  <%@include file="./templates/head.jsp"%>

  <script>
    $(document).ready(function () {
      //console.log("mypage");

      // 회원 정보 수정
      $("#send-btn").click(function () {
        let user = JSON.stringify({
          id: $("#id").val(),
          password: $("#password").val(),
          name: $("#name").val(),
          email: $("#email").val(),
          age: $("#age").val(),
        });
        //console.log(user);
        //console.log('${root}/user/update');
        $.ajax({
          url: "${root}/user/update",
          type: "PUT",
          contentType: "application/json;charset=utf-8",
          dataType: "json",
          data: user,
          success: function (user) {
            //console.log(user)
            $("#name").val(user.name);
            $("#id").val(user.id);
            $("#password").val(user.password);
            $("#age").val(user.age);
            $("#email").val(user.email);
            alert("정보 수정 완료 !");
          },
          error: function (xhr, status, msg) {
            //console.log("상태값 : " + status + " Http에러메시지 : " + msg);
          },
        });
      });

      //회원 탈퇴
      $("#delete-btn").click(function () {
        let id = $("#id").val();
        //console.log(id);
        //console.log('${root}/user/remove');
        $.ajax({
          url: "${root}/user/remove",
          type: "DELETE",
          contentType: "application/text;charset=utf-8",
          dataType: "text",
          data: id,
          success: function () {
            //console.log("회원 탈퇴");
            alert("탈퇴 완료 !");
            $(location).attr("href", "${root}${root}");
          },
          error: function (xhr, status, msg) {
            //console.log("상태값 : " + status + " Http에러메시지 : " + msg);
          },
        });
      });
    });
  </script>
  <body>
    <%@include file="./templates/header.jsp"%>

    <!-- 
        ================================================== 
            Global Page Section Start
        ================================================== -->
    <section class="global-page-header">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <div class="block">
              <h2>My Page</h2>
              <ol class="breadcrumb list-inline text-center">
                <li class="list-inline-item">
                  <a href="index.html">
                    <i class="ion-ios-home"></i> Home &nbsp; &nbsp;/
                  </a>
                </li>
                <li class="active list-inline-item">My Page</li>
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
              action=""
            >
              <input type="hidden" name="act" id="act" value="modify" />
              <input
                id="id"
                name="id"
                value="${userInfo.id}"
                type="text"
                placeholder="id"
              />
              <input
                id="password"
                value="${userInfo.password}"
                name="password"
                type="password"
                placeholder="password"
              />
              <input
                id="email"
                value="${userInfo.email}"
                name="email"
                type="email"
                placeholder="email address"
              />
              <input
                id="name"
                name="name"
                value="${userInfo.name}"
                type="text"
                placeholder="name"
              />
              <input
                id="age"
                name="age"
                value="${userInfo.age}"
                type="text"
                placeholder="age"
              />
              <button id="send-btn" type="button">정보 수정</button>
              <button id="delete-btn" type="button">회원 탈퇴</button>
            </form>
          </div>
        </div>
      </main>
    </section>

    <%@ include file="./templates/footer.jsp"%>
  </body>
</html>
