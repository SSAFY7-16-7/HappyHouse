<template>
  <div class="mx-3 mt-3 border-radius-xl position-relative">
    <main class="mt-7 main-content main-content-bg board-area">
      <div class="board" title="Card Title" no-body>
        <nav>
          <nav>
            <!-- <b-nav-item>'s with child routes. Note the trailing slash on the first <b-nav-item> -->
            <!-- <router-link
              to="/helpdesk/notice/"
              exact
              exact-active-class="active"
              >NOTICE</router-link
            > -->
            <!-- <router-link to="/helpdesk/qna/" exact exact-active-class="active"
              >Q&A</router-link
            > -->
          </nav>
        </nav>

        <router-view></router-view>
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

<style scoped>
.board {
  width: 100%;
  height: 100%;
}
.board-area {
  height: 80vh;
}
</style>
