<template>
  <div>
    <div class="map-select-div">
      <select class="form-control" v-model="sido" @change="gugunList">
        <option value="all" disabled selected>시/도</option>
        <option v-for="(sido, index) in sidos" :key="index" :value="sido">
          {{ sido }}
        </option>
      </select>
      <select class="form-control" v-model="gugun" @change="dongList">
        <option value="all" disabled selected>구/군</option>
        <option v-for="(gugun, index) in guguns" :key="index" :value="gugun">
          {{ gugun }}
        </option>
      </select>
      <select class="form-control" v-model="dong" @change="houseList">
        <option value="all" disabled selected>동</option>
        <option v-for="(dong, index) in dongs" :key="index" :value="dong">
          {{ dong }}
        </option>
      </select>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseSearchBar",
  data() {
    return {
      sido: null,
      gugun: null,
      dong: null,
    };
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.getSido();
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "dongs", "houses"]),
  },
  methods: {
    ...mapActions(houseStore, [
      "getSido",
      "getGugun",
      "getDong",
      "getHouseList",
    ]),
    ...mapMutations(houseStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
      "CLEAR_HOUSE_LIST",
    ]),

    gugunList() {
      this.CLEAR_GUGUN_LIST();
      this.gugun = null;
      if (this.sido) this.getGugun(this.sido);
    },
    dongList() {
      this.CLEAR_DONG_LIST();
      this.dong = null;
      if (this.gugun) this.getDong(this.gugun);
    },
    houseList() {
      //console.log("houselist vue", this.dong);
      this.CLEAR_HOUSE_LIST();
      if (this.dong) this.getHouseList(this.dong);
    },
  },
};
</script>

<style>
.map-select-div {
  display: flex;
  justify-content: center;
}
</style>
