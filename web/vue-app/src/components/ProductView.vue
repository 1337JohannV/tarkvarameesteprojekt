<template>

    <div id="productView">
        <div class="flex-item" v-for="p in this.products.length" :key="p" v-if="p-1 >= start">
            <div id="productContainer" v-on:click.prevent="showInfo(p-1)">
                <div id="productInfo">
                    <p id="productTitle">{{products[p-1].name}}</p>
                    <p v-if="products[p-1].quantity != null">
                        <b>Kogus:</b> {{products[p-1].quantity.value}} {{products[p-1].quantity.unit}}
                    </p>
                    <p> <b>Parim hind:</b> 
                        {{products[p-1].productPrices[0].unitPrice.amount}} {{products[p-1].productPrices[0].unitPrice.currency}} /
                        {{products[p-1].productPrices[0].unitPrice.perUnit}}
                    </p>
                </div>
                    <div id="productImageContainer">
                        <img id="productImage" v-if="products[p-1].imgUrl != 'NO IMAGE SET'" v-bind:src="products[p-1].imgUrl" alt="Toode" v-on:click.prevent="showInfo(p-1)">
                        <img id="productImage" v-else src="https://static1.squarespace.com/static/56eddde762cd9413e151ac92/t/570cb89a5bd33022b93a1ecb/1460466676034/copyright1.jpg" alt="Toode" v-on:click.prevent="showInfo(p-1)">
                    </div>
            </div>
        </div>


        <!-- Eraldi screen mis, ilmub toote peale vajutades -->
    
        <div v-if="this.infoShow" class="popup">

        
            <div class="modal-content">
                <div id="modalInside">
                    <div id="modalText">
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
                        <button v-on:click.prevent="hideInfo" id="menubutton">Close</button>
                    </div>
                    </div>
                    <div id="modalPicture">
                        <img id="productImage" v-if="products[this.currentProduct].imgUrl != 'NO IMAGE SET'" v-bind:src="products[this.currentProduct].imgUrl" alt="Toode" height="100" width="100">
                        <img id="productImage" v-else src="https://static1.squarespace.com/static/56eddde762cd9413e151ac92/t/570cb89a5bd33022b93a1ecb/1460466676034/copyright1.jpg" alt="Toode">
                    </div>
                
                </div>
                
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
            currentSearch: "",
            currentCategory: null,
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

            if(this.currentCategory != null){
                address = 'http://localhost:8080/products/' + this.currentCategory + '/' +   this.currentPage  +'/24/name/asc';
            } else if (this.currentSearch != "")   {
                address = 'http://localhost:8080/products/search/' + this.currentSearch + "/" + this.currentPage + "/24/asc";
            } else {
                address = 'http://localhost:8080/products/' +  this.currentPage  +'/24/name/asc';
            }
            

            const request = async() => {
                const response = await fetch(address);
                const json = await response.json();
                this.products = json;
                this.end = this.products.length;

                if(this.products.length < 24) {
                    this.$emit("stateNext", false);
                    this.$emit("statePrevious", true);
                } else {
                    this.$emit("stateNext", true);
                    this.$emit("statePrevious", true);
                }
            }

            request();

            

            
            
        },

        previous(){
            this.currentPage = this.currentPage - 1;

            if(this.currentCategory != null){
                address = 'http://localhost:8080/products/' + this.currentCategory + '/' +   this.currentPage  +'/24/name/asc';
            } else if (this.currentSearch != "")   {
                address = 'http://localhost:8080/products/search/' + this.currentSearch + "/" + this.currentPage + "/24/asc";
            } else {
                address = 'http://localhost:8080/products/' +  this.currentPage  +'/24/name/asc';
            }
            


            

            const request = async() => {
                const response = await fetch(address);
                const json = await response.json();
                this.products = json;

                this.end = this.products.length;
                this.$emit("statePrevious", false);

                if(this.currentPage == 0) {
                this.$emit("stateNext", true);
                this.$emit("statePrevious", false);
                } else {
                this.$emit("statePrevious", true);
                this.$emit("stateNext", true);
                }
            }

            request();
        },
        showInfo(p){
            this.currentProduct = p;
            this.infoShow = true;
        },
        hideInfo(){
            this.currentProduct = -1;
            this.infoShow = false;
        },
        filterbyCategory(category){
            this.currentSearch = "";
            this.currentCategory = category;
            this.currentPage = 0;

            address = 'http://localhost:8080/products/' + this.currentCategory + '/' +   this.currentPage  +'/24/name/asc';


            const request = async() => {
                const response = await fetch(address);
                const json = await response.json();
                this.products = json;
                this.end = this.products.length;

                this.end = this.products.length;
                this.$emit("statePrevious", false);
                if(this.products.length < 24) {
                    this.$emit("stateNext", false);
                } else {
                    this.$emit("stateNext", true);
                }
            }

            request();

            
        },
        filterBySearch(search){
            this.currentCategory = null;
            this.currentSearch = search;
            this.currentPage = 0;

            address = 'http://localhost:8080/products/search/' + search + "/0/24/asc";
            const request = async() => {
                const response = await fetch(address);
                const json = await response.json();
                this.products = json;
                this.end = this.products.length;

                this.end = this.products.length;
                this.$emit("statePrevious", false);
                if(this.products.length < 24) {
                    this.$emit("stateNext", false);
                } else {
                    this.$emit("stateNext", true);
                }
            }

            request();

        }
    },
    created: function() {
        fetch(address)
        .then(r => r.json())
        .then(json => this.products = json);
    },
    props:{
        cycle:{
            type:Number
        },
        filter:{
            type:String
        },
        search:{
            type:String
        }
    },
    watch:{
        cycle: function(){
            if(Math.abs(this.cycle-this.currentPage) < 2){

                if(this.cycle > this.currentPage){
                    this.next();
                } else {
                    this.previous();
                }
            }
            this.currentPage = this.cycle;
        },

        filter: function(){
            console.log(this.filter)
            if(this.filter == "KOIK"){

                address = 'http://localhost:8080/products/0/24/name/asc';
                this.currentCategory = null;
                this.currentPage = 0;
                this.currentSearch = "";
                const request = async() => {
                    const response = await fetch(address);
                    const json = await response.json();
                    this.products = json;
                    this.end = this.products.length;
                    this.$emit("stateNext", true);
                    this.$emit("statePrevious", false);
            }

            request();
                

            } else if(this.filter != null){
                if(this.currentCategory != this.filter){
                  this.filterbyCategory(this.filter);
                }
            }
        },
        search: function(){
            if(this.search != ""){
                if(this.currentSearch != this.search){
                    this.filterBySearch(this.search);
                }
            }
        }
    }
}

