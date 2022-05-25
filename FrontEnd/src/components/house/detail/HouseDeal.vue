<template>
  <div>
    <deal-chart />
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
import { mapState } from "vuex";
import DealChart from "@/components/Chart/DealChart.vue";
const houseStore = "houseStore";

export default {
  name: "HouseInfo",
  data() {
    return {
      overColumn: null,
    };
  },
  components: {
    DealChart,
  },
  computed: {
    ...mapState(houseStore, ["deals"]),
  },
  methods: {
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

.dealList {
  border-top: 2px solid #aeb4bf;
  width: 323px;
  padding: 14px;
}
</style>
