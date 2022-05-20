<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix ="c" uri ="http://java.sun.com/jsp/jstl/core" %>
  <head>
    <!-- Basic Page Needs
        ================================================== -->
    <meta charset="utf-8" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="icon" href="favicon.ico" />
    <title>Timer Agency Template</title>
    <meta name="description" content="" />
    <meta name="keywords" content="" />
    <meta name="author" content="" />
    <!-- Mobile Specific Metas
        ================================================== -->
    <meta name="format-detection" content="telephone=no" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />

    <!-- Template CSS Files
        ================================================== -->
    <!-- Twitter Bootstrs CSS -->
    <link rel="stylesheet" href="plugins/bootstrap/bootstrap.min.css" />
    <!-- Ionicons Fonts Css -->
    <link rel="stylesheet" href="plugins/ionicons/ionicons.min.css" />
    <!-- animate css -->
    <link rel="stylesheet" href="plugins/animate-css/animate.css" />
    <!-- Hero area slider css-->
    <link rel="stylesheet" href="plugins/slider/slider.css" />
    <!-- slick slider -->
    <link rel="stylesheet" href="plugins/slick/slick.css" />
    <!-- Fancybox -->
    <link rel="stylesheet" href="plugins/facncybox/jquery.fancybox.css" />
    <!-- hover -->
    <link rel="stylesheet" href="plugins/hover/hover-min.css" />
    <!-- template main css file -->
    <link rel="stylesheet" href="css/style.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script
      type="text/javascript"
      src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9759882258926d578dc7e0091446f7e3"
    ></script>

    <script>
      $(function () {
        var mapContainer = document.getElementById('map'), // 지도를 표시할 div
          mapOption = {
            center: new kakao.maps.LatLng(
              37.501329755121944,
              127.03965486430393
            ), // 지도의 중심좌표
            level: 6, // 지도의 확대 레벨
          };

        var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

        // 마커가 표시될 위치입니다
        var markerPosition = new kakao.maps.LatLng(
          37.501329755121944,
          127.03965486430393
        );

        var positions = [
          {
            title: '브라운스톤아파트',
            latlng: new kakao.maps.LatLng(
              37.49575103711257,
              127.03794677097643
            ),
          },
          {
            title: '역삼레미안아파트',
            latlng: new kakao.maps.LatLng(
              37.497458772286315,
              127.04992944035217
            ),
          },
          {
            title: '역삼대우푸르지오아파트',
            latlng: new kakao.maps.LatLng(
              37.49773966932328,
              127.04675370513647
            ),
          },
          {
            title: '멀티캠퍼스',
            latlng: new kakao.maps.LatLng(
              37.501329755121944,
              127.03965486430393
            ),
          },
        ];

        // 마커가 지도 위에 표시되도록 설정합니다
        for (var i = 0; i < positions.length; i++) {
          // 마커를 생성합니다
          var marker = new kakao.maps.Marker({
            position: positions[i].latlng,
            title: positions[i].title,
          });
          marker.setMap(map);
        }
      });
    </script>
  </head>