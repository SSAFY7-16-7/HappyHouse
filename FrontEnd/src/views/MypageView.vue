<template>
  <div>
    <div class="container top-0 position-sticky z-index-sticky">
      <div class="row">
        <div class="col-12">
          <navbar
            is-blur="blur blur-rounded my-3 py-2 start-0 end-0 mx-4 shadow"
            btn-background="bg-gradient-success"
            :dark-mode="true"
          />
        </div>
      </div>
    </div>
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
                      MY PAGE
                    </h3>
                    <p class="mb-0">회원정보 수정</p>
                  </div>
                  <div class="card-body">
                    <form role="form">
                      <div class="mb-3">
                        <vsud-input
                          id="password"
                          type="password"
                          placeholder="Password"
                          aria-label="Password"
                        />
                      </div>

                      <div class="mb-3">
                        <vsud-input
                          id="name"
                          type="text"
                          placeholder="Name"
                          aria-label="Name"
                        />
                      </div>
                      <div class="mb-3">
                        <vsud-input
                          id="email"
                          type="email"
                          placeholder="Email"
                          aria-label="Email"
                        />
                      </div>
                      <div class="mb-3">
                        <vsud-input
                          id="age"
                          type="age"
                          placeholder="Age"
                          aria-label="age"
                        />
                      </div>

                      <div class="text-center">
                        <vsud-button
                          color="dark"
                          full-width
                          variant="gradient"
                          class="my-4 mb-2"
                          >정보 수정
                        </vsud-button>
                      </div>
                    </form>
                  </div>
                  <div class="px-1 pt-0 text-center card-footer px-lg-2"></div>
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
  name: "MypageView",
  data() {
    return {};
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
