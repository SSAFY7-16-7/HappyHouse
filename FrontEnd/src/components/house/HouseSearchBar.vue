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
    <div class="map-name-div">
      <div>
        <input
          type="text"
          class="form-control name-search"
          default="아파트명 검색"
          style="width: 256px"
          v-model="name"
        />
      </div>
      <div>
        <button class="btn btn-primary" style="margin: 0" @click="nameList">
          검색
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
import { BUS } from "@/store/modules/EventBus";

const houseStore = "houseStore";

export default {
  name: "HouseSearchBar",
  data() {
    return {
      sido: null,
      gugun: null,
      dong: null,
      name: null,
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
      "getNameList",
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
      console.log("houselist vue", this.dong);
      this.CLEAR_HOUSE_LIST();
      if (this.dong) this.getHouseList(this.dong);

      BUS.$emit("moveDong", this.gugun + " " + this.dong);
    },
    nameList() {
      console.log("housename", this.name);
      this.CLEAR_HOUSE_LIST();
      if (this.name) this.getNameList(this.name);
    },
  },
};
</script>

<style>
.map-select-div {
  display: flex;
  justify-content: center;
}

.map-name-div {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  margin-top: 17px;
}
</style>
