<template>
  <div style="width: 100%; height: 100%">
    <div class="mx-3 mt-3 border-radius-xl position-relative">
      <main class="mt-0 main-content main-content-bg">
        <section>
          <div class="page-header min-vh-75">
            <kakao-map></kakao-map>
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
import KakaoMap from "@/components/Map/KakaoMap.vue";
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
    KakaoMap,
  },
};
</script>
