<template>

    <div class="flex-container" id="productView">
        <div class="flex-item" v-for="p in end" :key="p" v-if="p > start">
            <div id="productContainer">
                <div id="productInfo">
                    <p id="productTitle">{{products[p].name}}</p>
                    <!-- 
                    <p>EAN: {{p.ean}}</p>
                    <p>Tootja: {{p.producer}}</p> 
                    <p>Päritolumaa: {{p.origin}}</p> 
                    -->
                    <p v-if="products[p].quantity != null">
                        Kogus: {{products[p].quantity.value}} {{products[p].quantity.unit}}
                    </p>
                </div>
                <div id="productPrice">
                    <p id="bestPrice">Parim hind:</p>
                    <p>
                        {{products[p].productPrices[0].store}}:
                        {{products[p].productPrices[0].regularPrice.amount}} {{products[p].productPrices[0].regularPrice.currency}} - 
                        {{products[p].productPrices[0].unitPrice.amount}} {{products[p].productPrices[0].unitPrice.currency}}/
                        {{products[p].productPrices[0].unitPrice.perUnit}}
                    </p>
                    <p v-if="products[p].productPrices[0].specialPrice != null">
                        Kliendikaardiga: {{products[p].productPrices[0].specialPrice.amount}} {{products[p].productPrices[0].specialPrice.currency}}
                    </p>
                </div>
            </div>
        </div>
        <div id="navButtonContainer">
            <button v-on:click.prevent="previous" v-if="this.start > 0">Previous</button> 
            <button id="navButton" v-on:click.prevent="next" v-if="this.end != this.products.length-1">Next</button>
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
        }
    },
    created: function() {
        fetch(address)
        .then(r => r.json())
        .then(json => this.products = json);
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
    box-shadow: 1px 1px 1px 1px gray;
    width: 185px;
    height: 190px;
    padding: 5px;
    float: right;
}

p {
    font-size: 12px;

}

#productTitle, #bestPrice {
    font-size: 14px;
    font-weight: bold;
}

</style>
