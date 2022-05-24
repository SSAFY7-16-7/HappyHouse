<template>
  <div v-if="house">
    <div class="close-div">
      <b-icon-x-lg class="close" @click="dispalynone" />
    </div>
    <div class="detail-name">
      <div class="title-area">
        <like-button
          @btnClick="setLike"
          :isLiked="likeStatus"
          class="sellLikebtn"
        ></like-button>
        <h3>{{ house.apartmentName }}</h3>
      </div>
      <div class="title-sub-info">
        준공 : {{ house.buildYear }} | {{ house.roadName }} ({{ house.dong }})
      </div>
    </div>
    <div class="detail-img-div">
      <img :src="require('@/assets/img/apt.png')" />
    </div>
    <div
      v-for="(deal, index) in deals"
      :key="index"
      class="dealList"
      @mouseover="colorChange(true, index)"
      @mouseout="colorChange(false, index)"
      :class="{ 'mouse-over-bgcolor': columnHoverCheck(index) }"
    >
      <h4 style="font-color: blue">매매 {{ deal.dealAmount }} 만원</h4>
      <div>{{ deal.floor }}층 | {{ deal.area }}㎡</div>
      <div>
        거래일자 : {{ deal.dealYear }} / {{ deal.dealMonth }} /
        {{ deal.dealDay }}
      </div>
    </div>
  </div>
</template>

<script>
import LikeButton from "@/components/Interest/LikeButton.vue";
import { BUS } from "@/store/modules/EventBus";
import { mapActions, mapState } from "vuex";
import { apiInstance } from "@/api/index.js";
const http = apiInstance();
const houseStore = "houseStore";

export default {
  name: "HouseDetail",
  data() {
    return {
      overColumn: null,
      likeStatus: true,
    };
  },
  components: {
    LikeButton,
  },
  computed: {
    ...mapState(houseStore, ["house", "deals", "none"]),
    // house() {
    //   return this.$store.state.house;
    // },
  },
  methods: {
    ...mapActions(houseStore, ["setNoneFalse"]),
    colorChange(flag, index) {
      if (flag) {
        this.overColumn = index;
      } else {
        this.overColumn = null;
      }
    },
    columnHoverCheck(index) {
      if (index === this.overColumn) {
        return true;
      } else {
        return false;
      }
    },
    dispalynone() {
      this.setNoneFalse(true);
    },
    setLike() {
      let param = { code: "", user_id: "ssafy" };

      param.code = this.house.aptCode;

      console.log(param);
      if (this.likeStatus) {
        http
          .post(`/interest/delete/apt`, param)
          .then(() => {
            console.log("좋아요 취소 성공 ");
            this.likeStatus = !this.likeStatus;

            BUS.$emit("deleteLike", param.code);
          })
          .catch(() => {
            console.log("좋아요 취소 실패 ");
          });
      } else {
        console.log("좋아요 설정");
        http
          .post(`/interest/apt`, param)
          .then(() => {
            console.log("좋아요 성공 ");
            BUS.$emit("addLike", this.house);
          })
          .catch(() => {
            console.log("좋아요 실패 ");
          });
        this.likeStatus = !this.likeStatus;
      }
    },
  },
  updated() {
    const code = this.house.aptCode;
    // http
    //   .get(`/apt/${code}`)
    //   .then(({ data }) => {
    //     // this.item = data;
    //   })
    //   .catch(() => {
    //     console.log("거래 매물 정보 가져오기오류 ");
    //   });

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

.detail-row {
  /* text-align: end; */
  font-size: 19px;
  font-weight: bold;
  padding-top: 10px;
}

.house-detail {
  padding: 0px 30px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
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
}

.detail-info-div {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  width: 90%;
}

.detail-row {
  font-size: 19px;
  font-weight: bold;
  padding-top: 10px;
}

.dealList {
  border-top: 2px solid #aeb4bf;
  width: 323px;
  padding: 14px;
}
</style>
