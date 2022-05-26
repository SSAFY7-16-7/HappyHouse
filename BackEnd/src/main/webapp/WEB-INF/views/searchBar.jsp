<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<script
  type="text/javascript"
  src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ff39ee8d511b533648d3baac6dd7efff&libraries=services"
></script>

<script type="text/javascript">
  /* let colorArr = ['table-primary','table-success','table-danger']; */
  $(document).ready(function () {
    $.get(
      "${root}/map/sido",
      function (data, status) {
        $.each(data, function (index, vo) {
          $("#sido").append(
            "<option value='" + vo.sidoCode + "'>" + vo.sidoName + "</option>"
          );
        });
      },
      "json"
    );
  });

  $(document).on("change", "#sido", function () {
    $.get(
      "${root}/map/gugun",
      { sido: $("#sido").val() },
      function (data, status) {
        $("#gugun").empty();
        $("#gugun").append('<option value="0">선택</option>');
        $.each(data, function (index, vo) {
          $("#gugun").append(
            "<option value='" + vo.gugunCode + "'>" + vo.gugunName + "</option>"
          );
        });
      },
      "json"
    );
  });

  $(document).on("change", "#gugun", function () {
    $.get(
      "${root}/map/dong",
      { gugun: $("#gugun").val() },
      function (data, status) {
        //console.log(data);
        $("#dong").empty();
        $("#dong").append('<option value="0">선택</option>');
        $.each(data, function (index, vo) {
          $("#dong").append(
            "<option value='" + vo.dongName + "'>" + vo.dongName + "</option>"
          );
        });
      },
      "json"
    );
  });

  $(document).on("click", ".search_btn", function () {
    var id_check = $(this).attr("id");
    var key;
    var word;
    var searchInfo = "";
    if (id_check == "dong_btn") {
      key = "dong";
      word = $("#dong").val();
      searchInfo = "동";
    } else if (id_check == "name_btn") {
      key = "name";
      word = $("#name").val();
      searchInfo = "아파트";
    }
    $("#searchInfo").val(searchInfo);

    $.ajax({
      url: "${root}/showDeals/searchList",
      type: "GET",
      dataType: "json",
      data: { key: key, word: word, pageNo: 1 },
      success: function (data) {
        $("#dealsDiv").empty();
        $.each(data, function (index, deal) {
          let str = `
							<div class="col-md-12 info-card">
								<div class="info" data-wow-duration="400ms" data-wow-delay="600ms">
									<input type="hidden" name="aptCode" value="${"${deal.aptCode}"}"/>
									<h4 class='apt_title'> ${"${deal.aptName}"}</h4>
									<div class='apt_size'> ${"${deal.area}"}평 / ${"${deal.floor}"}층 </div>
									<div class='price'>거래 금액:${"${deal.dealAmount}"} 만원</div>
									<div class='apt_size'>위치 : ${"${deal.dongName}"} ${"${deal.jibun}"}</div>
									<div class='apt_deal_date'>거래 날짜 : ${"${deal.dealYear}"}.${"${deal.dealMonth}"}.${"${deal.dealDay}"}</div>
								</div>
							</div>
						`;
          $("#dealsDiv").append(str);
        });
        $("#searchInfoDiv").empty();
        let searchInfo = `<h2 class="res_info" style="display:inline">"${"${word}"}"(으)로 검색 : </h2>
						<button class="res_btn">관심 등록</button>`;
        $("#searchInfoDiv").append(searchInfo);

        displayMarkers(data);
      },
      error: function (e) {
        //console.log(e);
      },
    });
  });

  function pagelist(cpage) {
    var pageNo = document.getElementById("pageNo");
    pageNo.value = cpage;

    var key;
    var word;
    var searchInfo = $("#searchInfo").val();

    if (searchInfo != "") {
      if (searchInfo == "동") {
        key = "dong";
        word = $("#dong").val();
      } else if (searchInfo == "아파트") {
        key = "name";
        word = $("#name").val();
      }
    }

    $.ajax({
      url: "${root}/showDeals/searchList",
      type: "GET",
      dataType: "json",
      data: { key: key, word: word, pageNo: cpage },
      success: function (data) {
        $("#dealsDiv").empty();
        $.each(data, function (index, deal) {
          let str = `
								<div class="col-md-12 info-card">
									<div class="info" data-wow-duration="400ms" data-wow-delay="600ms">
										<input type="hidden" name="aptCode" value="${"${deal.aptCode}"}"/>
										<h4 class='apt_title'> ${"${deal.aptName}"}</h4>
										<div class='apt_size'> ${"${deal.area}"}평 / ${"${deal.floor}"}층 </div>
										<div class='price'>거래 금액:${"${deal.dealAmount}"} 만원</div>
										<div class='apt_size'>위치 : ${"${deal.dongName}"} ${"${deal.jibun}"}</div>
										<div class='apt_deal_date'>거래 날짜 : ${"${deal.dealYear}"}.${"${deal.dealMonth}"}.${"${deal.dealDay}"}</div>
									</div>
								</div>
							`;
          $("#dealsDiv").append(str);
        });
        $("#searchInfoDiv").empty();
        let searchInfo = `<h2 class="res_info" style="display:inline">"${"${word}"}"(으)로 검색 : </h2>
							<button class="res_btn">관심 등록</button>`;
        $("#searchInfoDiv").append(searchInfo);

        displayMarkers(data);
      },
      error: function (e) {
        //console.log(e);
      },
    });
  }
</script>
<section class="global-page-header">
  <div class="container">
    <div class="row">
      <div class="col-md-12">
        <div class="block">
          <ol class="breadcrumb list-inline text-center">
            <li class="list-inline-item">
              <a href="index.html">
                <i class="ion-ios-home"></i> Home &nbsp; &nbsp;/
              </a>
            </li>
            <li class="active list-inline-item">거래 조회</li>
          </ol>
        </div>
        <div class="block">
          <div
            class="search_div subtitle-des wow fadeInUp animated"
            data-wow-delay=".5s"
            data-wow-duration="500ms"
          >
            <form id="frm">
              <input type="hidden" id="pageNo" name="pageNo" value="1" />
              <input type="hidden" id="key" name="key" value="" />
              <input type="hidden" id="word" name="key" value="" />
              <input type="hidden" id="searchInfo" name="searchInfo" value="" />
              <span class="search_title">동별 검색</span>
              <select name="sido" id="sido" class="search_comp">
                <option value="">시/도</option>
              </select>
              <select name="gugun" id="gugun" class="search_comp">
                <option value="">시/군/구</option>
              </select>
              <select name="word" id="dong" class="search_comp">
                <option value="">읍/면/동</option>
              </select>
              <span class="search_btn" id="dong_btn">검색</span>
              <div style="margin-top: 15px">
                <span class="search_title">아파트 검색</span>
                <input
                  name="word"
                  id="name"
                  type="text"
                  placeholder="아파트 이름 입력"
                  class="search_comp"
                />
                <span class="search_btn" id="name_btn">검색</span>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
