<template>
    <!--
                <img class="card-img-top" data-src="holder.js/100px225?theme=thumb&amp;bg=55595c&amp;fg=eceeef&amp;text=Thumbnail" alt="Thumbnail [100%x225]" style="height: 225px; width: 100%; display: block;" src="data:image/svg+xml;charset=UTF-8,%3Csvg%20width%3D%22348%22%20height%3D%22224%22%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20viewBox%3D%220%200%20348%20224%22%20preserveAspectRatio%3D%22none%22%3E%3Cdefs%3E%3Cstyle%20type%3D%22text%2Fcss%22%3E%23holder_16a8f9cc06a%20text%20%7B%20fill%3A%23eceeef%3Bfont-weight%3Abold%3Bfont-family%3AArial%2C%20Helvetica%2C%20Open%20Sans%2C%20sans-serif%2C%20monospace%3Bfont-size%3A17pt%20%7D%20%3C%2Fstyle%3E%3C%2Fdefs%3E%3Cg%20id%3D%22holder_16a8f9cc06a%22%3E%3Crect%20width%3D%22348%22%20height%3D%22224%22%20fill%3D%22%2355595c%22%3E%3C%2Frect%3E%3Cg%3E%3Ctext%20x%3D%22116.734375%22%20y%3D%22119.33125%22%3EThumbnail%3C%2Ftext%3E%3C%2Fg%3E%3C%2Fg%3E%3C%2Fsvg%3E" data-holder-rendered="true">
                <div class="card-body">
                  <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                  <div class="d-flex justify-content-between align-items-center">
                    <div class="btn-group">
                      <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                      <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                    </div>
                    <small class="text-muted">9 mins</small>
                  </div>
                </div>
              
    -->
  <div class="container-fluid">
    <div class="row equal mt-3" v-for="p in Math.ceil(this.products.length / 4)" :key="p">
      <div class="col" v-if="(p-1)*4 < products.length" v-on:click.prevent="showInfo((p-1)*4)">
        <div id="productCard" class="card mb-4 box-shadow h-100"
          style="max-width: 218px;"
        >
        <img 
          v-if="products[(p-1)*4].imgUrl != null"
          v-bind:src="products[(p-1)*4].imgUrl" 
          class="img-fluid"
          style="width: 100%; height: 220px;" 
          alt="Toode"
        >
        <img
          v-else
          src="https://www.oland.se/en/book//Content/img/missingimage.jpg"
          class="img-fluid" 
          style="width: 100%; height: 220px;"
          alt="Toode"
        >
          <div class="card-body">
            <table class="table">
              <thead>
                <tr>
                  <th 
                  scope="col"
                  style="max-width: 172px;"
                  >{{products[(p-1)*4].name}}</th>
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
                      {{products[(p-1)*4].productPrices[0].unitPrice.amount}} {{products[(p-1)*4].productPrices[0].unitPrice.currency}} /
                      {{products[(p-1)*4].productPrices[0].unitPrice.perUnit}}
                </td>
              </tbody>
            </table>
          </div>

        </div>
      </div>
      <div class="col" v-if="(p-1)*4 + 1 < products.length" v-on:click.prevent="showInfo((p-1)*4 + 1) ">
        <div id="productCard" class="card mb-4 box-shadow h-100"
          style="max-width: 218px;"
        >
        <img 
          v-if="products[(p-1)*4 + 1].imgUrl != null"
          v-bind:src="products[(p-1)*4 + 1].imgUrl" 
          class="img-fluid"
          style="width: 100%; height: 220px;" 
          alt="Toode"
        >
        <img
          v-else
          src="https://www.oland.se/en/book//Content/img/missingimage.jpg"
          class="img-fluid" 
          style="width: 100%; height: 220px;"
          alt="Toode"
        >

          <div class="card-body">
            <table class="table">
              <thead>
                <tr>
                  <th 
                  scope="col"
                  style="max-width: 172px;"
                  >{{products[(p-1)*4 + 1].name}}</th>
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
                      {{products[(p-1)*4 + 1].productPrices[0].unitPrice.amount}} {{products[(p-1)*4 + 1].productPrices[0].unitPrice.currency}} /
                      {{products[(p-1)*4 + 1].productPrices[0].unitPrice.perUnit}}
                  </td>
                </tr>
              </tbody>
            </table>
          </div>

        </div>
      </div>
      <div class="col" v-if="(p-1)*4 + 2 < products.length" v-on:click.prevent="showInfo((p-1)*4  + 2)">
        <div id="productCard" class="card mb-4 box-shadow h-100"
          style="max-width: 218px;"
        >
        <img 
          v-if="products[(p-1)*4 + 2].imgUrl != null"
          v-bind:src="products[(p-1)*4 + 2].imgUrl" 
          class="img-fluid"
          style="width: 100%; height: 220px;" 
          alt="Toode"
        >
        <img
          v-else
          src="https://www.oland.se/en/book//Content/img/missingimage.jpg"
          class="img-fluid"
          style="width: 100%; height: 220px;" 
          alt="Toode"
        >

          <div class="card-body">
            <table class="table">
              <thead>
                <tr>
                  <th 
                    scope="col"
                    style="max-width: 172px;"
                  >{{products[(p-1)*4 + 2].name}}</th>
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
                      {{products[(p-1)*4 + 2].productPrices[0].unitPrice.amount}} {{products[(p-1)*4 + 2].productPrices[0].unitPrice.currency}} /
                      {{products[(p-1)*4 + 2].productPrices[0].unitPrice.perUnit}}
                  </td>
                </tr>
              </tbody>
            </table>
          </div>

        </div>
      </div>
      <div class="col" v-if="(p-1)*4 + 3 < products.length" v-on:click.prevent="showInfo((p-1)*4 + 3)">
        <div 
           id="productCard" class="card mb-4 box-shadow h-100"
          style="max-width: 218px;"
        >
        <img 
          v-if="products[(p-1)*4 + 3].imgUrl != null"
          v-bind:src="products[(p-1)*4 + 3].imgUrl" 
          class="img-fluid"
          style="width: 100%; height: 220px;" 
          alt="Toode"
        >
        <img
          v-else
          src="https://www.oland.se/en/book//Content/img/missingimage.jpg"
          class="img-fluid"
          style="width: 100%; height: 220px;" 
          alt="Toode"
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
                  <td v-if="products[(p-1)*4 + 3].quantity != null"
                  >
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
                      {{products[(p-1)*4 + 3].productPrices[0].unitPrice.amount}} {{products[(p-1)*4 + 3].productPrices[0].unitPrice.currency}} /
                      {{products[(p-1)*4 + 3].productPrices[0].unitPrice.perUnit}}
                  </td>
                </tr>
              </tbody>
            </table>
          </div>

        </div>
      </div>
    </div>


  <!-- Eraldi screen mis, ilmub toote peale vajutades 
  <div id="productView">
    <div class="flex-item" v-for="p in this.products.length" :key="p">
      <div id="productContainer" v-on:click.prevent="showInfo(p-1)">
        <div id="productInfo">
          <p id="productTitle">{{products[p-1].name}}</p>
          <p v-if="products[p-1].quantity != null">
            <b>Kogus:</b>
            {{products[p-1].quantity.value}} {{products[p-1].quantity.unit}}
          </p>
          <p>
            <b>Parim hind:</b>
            {{products[p-1].productPrices[0].unitPrice.amount}} {{products[p-1].productPrices[0].unitPrice.currency}} /
            {{products[p-1].productPrices[0].unitPrice.perUnit}}
          </p>
        </div>
        <div id="productImageContainer">
          <img
            id="productImage"
            v-if="products[p-1].imgUrl != 'NO IMAGE SET'"
            v-bind:src="products[p-1].imgUrl"
            alt="Toode"
            v-on:click.prevent="showInfo(p-1)"
          >
          <img
            id="productImage"
            v-else
            src="https://static1.squarespace.com/static/56eddde762cd9413e151ac92/t/570cb89a5bd33022b93a1ecb/1460466676034/copyright1.jpg"
            alt="Toode"
            v-on:click.prevent="showInfo(p-1)"
          >
        </div>
      </div>
    </div>
  
  -->
  <!-- Eraldi screen mis, ilmub toote peale vajutades -->
  
  <b-modal ref="my-modal" hide-footer title="Toote informatsioon">
    <div class="container-fluid m-0 p-0" v-if="currentProduct != -1">
      <table class="table table-borderless table-striped">
        <thead>
          <tr>
            <th scope="col">{{products[this.currentProduct].name}}</th>
            <th scope="col"> </th>
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
      <div class ="container text-center mt-3 mb-3">
        <img 
          v-if="products[this.currentProduct].imgUrl != null"
          v-bind:src="products[this.currentProduct].imgUrl" 
          class="img-fluid" 
          alt="Toode">
        <img
            v-else
            src="https://www.oland.se/en/book//Content/img/missingimage.jpg"
            class="img-fluid" 
            alt="Toode"
          >
      </div>

      <PriceTable v-bind:productPrices="products[this.currentProduct].productPrices"></PriceTable>
      <button v-on:click.prevent="hideInfo" id="menubutton" class="btn btn-primary btn-block">Close</button>
    </div>
    </b-modal>

  </div>
