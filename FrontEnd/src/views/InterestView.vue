<template>
  <div>
    <div class="mx-3 mt-3 border-radius-xl position-relative">
      <main class="mt-0 main-content main-content-bg">
        <section>
          <div class="page-header min-vh-75">
            <div class="container">
              <div class="inter-sells">
                <h3>관심매물리스트</h3>
                <interest-list :list="listSell" category="sell"></interest-list>
              </div>

              <div class="ineter-apts">
                <h3>관심 아파트 리스트</h3>
                <interest-list :list="listApt" category="apt"></interest-list>
              </div>
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
import InterestItem from "@/components/Interest/InterestList.vue";
import { apiInstance } from "@/api/index.js";
import { mapMutations } from "vuex";
import InterestList from "../components/Interest/InterestList.vue";
const http = apiInstance();
export default {
  name: "InterestView",
  components: {
    Navbar,
    AppFooter,
    VsudInput,
    VsudSwitch,
    VsudButton,
    InterestItem,
    InterestList,
  },
  data() {
    return {
      listSell: [],
      listApt: [],
    };
  },
  mounted() {
    http.get("/interest/aptlist/ssafy").then(({ data }) => {
      this.listApt = data;
    });
    http.get("/interest/selllist/ssafy").then(({ data }) => {
      this.listSell = data;
    });
  },
};
</script>
