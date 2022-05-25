<template>
  <div
    class="m-2"
    @click="selectHouse"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
    style="display: flex; align-items: center"
  >
    <div class="apt-icon-div">
      <b-icon-house-door-fill class="apt-icon" />
    </div>
    <div class="apt-info-div">
      <div style="text-overflow: ellipsis">
        [{{ house.dong }}] {{ house.apartmentName }}
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import { BUS } from "@/store/modules/EventBus";
import { LineElement } from "chart.js";

const houseStore = "houseStore";

export default {
  name: "HouseListItem",
  data() {
    return {
      isColor: false,
      houseinfo: {
        roadName: "",
        roadNameBonbun: "",
        roadNameBubun: "",
      },
    };
  },
  props: {
    house: Object,
  },
  computed: {
    ...mapState(houseStore, ["none"]),
  },
  methods: {
    ...mapActions(houseStore, ["detailHouse", "setNoneFalse", "getDetail"]),
    selectHouse() {
      console.log("선택:", this.house);
      BUS.$emit("change-hposition", {
        lat: this.house.lat,
        lng: this.house.lng,
      });

      // house deals가 바뀔 때 마다 새로운 detail창 불러오기
      this.setNoneFalse(true);
      this.detailHouse(this.house);

      this.setNoneFalse(false);

      this.houseinfo.roadName = this.house.roadName;

      let roadNameBonbun = this.house.roadNameBonbun;
      roadNameBonbun = Number(roadNameBonbun).toString();
      this.houseinfo.roadNameBonbun = roadNameBonbun;

      let roadNameBubun = this.house.roadNameBubun;
      roadNameBubun = Number(roadNameBubun).toString();
      this.houseinfo.roadNameBubun = roadNameBubun;
      console.log("houseinfo", this.houseinfo);
      this.getDetail(this.houseinfo);
    },
    colorChange(flag) {
      this.isColor = flag;
    },
  },
};
</script>

<style scoped>
.apt {
  width: 50px;
}

.apt-info-div div {
  white-space: nowrap;
  width: 278px;
  overflow: hidden;
  text-overflow: ellipsis;
  font-weight: bold;
  font-size: 16px;
}
.mouse-over-bgcolor {
  background-color: rgb(214, 211, 211);
}
.apt-icon-div {
  margin: 8px 21px 8px 15px;
}
.apt-icon {
  font-size: 25px;
  color: #bcb81e;
}
</style>
