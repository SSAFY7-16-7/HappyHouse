<template>
  <div>
    <div class="mx-3 mt-3 border-radius-xl position-relative">
      <main class="mt-0 main-content main-content-bg">
        <section>
          <div class="page-header min-vh-75">
            <div class="container">
              <h1>Map 보여줄 페이지</h1>
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
  name: "MapView",
  components: {
    Navbar,
    AppFooter,
    VsudInput,
    VsudSwitch,
    VsudButton,
  },
};
</script>
