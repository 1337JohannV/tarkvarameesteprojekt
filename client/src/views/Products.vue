<template>
  <div id="app">
    <TopBar @search="filterBySearch"/>
    <div class="container-fluid mt-3 mb-3 ">
      <div class="row">
        <div class="col-4 pl-1">

          <FilterBar :noFilter="filter" @category="filterByCategory"/>

        </div>
        <div class="col-8 pr-1">

          <ProductView
          :cycle="currentPage"
          :filter="currentCat"
          :search="currentSearch"
          @statePrevious="statePrev"
          @stateNext="stateNext"
          />

        </div>
      </div>
    </div>
    <div id="footer" class="navbar">
      <div class="container-fluid">

        <div class="col-4 pl-1">
        </div>

        <div class="col-8 p-0">
          <button
            id="previous"
            class="btn btn-primary btn-lg"
            v-on:click.prevent="newProduct('previous')"
            v-if="enablePrevious"
            >Previous</button>

          <button 
            id="next"
            class="btn btn-primary btn-lg float-right" 
            v-on:click.prevent="newProduct('next')" 
            v-if="enableNext"
            >Next</button>


        </div>

      </div>
    </div>

  </div>
</template>

<script>
import TopBar from "@/components/TopBar.vue";
import ProductView from "@/components/ProductView.vue";
import FilterBar from "@/components/FilterBar.vue";

export default {
  name: "Products",
  components: {
    TopBar,
    ProductView,
    FilterBar
  },
  data: function() {
    return {
      infoShow: false,
      currentSearch: "",
      currentPage: 0,
      currentCat: null,
      enablePrevious: false,
      enableNext: true,
      filter: false
    };
  },
  methods: {
    newProduct(variable) {
      if (variable == "next") {
        this.currentPage = this.currentPage + 1;
      } else {
        this.currentPage = this.currentPage - 1;
      }
    },
    statePrev(state) {
      this.enablePrevious = state;
    },
    stateNext(state) {
      this.enableNext = state;
    },
    filterByCategory(category) {
      this.currentPage = 0;
      this.currentCat = category;
      this.currentSearch = "";
      this.filter = false;
    },
    filterBySearch(search) {
      this.filter = true;
      this.currentCat = null;
      this.currentPage = 0;
      this.currentSearch = search;
    },
    show(){
      this.infoShow = true;
      console.log(this.infoShow);
    }
  }
};
</script>

<style scoped>
* {
  margin: 0;
}

#app {
  height: 100%;
  background-color: #f0f3bd;
}

#previous,
#next {
  border-radius: 10px;
  border: 0;
  outline: 0;
  color: white;
  background-color: #02c39a;
  transition-duration: 0.3s;
  font-size: 30px;
  font-family: monospace;
}

#previous:hover,
#next:hover {
  background-color: #00a896;
}

#previous:active,
#next:active {
  background-color: #02c39a;
  transition-duration: 0.1s;
}

#footer {
  background-color: #05668d;
}
</style>
