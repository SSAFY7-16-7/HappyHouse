<template>
  <div
    class="m-2"
    @click="selectHouse"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
    style="display: flex; align-items: center"
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
.mouse-over-bgcolor {
  background-color: rgb(214, 211, 211);
}
.apt-img-div {
  margin: 2px 13px 2px 0;
}
.apt-img {
  width: 44px;
}
</style>
