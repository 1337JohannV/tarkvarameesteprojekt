<template>
  <div class="container">
    <b-modal id="product-details" title="Toote detailvaade" size="lg" ok-only>
      <ProductDetails v-if="selectedProduct != null" :product="selectedProduct"/>
    </b-modal>
    <div class="d-block">
      <b-button v-b-toggle.options variant="dark" size="sm" class="shadow-sm">
        <font-awesome-icon icon="bars" class="mr-2"/>Valikud
      </b-button>
    </div>
    <b-collapse id="options" class="border rounded shadow-sm p-1 mt-1">
      <b-tabs pills small align="center" justified content-class="p-2">
        <b-tab title="Filtrid">
          <b-form inline class="mb-2">
            <b-form-group label="Kategooria" label-for="category">
              <b-form-select
                class="ml-2 w-100"
                id="category"
                v-model="category"
                :options="categoryOptions"
                size="sm"
              ></b-form-select>
            </b-form-group>
          </b-form>
          <b-form inline class="mb-2">
            <b-form-group label="Sortreeri" label-for="sort">
              <b-form-select
                size="sm"
                class="ml-2"
                id="sort"
                v-model="orderBy"
                :options="orderOptions"
              ></b-form-select>
            </b-form-group>
            <b-form-group class="ml-2" label="Järjesta" label-for="direction">
              <b-form-select
                class="ml-2"
                id="direction"
                size="sm"
                v-model="direction"
                :options="directionOptions"
              ></b-form-select>
            </b-form-group>
          </b-form>
          <b-form inline>
            <b-form-group label="Toodete arv lehel" label-for="page-size">
              <b-form-input type="number" size="sm" class="ml-2 w-25" v-model="pageSize"></b-form-input>
            </b-form-group>
          </b-form>
          <div class="d-block text-center">
            <b-button variant="primary" class="shadow" @click="fetchProducts()">
              <font-awesome-icon icon="redo"/>
            </b-button>
          </div>
        </b-tab>
        <b-tab title="Otsing">
          <b-form-input id="search-input" class="d-inline-block" v-model="searchQuery"></b-form-input>
          <b-button class="ml-2 mb-1" variant="primary">
            <font-awesome-icon icon="search"/>
          </b-button>
        </b-tab>
      </b-tabs>
    </b-collapse>
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
    <b-pagination
      v-model="currentPage"
      :total-rows="messageCount"
      :per-page="pageSize"
      align="center"
      @input="fetchData(); fetchPageCount()"
    ></b-pagination>
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
    fetchSearchResults: function() {
      var url = this.$serverBaseUrl + "/products/search/" + this.searchQuery;
    },
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
      orderOptions: [
        { value: "id", text: "Vaikeväärtus" },
        { value: "basePrice", text: "Hind" },
        { value: "baseWeight", text: "Kaal" },
        { value: "name", text: "Nimi" },
        { value: "ean", text: "Tootekood" },
        { value: "producer", text: "Tootja" },
        { value: "origin", text: "Päritoluriik" }
      ],
      directionOptions: [
        { value: "asc", text: "Kasvavalt" },
        { value: "desc", text: "Kahanevalt" }
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

#search-input {
  width: calc(100% - 3.15rem);
}
</style>