</script>

<style scoped>

#menubutton {
  width: 150px;
  height: 40%;

  border-radius: 10px;
  border: 0;
  outline: 0;

  color: white;
  background-color: #02C39A;
  transition-duration: 0.3s;

  font-size: 30px;
  font-family: monospace;
}

#menubutton:hover{
  background-color: #00A896;
}

#menubutton:active{
  background-color:#02C39A;
  transition-duration: 0.1s;
}

#navButtonContainer{
    width: 100%;
    height: 50px;
}

#productView {
    margin-bottom: 20px;
    margin-left: 20px;
    min-height: 100vh;
    height: 100%;
    width: 69%;
    display: flex;
    flex-wrap: wrap;

}

.flex-item {
    border: #02C39A solid 1px;
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

#productContainer:hover{
    background-color: #02809054;
}

p {
    color: black;
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




#productPrice{
    width: 100%;
    height: 70%;
}

#productInfo{
    margin-bottom: 10px;
    width: 100%;
    height: 25%;
    padding-left: 7px;
}

#productImageContainer{
    margin-top: 15%;
    height: 100%;
}

#productImage{
    height: 65%;
    width: 100%;
}

#modalPicture{
    height: 300px;
    width: 200px;
}

#modalText{
    height: 100%;
    width: 50%;
}

#modalInside{
    width: 100%;
    height: 250px;
    display: flex; 
    overflow: hidden;
}

.modal-content {
  background-color: #fefefe;
  margin: 15% auto;
  padding: 20px;
  width: 30%; 
}

</style>
