<template>
  <div class="container">
    <b-row>
      <b-col cols="6">
        <p class="h3">Toote andmed</p>
        <hr class="my-2">
        <p class="h4 mb-3">{{product.name}}</p>
        <p class="h6">EAN: {{product.ean}}</p>
        <p class="h6">Kategooria: {{product.category}}</p>
        <p class="h6">Tootja: {{product.producer}}</p>
        <p class="h6">P2ritolumaa: {{product.origin}}</p>
        <p class="h6">Kogus: {{product.quantity.value}} {{product.quantity.unit}}</p>
      </b-col>
      <b-col cols="6" class="border-left">
        <b-img fluid :src="product.imgUrl"/>
      </b-col>
    </b-row>
    <b-row>
      <b-col cols="12">
        <p class="h4">Toote hinnad poodides</p>
        <b-table striped hover small :items="product.productPrices" :fields="fields">
          <template slot="store" slot-scope="row">{{row.item}}</template>
        </b-table>
      </b-col>
    </b-row>
  </div>
</template>

<script>
export default {
  name: "ProductDetails",
  props: {
    product: {
      type: Object,
      required: true,
      default: function() {
        return {
          id: Number,
          name: String,
          ean: String,
          category: String,
          productPrices: [
            {
              store: String,
              url: String,
              regularPrice: {
                amount: Number,
                currency: String
              },
              specialPrice: {
                amount: Number,
                currency: String
              }
            }
          ],
          quantity: {
            value: Number,
            unit: String
          },
          producer: String,
          origin: String,
          imgUrl: String,
          basePrice: Number,
          baseWeight: Number
        };
      }
    },
    mounted() {
      this.prices = this.product.productPrices;
      console.log(this.prices);
      console.log(this.product.productPrices);
    },
    data: function() {
      return {
        fields: [
          { key: "store", label: "Pood" },
          { key: "regularPrice", label: "Hind" },
          { key: "specialPrice", label: "Hind kliendikaardiga" }
        ],
        prices: null
      };
    }
  }
};
</script>
