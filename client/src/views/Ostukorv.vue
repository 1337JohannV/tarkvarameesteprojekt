<template>
  <div style="background-color: #f0f3bd;">
    <nav id="topbar" class="navbar">
      <span id="topbartext" class="navbar-brand mb-0 h1">Ostukorv</span>
      <button type="button" 
        style="box-shadow: none;"
        class="btn btn-success btn-lg" 
        v-on:click.prevent="bestShop">Parim pood</button>
      <router-link id="tagasi" to="/" class="btn btn-primary btn-lg">Tagasi</router-link>
    </nav>
    <div 
    id="ostukorv"
    class="container shadow rounded" 
    style="height:35em; overflow-y: auto;">
      <div class="row  m-3 equal" v-for="n in Math.ceil(this.products.length/5)" :key="n">
        <div 
          class="col" 
          v-for="p in 5" :key="p"
          style="max-width: 15em; min-width: 10em"
          >
              <div class="card box-shadow h-100" v-if="(n-1)*5 + p - 1 < products.length">
                <img 
                  v-if="products[(n-1)*5 + p - 1].imgUrl != null"
                  v-bind:src="products[(n-1)*5 + p - 1].imgUrl" 
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
                  <p class="card-text"><b>{{products[(n-1)*5 + p - 1].name}}</b></p>
                </div>
              <div class="container mb-2">
                      <button type="button" class="btn btn-danger btn-lg" v-on:click.prevent="remove((n-1)*5 + p - 1)">Eemalda ostukorvist</button>
                  </div>
            </div>
          </div>
              
      </div>
    </div>

    <!-- Eraldi screen mis, ilmub toote peale vajutades -->
  
  <b-modal ref="my-modal" hide-footer title="Odavaim pood">
    <div class="container text-center" v-if="response != null">
      
      <img 
                  v-if="response.store == 'SELVER'"
                  src="https://astri.ee/assets/medias/752/selver-logo.png" 
                  class="img-fluid"
                  alt="Parim Pood"
                >
                <img
                  v-if="response.store == 'PRISMA'"
                  src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQZQ1xa2j3AMEJ1p6Mq89wNoF0A_5p0G6LLV97rEJL0tX2srapx9A"
                  class="img-fluid" 
                  alt="Parim Pood"
                >

      <table class="table">
              <thead>
                <tr>
                  <th 
                  scope="col"
                  >Pood: </th>
                  <th 
                  scope="col"
                  >{{response.store}}</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>
                    Ostu hind:
                  </td>
                  <td>
                   {{Math.round(response.basketPrice * 100) / 100}}  EUR
                  </td>
                </tr>
              </tbody>
            </table> 
    </div>
    
    
  </b-modal>

    <nav id="footer" class="navbar fixed-bottom"> 

    </nav>
    
  </div>
</template>

<script>
var aadress = "http://localhost:8080/products/ostukorv"
export default {
  name: "Ostukorv",
  data: function(){
    return {
      products: [],
      response: null,
      dummy: [1, 2, 3]
    }
  },
  methods:{
    remove(id){
      this.$root.$data.sourceOfTruth.splice(id, 1);
    },
    bestShop(){
      
      var idArray = []

      this.products.forEach(function(product){
        idArray.push(product.id)
      });

      

        

        const request = async () => {
        const response = await fetch("http://localhost:8080/products/ostukorv",{
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(idArray), 
        });
        const json = await response.json();
        this.response = json;
        console.log(this.response);
        this.$refs['my-modal'].show();
      };

      request();

    }
  },
  mounted(){
    this.products = this.$root.$data.sourceOfTruth;
    console.log(this.products)
  }
};
</script>

<style scoped>

::-webkit-scrollbar {
  width: 30px;
}

#ostukorv {
  background-color: #f0f3bd;
}

#topbar  {
  background-color: #05668d;
}

#footer {
  background-color: #05668d;
  height: 3.15em;
}

#topbartext {
  color: white;
}

#tagasi {
  border: 0;
  outline: 0;
  color: white;
  background-color: #02c39a;
  transition-duration: 0.3s;
  font-family: monospace;
}

#tagasi:hover{
  background-color: #00a896;
}

#tagasi:active{
  background-color: #02c39a;
  transition-duration: 0.1s;
}

</style>

