import Vue from 'vue';
import Router from 'vue-router';

import AdminPage from './views/AdminPage.vue'
import Products from './views/Products.vue'
//import Ostukorv from './views/Ostukorv.vue'

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
    /*
    {
      path: '/ostukorv',
      name: 'ostukorv',
      component: Ostukorv,
    },
    */
  ];
  
export default new Router({
  routes,
  mode: 'history',
  base: __dirname,
});