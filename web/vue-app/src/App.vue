<template>
  <div id="app">
    <TopBar />
    <div id="content">
        <SideMenu />
        <Products />
    </div>
  </div>
</template>

<script>
import TopBar from './components/TopBar.vue'
import SideMenu from './components/SideMenu.vue'
import Products from './components/Products.vue'

export default {
  name: 'app',
  components: {
    TopBar,
    SideMenu,
    Products
  }
}
</script>

<style>

  #content{
    border: black solid 2px;
    width: 100%;
    display: flex;
}

  body{
    background-color: rgb(89, 217, 240);
    margin: 1 1 1 1;
  }
</style>