</template>

<script>
var address = "http://localhost:8080/products/0/24/name/asc";

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
      currentImage: ""
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
          "http://localhost:8080/products/" +
          this.currentCategory +
          "/" +
          this.currentPage +
          "/24/name/asc";
      } else if (this.currentSearch != "") {
        address =
          "http://localhost:8080/products/search/" +
          this.currentSearch +
          "/" +
          this.currentPage +
          "/24/asc";
      } else {
        address =
          "http://localhost:8080/products/" + this.currentPage + "/24/name/asc";
      }

      const request = async () => {
        const response = await fetch(address);
        const json = await response.json();
        this.products = json;


        if (this.products.length < 24) {
          this.$emit("stateNext", false);
          this.$emit("statePrevious", true);
        } else {
          this.$emit("stateNext", true);
          this.$emit("statePrevious", true);
        }
      };

      request();
    },

    previous() {
      this.currentPage = this.currentPage - 1;

      if (this.currentCategory != null) {
        address =
          "http://localhost:8080/products/" +
          this.currentCategory +
          "/" +
          this.currentPage +
          "/24/name/asc";
      } else if (this.currentSearch != "") {
        address =
          "http://localhost:8080/products/search/" +
          this.currentSearch +
          "/" +
          this.currentPage +
          "/24/asc";
      } else {
        address =
          "http://localhost:8080/products/" + this.currentPage + "/24/name/asc";
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
      this.currentSearch = "";
      this.currentCategory = category;
      this.currentPage = 0;

      address =
        "http://localhost:8080/products/" +
        this.currentCategory +
        "/" +
        this.currentPage +
        "/24/name/asc";

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

      address = "http://localhost:8080/products/search/" + search + "/0/24/asc";
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
    }
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
        address = "http://localhost:8080/products/0/24/name/asc";
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
    }
  }
};
</script>

