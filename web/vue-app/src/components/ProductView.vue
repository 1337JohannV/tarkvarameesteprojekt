<template>

    <div id="productView">
        <div class="flex-item" v-for="p in end" :key="p" v-if="p-1 >= start">
            <div id="productContainer" v-on:click.prevent="showInfo(p-1)">
                <div id="productInfo">
                    <p id="productTitle">{{products[p-1].name}}</p>
                    <p v-if="products[p-1].quantity != null">
                        <b>Kogus:</b> {{products[p-1].quantity.value}} {{products[p-1].quantity.unit}}
                    </p>
                </div>
                <div id="productPrice" v-on:click.prevent="showInfo">
                    <p> <b>Parim hind:</b>
                        {{products[p-1].productPrices[0].unitPrice.amount}} {{products[p-1].productPrices[0].unitPrice.currency}} /
                        {{products[p-1].productPrices[0].unitPrice.perUnit}}
                    </p>
                    <div id="productImage">
                        <img v-bind:src="products[p-1].imgUrl" alt="Toode" height="100" width="100" v-on:click.prevent="showInfo(p-1)">
                    </div>
                    
                </div>
            </div>
        </div>

    <!-- Eraldi screen mis, ilmub toote peale vajutades -->

    <div v-if="this.infoShow" class="popup">
      <div class="modal-content">
        <div class="float-left product-data-container">
          <div class="product-title">
            <h1>{{products[this.currentProduct].name}}</h1>
          </div>
          <div class="product-data">
            <p>
              <b>EAN:</b>
              {{products[this.currentProduct].ean}}
            </p>
            <p v-if="products[this.currentProduct].producer != ''">
              <b>Tootja:</b>
              {{products[this.currentProduct].producer}}
            </p>
            <p v-else>
              <b>Tootja:</b> -
            </p>
            <p>
              <b>Päritolumaa:</b>
              {{products[this.currentProduct].origin}}
            </p>
            <p v-if="products[this.currentProduct].quantity != null">
              <b>Kogus:</b>
              {{products[this.currentProduct].quantity.value}} {{products[this.currentProduct].quantity.unit}}
            </p>
          </div>
        </div>
        <div class="float-right product-image-container">
          <img :src="products[this.currentProduct].imgUrl">
        </div>
        <PriceTable v-bind:productPrices="products[this.currentProduct].productPrices"/>
        <button v-on:click.prevent="hideInfo" id="menubutton">Close</button>
      </div>
    </div>
  </div>
</template>

<script>
var address = 'http://localhost:8080/products/0/24/name/asc';

export default {
    name: 'ProductView',
    data: function() {
        return {
            currentPage: 0,
            products: null,
            start: 0,
            end: 24,
            infoShow: false,
            currentProduct: -1,
            currentImage: '',
        }
    },

    methods: {
        next(){
            this.currentPage = this.currentPage + 1;
            address = 'http://localhost:8080/products/' +  this.currentPage  +'/24/name/asc'

            fetch(address)
            .then(r => r.json())
            .then(json => this.products = json);

            this.end = this.products.length;

            if(this.products.length < 24) {
                this.$emit("stateNext", false);
            } else {
                this.$emit("stateNext", true);
                this.$emit("statePrevious", true);
            }
            
        },

        previous(){
            this.currentPage = this.currentPage - 1;
            address = 'http://localhost:8080/products/' +  this.currentPage  +'/24/name/asc'

            fetch(address)
            .then(r => r.json())
            .then(json => this.products = json);

            this.end = this.products.length;

            if(this.currentPage == 0) {
                this.$emit("stateNext", true);
                this.$emit("statePrevious", false);
            } else {
                this.$emit("statePrevious", true);
            }
           

      this.tempMessage = "";
        },
        showInfo(p){
            this.currentProduct = p;
            console.log(this.products[p]);
            this.infoShow = true;
        },
        hideInfo(){
            this.currentProduct = -1;
            this.infoShow = false;
        },
    },
    created: function() {
        fetch(address)
        .then(r => r.json())
        .then(json => this.products = json);
    },
    props:{
        cycle:{
            type:Number
        }
    },
    watch:{
        cycle: function(){
            console.log(this.currentPage);
            console.log(this.cycle);
            if(this.cycle > this.currentPage){
                this.next();
            } else {

                this.previous();
            }
            this.currentPage = this.cycle;
        }
    }
}

</script>

<style scoped>
.float-left {
  float: left;
}

.float-right {
  float: right;
}

.product-data-container,
.product-image-container {
  width: 50%;
}

.product-image-container {
    text-align: center;
}

#menubutton {
  width: 20%;
  height: 60%;

  border-radius: 10px;
  border: 0;
  outline: 0;

  color: white;
  background-color: aqua;
  transition-duration: 0.3s;
  border: 2px solid aqua;

  font-size: 30px;
  font-family: monospace;
}

#menubutton:hover {
  background-color: white;
  color: black;
}

#menubutton:active {
  background-color: aqua;
  opacity: 0.4;
  transition-duration: 0.1s;
}

#navButtonContainer {
  width: 100%;
  height: 50px;
}

#productView {

    height: 100%;
    width: 69%;
    display: flex;
    flex-wrap: wrap;
  height: 100%;
  width: 57%;
  display: flex;
  flex-wrap: wrap;
}

.product-data {
  margin-top: 2rem;
  padding-left: 1rem;
}

.product-data p {
  font-size: 18px;
}

.flex-item {
  border: aqua solid 2px;
  border-radius: 15px;
  width: 185px;
  height: 250px;
  display: block;
  margin: 5px 5px 5px 10px;
  overflow: hidden;
}

#productContainer {
  padding: 8px 0px 0px 0px;
  width: 100%;
  height: 100%;
  overflow: hidden;
  background: white;
}

#productContainer:hover {
  background-color: rgb(235, 232, 232);
}

p {
  font-size: 12px;
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

.modal-content {
  background-color: #fefefe;
  margin: 15% 15%;
  padding: 20px;
  border-radius: 1rem;
  box-shadow: 10px 10px;
}

#productImage {
  align-items: center;
  justify-items: center;
  width: 100%;
}

</style>
