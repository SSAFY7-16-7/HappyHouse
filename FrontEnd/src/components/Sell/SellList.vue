<template>
  <div>
    <h1>매물리스트</h1>
    <div class="sell-list">
      <div v-for="(item, index) in list" :key="index">
        <sell-list-item :item="item"></sell-list-item>
      </div>
    </div>
  </div>
</template>

<script>
import { apiInstance, kakaoInstance } from "@/api/index.js";
import { mapState } from "vuex";
import SellListItem from "./SellListItem.vue";
const http = apiInstance();
const kakao = kakaoInstance();
export default {
  components: { SellListItem },
  data() {
    return {
      list: [],
    };
  },
  mounted() {
    http
      .get(`/sell?pageNo=1`)
      .then(({ data }) => {
        this.list = data;
        console.log(this.list);
      })
      .catch(() => {
        console.log("거래 매물 정보 가져오기오류 ");
      });
  },
};
</script>

<style>
.sell-list {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  justify-content: center;
}
</style>
