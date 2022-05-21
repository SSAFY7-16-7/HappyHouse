<template>
  <div style="width: 100%; height: 100%">
    <div class="position-relative">
      <main class="mt-0 main-content main-content-bg">
        <section>
          <kakao-map class="kakao-map"></kakao-map>
          <div>
            <div class="search-div">
              <house-search-bar class="house-search-bar" />
              <div class="list-div type1">
                <house-list class="house-list" />
              </div>
            </div>
            <div class="detail-div type1">
              <house-detail class="house-detail" />
            </div>
          </div>
        </section>
      </main>
    </div>
  </div>
</template>
<script>
import HouseSearchBar from "@/components/house/HouseSearchBar.vue";
import HouseList from "@/components/house/HouseList.vue";
import HouseDetail from "@/components/house/HouseDetail.vue";

import Navbar from "@/components/PageLayout/Navbar.vue";
import AppFooter from "@/components/PageLayout/Footer.vue";
import VsudInput from "@/components/soft-ui-components/VsudInput.vue";
import VsudSwitch from "@/components/soft-ui-components/VsudSwitch.vue";
import VsudButton from "@/components/soft-ui-components/VsudButton.vue";

import KakaoMap from "@/components/Map/KakaoMap.vue";

export default {
  name: "HouseView",
  components: {
    HouseSearchBar,
    HouseList,
    HouseDetail,
    Navbar,
    AppFooter,
    VsudInput,
    VsudSwitch,
    VsudButton,
    KakaoMap,
  },
};
</script>
<style scoped>
.underline-orange {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 149, 27, 0.3) 30%
  );
}

.search-div {
  position: absolute;
  width: 418px;
  height: 100vh;
  z-index: 2;
  top: 54px;
  left: 0;
  background-color: #fcfbf6e0;
}

.list-div {
  position: relative;
  width: 418px;
  height: 100vh;
  overflow-y: scroll;
}

.detail-div {
  position: absolute;
  width: 401px;
  height: 100vh;
  z-index: 2;
  top: 54px;
  left: 417px;
  background-color: #fcfbf6e0;
  overflow-y: scroll;
}

.house-search-bar {
  z-index: 3;
  position: absolute;
  top: 34px;
  left: 28px;
  width: 340px;
}
.house-list {
  position: absolute;
  z-index: 3;
  top: 91px;
  left: 27px;
}

.kakao-map {
  height: 100vh;
  z-index: 1;
  position: relative;
}
.house-search-bar {
  z-index: 3;
  position: absolute;
  top: 26px;
  left: 28px;
  width: 340px;
}

/* 스크롤바 설정*/
.type1::-webkit-scrollbar {
  width: 14px;
}

/* 스크롤바 막대 설정*/
.type1::-webkit-scrollbar-thumb {
  height: 17%;
  background-color: rgb(174 180 191);
  /* 스크롤바 둥글게 설정    */
  border-radius: 10px;
}

/* 스크롤바 뒷 배경 설정*/
.type1::-webkit-scrollbar-track {
  background-color: rgb(222 222 222);
}
</style>
