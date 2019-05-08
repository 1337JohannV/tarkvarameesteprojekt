<template>
  <div class="container-fluid">
    <div 
      class="row equal mt-3" 
      v-for="p in Math.ceil(this.products.length / 4)" :key="p"
    >
      <div 
        class="col" 
        v-if="(p-1)*4 < products.length" 
        v-on:click.prevent="showInfo((p-1)*4)"
      >
        <div 
          id="productCard" 
          class="card mb-4 box-shadow h-100"
          style="max-width: 218px; overflow: hidden;"
        >
          <img 
            v-if="products[(p-1)*4].imgUrl != null"
            v-bind:src="products[(p-1)*4].imgUrl" 
            class="img-fluid"
            alt="Toode"
            style="min-height: 150px; min-width: 220px;"
          >
          <img
            v-else
            src="https://www.oland.se/en/book//Content/img/missingimage.jpg"
            class="img-fluid" 
            alt="Toode"
            style="min-height: 150px; min-width: 220px;"
          >
          <div class="card-body">
            <table class="table">
              <thead>
                <tr>
                  <th 
                    scope="col"
                    style="max-width: 172px;"
                  >{{products[(p-1)*4].name}}
                  </th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td v-if="products[(p-1)*4].quantity != null">
                    <b>Kogus:</b>
                    {{products[(p-1)*4].quantity.value}} {{products[(p-1)*4].quantity.unit}}
                  </td>
                  <td v-else>
                    <b>Kogus: </b>
                    -
                  </td>
                </tr>
                <td>
                  <b>Parim hind:</b><br>
                  {{products[(p-1)*4].basePrice}} EUR

                </td>
              </tbody>
            </table>
          </div>
        </div>
      </div>
      <div 
        class="col" 
        v-if="(p-1)*4 + 1 < products.length" 
        v-on:click.prevent="showInfo((p-1)*4 + 1) "
      >
        <div 
          id="productCard" 
          class="card mb-4 box-shadow h-100"
          style="max-width: 218px; overflow: hidden;"
        >
          <img 
            v-if="products[(p-1)*4 + 1].imgUrl != null"
            v-bind:src="products[(p-1)*4 + 1].imgUrl" 
            class="img-fluid"
            alt="Toode"
            style="min-height: 150px; min-width: 220px;"
          >
          <img
            v-else
            src="https://www.oland.se/en/book//Content/img/missingimage.jpg"
            class="img-fluid" 
            alt="Toode"
            style="min-height: 150px; min-width: 220px;"
          >
          <div class="card-body">
            <table class="table">
              <thead>
                <tr>
                  <th 
                    scope="col"
                    style="max-width: 172px;"
                  >{{products[(p-1)*4 + 1].name}}
                  </th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td v-if="products[(p-1)*4 + 1].quantity != null">
                    <b>Kogus:</b>
                    {{products[(p-1)*4 + 1].quantity.value}} {{products[(p-1)*4 + 1].quantity.unit}}
                  </td>
                  <td v-else>
                    <b>Kogus: </b>
                    -
                  </td>
                </tr>
                <tr>
                  <td>
                    <b>Parim hind:</b><br>
                      {{products[(p-1)*4 + 1].basePrice}} EUR
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
      <div 
        class="col" 
        v-if="(p-1)*4 + 2 < products.length" 
        v-on:click.prevent="showInfo((p-1)*4  + 2)"
      >
        <div 
          id="productCard" 
          class="card mb-4 box-shadow h-100"
          style="max-width: 218px; overflow: hidden;"
        >
          <img 
            v-if="products[(p-1)*4 + 2].imgUrl != null"
            v-bind:src="products[(p-1)*4 + 2].imgUrl" 
            class="img-fluid"
            alt="Toode"
            style="min-height: 150px; min-width: 220px;"
          >
          <img
            v-else
            src="https://www.oland.se/en/book//Content/img/missingimage.jpg"
            class="img-fluid"
            alt="Toode"
            style="min-height: 150px; min-width: 220px;"
          >
          <div class="card-body">
            <table class="table">
              <thead>
                <tr>
                  <th 
                    scope="col"
                    style="max-width: 172px;"
                  >{{products[(p-1)*4 + 2].name}}
                  </th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td v-if="products[(p-1)*4 + 2].quantity != null">
                    <b>Kogus:</b>
                    {{products[(p-1)*4 + 2].quantity.value}} {{products[(p-1)*4 + 2].quantity.unit}}
                  </td>
                  <td v-else>
                    <b>Kogus: </b>
                    -
                  </td>
                </tr>
                <tr>
                  <td>
                    <b>Parim hind:</b><br>
                    {{products[(p-1)*4 + 2].basePrice}} EUR
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
      <div 
        class="col" 
        v-if="(p-1)*4 + 3 < products.length" 
        v-on:click.prevent="showInfo((p-1)*4 + 3)"
      >
        <div 
          id="productCard" class="card mb-4 box-shadow h-100"
          style="max-width: 218px; overflow: hidden;"
        >
          <img 
            v-if="products[(p-1)*4 + 3].imgUrl != null"
            v-bind:src="products[(p-1)*4 + 3].imgUrl" 
            class="img-fluid"
            alt="Toode"
            style="min-height: 150px; min-width: 220px;"
          >
          <img
            v-else
            src="https://www.oland.se/en/book//Content/img/missingimage.jpg"
            class="img-fluid"
            alt="Toode"
            style="min-height: 150px; min-width: 220px;"
          >
          <div class="card-body">
            <table class="table">
              <thead>
                <tr>
                  <th 
                    scope="col"
                    style="max-width: 172px;"
                  >{{products[(p-1)*4 + 3].name}}</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td v-if="products[(p-1)*4 + 3].quantity != null">
                    <b>Kogus:</b>
                    {{products[(p-1)*4 + 3].quantity.value}} {{products[(p-1)*4 + 3].quantity.unit}}
                  </td>
                  <td v-else>
                    <b>Kogus: </b>
                    -
                  </td>
                </tr>
                <tr>
                  <td>
                    <b>Parim hind:</b><br>
                      {{products[(p-1)*4 + 3].basePrice}} EUR
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>

  <!-- Eraldi screen mis, ilmub toote peale vajutades -->
  
    <b-modal 
      ref="my-modal" 
      hide-footer
      hide-header
      @hide="onModalClose"
      >
      <div 
        class="container-fluid m-0 p-0" 
        v-if="currentProduct != -1"
        >
        <div 
          class="jumbotron"
          style="background-color: white;"
        >
        <h1 class="display-4"> Info</h1>  
        <table class="table table-striped mb-4 mt-4">
          <thead id="tablehead">
            <tr>
              <th id="tdhead" scope="col">Toode: </th>
              <th id="tdhead" scope="col">{{products[this.currentProduct].name}}</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>Kategooria: </td>
              <td>{{products[this.currentProduct].category}}</td>
            </tr>
            <tr>
              <td>EAN: </td>
              <td>{{products[this.currentProduct].ean}}</td>
            </tr>
            <tr v-if="products[this.currentProduct].origin != null">
              <td>Päritolumaa: </td>
              <td>{{products[this.currentProduct].origin}}</td>
            </tr>
            <tr v-if="products[this.currentProduct].producer != null" >
              <td>Tootja: </td>
              <td>{{products[this.currentProduct].producer}}</td>
            </tr>
            <tr v-if="products[this.currentProduct].quantity.unit != null">
              <td>Kogus: </td>
              <td>{{products[this.currentProduct].quantity.value}} {{products[this.currentProduct].quantity.unit}}</td>
            </tr>
          </tbody>
        </table>
        <div class ="container text-center pt-4 pb-4">
          <img 
            v-if="products[this.currentProduct].imgUrl != null"
            v-bind:src="products[this.currentProduct].imgUrl" 
            class="img-fluid" 
            alt="Toode"
            >
          <img
            v-else
            src="https://www.oland.se/en/book//Content/img/missingimage.jpg"
            class="img-fluid" 
            alt="Toode"
            >
        </div>
          <h1 class="display-4 mb-3">
          Hinnad</h1>
          
          <PriceTable v-bind:productPrices="products[this.currentProduct].productPrices"></PriceTable>
          
          

          
          
        </div>

        <div class="d-flex justify-content-between align-items-center">
            
              <button v-on:click.prevent="hideInfo" id="menubutton" class="btn btn-primary float-left">Tagasi</button>
              <button v-if="products[this.currentProduct].productPrices[0].store != 'MAXIMA'" v-on:click.prevent="addToShoppingcart(currentProduct)" id="menubutton" class="btn btn-primary float-right">Lisa ostukorvi</button>
            
        </div>
      
      </div>
    </b-modal>
  </div>
