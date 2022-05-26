<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix ="c" uri
="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html class="no-js">
  <%@include file="./templates/head.jsp" %>
  <script>
    $(document).ready(function () {
      $.ajax({
        url: "${root}/user/likeCntList",
        type: "GET",
        contentType: "application/json;charset=utf-8",
        success: function (list) {
          //console.log(list);
          makeList(list);
        },
        error: function (xhr, status, error) {
          //console.log("상태값 : " + xhr.status + "\tHttp 에러메시지 : " + xhr.responseText);
        },
        statusCode: {
          500: function () {
            alert("서버에러.");
            // location.href = "/error/e500";
          },
          404: function () {
            alert("페이지없다.");
            // location.href = "/error/e404";
          },
        },
      });

      $.ajax({
        url: "${root}/random",
        type: "GET",
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function (data) {
          //console.log(data);

          $("#aptName").text(data.aptName);
          $("#price").text(data.dealAmount);
          $("#location").text(data.dongName);
        },
        error: function (xhr, status, msg) {
          //console.log("상태값 : " + status + " Http에러메시지 : " + msg);
        },
      });

      $(document).on("click", "#randBtn", function () {
        $.ajax({
          url: "${root}/random",
          type: "GET",
          contentType: "application/json;charset=utf-8",
          dataType: "json",
          success: function (data) {
            //console.log(data);

            var src =
              "images/portfolio/item-" +
              (Math.floor(Math.random() * 6) + 1) +
              ".jpg";

            $("#aptName").text(data.aptName);
            $("#price").text(data.dealAmount);
            $("#location").text(data.dongName);
            $("#aptImg").attr("src", src);
          },
          error: function (xhr, status, msg) {
            //console.log("상태값 : " + status + " Http에러메시지 : " + msg);
          },
        });
      });

      function makeList(list) {
        $("#likeRank").empty();
        $(list).each(function (index, item) {
          let str = `
				<tr id="" class="view">
				<td class="rank-td">${"${item.cnt}"}</td>
					<td class="rank-td">${"${item.aptName}"}</td>
					<td class="rank-td" >${"${item.dongName}"}</td>
					<td class="rank-td">${"${item.buildYear}"}</td>
					<td class="rank-td">${"${item.jibun}"}</td>
				</tr>
				`;
          $("#likeRank").append(str);
        }); //each
      }
    });
  </script>
  <body>
    <%@include file="./templates/header.jsp" %>

    <!-- ================================================== 
    Slider Section Start
    ================================================== -->
    <section id="hero-area">
      <div class="container">
        <div class="row">
          <div class="col-md-12 text-center">
            <div class="block wow fadeInUp" data-wow-delay=".3s">
              <!-- Slider -->
              <section class="cd-intro">
                <h1
                  class="wow fadeInUp animated cd-headline slide"
                  data-wow-delay=".4s"
                >
                  <span>HAPPY HOUSE</span><br />
                  <span class="cd-words-wrapper">
                    <b class="is-visible">행복한 우리 집</b>
                    <b>AHN JIYOUNG</b>
                    <b>PARK YAEJIN</b>
                  </span>
                </h1>
              </section>
              <!-- cd-intro -->
              <!-- /.slider -->
            </div>
          </div>
        </div>
      </div>
    </section>
    <!--/#main-slider-->

    <!-- 
        ================================================== 
            Global Page Section Start
        ================================================== -->
    <%@include file="searchBar.jsp" %>

    <!--
==================================================
About Section Start
================================================== -->
    <section id="about">
      <div class="container">
        <div class="row">
          <div class="map_area col">
            <div id="map" style="width: 100%; height: 500px"></div>
            <script type="text/javascript" src="${root}/js/map.js"></script>
          </div>
        </div>
      </div>
    </section>
    <!-- /#about -->

    <!--
==================================================
Random Apt Section Start
================================================== -->
    <section id="works" class="works">
      <div class="section-heading">
        <h1
          class="title wow fadeInDown"
          data-wow-delay=".3s"
          style="margin-top: 30px"
        >
          Today House
        </h1>
        <button id="randBtn" class="btn btn-info">다음 추천</button>
      </div>
      <div class="container" style="display: flex; justify-content: center">
        <div class="col-md-7 col-sm-5">
          <figure
            class="wow fadeInLeft animated portfolio-item"
            data-wow-duration="500ms"
            data-wow-delay="0ms"
          >
            <div class="img-wrapper">
              <img
                src="images/portfolio/item-1.jpg"
                class="img-fluid"
                alt="this is a title"
                id="aptImg"
              />
              <div class="overlay">
                <div class="buttons">
                  <a target="_blank" href="showDeals.html">구경하기</a>
                </div>
              </div>
            </div>
            <figcaption>
              <h4>
                <a href="#"> <span id="aptName">은마아파트 </span></a>
              </h4>

              <p>
                <span id="price">15,000</span>만원 /
                <span id="location">역삼동</span>
              </p>
            </figcaption>
          </figure>
        </div>
      </div>
    </section>

    <!--
==================================================
Portfolio Section Start
================================================== -->
    <section id="works" class="works">
      <h2>사람들이 많이 찜한 아파트</h2>
      <div class="tbcont">
        <table class="table rank-table">
          <colgroup>
            <col width="100" />
            <col width="240" />
            <col width="110" />
            <col width="110" />
            <col width="120" />
          </colgroup>
          <thead>
            <tr>
              <th class="text-center rank-th">찜한 사람(명)</th>
              <th class="text-center rank-th">아파트 이름</th>
              <th class="text-center rank-th">동</th>
              <th class="text-center rank-th">건축 연도</th>
              <th class="text-center rank-th">지번</th>
            </tr>
          </thead>
          <tbody id="likeRank"></tbody>
        </table>
      </div>
    </section>
    <!-- #works -->
    <!--
==================================================
Portfolio Section Start
================================================== -->
    <section id="feature">
      <div class="container">
        <div class="row">
          <div class="col-sm-6 col-lg-4">
            <div
              class="media wow fadeInUp animated"
              data-wow-duration="500ms"
              data-wow-delay="300ms"
            >
              <div class="media-left">
                <div class="icon">
                  <i class="ion-ios-flask-outline"></i>
                </div>
              </div>
              <div class="media-body">
                <h4 class="media-heading">공지사항</h4>
                <h7>● 행복한 우리 집 5조의 Happy House입니다 !</h7>
              </div>
            </div>
          </div>
          <div class="col-sm-6 col-lg-4">
            <div
              class="media wow fadeInDown animated"
              data-wow-duration="500ms"
              data-wow-delay="600ms"
            >
              <div class="media-left">
                <div class="icon">
                  <i class="ion-ios-lightbulb-outline"></i>
                </div>
              </div>
              <div class="media-body">
                <h4 class="media-heading">자유게시판</h4>
                <h7>● 너무 좋은 정보 많이 알아갑니다 ! 감사합니다 !</h7>
              </div>
            </div>
          </div>
          <div class="col-sm-6 col-lg-4">
            <div
              class="media wow fadeInDown animated"
              data-wow-duration="500ms"
              data-wow-delay="900ms"
            >
              <div class="media-left">
                <div class="icon">
                  <i class="ion-ios-lightbulb-outline"></i>
                </div>
              </div>
              <div class="media-body">
                <h4 class="media-heading">Q &amp; A</h4>
                <h7>● 로그인이 안됩니다 !</h7>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- /#feature -->

    <!--
            ==================================================
            Footer Section Start
            ================================================== -->
    <%@ include file="./templates/footer.jsp" %>
  </body>
</html>
