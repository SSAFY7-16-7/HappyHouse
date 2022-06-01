<template>
  <div class="news-page">
    <div class="infos-header">
      <h2>최신 부동산 관련 기사</h2>
    </div>

    <div class="infos-list">
      <div v-for="(item, index) in news" :key="index">
        <sell-list-item :item="item"></sell-list-item>
      </div>
    </div>
  </div>
</template>

<script>
import { getNews } from "@/api/news.js";
import SellListItem from "./SellListItem.vue";
import { apiInstance } from "@/api/index.js";
const http = apiInstance();

export default {
  components: { SellListItem },
  data() {
    return {
      news: null,
    };
  },
  created() {
    // this.news = getNews();
    http.get(`/api/news`).then(({ data }) => {
      this.news = data.news_results;
    });
  },
};
</script>

<style>
.infos-list {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  justify-content: center;
}
.infos-header {
  display: flex;
  align-items: center;
  justify-content: center;
  justify-content: space-around;
  padding: 50px 0 30px;
}

.infos-add-btn {
  margin: 0 !important;
  margin-left: 20px;
}

.news-page {
  height: 100vh;
}
.video-list {
  display: flex;
  flex-wrap: wrap;
}
</style>
