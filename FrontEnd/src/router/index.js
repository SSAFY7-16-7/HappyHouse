import Vue from "vue";
import VueRouter from "vue-router";
import SigninView from "@/views/SigninView.vue";
import HomeView from "@/views/HomeView.vue";
import HouseView from "@/views/HouseView.vue";
import AdminView from "@/views/AdminView.vue";
import DealsView from "@/views/DealsView.vue";
import HelpdeskView from "@/views/HelpdeskView.vue";
import MapView from "@/views/MapView.vue";
import MypageView from "@/views/MypageView.vue";
import SignupView from "@/views/SignupView.vue";
import RecommendView from "@/views/RecommendView.vue";
import NoticeBoard from "@/components/NoticeBoard.vue";
import QnaBoard from "@/components/QnaBoard.vue";
import QnaDetail from "@/components/Board/Qna/Qna/QnaDetail.vue";
import QnaRegist from "@/components/Board/Qna/Qna/QnaRegist.vue";
Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "/",
    redirect: "/home",
  },
  {
    path: "/home",
    name: "home",
    component: HomeView,
  },
  {
    path: "/house",
    name: "house",
    component: HouseView,
  },
  {
    path: "/map",
    name: "map",
    component: MapView,
    children: [
      {
        path: "api",
        name: "api",
        component: () => import("@/components/Map/KakaoMap copy.vue"),
      },
    ],
  },
  {
    path: "/deals",
    name: "deals",
    component: DealsView,
  },
  {
    path: "/recommend",
    name: "recommend",
    component: RecommendView,
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
    path: "/mypage",
    name: "mypage",
    component: MypageView,
  },
  {
    path: "/admin",
    name: "admin",
    component: AdminView,
  },
  {
    path: "/helpdesk",
    name: "helpdesk",
    component: HelpdeskView,
    redirect: "/helpdesk/notice/",
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
