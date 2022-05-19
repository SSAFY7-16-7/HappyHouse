<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

  <!--
        ==================================================
        Header Section Start
        ================================================== -->
    <section class="top-bar animated-header">
      <div class="container">
        <div class="row">
          <div class="col-lg-12">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
              <a class="navbar-brand" href="${root}${root}">
                <img src="${root}/images/logo.png" alt="logo" width="50" height="50"/>
              </a>
              <button
                class="navbar-toggler"
                type="button"
                data-toggle="collapse"
                data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent"
                aria-expanded="false"
                aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
              </button>

              <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ml-auto">
                  <li class="nav-item">
                    <a class="nav-link" href="${root}${root}">Home
                      <span class="sr-only">(current)</span>
                    </a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="${root}/showDeals/list">실거래가 조회</a>
                  </li>
                   <c:if test="${!empty userInfo }">
                   <li class="nav-item">
                    <a class="nav-link" href="${root}/likeList">관심 아파트</a>
                  </li>
                  </c:if>
                  <li class="nav-item">
      
                  	<c:if test="${empty userInfo}">
                  	
                  	 <a class="nav-link" href="${root}/loginPage">LOGIN</a>
                  	</c:if>
                  	<c:if test="${!empty userInfo}">
                  	 <a class="nav-link" href="${root}/logout">LOGOUT</a>
                  	</c:if>
                   
                  </li>
                  <li class="nav-item">
                  	<c:if test="${!empty userInfo}">
                  	 <a class="nav-link" href="${root}/myPage">MY PAGE</a>
                  	</c:if>
                  	<c:if test="${empty userInfo}">
                  	<a class="nav-link" href="${root}/signUp">회원가입</a>
                  	</c:if>
                    
                  </li>
                  <c:if test="${!empty userInfo and (userInfo.id eq 'admin')  }">
                   <li class="nav-item">
                    <a class="nav-link" href="${root}/userList">회원 관리 </a>
                  </li>
                  </c:if>
                </ul>
              </div>
            </nav>
          </div>
        </div>
      </div>
    </section>