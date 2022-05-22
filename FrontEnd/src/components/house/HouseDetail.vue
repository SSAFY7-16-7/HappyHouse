<template>
  <div v-if="house">
    <div class="btn" @click="dispalynone">X</div>
    <div class="detail-name">
      <h3>{{ house.apartmentName }}</h3>
      준공 : {{ house.buildYear }} | {{ house.roadName }} ({{ house.dong }})
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
import { mapActions, mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseDetail",
  data() {
    return {
      overColumn: null,
    };
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
  },
};
</script>

<style>
.mouse-over-bgcolor {
  background-color: rgb(214, 211, 211);
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
  padding-top: 30px;
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
