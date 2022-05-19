<template>
  <div>
    <div class="mx-3 mt-3 border-radius-xl position-relative">
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
      <main class="mt-7 main-content main-content-bg board-area">
        <section>
          <div class="page-header min-vh-75">
            <div class="container">
              <b-card title="Card Title" no-body>
                <b-card-header header-tag="nav">
                  <b-nav card-header tabs>
                    <!-- <b-nav-item>'s with child routes. Note the trailing slash on the first <b-nav-item> -->
                    <b-nav-item
                      to="/helpdesk/notice/"
                      exact
                      exact-active-class="active"
                      >NOTICE</b-nav-item
                    >
                    <b-nav-item
                      to="/helpdesk/qna/"
                      exact
                      exact-active-class="active"
                      >Q&A</b-nav-item
                    >
                  </b-nav>
                </b-card-header>

                <b-card-body>
                  <router-view></router-view>
                </b-card-body>
              </b-card>
            </div>
          </div>
        </section>
      </main>
    </div>
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
  name: "HelpdeskView",
  components: {
    Navbar,
    AppFooter,
    VsudInput,
    VsudSwitch,
    VsudButton,
  },
};
</script>

<style scoped></style>