<style scoped>

#productCard:hover{
  opacity: 0.9;
  border: 2px solid #00a896;
}

.product-info {
  outline: 1px solid black;
}

.product-prices {
  outline: 1px solid black;
}
.float-right {
  float: right;
}

.float-left {
  float: left;
}

.product-data,
.product-image {
  min-width: 50%;
  max-width: 50%;
}

.product-data h2 {
  margin-bottom: 2rem;
}

.product-data p {
  margin-left: 2rem;
}
.product-image {
  text-align: center;
}

.product-image img {
  max-width: 20rem;
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

#navButtonContainer {
  width: 100%;
  height: 50px;
}

#productView {
  min-height: 100vh;
  display: flex;
  flex-wrap: wrap;
}

.flex-item {
  border: #02c39a solid 1px;
  width: 200px;
  height: 300px;
  display: block;

  overflow: hidden;
}

#productContainer {
  width: 100%;
  height: 100%;
  overflow: hidden;
  background: white;
}

#productContainer:hover {
  background-color: #02809054;
}

.popup {
  position: fixed;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  background-color: rgb(0, 0, 0);
  background-color: rgba(0, 0, 0, 0.4);
}

#productPrice {
  width: 100%;
  height: 70%;
}

#productInfo {
  margin-bottom: 10px;
  width: 100%;
  height: 25%;
  padding-left: 7px;
}

#productImageContainer {
  margin-top: 15%;
  height: 100%;
}

#productImage {
  max-width: 10rem;
}

.modal-content {
  background-color: #fefefe;
  margin: 10% 15%;
  padding: 2rem;
}
</style>
