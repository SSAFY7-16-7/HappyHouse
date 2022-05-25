<template>
  <div>
    <main class="signin-area">
      <div class="container">
        <div class="row">
          <div class="mx-auto col-xl-4 col-lg-5 col-md-6 d-flex flex-column">
            <div class="mt-8 card sigin-card">
              <div class="text-start">
                <h3 class="font-weight-bolder text-success text-gradient">
                  Welcome back
                </h3>
                <p class="mb-0">아이디와 패스워드를 입력하세요.</p>
              </div>
              <div class="card-body signin-body">
                <form role="form" class="text-start">
                  <label>Id</label>
                  <b-form-input
                    v-model="user.id"
                    id="email"
                    type="email"
                    placeholder="Id"
                    name="email"
                  ></b-form-input>
                  <label>Password</label>
                  <b-form-input
                    v-model="user.password"
                    id="password"
                    type="password"
                    placeholder="Password"
                    name="password"
                  ></b-form-input>
                  <vsud-switch id="rememberMe" name="rememberMe" checked>
                    Remember me
                  </vsud-switch>
                  <div class="text-center login-btn-div">
                    <b-button
                      block
                      @click="confirm"
                      class="my-4 mb-2 logbtn"
                      variant="success"
                      size="lg"
                      >로그인
                    </b-button>
                    <div @click="kakaoLoginBtn">
                      <img
                        src="@/assets/img/pyj/kakao_login_medium.png"
                        alt=""
                        style="margin-bottom: 8px"
                      />
                    </div>
                  </div>
                </form>
              </div>
              <div
                class="px-1 pt-0 text-center card-footer signin-body px-lg-2"
              >
                <p class="mx-auto mb-4 text-sm">
                  계정이 없다면?
                  <router-link
                    :to="{ name: 'signup' }"
                    class="text-success text-gradient font-weight-bold"
                    >회원가입</router-link
                  >
                </p>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div
              class="top-0 oblique position-absolute h-100 d-md-block d-none me-n8"
            >
              <div
                class="bg-cover oblique-image position-absolute fixed-top ms-auto h-100 z-index-0 ms-n6"
                :style="{
                  backgroundImage:
                    //'url(' + require('@/assets/img/apt/apt2.jpeg') + ')',
                    'url(https://ifh.cc/g/WslfFc.jpg)',
                }"
              ></div>
            </div>
          </div>
        </div>
      </div>
    </main>
  </div>
</template>

<script>
import Navbar from "@/components/PageLayout/Navbar.vue";
import AppFooter from "@/components/PageLayout/Footer.vue";
import VsudInput from "@/components/soft-ui-components/VsudInput.vue";
import VsudSwitch from "@/components/soft-ui-components/VsudSwitch.vue";
import VsudButton from "@/components/soft-ui-components/VsudButton.vue";

import { mapState, mapActions } from "vuex";
import { login } from "@/api/member.js";

const memberStore = "memberStore";

export default {
  name: "SigninView",
  data() {
    return {
      user: {
        id: null,
        password: null,
      },
    };
  },
  components: {
    Navbar,
    AppFooter,
    VsudInput,
    VsudSwitch,
    VsudButton,
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError"]),
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo", "testUser"]),
    async confirm() {
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        await this.getUserInfo(token);
        this.$router.push({ name: "home" });
      }
    },
    kakaoLoginBtn: function () {
      window.Kakao.init("ff39ee8d511b533648d3baac6dd7efff"); // Kakao Developers에서 요약 정보 -> JavaScript 키

      if (window.Kakao.Auth.getAccessToken()) {
        window.Kakao.API.request({
          url: "/v1/user/unlink",
          success: function (response) {
            console.log("accessToken", response);
          },
          fail: function (error) {
            console.log("accessToken error", error);
          },
        });
        window.Kakao.Auth.setAccessToken(undefined);
      }

      const base = this;

      window.Kakao.Auth.login({
        success: function () {
          window.Kakao.API.request({
            url: "/v2/user/me",
            data: {
              property_keys: ["kakao_account.email"],
            },
            success: async function (response) {
              console.log("login", response.kakao_account.email);
              // this.user = response.kakao_account.email;
              base.testUser();
              base.$router.push({ name: "home" });
            },
            fail: function (error) {
              console.log(error);
            },
          });
        },
        fail: function (error) {
          console.log(error);
        },
      });
    },
  },
};
</script>

<style scoped>
.sigin-card {
  border: 1px solid #9a9a9a4f !important;
  padding: 30px;
}
.signin-body {
  background-color: #fff !important;
}
.signin-area {
  height: 100vh;
  display: flex;
  align-items: center;
}
.text-gradient.text-success {
  background-image: linear-gradient(310deg, #d5ee53, #2196f3);
}
.btn-success {
  color: #000;
  background-color: #2598f3;
  border-color: #2196f3;
}

.login-btn-div {
  display: flex;
  flex-direction: row;
  align-items: flex-end;
  justify-content: space-around;
}
</style>
