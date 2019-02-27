<template>
    <div id="app">
      <TopBar/>
      <div id="content">
          <ProductView/>
      </div>
    </div>
</template>

<script>
import TopBar from './components/TopBar.vue'
import ProductView from './components/ProductView.vue'

export default {
  name: 'app',
  components: {
    TopBar,
    ProductView
  }
}
</script>

<style>

#app {
  position: absolute;
  width: 1060px;
  left: 20%;
  margin-bottom: 40px;
}

#content {
  
}

body {
  margin-bottom: 50 px;
}


</style>
