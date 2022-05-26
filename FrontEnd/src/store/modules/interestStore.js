import {
  sidoList,
  gugunList,
  dongList,
  houseList,
  housDeal,
} from "@/api/house.js";

const interestStore = {
  namespaced: true,
  state: {
    sidos: {},
    guguns: {},
    dongs: {},
    houses: [],
    house: null,
    deals: null,
    none: true,
    category: "apt",
  },

  getters: {},

  mutations: {
    SET_CATEGORY: (state, category) => {
      state.category = category;
    },
    SET_SIDO_LIST: (state, sidos) => {
      sidos.forEach((sido) => {
        state.sidos.push(sido);
      });
    },
    SET_GUGUN_LIST: (state, guguns) => {
      guguns.forEach((gugun) => {
        state.guguns.push(gugun);
      });
    },
    SET_DONG_LIST: (state, dongs) => {
      dongs.forEach((dong) => {
        state.dongs.push(dong);
      });
    },

    CLEAR_SIDO_LIST: (state) => {
      state.sidos = [];
    },
    CLEAR_GUGUN_LIST: (state) => {
      state.guguns = [];
    },
    CLEAR_DONG_LIST: (state) => {
      state.dongs = [];
    },

    CLEAR_HOUSE_LIST: (state) => {
      state.houses = [];
    },
    SET_HOUSE_LIST: (state, houses) => {
      //console.log("SET_HOUSE_LIST", houses);
      houses.forEach((house) => {
        state.houses.push(house);
      });
      // state.houses = houses;
    },
    SET_DETAIL_HOUSE: (state, house) => {
      state.house = house;
    },
    SET_DETAIL_DEAL: (state, deals) => {
      //console.log("SET_DETAIL_DEAL", deals);
      state.deals = deals;
    },
    SET_NONE: (state, data) => {
      state.none = data;
    },
  },

  actions: {
    getSido: ({ commit }) => {
      sidoList(
        ({ data }) => {
          // //console.log(data);
          commit("SET_SIDO_LIST", data);
        },
        (error) => {
          //console.log(error);
        }
      );
    },
    getGugun: ({ commit }, sidoName) => {
      const params = {
        sidoName: sidoName,
      };
      gugunList(
        params,
        ({ data }) => {
          // //console.log(commit, response);
          commit("SET_GUGUN_LIST", data);
        },
        (error) => {
          //console.log(error);
        }
      );
    },
    getDong: ({ commit }, gugunName) => {
      const params = {
        gugunName: gugunName,
      };
      dongList(
        params,
        ({ data }) => {
          // //console.log(commit, response);
          commit("SET_DONG_LIST", data);
        },
        (error) => {
          //console.log(error);
        }
      );
    },
    getHouseList: ({ commit }, dongName) => {
      const params = {
        dongName: dongName,
      };
      houseList(
        params,
        ({ data }) => {
          commit("SET_HOUSE_LIST", data);
        },
        (error) => {
          //console.log("getHouseList 에러", error);
        }
      );
    },
    detailHouse: ({ commit }, house) => {
      commit("SET_DETAIL_HOUSE", house);

      const params = {
        aptCode: house.aptCode,
      };
      housDeal(
        params,
        ({ data }) => {
          // 나중에 house.일련번호를 이용하여 API 호출
          //console.log("detailHouse", data);
          commit("SET_DETAIL_DEAL", data);
        },
        (error) => {
          //console.log("detailHouse 에러", error);
        }
      );
    },
    setNoneFalse: ({ commit }, data) => {
      //console.log("setNoneFalse");
      commit("SET_NONE", data);
    },
    setCategory: ({ commit }, data) => {
      //console.log("set category");
      commit("SET_CATEGORY", data);
    },
  },
};

export default interestStore;
