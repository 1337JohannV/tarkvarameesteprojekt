<template>
  <div id="menucontainer">
    <div id="welcometextcontainer">
      <p id="welcometext">Product price locator</p>
    </div>
            
    <div id="buttoncontainer" v-for="item in buttonText" :key="item">
      <button id="menubutton" v-if="item == 'Segu'" v-on:click.prevent="initiateSelver">
        <p>{{ item }}</p>
      </button>
      <button id="menubutton" v-else-if="item == 'Prisma'" v-on:click.prevent="initiatePrisma">
        <p>{{ item }}</p>
      </button>
      <button id="menubutton" v-else>
        <p>{{ item }}</p>
      </button>
    </div>
  </div>
</template>

<script>

export default {
  name: 'TopBar',
  data: function() {
    return {
      buttonText: ["Segu", "Prisma", "Empty1","Empty2"],
      products: null

    }
  },
  methods:{
    initiateSelver: function(){
      fetch('http://localhost:8080/products/')
        .then(r => r.json())
        .then(json => this.products = json);
        this.$emit("productsData", this.products);
    },

    initiatePrisma: function(){
      fetch('http://localhost:8080/products/test/')
        .then(r => r.json())
        .then(json => this.products = json);
        this.$emit("productsData", this.products);
    }
  }
}
</script>

<style scoped>


#menucontainer{
  background-color: rgb(148, 221, 221);
  display: inline-block;
  width: 100%;
  height: 150px;
}

#welcometextcontainer{
  float: left;
  height: 100%;
  width: 40%;
  
  display: flex;
  justify-content: center; 
  align-items: center; 
}

#buttoncontainer{
  height: 100%;
  width: 15%;
  float: left;
  
  display: flex;
  justify-content: center; 
  align-items: center; 
}

#menubutton {
  width: 80%;
  height: 60%;

  border-radius: 10px;
  border: 0;
  outline: 0;

  color: white;
  background-color: aqua;
  transition-duration: 0.3s;
  border: 2px solid aqua;

  font-size: 30px;
  font-family: monospace;
}

#menubutton:hover{
  background-color: white;
  color: black;
}

#menubutton:active{
  background-color:aqua;
  opacity: 0.4;
  transition-duration: 0.1s;
}

#welcometext{
  font-family: monospace;
  font-size: 40px;
}
</style>
