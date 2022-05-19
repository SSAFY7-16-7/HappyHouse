<template>
  <div>
    <div
      class="w-auto h-auto collapse navbar-collapse max-height-vh-100 h-100"
      id="sidenav-collapse-main"
    >
      <ul class="navbar-nav">
        <li class="nav-item">
          <sidenav-collapse navText="Home" :to="{ name: 'home' }">
            <template #icon>
              <shop />
            </template>
          </sidenav-collapse>
        </li>
        <li class="nav-item">
          <sidenav-collapse navText="Search" :to="{ name: 'search' }">
            <template #icon>
              <office />
            </template>
          </sidenav-collapse>
        </li>
        <!-- <li class="nav-item">
          <sidenav-collapse navText="Reviews " :to="{ name: '/' }">
            <template #icon>
              <customer-support />
            </template>
          </sidenav-collapse>
        </li> -->
        <li class="nav-item">
          <sidenav-collapse navText="Sign In" :to="{ name: 'Sign In' }">
            <template #icon>
              <document />
            </template>
          </sidenav-collapse>
        </li>
        <!-- <li class="nav-item">
          <sidenav-collapse navText="Sign Up" :to="{ name: 'Sign Up' }">
            <template #icon>
              <spaceship />
            </template>
          </sidenav-collapse>
        </li> -->

        <li class="mt-3 nav-item">
          <h6
            class="text-xs ps-4 text-uppercase font-weight-bolder opacity-6"
            :class="this.$store.state.isRTL ? 'me-4' : 'ms-2'"
          >
            Community
          </h6>
        </li>
        <!-- <li class="nav-item">
          <sidenav-collapse navText="공지사항" :to="{ name: 'Profile' }">
            <template #icon>
              <customer-support />
            </template>
          </sidenav-collapse>
        </li>
        <li class="nav-item">
          <sidenav-collapse navText="Q & A" :to="{ name: 'Profile' }">
            <template #icon>
              <customer-support />
            </template>
          </sidenav-collapse>
        </li>
        <li class="nav-item">
          <sidenav-collapse navText="자유 게시판 " :to="{ name: 'Profile' }">
            <template #icon>
              <customer-support />
            </template>
          </sidenav-collapse>
        </li> -->
      </ul>
    </div>
    <div class="pt-3 mx-3 mt-3 sidenav-footer">
      <sidenav-card
        :class="cardBg"
        textPrimary="Happy House Project"
        textSecondary="프로젝트 소개"
        route=""
        label="Visit Repository"
        icon="ni ni-laptop"
      />
      <a class="btn bg-gradient-success mt-4 w-100" href="" type="button"
        >뭐 써야 할지
      </a>
    </div>
  </div>
</template>
<script>
import SidenavCollapse from "@/components/Sidenav/SidenavCollapse.vue";
import SidenavCard from "@/components/Sidenav/SidenavCard.vue";
import Shop from "@/components/soft-ui-components/Icon/Shop.vue";
import Office from "@/components/soft-ui-components/Icon/Office.vue";
import CreditCard from "@/components/soft-ui-components/Icon/CreditCard.vue";
import Box3d from "@/components/soft-ui-components/Icon/Box3d.vue";
import CustomerSupport from "@/components/soft-ui-components/Icon/CustomerSupport.vue";
import Document from "@/components/soft-ui-components/Icon/Document.vue";
import Spaceship from "@/components/soft-ui-components/Icon/Spaceship.vue";
import Settings from "@/components/soft-ui-components/Icon/Settings.vue";

export default {
  name: "SidenavList",
  props: {
    cardBg: String,
  },
  data() {
    return {
      title: "Soft UI Dashboard PRO",
      controls: "dashboardsExamples",
      isActive: "active",
    };
  },
  components: {
    SidenavCollapse,
    SidenavCard,
    Shop,
    Office,
    CreditCard,
    Box3d,
    CustomerSupport,
    Document,
    Spaceship,
    Settings,
  },
  created() {
    console.log(this.getRoute());
  },
  methods: {
    getRoute() {
      const routeArr = this.$route.path.split("/");
      return routeArr[1];
    },
  },
};
</script>
