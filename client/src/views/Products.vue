<template>
  <div id="app">
    <TopBar @search="filterBySearch"/>
    <div id="products">
      <FilterBar :noFilter="filter" @category="filterByCategory"/>
      <ProductView
        :cycle="currentPage"
        :filter="currentCat"
        :search="currentSearch"
        @statePrevious="statePrev"
        @stateNext="stateNext"
      />
    </div>
    <div id="footer">
      <button
        id="previous"
        v-on:click.prevent="newProduct('previous')"
        v-if="enablePrevious"
      >Previous</button>
      <button id="next" v-on:click.prevent="newProduct('next')" v-if="enableNext">Next</button>
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
    }
  }
};
</script>

<style>
* {
  margin: 0;
}

body {
  height: 100%;
  margin: 0;
  background-color: #f0f3bd;
}

#app {
  height: 100%;
}

#products {
  margin-top: 1%;
  display: flex;

  height: 100%;
  width: 100%;
}

#next {
  float: right;
  margin-right: 6%;
  margin-top: 25px;
  width: 100px;
  height: 50px;
}

#previous {
  float: left;
  margin-left: 31%;
  margin-top: 25px;
  width: 150px;
  height: 50px;
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
  height: 100px;
  overflow: hidden;
}
</style>
