import Vue from 'vue';
import App from './App.vue';
import router from './router';
import axios from 'axios'
import VueAxios from 'vue-axios'
import './bootstrap.js';
import './icons.js';

Vue.config.productionTip = false;

Vue.use(VueAxios, axios);
Vue.prototype.$serverBaseUrl = "http://ec2-184-72-206-167.compute-1.amazonaws.com:8080";
Vue.prototype.$placeholderImgUrl = "https://media.self.com/photos/599c997a774b667d3bbe1214/4:3/w_654,c_limit/groceries-family-month.jpg";

const sourceOfTruth = [];

new Vue({
  data: function() {return{sourceOfTruth}},
  router,
  render: h => h(App),
}).$mount('#app');