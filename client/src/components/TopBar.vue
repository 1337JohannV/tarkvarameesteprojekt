<template>
  <nav id="menucontainer" class="navbar">
    <span id="brand" class="navbar-brand mb-0 h1">Product price locator</span>
    <form class="form-inline">
      <input  v-model="searchText" class="form-control mr-sm-2" placeholder="Search" aria-label="Search">
      <button id="menubutton" v-on:click.prevent="searchDb" class="btn btn-outline-success my-2 my-sm-0">Search</button>
    </form>
    <router-link to="/ostukorv" id="menubutton" class="btn" type="button">Ostukorv</router-link>
  </nav>
</template>

<script>

export default {
  name: 'TopBar',
  data: function() {
    return {
      searchText: "",
      buttonText: ["Otsi"],
      test: "Working!"
    }
  },
  methods:{
    searchDb(){
      this.$emit("search", this.searchText);
      this.searchText = "";
    },
  }
}
</script>

<style scoped>

#brand{
  font-family: "Comic Sans MS", cursive, sans-serif;
  color: white;
}

#menucontainer{
  background-color: #05668D;
}

#menubutton {
  color: white;
  background-color: #02C39A;
  transition-duration: 0.3s;
  border-radius: 10px;
  box-shadow: none;
  font-family: monospace;
}

#menubutton:hover{
  background-color: #00A896;
}

#menubutton:active{
  background-color:#02C39A;
  transition-duration: 0.1s;
}

</style>
