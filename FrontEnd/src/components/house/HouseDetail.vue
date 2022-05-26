<template>
  <div v-if="house">
    <div class="close-div">
      <b-icon-x-lg class="close" @click="dispalynone" />
    </div>
    <div class="detail-name">
      <div class="title-area">
        <like-button
          v-if="isLogin"
          @btnClick="setLike"
          :isLiked="likeStatus"
        ></like-button>
        <h3>{{ house.apartmentName }}</h3>
      </div>
      <div class="title-sub-info">
        준공 : {{ house.buildYear }} | {{ house.roadName }} ({{ house.dong }})
      </div>
    </div>
    <div class="detail-img-div">
      <img class="sell-thumbnail" :src="img_url" alt="Image 1" />
    </div>
    <div class="detail-menu-bar">
      <b-button
        @click="showInfo"
        class="menu"
        :class="{ active: detail_type.info }"
        id="info"
      >
        매물정보
      </b-button>
      <b-button
        @click="showDeal"
        class="menu"
        :class="{ active: detail_type.deal }"
        id="deal"
      >
        실거래가
      </b-button>
      <b-button
        @click="showInfra"
        class="menu"
        :class="{ active: detail_type.infra }"
        id="infra"
      >
        인프라
      </b-button>
    </div>
    <!-- <router-view /> -->
    <detail-info v-if="detail_type.info"></detail-info>
    <detail-deal v-else-if="detail_type.deal"></detail-deal>
    <detail-infra v-else-if="detail_type.infra"></detail-infra>
  </div>
</template>

<script>
import LikeButton from "@/components/Interest/LikeButton.vue";
import HouseInfo from "@/components/house/detail/HouseInfo.vue";
import { BUS } from "@/store/modules/EventBus";
import { mapActions, mapState } from "vuex";
import { apiInstance } from "@/api/index.js";
import DetailInfra from "@/components/house/detail/HouseInfra.vue";
import DetailInfo from "@/components/house/detail/HouseInfo.vue";
import DetailDeal from "@/components/house/detail/HouseDeal.vue";
const http = apiInstance();
const houseStore = "houseStore";
import { ramdomImg } from "@/api/image.js";
export default {
  name: "HouseDetail",
  data() {
    return {
      overColumn: null,
      likeStatus: false,
      img_url: "",
      detail_type: { info: true, deal: false, infra: false },
      buttons: [
        { caption: "매물정보", state: true },
        { caption: "실거래가", state: false },
        { caption: "인프라", state: false },
      ],
    };
  },
  components: {
    LikeButton,
    HouseInfo,
    DetailInfra,
    DetailInfo,
    DetailDeal,
  },
  computed: {
    ...mapState(houseStore, ["house", "none"]),
    ...mapState("memberStore", ["userInfo", "isLogin"]),
    // house() {
    //   return this.$store.state.house;
    // },
  },
  created() {
    this.img_url = ramdomImg();
    // const path = this.$route.path;
    // if (path === "/house") {
    //   this.likeStatus = false;
    // } else this.likeStatus = true;
    const code = this.house.aptCode;
    http
      .post(`/interest/check/likeApt`, { code, user_id: "ssafy" })
      .then(({ data }) => {
        if (data.isLiked === "Y") {
          this.likeStatus = true;
        } else {
          this.likeStatus = false;
        }
      });
  },
  methods: {
    showInfo() {
      this.detail_type.info = true;
      this.detail_type.deal = false;
      this.detail_type.infra = false;
    },
    showDeal() {
      this.detail_type.info = false;
      this.detail_type.deal = true;
      this.detail_type.infra = false;
    },
    showInfra() {
      this.detail_type.info = false;
      this.detail_type.deal = false;
      this.detail_type.infra = true;
    },
    ...mapActions(houseStore, ["setNoneFalse"]),
    hi() {
      alert("hi");
    },
    classOn() {
      // const target = event.currentTarget.id;
      alert("target");
      // document.getElementById(target).className = "on";
    },
    dispalynone() {
      this.setNoneFalse(true);
    },
    setLike() {
      let param = { code: "", user_id: "ssafy" };

      param.code = this.house.aptCode;

      //console.log(param);
      if (this.likeStatus) {
        http
          .post(`/interest/delete/apt`, param)
          .then(() => {
            //console.log("좋아요 취소 성공 ");
            this.likeStatus = !this.likeStatus;

            BUS.$emit("deleteLike", param.code);
          })
          .catch(() => {
            //console.log("좋아요 취소 실패 ");
          });
      } else {
        //console.log("좋아요 설정");
        http
          .post(`/interest/apt`, param)
          .then(() => {
            //console.log("좋아요 성공 ");
            BUS.$emit("addLike", this.house);
          })
          .catch(() => {
            //console.log("좋아요 실패 ");
          });
        this.likeStatus = !this.likeStatus;
      }
    },
  },
  updated() {
    const code = this.house.aptCode;
    http
      .post(`/interest/check/likeApt`, { code, user_id: "ssafy" })
      .then(({ data }) => {
        if (data.isLiked === "Y") {
          this.likeStatus = true;
        } else {
          this.likeStatus = false;
        }
      });
  },
};
</script>

<style>
.title-area {
  display: flex;
  align-items: center;
  justify-content: flex-start !important;
  width: 325px !important;
}
.mouse-over-bgcolor {
  background-color: rgb(214, 211, 211);
}

.close-div {
  position: relative;
  width: 323px;
  padding-top: 31px;
  right: -295px;
}

.close {
  background-color: #9ca3b1;
  width: 32px;
  height: 32px;
  color: white;
  padding: 9px;
  border-radius: 3px;
}

.detail-name {
  padding-top: 14px;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.detail-img-div img {
  padding: 11px 0px 40px 0;
  width: 328px;
  height: 300px;
}
.detail-menu-bar {
  margin-bottom: 20px;
  display: flex;
  justify-content: space-around;
  width: 326px;
}

.on {
  font-weight: bold;
  color: #2196f3;
}
.table td,
.table th {
  white-space: break-spaces;
}

.detail-menu-bar .menu {
  font-weight: bold;
}
.menu.active {
  background-color: #2196f3 !important;
}
</style>
