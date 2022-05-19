<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html class="no-js">
<%@include file="./templates/head.jsp" %>
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
            <div id="map" style="width: 100%; height: 100%"></div>
          </div>
        </div>
      </div>
    </section>
    <!-- /#about -->

    <!--
==================================================
Portfolio Section Start
================================================== -->
    <section id="works" class="works">
      <div class="container">
        <div class="section-heading">
          <h1 class="title wow fadeInDown" data-wow-delay=".3s">
            Latest Works
          </h1>
        </div>
        <div class="row">
          <div class="col-md-4 col-sm-6">
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
                />
                <div class="overlay">
                  <div class="buttons">
                    <a
                      rel="gallery"
                      class="fancybox"
                      href="images/portfolio/item-1.jpg"
                      >Demo</a
                    >
                    <a target="_blank" href="showDeals.html">Details</a>
                  </div>
                </div>
              </div>
              <figcaption>
                <h4>
                  <a href="#"> 서울 </a>
                </h4>
                <p>은마아파트</p>
              </figcaption>
            </figure>
          </div>
          <div class="col-md-4 col-sm-6">
            <figure
              class="wow fadeInLeft animated"
              data-wow-duration="500ms"
              data-wow-delay="300ms"
            >
              <div class="img-wrapper">
                <img
                  src="images/portfolio/item-2.jpg"
                  class="img-fluid"
                  alt="this is a title"
                />
                <div class="overlay">
                  <div class="buttons">
                    <a
                      rel="gallery"
                      class="fancybox"
                      href="images/portfolio/item-2.jpg"
                      >Demo</a
                    >
                    <a target="_blank" href="showDeals.html">Details</a>
                  </div>
                </div>
              </div>
              <figcaption>
                <h4>
                  <a href="#"> 경기 </a>
                </h4>
                <p>청명마을동신아파트</p>
              </figcaption>
            </figure>
          </div>
          <div class="col-md-4 col-sm-6">
            <figure
              class="wow fadeInLeft animated"
              data-wow-duration="500ms"
              data-wow-delay="300ms"
            >
              <div class="img-wrapper">
                <img
                  src="images/portfolio/item-3.jpg"
                  class="img-fluid"
                  alt=""
                />
                <div class="overlay">
                  <div class="buttons">
                    <a
                      rel="gallery"
                      class="fancybox"
                      href="images/portfolio/item-3.jpg"
                      >Demo</a
                    >
                    <a target="_blank" href="showDeals.html">Details</a>
                  </div>
                </div>
              </div>
              <figcaption>
                <h4>
                  <a href="#"> 대전 </a>
                </h4>
                <p>대전도안아이파크아파트</p>
              </figcaption>
            </figure>
          </div>
          <div class="col-md-4 col-sm-6">
            <figure
              class="wow fadeInLeft animated"
              data-wow-duration="500ms"
              data-wow-delay="600ms"
            >
              <div class="img-wrapper">
                <img
                  src="images/portfolio/item-4.jpg"
                  class="img-fluid"
                  alt=""
                />
                <div class="overlay">
                  <div class="buttons">
                    <a
                      rel="gallery"
                      class="fancybox"
                      href="images/portfolio/item-4.jpg"
                      >Demo</a
                    >
                    <a target="_blank" href="showDeals.html">Details</a>
                  </div>
                </div>
              </div>
              <figcaption>
                <h4>
                  <a href="#"> 대구 </a>
                </h4>
                <p>영남네오빌아파트</p>
              </figcaption>
            </figure>
          </div>
          <div class="col-md-4 col-sm-6">
            <figure
              class="wow fadeInLeft animated"
              data-wow-duration="500ms"
              data-wow-delay="900ms"
            >
              <div class="img-wrapper">
                <img
                  src="images/portfolio/item-5.jpg"
                  class="img-fluid"
                  alt=""
                />
                <div class="overlay">
                  <div class="buttons">
                    <a
                      rel="gallery"
                      class="fancybox"
                      href="images/portfolio/item-5.jpg"
                      >Demo</a
                    >
                    <a target="_blank" href="showDeals.html">Details</a>
                  </div>
                </div>
              </div>
              <figcaption>
                <h4>
                  <a href="#"> 부산 </a>
                </h4>
                <p>더샵파크시티아파트</p>
              </figcaption>
            </figure>
          </div>
          <div class="col-md-4 col-sm-6">
            <figure
              class="wow fadeInLeft animated"
              data-wow-duration="500ms"
              data-wow-delay="1200ms"
            >
              <div class="img-wrapper">
                <img
                  src="images/portfolio/item-6.jpg"
                  class="img-fluid"
                  alt=""
                />
                <div class="overlay">
                  <div class="buttons">
                    <a
                      rel="gallery"
                      class="fancybox"
                      href="images/portfolio/item-6.jpg"
                      >Demo</a
                    >
                    <a target="_blank" href="showDeals.html">Details</a>
                  </div>
                </div>
              </div>
              <figcaption>
                <h4>
                  <a href="#"> 전북 </a>
                </h4>
                <p>전주효천대방노블랜드에코파크</p>
              </figcaption>
            </figure>
          </div>
        </div>
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
