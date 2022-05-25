<template>
  <div style="width: 100%; height: 100%">
    <div class="position-relative">
      <main class="mt-0 main-content main-content-bg">
        <section>
          <kakao-map></kakao-map>
          <div>
            <div class="search-div">
              <!-- <dong-search-bar class="house-search-bar"></dong-search-bar> -->
              <div class="list-div type1">
                <div class="house-list">
                  <div class="buttons">
                    <b-button @click="changeCategory('apt')"
                      >관심 아파트</b-button
                    >
                    <b-button @click="changeCategory('sell')"
                      >관심 매물</b-button
                    >
                  </div>
                  <div class="inter-sells" v-if="category === 'sell'">
                    <h3 style="padding-bottom: 35px">관심매물리스트</h3>
                    <interest-list
                      :list="listSell"
                      category="sell"
                    ></interest-list>
                  </div>
                  <div class="ineter-apts" v-else>
                    <h3 style="padding-bottom: 35px">관심 아파트 리스트</h3>
                    <interest-list
                      :list="listApt"
                      category="apt"
                    ></interest-list>
                  </div>
                </div>
              </div>
            </div>
            <div
              class="detail-div type1"
              v-if="!this.$store.state.houseStore.none"
            >
              <div v-if="category === 'apt'">
                <house-detail class="house-detail" />
              </div>
              <div v-else>
                <sell-detail class="house-detail" />
              </div>
            </div>
          </div>
        </section>
      </main>
    </div>
  </div>
</template>

<script>
import Navbar from "@/components/PageLayout/Navbar.vue";
import AppFooter from "@/components/PageLayout/Footer.vue";
import VsudInput from "@/components/soft-ui-components/VsudInput.vue";
import VsudSwitch from "@/components/soft-ui-components/VsudSwitch.vue";
import VsudButton from "@/components/soft-ui-components/VsudButton.vue";
import InterestItem from "@/components/Interest/InterestList.vue";
import { apiInstance } from "@/api/index.js";
import { mapMutations, mapState, mapActions } from "vuex";
import InterestList from "../components/Interest/InterestList.vue";
import KakaoMap from "../components/Interest/KakaoMap.vue";
import DongSearchBar from "../components/Interest/DongSearchBar.vue";
import HouseDetail from "@/components/house/HouseDetail.vue";
import SellDetail from "../components/Sell/SellDetail.vue";
import { BUS } from "@/store/modules/EventBus";
const http = apiInstance();
export default {
  name: "InterestView",
  components: {
    Navbar,
    AppFooter,
    VsudInput,
    VsudSwitch,
    VsudButton,
    InterestItem,
    InterestList,
    KakaoMap,
    DongSearchBar,
    HouseDetail,
    SellDetail,
  },
  data() {
    return {
      listSell: [],
      listApt: [],
    };
  },
  computed: {
    ...mapState("interestStore", ["none", "category"]),
  },
  methods: {
    ...mapMutations("interestStore", ["SET_CATEGORY"]),
    changeCategory(category) {
      this.$store.state.houseStore.none = true;
      this.SET_CATEGORY(category);
    },
  },
  created() {
    http.get("/interest/aptlist/ssafy").then(({ data }) => {
      this.listApt = data;
      BUS.$emit("init-position", { lat: data[0].lat, lng: data[0].lng });
    });
    http.get("/interest/selllist/ssafy").then(({ data }) => {
      this.listSell = data;
    });
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

.none {
  display: none;
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
