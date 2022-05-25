<template>
  <div class="card video-card" @click="moveDetail">
    <a :href="item.link">
      <div class="video-card-img">
        <b-badge class="views">{{ item.views }} 조회</b-badge>
        <img
          class="video-thumbnail"
          :src="item.channel.thumbnail"
          alt="Image 1"
        />
      </div>
      <div class="video-card-info">
        <h4 class="video-title">{{ item.title }}</h4>
        <div>{{ item.channel.name }}</div>
      </div>
    </a>
  </div>
</template>

<script>
export default {
  name: "videocard",
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
.video-card {
  background-color: #fff;
  border: 1px solid #73738a3f !important;
  border-radius: 0 !important;
  width: 350pxx;
  height: 270px;
  /* margin: 15px; */
  display: flex;
  flex-direction: column;
  align-items: center;
}
.video-title {
  text-align: center;
  width: 80%;
  font-size: 1.3em;
}
.video-card-img {
  display: flex;
  justify-content: center;
}
.video-thumbnail {
  width: 150px;
  height: 150px;
  border-radius: 10px;
  margin-top: 20px;
}
.video-card-info {
  width: 250px;
  padding-top: 20px;
}
.badge {
  color: black !important;
}

.key-info {
  margin: 3px;
}
</style>
