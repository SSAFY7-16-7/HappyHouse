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

const houseStore = "houseStore";

export default {
  name: "HouseListItem",
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    house: Object,
  },
  computed: {
    ...mapState(houseStore, ["none"]),
  },
  methods: {
    ...mapActions(houseStore, ["detailHouse", "setNoneFalse"]),
    selectHouse() {
      this.detailHouse(this.house);
      this.setNoneFalse(false);
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
