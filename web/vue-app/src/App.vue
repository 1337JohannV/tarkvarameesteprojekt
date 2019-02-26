<template>
  <div id="app">
    <TopBar />
    <div id="content">
        <SideMenu />
        <ProductView/>
    </div>
  </div>
</template>

<script>
import TopBar from './components/TopBar.vue'
import SideMenu from './components/SideMenu.vue'
import ProductView from './components/ProductView.vue'

export default {
  name: 'app',
  components: {
    TopBar,
    SideMenu,
    ProductView
  }
}
</script>

<style>

  #content{
    border: black solid 2px;
    
    width: 80%;
    display: flex;
}

  body{
    background-color: rgb(89, 217, 240);
    margin: 1 1 1 1;
  }
</style>
