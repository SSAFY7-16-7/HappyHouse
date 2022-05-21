<template>
  <div
    class="m-2"
    @click="selectHouse"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
    style="display: flex"
  >
    <div class="apt-img-div">
      <img
        src="https://picsum.photos/250/250/?image=58"
        alt="Image 1"
        class="apt-img"
      />
    </div>
    <div class="apt-info-div">
      <div style="text-overflow: ellipsis">
        [{{ house.법정동 }}] {{ house.아파트 }}
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions } from "vuex";

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
  methods: {
    ...mapActions(houseStore, ["detailHouse"]),
    selectHouse() {
      // console.log("listRow : ", this.house);
      // this.$store.dispatch("getHouse", this.house);
      this.detailHouse(this.house);
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
.mouse-over-bgcolor {
  background-color: rgb(214, 211, 211);
}
.apt-img-div {
  margin-right: 40px;
}
.apt-img {
  width: 75px;
}
</style>
