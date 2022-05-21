<template>
  <div>
    <div class="mx-3 mt-3 border-radius-xl position-relative">
      <main class="mt-7 main-content main-content-bg board-area">
        <section>
          <div class="page-header min-vh-75">
            <div class="container">
              <router-view></router-view>
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
