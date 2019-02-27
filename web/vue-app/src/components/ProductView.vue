<template>

    <div class="flex-container" id="productView">
        <div class="flex-item" v-for="p in end" :key="p" v-if="p > start">
            <div id="productContainer" v-on:click.prevent="showInfo(p)">
                <div id="productInfo">
                    <p id="productTitle">{{products[p].name}}</p>
                    <p v-if="products[p].quantity != null">
                        <b>Kogus:</b> {{products[p].quantity.value}} {{products[p].quantity.unit}}
                    </p>
                </div>
                <div id="productPrice" v-on:click.prevent="showInfo">
                    <p> <b>Parim hind:</b> 
                        {{products[p].productPrices[0].unitPrice.amount}} {{products[p].productPrices[0].unitPrice.currency}} /
                        {{products[p].productPrices[0].unitPrice.perUnit}}
                    </p>
                    <div id="productImage">
                        <img v-bind:src="products[p].imgUrl" alt="Toode" height="100" width="100" v-on:click.prevent="showInfo(p)">
                    </div>
                    
                </div>
            </div>
        </div>
        <div id="navButtonContainer">
            <button v-on:click.prevent="previous" v-if="this.start > 0">Previous</button> 
            <button id="navButton" v-on:click.prevent="next" v-if="this.end != this.products.length-1">Next</button>
        </div>


        <!-- Eraldi screen mis, ilmub toote peale vajutades -->
    
        <div v-if="this.infoShow" class="popup">

        
            <div class="modal-content">
                <p id="productTitle">{{products[this.currentProduct].name}}</p>
                    <!-- 
                    <p>EAN: {{p.ean}}</p>
                    -->
                <p v-if="products[this.currentProduct].producer != ''"><b>Tootja:</b> {{products[this.currentProduct].producer}}</p>
                <p v-else><b>Tootja:</b> -</p>  
                <p><b>Päritolumaa:</b> {{products[this.currentProduct].origin}}</p> 

                <p v-if="products[this.currentProduct].quantity != null">
                    <b>Kogus:</b> {{products[this.currentProduct].quantity.value}} {{products[this.currentProduct].quantity.unit}}
                </p>
                <div id="productPrice" v-on:click.prevent="showInfo">
                    <p> <b>Parim hind:</b> 
                        {{products[this.currentProduct].productPrices[0].unitPrice.amount}} {{products[this.currentProduct].productPrices[0].unitPrice.currency}} /
                        {{products[this.currentProduct].productPrices[0].unitPrice.perUnit}}
                    </p>
                    <p> 
                        <b>Pood:</b> {{products[this.currentProduct].productPrices[0].store}}
                    </p>
                    <p v-if="products[this.currentProduct].productPrices[0].specialPrice != null">
                        <b>Kliendikaardiga:</b> {{products[this.currentProduct].productPrices[0].specialPrice.amount}} {{products[this.currentProduct].productPrices[0].specialPrice.currency}}
                    </p>
                    <p v-else>
                        <b>Kliendikaardiga:</b> Hind ei muutu
                    </p>
                </div>
                <button v-on:click.prevent="hideInfo">Close</button>
            </div>
        </div>
</div>



    

</template>

<script>
var address = 'http://localhost:8080/products/';

export default {
    name: 'ProductView',
    data: function() {
        return {
            products: null,
            start: 0,
            end: 20,
            infoShow: false,
            currentProduct: -1,
            currentImage: '',
        }
    },

    methods: {
        next(){
            this.start = this.end;
            if(this.end + 20 >= this.products.length-1){
                this.end = this.products.length-1;
            } else {
                this.end = this.end + 20;
            }
        },

        previous(){
            this.end = this.start;
            this.start = this.start - 20;
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
        productsData: null
    },
    watch:{
        productsData: function(){
            if(this.productsData != null){
                this.products = this.productsData;
                
            }
            
        }
    }
}

</script>

<style scoped>


Button{
    background-image: linear-gradient(-45deg, rgb(122, 235, 255), rgb(0, 195, 255));
    text-align: center;
    border: black solid 2px;
    color: white;
    font-family: monospace;
    font-size:30px;
    margin-left: 9px;
    margin-top: 10px;
}

Button:hover{
    opacity: 0.8;
}

#navButton {
    position: absolute;
    margin-left: 0;
    left: 91.4%;
}

#navButtonContainer{
    width: 100%;
    height: 50px;
}

#productView {
    height: 865px;
}

.flex-container {
    display: flex;
    flex-wrap: wrap;
}

.flex-item {
    display: block;
    margin: 5px 5px 5px 10px;
}


#productContainer {
    box-shadow: 1px 1px 1px 1px rgb(228, 228, 228);
    width: 185px;
    height: 220px;
    padding: 5px;
    overflow: hidden;
}

#productContainer:hover{
    background-color: rgb(235, 232, 232);
}

p {
    font-size: 12px;

}

#productTitle, #bestPrice {
    font-size: 14px;
    font-weight: bold;
}

.popup {
  position: fixed; 
  left: 0;
  top: 0;
  width: 100%; 
  height: 100%; 
  background-color: rgb(0,0,0); 
  background-color: rgba(0,0,0,0.4); 
}

.modal-content {
  background-color: #fefefe;
  margin: 15% auto;
  padding: 20px;
  width: 30%; 
}

#productImage{
    align-items: center;
    justify-items: center;
    width: 100%;
}

</style>
