<template>
  <div class="container">
    <b-modal id="product-details" title="Toote detailvaade" size="lg" ok-only>
      <ProductDetails v-if="selectedProduct != null" :product="selectedProduct"/>
    </b-modal>
    <b-form-select v-model="category" :options="categoryOptions" size="sm"></b-form-select>
    <div class="d-flex flex-wrap">
      <div v-for="product in products" :key="product.id" class="p-2 product-wrapper">
        <div
          class="border p-1 shadow-sm product"
          v-b-modal.product-details
          @click="selectedProduct = product"
        >
          <b-img :src="product.imgUrl" fluid></b-img>
          <p class="h4">{{product.name}}</p>
          <p class="h6">EAN: {{product.ean}}</p>
          <p class="h6">Kategooria: {{product.category}}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ProductDetails from "@/components/ProductDetails.vue";
export default {
  name: "AdminProductView",
  components: {
    ProductDetails
  },
  mounted() {
    this.fetchProducts();
  },
  methods: {
    fetchProducts: function() {
      if (this.category != null) {
        var url =
          this.$serverBaseUrl +
          "/products/" +
          this.category +
          "/" +
          this.page +
          "/" +
          this.pageSize +
          "/" +
          this.orderBy +
          "/" +
          this.direction;
      } else if (this.category == null) {
        var url =
          this.$serverBaseUrl +
          "/products/" +
          this.page +
          "/" +
          this.pageSize +
          "/" +
          this.orderBy +
          "/" +
          this.direction;
      }
      console.log(url);
      this.$http({
        method: "get",
        url: url
      }).then(response => (this.products = response.data));
    }
  },
  watch: {
    selectedProduct: function() {
      this.fetchProducts();
    } 
  },
  data: function() {
    return {
      searchQuery: "",
      page: 0,
      pageSize: 20,
      orderBy: "id",
      direction: "asc",
      category: null,
      selectedProduct: null,
      categoryOptions: [
        { value: null, text: "Kõik" },
        { value: "LIHA_JA_KALA", text: "Liha ja kala" },
        { value: "PUU_JA_KOOGIVILJAD", text: "Puu ja köögiviljad" },
        { value: "PIIMATOOTED_MUNAD_VOID", text: "Piimatooted, munad, võid" },
        {
          value: "LEIVAD_SAIAD_KONDIITRITOOTED",
          text: "Leivad, saiad, kondiitritooted"
        },
        { value: "KUIVAINED_HOIDISED", text: "Kuivained ja hoidised" },
        { value: "KASTMED_OLID", text: "Kastmed ja õlid" },
        {
          value: "MAIUSTUSED_KUPSISED_NAKSID",
          text: "Maiustused, küpsised, näksid"
        },
        { value: "KULMUTATUD_TOIDUKAUBAD", text: "Külmutatud toidukaubad" },
        { value: "JOOGID", text: "Joogid" }
      ],
      products: [
        {
          id: 0,
          name: "agasgasg",
          ean: "gsagasgas",
          category: "asgasgasg",
          productPrices: [
            {
              store: "sagasgasg",
              url: "asgasgasg",
              regularPrice: {
                amount: 0,
                currency: "asgas"
              },
              specialPrice: {
                amount: 0,
                currency: "agsg"
              }
            }
          ],
          quantity: {
            value: 0,
            unit: "gasg"
          },
          producer: "gasg",
          origin: "gas",
          imgUrl:
            "https://www.selver.ee/media/catalog/product/cache/1/image/800x/9df78eab33525d08d6e5fb8d27136e95/4/7/4740153410054.jpg",
          basePrice: 0,
          baseWeight: 0
        }
      ]
    };
  }
};
</script>

<style lang="less" scoped>
.product-wrapper {
  width: 25%;
  min-width: 12rem;
}

.product {
  height: 25rem;
}

.product-wrapper :hover {
  cursor: pointer;
  img {
    transform: scale(1.01);
  }
}
</style>

