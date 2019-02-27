<template>
    <div id="app">
      <TopBar @productsData="updateProduct"/>
      <div id="content">
          <ProductView :productsData="productsData"/>
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
  },
  data: function(){
    return {
      productsData: null
    };
  },
  methods: {
    updateProduct(products){
      this.productsData = products;
    }
  }
}
</script>

<style>

#app {
  position: absolute;
  width: 1060px;
  left: 20%;
  margin-bottom: 20px;
}

#content {
  
}




</style>
