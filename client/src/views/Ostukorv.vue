<template>
  <div>
    <nav class="navbar navbar-light bg-light">
      <span class="navbar-brand mb-0 h1">Navbar</span>
      <button type="button" class="btn btn-sm btn-outline-secondary" v-on:click.prevent="bestShop">Parim pood</button>
      <router-link to="/" class="btn btn-primary">Tagasi</router-link>
    </nav>
    <div class="container">
      <div class="row  m-3 equal">
        <div 
          class="col" 
          v-for="n in this.products.length" :key="n"
          style="max-width: 220px;"
          >
              <div class="card mb-4 box-shadow h-100">
                <img 
                  v-if="products[n-1].imgUrl != null"
                  v-bind:src="products[n-1].imgUrl" 
                  class="img-fluid"
                  alt="Toode"
                >
                <img
                  v-else
                  src="https://www.oland.se/en/book//Content/img/missingimage.jpg"
                  class="img-fluid" 
                  alt="Toode"
                >
                <div class="card-body">
                  <p class="card-text"><b>{{products[n-1].name}}</b></p>
                </div>
              </div>
              <div class="container">
                      <button type="button" class="btn btn-sm btn-outline-secondary" v-on:click.prevent="remove(n-1)">Eemalda ostukorvist</button>
                  </div>
            </div>
      </div>
    </div>

    
    
  </div>
</template>

<script>
var aadress = "http://localhost:8080/products/ostukorv"
export default {
  name: "Ostukorv",
  data: function(){
    return {
      products: [],
      response: '',
      dummy: [1, 2, 3]
    }
  },
  methods:{
    remove(id){
      this.products.splice(id, id+1);
      this.$root.$data.sourceOfTruth.splice(id, id+1);

    },
    bestShop(){
      
      var idArray = []

      this.products.forEach(function(product){
        idArray.push(product.id)
      });

      console.log(this.dummy);

      fetch("http://localhost:8080/products/ostukorv",{
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(idArray), 
        }).then(res=>res.json())
        .then(res => console.log(res));

    }
  },
  mounted(){
    this.products = this.$root.$data.sourceOfTruth;
    console.log(this.products)
  }
};
</script>

<style scoped>


</style>

