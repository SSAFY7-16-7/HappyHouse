<template>
  <div v-if="house">
    <div class="btn" @click="dispalynone">X</div>
    <div class="detail-name">
      <h3>{{ house.title }}</h3>
      가격 : {{ house.price }}<br />
      주소: {{ house.address }} <br />
      층수 : {{ house.floor_now }}층/{{ house.floor_tot }}<br />
      타입 : {{ house.type }}<br />
      크기 : {{ house.size }}<br />
      설명 : {{ house.content }}<br />
    </div>
    <div class="detail-img-div">
      <img :src="require('@/assets/img/apt.png')" />
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";

const Store = "interestStore";

export default {
  name: "SellDetail",
  data() {
    return {
      overColumn: null,
    };
  },
  computed: {
    ...mapState(Store, ["house", "deals", "none"]),
    // house() {
    //   return this.$store.state.house;
    // },
  },
  methods: {
    ...mapActions(Store, ["setNoneFalse"]),
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
