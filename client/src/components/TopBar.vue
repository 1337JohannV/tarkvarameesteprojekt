<template>
  <nav id="menucontainer" class="navbar">
    <router-link to="/admin" id="brand" class="navbar-brand mb-0 h1">Product price locator</router-link>
    
    <form class="form-inline">

      <div>
        <b-dropdown id="dropdown-1" :text="direction" class="sm-md-2">
        <b-dropdown-item v-on:click.prevent="direct('Kahanev')">Kahanev</b-dropdown-item>
        <b-dropdown-item v-on:click.prevent="direct('Tõusev')">Tõusev</b-dropdown-item>
        </b-dropdown>
      </div>

      <div>
        <b-dropdown id="dropdown-1" :text="currentFilter" class="sm-md-2">
        <b-dropdown-item v-on:click.prevent="filter('Nimi')">Nimi</b-dropdown-item>
        <b-dropdown-item v-on:click.prevent="filter('EAN')">EAN</b-dropdown-item>
        <b-dropdown-item v-on:click.prevent="filter('Tootja')">Tootja</b-dropdown-item>
        <b-dropdown-item v-on:click.prevent="filter('Päritolu')">Päritolu</b-dropdown-item>
        </b-dropdown>
      </div>

      <input  v-model="searchText" class="form-control mr-sm-2" placeholder="Search" aria-label="Search">
      <button id="menubutton" v-on:click.prevent="searchDb" class="btn btn-outline-success my-2 my-sm-0">Search</button>
    </form>
    <router-link to="/ostukorv" id="menubutton" class="btn" type="button">Ostukorv ({{this.$root.$data.sourceOfTruth.length}})</router-link>
  </nav>
</template>

<script>

export default {
  name: 'TopBar',
  data: function() {
    return {
      searchText: "",
      currentFilter: "Nimi",
      direction: "Tõusev",
      buttonText: ["Otsi"],
    }
  },
  methods:{
    searchDb(){
      this.$emit("search", this.searchText);
      this.searchText = "";
    },
    filter(filter){
      

      if(filter == "Nimi"){
        this.$emit("ordering", "name");
      } 
      else if(filter == "EAN"){
        this.$emit("ordering", "ean");
      }
      else if(filter == "Tootja"){
        this.$emit("ordering", "producer");
      }
      else if(filter == "Päritolu"){
        this.$emit("ordering", "origin");
      }
      this.currentFilter = filter;
    },

    direct(direction){
      

      if(direction == "Kahanev"){
        this.$emit("direct", "desc");
      } 
      else if(direction == "Tõusev"){
        this.$emit("direct", "asc");
      }
      this.direction = direction;
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
