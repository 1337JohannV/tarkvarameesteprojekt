<template>
  <div class="container">
    <b-modal id="product-details" size="lg" hide-footer>
      <template slot="modal-title">
        <p v-if="selectedProduct != null" class="h3 m-0">{{selectedProduct.name}}</p>
      </template>
      <ProductDetails v-if="selectedProduct != null" :product="selectedProduct"/>
    </b-modal>
    <div class="d-block">
      <b-button v-b-toggle.options variant="dark" size="sm" class="shadow m-1">
        <font-awesome-icon icon="bars" class="mr-2"/>Valikud
      </b-button>
    </div>
    <b-collapse id="options" class="border rounded shadow-sm p-1 mt-1">
      <b-tabs pills small align="center" justified content-class="p-2">
        <b-tab title="Filtrid">
          <b-form inline class="mb-2">
            <b-form-group label="Kategooria" label-for="category">
              <b-form-select
                class="ml-2 w-100 shadow-sm"
                id="category"
                v-model="category"
                :options="categoryOptions"
                size="sm"
              ></b-form-select>
            </b-form-group>
          </b-form>
          <b-form inline class="mb-2 p-0">
            <b-form-group label="Sortreeri" label-for="sort">
              <b-form-select
                size="sm"
                class="ml-2 mr-2 shadow-sm"
                id="sort"
                v-model="orderBy"
                :options="orderOptions"
              ></b-form-select>
            </b-form-group>
            <b-form-group label="Järjesta" label-for="direction">
              <b-form-select
                class="shadow-sm ml-2"
                id="direction"
                size="sm"
                v-model="direction"
                :options="directionOptions"
              ></b-form-select>
            </b-form-group>
          </b-form>
          <b-form inline>
            <b-form-group label="Toodete arv lehel" label-for="page-size">
              <b-form-input type="number" size="sm" class="ml-2 w-50 shadow-sm" v-model="pageSize"></b-form-input>
            </b-form-group>
          </b-form>
          <div class="d-block text-center">
            <b-button variant="primary" class="shadow" @click="fetchProducts(); fetchRowCount()">
              <font-awesome-icon icon="redo"/>
            </b-button>
          </div>
        </b-tab>
        <b-tab title="Otsing">
          <b-form-input id="search-input" class="d-inline-block shadow-sm" v-model="searchQuery"></b-form-input>
          <b-button class="ml-2 mb-1 shadow" variant="primary" @click="fetchSearchResults()">
            <font-awesome-icon icon="search"/>
          </b-button>
        </b-tab>
      </b-tabs>
    </b-collapse>
    <div v-if="errored" class="d-block text-center my-5">
      <p class="h1 display-4">Päring ebaõnnestus, vabandame</p>
    </div>
    <div
      v-else-if="search && products != null && products.length == 0"
      class="d-block text-center my-5"
    >
      <p class="h1 display-4">Tulemused puuduvad</p>
    </div>
    <div v-else-if="products == null" class="d-block text-center my-5">
      <b-spinner variant="primary"></b-spinner>
      <p class="m-0 mt-2 lead text-secondary">Tooteid laetakse, palun oota</p>
    </div>
    <div v-else-if="products != null && products.length > 0">
      <p v-if="search" class="lead m-0 ml-1">Leiti {{products.length}} vastet</p>
      <div class="d-flex flex-wrap justify-content-center">
        <div
          v-for="product in products"
          :key="product.id"
          class="p-1 product-wrapper d-inline-block"
        >
          <div
            class="p-1 shadow-sm product-tile rounded border bg-light text-secondary"
            v-b-modal.product-details
            @click="selectedProduct = product"
          >
            <b-img :src="getProductImage(product)" class="border rounded shadow-sm bg-white"></b-img>
            <p class="h5 m-0 mt-1 text-dark text-center">{{product.name}}</p>
            <hr class="my-1">
            <p v-if="product.ean != null" class="small m-0">EAN: {{product.ean}}</p>
            <p class="small m-0">
              Kategooria:
              <FormatCategory :category="product.category"/>
            </p>
          </div>
        </div>
      </div>
    </div>
    <b-pagination
      v-if="(rows > pageSize) && !search && !errored"
      v-model="page"
      class="mt-2"
      :total-rows="rows"
      :per-page="pageSize"
      align="center"
      @input="fetchProducts()"
    ></b-pagination>
  </div>
</template>

<script>
import ProductDetails from "@/components/ProductDetails.vue";
import FormatCategory from "@/components/FormatCategory.vue";

export default {
  name: "AdminProductView",
  components: {
    ProductDetails,
    FormatCategory
  },
  mounted() {
    this.fetchProducts();
    this.fetchRowCount();
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
    },
    fetchSearchResults: function() {
      if (this.searchQuery.length > 0) {
        this.search = true;
        this.products = null;
        var searchQuery = this.searchQuery.replace(/\s+/g, "-").toLowerCase();
        var url = this.$serverBaseUrl + "/products/search/" + searchQuery;
        this.$http({
          method: "get",
          url: url
        })
          .then(response => (this.products = response.data))
          .then(() => (this.errored = false))
          .catch(error => (this.errored = true));
      }
    },
    fetchRowCount() {
      if (this.category != null) {
        var url = this.$serverBaseUrl + "/products/rows/" + this.category;
      } else {
        var url = this.$serverBaseUrl + "/products/rows";
      }
      this.$http({
        method: "get",
        url: url
      })
        .then(response => (this.rows = response.data))
        .then(() => (this.errored = false))
        .catch(error => (this.errored = true));
    },
    fetchProducts: function() {
      this.search = false;
      this.products = null;
      if (this.category != null) {
        var url =
          this.$serverBaseUrl +
          "/products/" +
          this.category +
          "/" +
          (this.page - 1) +
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
          (this.page - 1) +
          "/" +
          this.pageSize +
          "/" +
          this.orderBy +
          "/" +
          this.direction;
      }
      this.$http({
        method: "get",
        url: url
      })
        .then(response => (this.products = response.data))
        .then(() => (this.errored = false))
        .catch(error => (this.errored = true));
    }
  },
  data: function() {
    return {
      searchQuery: "",
      page: 1,
      pageSize: 12,
      rows: 0,
      orderBy: "id",
      direction: "asc",
      errored: false,
      category: null,
      selectedProduct: null,
      search: false,
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
      products: null
    };
  }
};
</script>

<style lang="less" scoped>
.product-wrapper {
  width: 25%;
  min-width: 12rem;
  img {
    display: block;
    width: 100%;
    max-height: 9rem;
    object-fit: cover;
  }
}

.product-tile {
  height: 20rem;
}

.product-wrapper :hover {
  cursor: pointer;
  img {
    transform: scale(1.03);
  }
}

#search-input {
  width: calc(100% - 3.15rem);
}
</style>

