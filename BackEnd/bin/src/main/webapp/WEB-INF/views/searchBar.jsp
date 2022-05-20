<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ taglib prefix ="c" uri ="http://java.sun.com/jsp/jstl/core" %>
	<c:set var="root" value="${pageContext.request.contextPath}"></c:set>    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    

    <script type="text/javascript">
    $(function () {
        // ysno 시군동 정보 & 아파트 매매 정보 가져오는 js 함수
  /*       for (let i = 2000; i < 2023; i++) {
          $("#year").append(`<option value="${i}">${i}년</option>`);
        }
  
        for (let i = 1; i < 13; i++) {
          $("#month").append(`<option value="${i < 10 ? "0" + i : i}">${i}월</option>`);
        } */
  
        sendRequest("sido", "*00000000");
      });
    
    $(function () {
		$('.search_btn').click(function () {
			pagelist(1);
		})
		<c:if test="${!empty param.key}">
		  $('#key').val("${param.key}")
		</c:if>
		$('.word').val("${param.word}")
		$('#pageNo').val("${param.pageNo}")
	})
	
	function pagelist(cpage){
    	alert("${root}");
		var frm = document.getElementByClass('search_apt');
		var pageNo = document.getElementById('pageNo');
		pageNo.value=cpage;
		frm.action="${root}/housedeal/showDeals"
		frm.submit();
	}
    
    $(document).on("change", "#sido", function () {
        regcode = $(this).val().substr(0, 2) + "*00000";
        console.log("sido select regcode : " + regcode);
        $("#gugun").empty();
        $("#dong").empty().append("<option>동선택</option>");
        sendRequest("gugun", regcode);
      });
  
      $(document).on("change", "#gugun", function () {
        regcode = $(this).val().substr(0, 5) + "*";
        console.log("gugun select regcode : " + regcode);
        sendRequest("dong", regcode);
      });
  
      function sendRequest(selid, regcode) {
        console.log("regcode : " + regcode);
        $.ajax({
        	
          url: "https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes",
          type: "GET",
          data: {
            regcode_pattern: regcode,
            is_ignore_zero: true,
          },
          dataType: "json",
          success: function (response) {
            console.log(response);
            addOption(selid, response);
          },
          error: function (xhr, status, msg) {
            console.log("상태값 : " + status + " Http에러메시지 : " + msg);
          },
        });
      }
  
      function addOption(selid, data) {
        let code = ``;
        let initOption = ``;
        switch (selid) {
          case "sido":
        	  initOption = `<option>시도선택</option>`;
              $("#sido").empty().append(initOption);
              $.each(data.regcodes, function (i, regcode) {
            	
                code += ("<option value=" +"\""+regcode.code+"\">"+regcode.name.split(" ")[0]+"</option>");
              });
            break;
          case "gugun":
            initOption = `<option>구군선택</option>`;
            $("#gugun").empty().append(initOption);
            console.log(data.regcodes);
            for (let i = 0; i < data.regcodes.length; i++) {
              if (i != data.regcodes.length - 1) {
                if (
                  data.regcodes[i].name.split(" ")[1] == data.regcodes[i + 1].name.split(" ")[1] &&
                  data.regcodes[i].name.split(" ").length !=
                  data.regcodes[i + 1].name.split(" ").length
                ) {
                  data.regcodes.splice(i, 1);
                  i--;
                }
              }
            }
            console.log(data.regcodes);
            let name = "";
            $.each(data.regcodes, function (i, regcode) {
              if (regcode.name.split(" ").length == 2) name = regcode.name.split(" ")[1];
              else name = regcode.name.split(" ")[1] + " " + regcode.name.split(" ")[2];
              code += ("<option value=" + "\""+regcode.code+"\">"+name+"</option>");
              
            });
            break;
          case "dong":
            initOption = `<option>동선택</option>`;
            $("#dong").empty().append(initOption);
            let idx = 2;
            $.each(data.regcodes, function (i, regcode) {
              if (data.regcodes[i].name.split(" ").length != 3) idx = 3;
              code += "<option value=" + "\""+regcode.code+"\">"+regcode.name.split(" ")[idx]+"</option>";
            });
            break;
        }
        console.log(selid);
        $("#" + selid).append(code);
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
                    <i class="ion-ios-home"></i>
                    Home &nbsp; &nbsp;/
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
               <%-- method="post" action="${root}/housedeal/search" --%>
                <form class="search_dong">
                  <input type="hidden" 	id="pageNo" 	name="pageNo" value="1" />
                  <input type="hidden" name="key" value="dong">
                  <div class="search_title">동별 검색</div>
                  <select name="sido" id="sido">
                    <option value="">시/도</option>
                  </select>
                  
                  <select  name="gugun" id="gugun">
                    <option value="">시/군/구</option>
                  </select>
                  
                  <select name="word" id="dong" class="word">
                    <option value="">읍/면/동</option>
                  </select>
            	  <input type="submit" value="검색" class="search_btn"/>
                </form>
                
                <form class="search_apt">
                  <input type="hidden" 	id="pageNo" 	name="pageNo" value="1" />
                  <input type="hidden" name="name" value="dong">
                  <div class="search_title">아파트 검색</div>
                  <input class="apt_name" class="word" name="word" type="text" placeholder="아파트 이름 입력" />
                  <button class="search_btn">검색</button>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>