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
      <transition name="slide-fade" mode="out-in">
        <router-view />
      </transition>

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

<style>
.fade-enter {
  opacity: 0;
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s ease-out;
}

.fade-leave-to {
  opacity: 0;
}

.slide-fade-enter {
  transform: translateX(10px);
  opacity: 0;
}

.slide-fade-enter-active,
.slide-fade-leave-active {
  transition: all 0.2s ease;
}

.slide-fade-leave-to {
  transform: translateX(-10px);
  opacity: 0;
}
</style>
