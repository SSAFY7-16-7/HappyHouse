<template>
  <div class="inter-item" @click="itemClick">
    <div class="item-info">
      <div v-if="category === 'sell'" class="info-text">
        {{ item.address }} / {{ item.price }}
      </div>
      <div v-else class="info-text">{{ item.apartmentName }}</div>
    </div>
    <like-button
      :isLiked="likeStatus"
      size="1"
      @btnClick="setLike"
    ></like-button>
  </div>
</template>

<script>
import { BUS } from "@/store/modules/EventBus";
import LikeButton from "./LikeButton.vue";
import { apiInstance } from "@/api/index.js";
import { mapActions, mapState } from "vuex";
const http = apiInstance();
const houseStore = "houseStore";
export default {
  name: "InterestItem",
  components: {
    LikeButton,
  },
  props: ["item", "category", "index"],
  data() {
    return {
      likeStatus: true,
    };
  },
  computed: {
    ...mapState("interestStore", ["none"]),
  },
  methods: {
    setLike() {
      if (this.likeStatus) {
        let param = { code: "", user_id: "ssafy" };
        if (this.category === "sell") {
          param.code = this.item.idx;
        } else {
          param.code = this.item.aptCode;
        }
        http
          .post(`/interest/delete/${this.category}`, param)
          .then(() => {
            console.log("좋아요 취소 성공 ");
            // this.likeStatus = !this.likeStatus;

            this.$emit("deleteLkie", this.index);
          })
          .catch(() => {
            console.log("좋아요 취소 실패 ");
          });
      } else {
        console.log("좋아요 설정");
        this.likeStatus = !this.likeStatus;
      }
    },
    itemClick() {
      // console.log("아이템 클릭", this.index);

      BUS.$emit("change-postion", {
        lat: this.item.lat,
        lng: this.item.lng,
      });
      if (this.category === "sell") {
        this.showSellDetail();
      } else {
        this.showHouseDetail();
      }
    },
    showSellDetail() {
      // console.log(this.item);
      this.$store.state.interestStore.house = this.item;
      this.$store.state.houseStore.none = false;
    },
    ...mapActions(houseStore, ["detailHouse", "setNoneFalse"]),
    showHouseDetail() {
      console.log("아파트 디테일", this.item);
      this.setNoneFalse(true);
      this.detailHouse(this.item);
      // this.$store.state.houseStore.none = false;
    },
  },
};
</script>

<style>
.inter-item {
  display: flex;
  justify-content: space-between;
  width: 320px;
  margin-left: 16px;
  padding-bottom: 18px;
}
.info-text {
  font-weight: bold;
  font-size: 19px;
}
</style>
