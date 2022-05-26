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
    <main class="main-content position-relative border-radius-lg">
      <transition name="slide-fade" mode="out-in">
        <router-view />
      </transition>

      <app-footer />
    </main>
  </div>
</template>
<script>
import Navbar from "@/components/PageLayout/Navbar.vue";
import AppFooter from "@/components/Footer.vue";
export default {
  name: "App",
  components: {
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
body {
  /* background-image: linear-gradient(
    to right top,
    #4f91f5,
    #5c97f8,
    #689dfa,
    #73a3fd,
    #7da9ff
  );
  background-size: cover;
  background-position: center;
  background: url("@/assets/img/bg/mesh-gradient.png") no-repeat center;
  background-size: cover; */
}
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