</template>

<script>

var address = "http://ec2-184-72-206-167.compute-1.amazonaws.com:8080/products/0/24/name/asc";
import PriceTable from "@/components/PriceTable.vue";

export default {
  name: "ProductView",
  data: function() {
    return {
      currentSearch: "",
      currentCategory: null,
      currentPage: 0,
      products: null,
      currentProduct: -1,
      shoppingCart: [],
      currentDirection: 'asc',
      currentSort: 'name' 
    };
  },
  components: {
    PriceTable
  },

  methods: {
    next() {
      this.currentPage = this.currentPage + 1;
      if (this.currentCategory != null) {
        address =
          "http://ec2-184-72-206-167.compute-1.amazonaws.com:8080/products/" +
          this.currentCategory +
          "/" +
          this.currentPage +
          "/24/" +
          this.currentSort + 
          "/" +
          this.currentDirection;
      } else if (this.currentSearch != "") {
        address =
          "http://ec2-184-72-206-167.compute-1.amazonaws.com:8080/products/search/" +
          this.currentSearch +
          "/" +
          this.currentPage +
          "/24/" + 
          this.currentDirection;
      } else {
        address =
          "http://ec2-184-72-206-167.compute-1.amazonaws.com:8080/products/" + 
          this.currentPage + 
          "/24/" +
           this.currentSort + 
           "/" +
          this.currentDirection;
      }

      console.log(address)

      const request = async () => {
        const response = await fetch(address);
        const json = await response.json();
        this.products = json;

        if (this.products.length < 24) {
          this.$emit("statePrevious", true);
        } else {
          this.$emit("stateNext", true);
          this.$emit("statePrevious", true);
        }
        console.log(this.products)
      };

      request();

      
    },

    previous() {
      this.currentPage = this.currentPage - 1;

      if (this.currentCategory != null) {
        address =
          "http://ec2-184-72-206-167.compute-1.amazonaws.com:8080/products/" +
          this.currentCategory +
          "/" +
          this.currentPage +
          "/24/"+ 
          this.currentSort + 
          "/"+
          this.currentDirection;
      } else if (this.currentSearch != "") {
        address =
          "http://ec2-184-72-206-167.compute-1.amazonaws.com:8080/products/search/" +
          this.currentSearch +
          "/" +
          this.currentPage +
          "/24/" + 
          this.currentDirection;
      } else {
        address =
          "http://ec2-184-72-206-167.compute-1.amazonaws.com:8080/products/" 
          + this.currentPage 
          + "/24/" + 
          this.currentSort + 
          "/" +
          this.currentDirection;
      }

      const request = async () => {
        const response = await fetch(address);
        const json = await response.json();
        this.products = json;

        this.$emit("statePrevious", false);
        if (this.currentPage == 0) {
          this.$emit("stateNext", true);
          this.$emit("statePrevious", false);
        } else {
          this.$emit("statePrevious", true);
          this.$emit("stateNext", true);
        }
      };

      request();
    },
    showInfo(p) {
      this.currentProduct = p;
      this.$refs['my-modal'].show();
      console.log(this.products);
    },
    hideInfo() {
      this.currentProduct = -1;
      this.$refs['my-modal'].hide();
    },
    filterbyCategory(category) {
      this.currentProduct = -1;
      this.currentSearch = "";
      this.currentCategory = category;
      this.currentPage = 0;

      address =
        "http://ec2-184-72-206-167.compute-1.amazonaws.com:8080/products/" +
        this.currentCategory +
        "/" +
        this.currentPage +
        "/24/" + 
        this.currentSort + 
        "/" + 
        this.currentDirection;

      const request = async () => {
        const response = await fetch(address);
        const json = await response.json();
        this.products = json;


        this.$emit("statePrevious", false);
        if (this.products.length < 24) {
          this.$emit("stateNext", false);
        } else {
          this.$emit("stateNext", true);
        }
      };

      request();
    },
    filterBySearch(search) {
      this.currentCategory = null;
      this.currentSearch = search;
      this.currentPage = 0;

      address = "http://ec2-184-72-206-167.compute-1.amazonaws.com:8080/products/search/" 
                + search 
                + "/0/24/" + 
                this.currentDirection;
      const request = async () => {
        const response = await fetch(address);
        const json = await response.json();
        this.products = json;



        this.$emit("statePrevious", false);
        if (this.products.length < 24) {
          this.$emit("stateNext", false);
        } else {
          this.$emit("stateNext", true);
        }
      };

      request();
    },
    addToShoppingcart(id){
      this.$root.$data.sourceOfTruth.push(this.products[id]);
      this.$refs['my-modal'].hide();
    },
    onModalClose(){
      console.log("works")
      this.currentProduct = -1;
    },
  },
  mounted: function() {
    console.log("reset")
    address = "http://ec2-184-72-206-167.compute-1.amazonaws.com:8080/products/0/24/name/asc";
    this.currentSearch = "";
    this.currentCategory = null
    this.currentPage = 0
    this.products = null
    this.currentProduct = -1
    this.currentDirection = 'asc'
    this.currentSort = 'name' 

    fetch(address)
      .then(r => r.json())
      .then(json => (this.products = json));

    console.log(this.products)
  },
  created: function() {
    fetch(address)
      .then(r => r.json())
      .then(json => (this.products = json));
  },
  props: {
    cycle: {
      type: Number
    },
    filter: {
      type: String
    },
    search: {
      type: String
    },
    order: {
      type: String
    },
    direct: {
      type: String
    }
  },
  watch: {
    cycle: function() {
      if (Math.abs(this.cycle - this.currentPage) < 2) {
        if (this.cycle > this.currentPage) {
          this.next();
        } else {
          this.previous();
        }
      }
      this.currentPage = this.cycle;
    },

    filter: function() {
      console.log(this.filter);
      if (this.filter == "KOIK") {
        address = "http://ec2-184-72-206-167.compute-1.amazonaws.com:8080/products/0/24/"+ this.currentSort + "/" + this.currentDirection;
        this.currentCategory = null;
        this.currentPage = 0;
        this.currentSearch = "";

        const request = async () => {
          const response = await fetch(address);
          const json = await response.json();
          this.products = json;
          this.$emit("stateNext", true);
          this.$emit("statePrevious", false);
        };

        request();

      } else if (this.filter != null) {
        if (this.currentCategory != this.filter) {
          this.filterbyCategory(this.filter);
        }
      }
    },
    search: function() {
      if (this.search != "") {
        if (this.currentSearch != this.search) {
          this.filterBySearch(this.search);
        }
      }
    },
    order: function(){
      this.currentSort = this.order;
    },
    direct: function(){
      console.log(this.direct);
      this.currentDirection = this.direct;
    }
  }
};
</script>

<style scoped>

#productCard:hover{
  opacity: 0.9;
  border: 2px solid #00a896;
}

#menubutton {
  height: 40%;

  border-radius: 10px;
  border: 0;
  outline: 0;

  color: white;
  background-color: #02c39a;
  transition-duration: 0.3s;

  font-size: 30px;
  font-family: monospace;
}

#menubutton:hover {
  background-color: #00a896;
}

#menubutton:active {
  background-color: #02c39a;
  transition-duration: 0.1s;
}

#tablehead {
  background-color: #02c39a;
}

#tdhead {
  color: white;
}

.alert{
  display:none;
}

</style>
