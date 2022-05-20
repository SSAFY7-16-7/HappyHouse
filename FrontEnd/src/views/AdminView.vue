<template>
  <div>
    <div class="mx-3 mt-3 border-radius-xl position-relative">
      <main class="mt-7 main-content main-content-bg">
        <section>
          <div class="page-header min-vh-75">
            <div class="container">
              <div class="row">
                <div class="col-12">
                  <user-list />
                </div>
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
import UserList from "@/components/Userlist.vue";
import BoardList from "@/components/Board/BoardList.vue";
const body = document.getElementsByTagName("body")[0];
import { mapMutations } from "vuex";

export default {
  name: "SignIn",
  components: {
    Navbar,
    AppFooter,
    VsudInput,
    VsudSwitch,
    VsudButton,
    BoardList,
    UserList,
  },
};
</script>
