<template>

    <div class="flex-container" id="productView">
        <div class="flex-item" v-for="p in products">
            <div id="productContainer">
                <div id="productInfo">
                    <p id="productTitle">{{p.name}}</p>
                    <!-- 
                    <p>EAN: {{p.ean}}</p>
                    <p>Tootja: {{p.producer}}</p> 
                    <p>Päritolumaa: {{p.origin}}</p> 
                    -->
                    <p v-if="p.quantity != null">
                        Kogus: {{p.quantity.value}} {{p.quantity.unit}}
                    </p>
                </div>
                <div id="productPrice">
                    <p id="bestPrice">Parim hind:</p>
                    <p>
                        {{p.productPrices[0].store}}:
                        {{p.productPrices[0].regularPrice.amount}} {{p.productPrices[0].regularPrice.currency}} - 
                        {{p.productPrices[0].unitPrice.amount}} {{p.productPrices[0].unitPrice.currency}}/
                        {{p.productPrices[0].unitPrice.perUnit}}
                    </p>
                    <p v-if="p.productPrices[0].specialPrice != null">
                        Kliendikaardiga: {{p.productPrices[0].specialPrice.amount}} {{p.productPrices[0].specialPrice.currency}}
                    </p>
                </div>
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
            products: null
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

#productView {
    overflow-y: scroll;
    height: 700px;
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
    height: 185px;
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
