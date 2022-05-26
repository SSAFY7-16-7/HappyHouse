import Vue from "vue";

import App from "./App.vue";
import router from "./router";
import store from "./store";

import { BootstrapVue, IconsPlugin } from "bootstrap-vue";
import "./assets/css/nucleo-icons.css";
import "./assets/css/nucleo-svg.css";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
import SoftUIDashboard from "./soft-ui-dashboard";

Vue.config.productionTip = false;
// Vue.use(router);
Vue.use(BootstrapVue);
Vue.use(IconsPlugin);
Vue.use(SoftUIDashboard);
// Vue.use(store);

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
