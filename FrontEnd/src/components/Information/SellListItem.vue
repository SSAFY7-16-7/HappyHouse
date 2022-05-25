<template>
  <div class="card news-card" @click="moveDetail">
    <a :href="item.link">
      <div class="news-card-img">
        <b-badge class="views">{{ item.news_info.news_date }}</b-badge>
        <img class="news-thumbnail" :src="item.thumbnail" alt="Image 1" />
      </div>
      <div class="news-card-info">
        <h4 class="news-title">{{ item.title }}</h4>
        <div>{{ item.news_info.press_name }}</div>

        <div>{{ item.snippet }}</div>
      </div>
    </a>
  </div>
</template>

<script>
export default {
  name: "SelllistItem",
  props: ["item"],
  data() {
    return {};
  },
  created() {},
  methods: {
    moveDetail() {},
  },
};
</script>

<style>
.news-card {
  background-color: #fff;
  border: 1px solid #73738a3f !important;
  border-radius: 0 !important;
  width: 300px;
  height: 450px;
  /* margin: 15px; */
  display: flex;
  flex-direction: column;
  align-items: center;
}

.news-card:hover {
  cursor: pointer;
}
.news-title {
  position: absolute;
  top: 28%;
  left: 10%;
  background-color: black;
  width: 80%;
  font-size: 1.3em;
  color: #fff;
}

.news-thumbnail {
  width: 250px;
  height: 200px;
  border-radius: 10px;
  margin-top: 20px;
}
.news-card-info {
  width: 250px;
  padding-top: 20px;
}
.views {
  position: absolute;
  top: 25px;
  left: 30px;
  background-color: #00fbff !important;
}
.room-type {
  position: absolute;
  top: 25px;
  left: 30px;
}
.key-info {
  margin: 3px;
}
</style>
