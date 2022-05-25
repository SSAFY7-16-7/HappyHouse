<template>
  <div>
    <div class="sell-header">
      <h2>사용자들이 등록한 매물입니다</h2>
      <b-button class="sell-add-btn" pill variant="info" @click="moveRegist"
        >매물 등록하기
      </b-button>
    </div>

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
  methods: {
    moveRegist() {
      this.$router.push({ name: "sellregist" });
    },
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
.sell-header {
  display: flex;
  align-items: center;
  justify-content: center;
  justify-content: space-around;
}

.sell-add-btn {
  margin: 0 !important;
  margin-left: 20px;
}
</style>
