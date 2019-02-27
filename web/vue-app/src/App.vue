<template>
  <div id="app">
    <TopBar/>
    <div id="content">
        <SideMenu/>
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

#app {
  position: absolute;
  width: 1024px;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

#content {
  margin: auto;
}

body {
}
</style>
