import jwt_decode from "jwt-decode";
import { login, update } from "@/api/member.js";
import { findById } from "../../api/member";

const memberStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    userInfo: null,
    isAdmin: false,
    /// 테스트용
    // isLogin: true,
    // isLoginError: false,
    // userInfo: {
    //   id: "admin",
    //   password: "admin",
    //   email: "admin",
    //   age: "1",
    //   name: "admin",
    // },
    // isAdmin: true,
    // end 테스트 값
  },
  getters: {
    checkUserInfo: function (state) {
      return state.userInfo;
    },
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
    SET_IS_ADMIN: (state, isAdmin) => {
      state.isAdmin = isAdmin;
    },
  },
  actions: {
    async userConfirm({ commit }, user) {
      await login(
        user,
        (response) => {
          if (response.data.message === "success") {
            let token = response.data["access-token"];
            console.log(token);
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);
            sessionStorage.setItem("access-token", token);
            if (user.id === "admin") commit("SET_IS_ADMIN", true);
          } else {
            console.log(response);
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
          }
        },
        () => {}
      );
    },
    getUserInfo({ commit }, token) {
      console.log("get logininfo ");
      let decode_token = jwt_decode(token);
      findById(
        decode_token.userid,
        (response) => {
          if (response.data.message === "success") {
            commit("SET_USER_INFO", response.data.userInfo);
          } else {
            console.log("유저 정보 없음!!");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async updateUserInfo({ commit }, user) {
      console.log("updateuser");
      await update(
        user,
        (res) => {
          console.log(res.data);
          commit("SET_USER_INFO", res.data);
        },
        () => {}
      );
    },
    // ui 테스트를 위한 함수
    testAdmin({ commit }) {
      commit("SET_IS_LOGIN", true);
      commit("SET_IS_ADMIN", true);
      commit("SET_USER_INFO", {
        id: "admin",
        password: "admin",
        email: "admin",
        age: "1",
        name: "admin",
      });
    },
    testUser({ commit }) {
      commit("SET_IS_LOGIN", true);
      commit("SET_IS_ADMIN", false);

      commit("SET_USER_INFO", {
        id: "ssafy",
        password: "ssfay",
        email: "ssafy123",
        age: "1",
        name: "ssafy",
      });
    },
    testLogout({ commit }) {
      commit("SET_USER_INFO", null);
      commit("SET_IS_LOGIN", false);
      commit("SET_IS_ADMIN", false);
    },
  },
};

export default memberStore;
