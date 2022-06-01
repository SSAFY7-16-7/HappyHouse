<template>
  <div>
    <main class="signin-area">
      <div class="container">
        <div class="row">
          <div class="mx-auto col-xl-4 col-lg-5 col-md-6 d-flex flex-column">
            <div class="mt-8 card sigin-card">
              <div class="text-start">
                <h3 class="font-weight-bolder text-success text-gradient">
                  임시 비밀번호 발급
                </h3>
                <p class="mb-0">
                  임시 비밀번호를 발급 받을 아이디와<br />
                  회원 정보를 입력하세요.
                </p>
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
                  <label>이름</label>
                  <b-form-input
                    v-model="user.name"
                    id="name"
                    type="text"
                    placeholder="name"
                    name="name"
                  ></b-form-input>
                  <label>이메일</label>
                  <b-form-input
                    v-model="user.email"
                    id="email"
                    type="text"
                    placeholder="email"
                    name="email"
                  ></b-form-input>

                  <div class="text-center login-btn-div">
                    <b-button
                      block
                      @click="getTempPassword"
                      class="my-4 mb-2 logbtn"
                      variant="success"
                      size="lg"
                    >
                      임시 비밀번호 발급 받기
                    </b-button>
                  </div>
                </form>
              </div>
              <div
                class="px-1 pt-0 text-center card-footer signin-body px-lg-2"
              >
                <p class="mx-auto mb-4 text-sm">
                  <router-link
                    :to="{ name: 'signin' }"
                    class="text-success text-gradient font-weight-bold"
                  >
                    로그인 하러 가기
                  </router-link>
                </p>
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
    <div>
      <b-modal id="temp-pass" centered title="임시비밀번호 발급 ">
        <div v-if="!isError">
          <p class="my-4">
            임시 비밀 번호는 :<span
              class="temppass-area"
              v-text="tempPass"
            ></span>
            입니다.
          </p>
          <p>로그인 후 비밀번호를 변경 해주세요.</p>
        </div>
        <div v-else>
          <p class="my-4" style="color: red">Error</p>
          <p>{{ this.ErrorMessage }}</p>
        </div>
      </b-modal>
    </div>
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
import { apiInstance } from "@/api/index.js";
const memberStore = "memberStore";
const http = apiInstance();
export default {
  name: "FindpasswordView",
  data() {
    return {
      user: {
        age: "",
        email: null,
        id: null,
        name: null,
        password: "",
      },
      tempPass: "",
      isError: false,
      ErorMessage: "",
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
  mounted() {
    this.$root.$on("bv::modal::hide", (bvEvent, modalid) => {
      //console.log("Modal is about to be shown", bvEvent, modalid);
      this.isError = false;
      this.ErrorMessage = "";
    });
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo", "testUser"]),
    getTempPassword() {
      http
        .post("/user/password", this.user)
        .then(({ data }) => {
          this.tempPass = data.tempPass;
          this.$bvModal.show("temp-pass");
        })
        .catch(({ response }) => {
          //console.log(response);
          this.isError = true;
          this.ErrorMessage = response.data;
          this.$bvModal.show("temp-pass");
        });
    },
  },
};
</script>

<style scoped>
.temppass-area {
  background-color: #d5ee53;
}
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
