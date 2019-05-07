<template>
  <div class="container">
    <b-row>
      <b-col cols="5">
        <p class="h4">Toote andmed</p>
        <hr class="my-2">
        <p v-if="product.ean != null" class="lead m-0 p-1">EAN: {{product.ean}}</p>
        <p class="lead m-0 p-1">
          Kategooria:
          <FormatCategory :category="product.category"/>
        </p>
        <p
          v-if="product.producer != null && product.producer != ''"
          class="lead m-0 p-1"
        >Tootja: {{product.producer}}</p>
        <p v-if="product.origin != null" class="lead m-0 p-1">Päritolumaa: {{product.origin}}</p>
        <p
          v-if="product.quantity != null"
          class="lead m-0 p-1"
        >Kogus: {{product.quantity.value}} {{product.quantity.unit}}</p>
      </b-col>
      <b-col cols="7">
        <b-img fluid :src="getProductImage(product)" class="mx-auto d-block"/>
      </b-col>
    </b-row>
    <b-row>
      <b-col cols="12">
        <p class="h4">Toote hinnad poodides</p>
        <ProductPriceTable :productPrices="product.productPrices"/>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import FormatCategory from "@/components/FormatCategory.vue";
import ProductPriceTable from "@/components/ProductPriceTable.vue";

export default {
  name: "ProductDetails",
  components: {
    FormatCategory,
    ProductPriceTable
  },
  methods: {
    getProductImage(product) {
      if (
        product.imgUrl != null &&
        product.imgUrl !=
          "https://www.prismamarket.ee/images/entry-no-image.png"
      ) {
        return product.imgUrl;
      } else {
        return this.$placeholderImgUrl;
      }
    }
  },
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
      console.log(this.product);
      console.log(this.prices);
      console.log(this.product.productPrices);
    }
  }
};
</script>
