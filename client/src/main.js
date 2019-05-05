import Vue from 'vue';
import App from './App.vue';
import router from './router';
import axios from 'axios'
import VueAxios from 'vue-axios'
import './bootstrap.js';
import './icons.js';

Vue.config.productionTip = false;

Vue.use(VueAxios, axios);
Vue.prototype.$serverBaseUrl = "http://localhost:8080";

new Vue({
  router,
  render: h => h(App),
}).$mount('#app');