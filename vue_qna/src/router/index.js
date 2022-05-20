import Vue from "vue";
import VueRouter from "vue-router";

import QnaView from "@/views/QnaView.vue";
import QnaList from "@/components/qna/QnaList.vue";
import QnaRegist from "@/components/qna/QnaRegist.vue";
import QnaDetail from "@/components/qna/QnaDetail.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/happyhouse/qnaVue",
    component: QnaView,
    name: "QnaView",
    redirect: "/happyhouse/qnaVue/qnalist",
    children: [
      {
        path: "qnalist",
        component: QnaList,
        name: "QnaList",
      },
      {
        path: "qnaregist",
        component: QnaRegist,
        name: "QnaRegist",
      },
      {
        path: "qnadetail",
        component: QnaDetail,
        name: "QnaDetail",
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
