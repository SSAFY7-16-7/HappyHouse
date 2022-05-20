<template>
  <div>
    <main class="mt-0 main-content main-content-bg">
      <section>
        <div class="page-header min-vh-75">
          <div class="container">
            <div class="row">
              <div
                class="mx-auto col-xl-4 col-lg-5 col-md-6 d-flex flex-column"
              >
                <div class="mt-8 card card-plain">
                  <div class="pb-0 card-header text-start">
                    <h3 class="font-weight-bolder text-success text-gradient">
                      Welcome back
                    </h3>
                    <p class="mb-0">아이디와 패스워드를 입력하세요.</p>
                  </div>
                  <div class="card-body">
                    <form role="form" class="text-start">
                      <label>Id</label>
                      <b-form-input
                        v-model="id"
                        id="email"
                        type="email"
                        placeholder="Id"
                        name="email"
                      ></b-form-input>
                      <label>Password</label>
                      <b-form-input
                        v-model="password"
                        id="password"
                        type="password"
                        placeholder="Password"
                        name="password"
                      ></b-form-input>
                      <vsud-switch id="rememberMe" name="rememberMe" checked>
                        Remember me
                      </vsud-switch>
                      <div class="text-center">
                        <vsud-button
                          class="my-4 mb-2"
                          variant="gradient"
                          color="success"
                          full-width
                          >로그인
                        </vsud-button>
                      </div>
                    </form>
                  </div>
                  <div class="px-1 pt-0 text-center card-footer px-lg-2">
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
                        'url(' +
                        require('@/assets/img/curved-images/curved9.jpg') +
                        ')',
                    }"
                  ></div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
    </main>
  </div>
</template>

<script>
import Navbar from "@/components/PageLayout/Navbar.vue";
import AppFooter from "@/components/PageLayout/Footer.vue";
import VsudInput from "@/components/soft-ui-components/VsudInput.vue";
import VsudSwitch from "@/components/soft-ui-components/VsudSwitch.vue";
import VsudButton from "@/components/soft-ui-components/VsudButton.vue";
const body = document.getElementsByTagName("body")[0];
import { mapMutations } from "vuex";

export default {
  name: "SigninView",
  data() {
    return {
      id: "",

      password: "",
    };
  },
  components: {
    Navbar,
    AppFooter,
    VsudInput,
    VsudSwitch,
    VsudButton,
  },
};
</script>
