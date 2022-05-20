<template>
  <div>
    <main class="mt-0 main-content main-content-bg">
      <section>
        <div class="page-header min-vh-75">
          <div class="container">
            <div class="row">
              <div
                class="mx-auto col-xl-4 col-lg-5 col-md-6 d-flex flex-column"
              >
                <div class="mt-8 card card-plain">
                  <div class="pb-0 card-header text-start">
                    <h3 class="font-weight-bolder text-success text-gradient">
                      MY PAGE
                    </h3>
                    <p class="mb-0">회원정보 수정</p>
                  </div>
                  <div class="card-body">
                    <form role="form">
                      <p>ID : {{ userInfo.id }}</p>
                      <div class="mb-3">
                        <label for="type - password">Password</label>
                        <b-input
                          v-model="user.id"
                          id="password"
                          aria-label="Password"
                        />
                      </div>

                      <div class="mb-3">
                        <label for="type - name">Name</label>
                        <b-input v-model="user.name" id="name" />
                      </div>
                      <div class="mb-3">
                        <label for="type - email">Email</label>
                        <b-input v-model="user.email" id="email" />
                      </div>
                      <div class="mb-3">
                        <label for="type - age">Age</label>
                        <b-input v-model="user.age" id="number" />
                      </div>

                      <div class="text-center">
                        <b-button
                          @click="updateUserInfo(user)"
                          color="dark"
                          full-width
                          variant="gradient"
                          class="my-4 mb-2"
                          >정보 수정
                        </b-button>
                      </div>
                    </form>
                  </div>
                  <div class="px-1 pt-0 text-center card-footer px-lg-2"></div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
    </main>
  </div>
</template>

<script>
import Navbar from "@/components/PageLayout/Navbar.vue";
import AppFooter from "@/components/PageLayout/Footer.vue";
import VsudInput from "@/components/soft-ui-components/VsudInput.vue";
import VsudSwitch from "@/components/soft-ui-components/VsudSwitch.vue";
import VsudButton from "@/components/soft-ui-components/VsudButton.vue";
import { mapMutations, mapState, mapActions } from "vuex";

export default {
  name: "MypageView",
  data() {
    return {
      user: {
        id: "",
        password: "",
        email: "",
        age: "",
      },
    };
  },
  components: {
    Navbar,
    AppFooter,
    VsudInput,
    VsudSwitch,
    VsudButton,
  },
  computed: {
    ...mapState("memberStore", ["userInfo", "isLogin"]),
  },
  methods: {
    ...mapActions("memberStore", ["updateUserInfo"]),
  },
  mounted() {
    this.user.id = this.userInfo.id;
    this.user.password = this.userInfo.password;
    this.user.name = this.userInfo.name;
    this.user.email = this.userInfo.email;
    this.user.age = this.userInfo.age;
  },
};
</script>
