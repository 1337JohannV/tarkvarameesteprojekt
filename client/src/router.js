import Vue from 'vue';
import Router from 'vue-router';

import AdminPage from './views/AdminPage.vue'
import Products from './views/Products.vue'

Vue.use(Router);

const routes = [
    {
      path: '/',
      name: 'home',
      component: Products
    },
    {
      path: '/admin',
      name: 'admin',
      component: AdminPage
    },
  ];
  
export default new Router({
  routes,
  mode: 'history',
  base: __dirname,
});