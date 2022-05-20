<template>
  <div id="app" class="">
    <!-- nav -->
    <div class="top-0">
      <navbar
        is-blur="blur py-2 start-0 end-0  shadow"
        btn-background="bg-gradient-success"
        :dark-mode="true"
      />
    </div>
    <main
      class="main-content position-relative max-height-vh-100 h-100 border-radius-lg"
    >
      <router-view />
      <app-footer />
    </main>
  </div>
</template>
<script>
import Sidenav from "@/components/Sidenav";
import Configurator from "@/components/Configurator.vue";
import Navbar from "@/components/PageLayout/Navbar.vue";
import AppFooter from "@/components/Footer.vue";
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
