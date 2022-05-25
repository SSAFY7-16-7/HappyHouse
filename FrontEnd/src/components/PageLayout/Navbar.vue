<template>
  <!-- Navbar -->
  <nav
    class="top-0 navbar navbar-expand-lg position-absolute z-index-3"
    :class="isBlur ? isBlur : 'shadow-none my-2 navbar-transparent w-100'"
  >
    <div class="container">
      <router-link
        class="navbar-brand font-weight-bolder ms-lg-0 ms-3"
        to="/home"
        v-bind="$attrs"
        :class="isBlur ? 'text-dark' : 'text-white'"
      >
        <!-- Happy House -->
        <img src="https://ifh.cc/g/8vjkpZ.png" alt="" style="width: 80px" />
      </router-link>
      <button
        class="navbar-toggler shadow-none ms-2"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navigation"
        aria-controls="navigation"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon mt-2">
          <span class="navbar-toggler-bar bar1"></span>
          <span class="navbar-toggler-bar bar2"></span>
          <span class="navbar-toggler-bar bar3"></span>
        </span>
      </button>
      <div class="collapse navbar-collapse" id="navigation">
        <ul class="navbar-nav mx-auto">
          <li class="nav-item">
            <router-link class="nav-link me-2" to="/house">
              <i
                class="fa fa-solid fa-map opacity-6 me-1"
                aria-hidden="true"
                :class="isBlur ? 'text-dark' : 'text-white'"
              ></i>
              실거래
            </router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link me-2" to="/interest">
              <i
                class="fa-solid fa-house opacity-6 me-1"
                aria-hidden="true"
                :class="isBlur ? 'text-dark' : 'text-white'"
              ></i>
              관심 리스트
            </router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link me-2" to="/sell">
              <i
                class="fa-solid fa-house opacity-6 me-1"
                aria-hidden="true"
                :class="isBlur ? 'text-dark' : 'text-white'"
              ></i>
              매물
            </router-link>
          </li>
          <!-- <li class="nav-item">
            <router-link class="nav-link me-2" to="/recommend">
              <i
                class="fa-solid fa-magnifying-glass-location opacity-6 me-1"
                aria-hidden="true"
                :class="isBlur ? 'text-dark' : 'text-white'"
              ></i>
              지역 추천
            </router-link>
          </li> -->

          <li class="nav-item">
            <router-link class="nav-link me-2" to="/helpdesk">
              <i
                class="fas fa-user-circle opacity-6 me-1"
                aria-hidden="true"
                :class="isBlur ? 'text-dark' : 'text-white'"
              ></i>
              HELP DESK
            </router-link>
          </li>
          <!-- 
          <li class="nav-item">
            <router-link class="nav-link me-2" to="/signup">
              <i
                class="fas fa-key opacity-6 me-1"
                aria-hidden="true"
                :class="isBlur ? 'text-dark' : 'text-white'"
              ></i>
              Sign Up
            </router-link>
          </li> -->

          <li class="nav-item" v-if="userInfo">
            <router-link class="nav-link me-2" to="/mypage">
              <i
                class="fas fa-key opacity-6 me-1"
                aria-hidden="true"
                :class="isBlur ? 'text-dark' : 'text-white'"
              ></i>
              My Page
            </router-link>
          </li>
          <li class="nav-item" v-else>
            <router-link class="nav-link me-2" to="/signin">
              <i
                class="fas fa-key opacity-6 me-1"
                aria-hidden="true"
                :class="isBlur ? 'text-dark' : 'text-white'"
              ></i>
              Sign In
            </router-link>
          </li>
          <li class="nav-item" v-if="isAdmin">
            <router-link class="nav-link me-2" to="/admin">
              <i
                class="fas fa-key opacity-6 me-1"
                aria-hidden="true"
                :class="isBlur ? 'text-dark' : 'text-white'"
              ></i>
              Admin
            </router-link>
          </li>
        </ul>
        <div class="testbtns">
          <b-button @click="testAdmin" size="sm">관리자</b-button>
          <b-button @click="testUser" size="sm">일반유저 (id: ssafy)</b-button>
          <b-button @click="testLogout" size="sm">로그아웃 </b-button>
        </div>
      </div>
    </div>
  </nav>
  <!-- End Navbar -->
</template>

<script>
import downArrWhite from "@/assets/img/down-arrow-white.svg";
import downArrBlack from "@/assets/img/down-arrow-dark.svg";
import { mapState, mapActions } from "vuex";

export default {
  name: "navbar",
  data() {
    return {
      downArrWhite,
      downArrBlack,
    };
  },
  props: {
    btnBackground: String,
    isBlur: String,
    darkMode: {
      type: Boolean,
      default: false,
    },
  },
  methods: {
    ...mapActions("memberStore", ["testAdmin", "testUser", "testLogout"]),
  },
  computed: {
    ...mapState("memberStore", ["isLogin", "userInfo", "isAdmin"]),

    darkModes() {
      return {
        "text-dark": this.darkMode,
      };
    },
  },
};
</script>

<style lang="css">
.testbtns {
  display: flex;
  align-content: center;
  justify-content: center;
}
.testbtns button {
  display: block;
  margin: 0;
  margin-right: 3px;
}
</style>
