<template>
  <div class="card sell-card" @click="moveDetail">
    <div class="sell-card-img">
      <b-badge class="room-type">{{ item.room_type }}</b-badge>
      <img
        class="sell-thumbnail"
        src="https://picsum.photos/250/250/?image=54"
        alt="Image 1"
      />
    </div>
    <div class="sell-card-info">
      <h4>{{ item.title }}</h4>
      <div>{{ item.address }}</div>
      가격 : {{ item.price }} 만원
      <div>관리비 : {{ item.admin_price }} 만원</div>
      <div>관리비 포함 항목 : {{ item.admin_desc }}</div>
      <div class="keywords">
        <b-badge
          class="key-info"
          v-for="(key, index) in keywords"
          :key="index"
          >{{ key }}</b-badge
        >
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "SelllistItem",
  props: ["item"],
  data() {
    return {
      keywords: [],
    };
  },
  created() {
    this.keywords = this.item.keyword.split(",");
  },
  methods: {
    moveDetail() {
      this.$router.push({ name: "sellDeatil", query: { no: this.item.idx } });
    },
  },
};
</script>

<style>
.sell-card {
  background-color: #fff;
  border-radius: 20px;
  width: 300px;
  height: 450px;
  margin: 15px;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.sell-thumbnail {
  width: 250px;
  height: 200px;
  border-radius: 10px;
  margin-top: 20px;
}
.sell-card-info {
  width: 250px;
  padding-top: 20px;
}
.badge {
  color: black !important;
  background-color: #d9ff4f;
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
