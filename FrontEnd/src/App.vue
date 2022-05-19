<template>
  <div id="app" class="">
    <main
      class="main-content position-relative max-height-vh-100 h-100 border-radius-lg"
      :style="this.$store.state.isRTL ? 'overflow-x: hidden' : ''"
    >
      <!-- nav -->
      <navbar
        class="position-sticky blur shadow-blur mt-4 left-auto top-1 z-index-sticky"
        :textWhite="this.$store.state.isAbsolute ? 'text-white opacity-8' : ''"
        :minNav="navbarMinimize"
        v-if="this.$store.state.showNavbar"
      />
      <router-view />
      <app-footer v-show="this.$store.state.showFooter" />
    </main>
  </div>
</template>
<script>
import Sidenav from "@/components/Sidenav";
import Configurator from "@/components/Configurator.vue";
import Navbar from "@/components/Navbars/Navbar.vue";
import AppFooter from "@/components/Footer.vue";
import { mapMutations } from "vuex";
export default {
  name: "App",
  components: {
    Sidenav,
    Configurator,
    Navbar,
    AppFooter,
  },
  methods: {
    // ...mapMutations(["toggleConfigurator", "navbarMinimize"]),
  },

  computed: {},
  beforeMount() {
    // this.$store.state.isTransparent = "bg-transparent";
  },
};
</script>
