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
      <select class="form-control" v-model="dong" @change="aptList">
        <option value="all" disabled selected>동</option>
        <option v-for="(dong, index) in dongs" :key="index" :value="dong">
          {{ dong }}
        </option>
      </select>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { API_BASE_URL } from "@/config/index.js";
import { mapState, mapActions, mapMutations } from "vuex";

export default {
  name: "HouseSearchBar",
  data() {
    return {
      house: [],
      // sidos: {},
      // guguns: {},
      // dongs: {},
      sido: "",
      gugun: "",
      dong: "",
    };
  },
  created() {
    this.sidoList();
  },
  computed: {
    ...mapState("houseStore", ["sidos", "guguns", "houses"]),
  },
  methods: {
    sidoList() {
      axios
        .get(`${API_BASE_URL}/address/sido`)
        .then((response) => {
          // this.sidos = response.data;
          state.sidos = response.data;
          console.log("시도", response.data);
        })
        .catch((response) => {
          console.log("에러", response);
        });
    },
    gugunList() {
      axios
        .get(`${API_BASE_URL}/address/gugun?sidoName=${this.sido}`)
        .then((response) => {
          this.guguns = response.data;
          console.log("구군", response.data);
        })
        .catch((response) => {
          console.log("에러", response);
        });
    },
    dongList() {
      axios
        .get(`${API_BASE_URL}/address/dong?gugunName=${this.gugun}`)
        .then((response) => {
          this.dongs = response.data;
          console.log("시도", response.data);
        })
        .catch((response) => {
          console.log("에러", response);
        });
    },
    aptList() {
      axios
        .get(`${API_BASE_URL}/deal/dongList?dongName=${this.dong}`)
        .then((response) => {
          this.house = response;
          console.log(this.house);
        })
        .catch((response) => {
          console.log("에러", response);
        });
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
