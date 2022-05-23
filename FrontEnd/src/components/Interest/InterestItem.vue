<template>
  <div class="inter-item" @click="itemClick">
    <div class="item-info">
      <div v-if="category === 'sell'">
        {{ item.address }} / {{ item.price }}
      </div>
      <div v-else>{{ item.apartmentName }}</div>
    </div>
    <like-button
      :isLiked="likeStatus"
      size="1"
      @btnClick="setLike"
    ></like-button>
  </div>
</template>

<script>
import LikeButton from "./LikeButton.vue";
import { apiInstance } from "@/api/index.js";
const http = apiInstance();
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
            this.likeStatus = !this.likeStatus;

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
      console.log("아이템 클릭", this.index);
    },
  },
};
</script>

<style>
.inter-item {
  display: flex;
}
</style>
