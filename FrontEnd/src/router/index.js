import Vue from "vue";
import VueRouter from "vue-router";
import SigninView from "@/views/SigninView.vue";
import HomeView from "@/views/HomeView.vue";
import HouseView from "@/views/HouseView.vue";
import AdminView from "@/views/AdminView.vue";
import SellView from "@/views/SellView.vue";
import HelpdeskView from "@/views/HelpdeskView.vue";
import MypageView from "@/views/MypageView.vue";
import SignupView from "@/views/SignupView.vue";
import RecommendView from "@/views/RecommendView.vue";
import NoticeBoard from "@/components/NoticeBoard.vue";
import QnaBoard from "@/components/QnaBoard.vue";
import QnaDetail from "@/components/Board/Qna/Qna/QnaDetail.vue";
import QnaRegist from "@/components/Board/Qna/Qna/QnaRegist.vue";
import SellRegist from "@/components/Sell/SellRegist.vue";
import SellList from "@/components/Sell/SellList.vue";
import SellItemDetail from "@/components/Sell/SellItemDetail.vue";
import InterestView from "@/views/InterestView.vue";
import IndexView from "@/views/IndexView.vue";
import DealChart from "@/components/Chart/DealChart.vue";
import HouseInfo from "@/components/house/detail/HouseInfo.vue";
import HouseDeal from "@/components/house/detail/HouseDeal.vue";
import HouseInfra from "@/components/house/detail/HouseInfra.vue";
import InformationView from "@/views/InformationView.vue";
import FindpasswordView from "@/views/FindpasswordView.vue";

import store from "@/store/index.js";
Vue.use(VueRouter);
const onlyAuthUser = async (to, from, next) => {
  //console.log(store);
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  //console.log(checkUserInfo);
  const getUserInfo = store._actions["memberStore/getUserInfo"];
  //console.log(getUserInfo);
  let token = sessionStorage.getItem("access-token");
  if (checkUserInfo == null && token) {
    await getUserInfo(token);
  }
  if (checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    next({ name: "signin" });
    // router.push({ name: "signIn" });
  } else {
    // //console.log("로그인 했다.");
    next();
  }
};

const routes = [
  {
    path: "/",
    name: "/",
    redirect: "/home",
  },
  {
    path: "/chart",
    name: "/chart",
    component: DealChart,
  },
  {
    path: "/home",
    name: "home",
    component: IndexView,
  },
  {
    path: "/infomation",
    name: "information",
    component: InformationView,
  },
  {
    path: "/house",
    name: "house",
    component: HouseView,
    children: [
      {
        path: "info",
        name: "info",
        component: HouseInfo,
      },
      {
        path: "deal",
        name: "deal",
        component: HouseDeal,
      },
      {
        path: "infra",
        name: "infra",
        component: HouseInfra,
      },
    ],
  },
  {
    path: "/sell",
    name: "sell",
    component: SellView,
    redirect: "/sell/list",
    children: [
      {
        path: "list",
        name: "sellList",
        component: SellList,
      },
      {
        path: "detail",
        name: "sellDeatil",
        component: SellItemDetail,
      },
      {
        path: "sellregist",
        name: "sellregist",
        beforeEnter: onlyAuthUser,
        component: SellRegist,
      },
    ],
  },

  {
    path: "/recommend",
    name: "recommend",
    component: RecommendView,
  },
  {
    path: "/interest",
    name: "interest",
    // beforeEnter: onlyAuthUser,
    component: InterestView,
  },
  {
    path: "/signin",
    name: "signin",
    component: SigninView,
  },
  {
    path: "/signup",
    name: "signup",
    component: SignupView,
  },
  {
    path: "/findpassword",
    name: "findpassword",
    component: FindpasswordView,
  },
  {
    path: "/mypage",
    name: "mypage",
    beforeEnter: onlyAuthUser,
    component: MypageView,
  },
  {
    path: "/admin",
    name: "admin",
    beforeEnter: onlyAuthUser,
    component: AdminView,
  },
  {
    path: "/helpdesk",
    name: "helpdesk",
    component: HelpdeskView,
    redirect: "/helpdesk/qna/",
    children: [
      {
        path: "notice",
        name: "notice",
        component: NoticeBoard,
      },
      {
        path: "qna",
        name: "qna",
        component: QnaBoard,
        children: [{ path: "article/:no", name: "qnaDetail" }],
      },
      {
        path: "qnadetail",
        component: QnaDetail,
        name: "QnaDetail",
      },
      {
        path: "qnaregist",
        beforeEnter: onlyAuthUser,
        component: QnaRegist,
        name: "QnaRegist",
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
  linkActiveClass: "active",
});

export default router;
