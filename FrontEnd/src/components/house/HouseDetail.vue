<template>
  <div v-if="house">
    <div class="detail-name">
      <h3>{{ house.아파트 }}</h3>
    </div>
    <div class="detail-img-div">
      <img :src="require('@/assets/img/apt.png')" />
    </div>
    <div class="detail-info-div">
      <div class="detail-row">
        {{ house.아파트 }}
      </div>
      <div class="detail-row">법정동 : {{ house.법정동 }}</div>
      <div class="detail-row">층수 : {{ house.층 }}층</div>
      <div class="detail-row">
        거래금액 :
        {{ (parseInt(house.거래금액.replace(",", "")) * 10000) | price }}원
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseDetail",
  computed: {
    ...mapState(houseStore, ["house"]),
    // house() {
    //   return this.$store.state.house;
    // },
  },
  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
};
</script>

<style>
.detail-row {
  /* text-align: end; */
  font-size: 19px;
  font-weight: bold;
  padding-top: 10px;
}

.house-detail {
  padding: 50px 30px;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.detail-name {
  padding-top: 30px;
}

.detail-img-div {
  /* width: 300px; */
  padding: 36px 0px;
}

.detail-img-div img {
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
</style>
