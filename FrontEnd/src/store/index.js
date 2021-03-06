import Vue from "vue";
import Vuex, { Store } from "vuex";
import bootstrap from "bootstrap/dist/js/bootstrap.min.js";

import memberStore from "@/store/modules/memberStore.js";
import houseStore from "@/store/modules/houseStore.js";
import interestStore from "@/store/modules/interestStore.js";
import createPersistedState from "vuex-persistedstate";
Vue.use(Vuex);
const store = new Vuex.Store({
  modules: {
    memberStore,
    houseStore,
    bootstrap,
    interestStore,
  },
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
});

export default store;
