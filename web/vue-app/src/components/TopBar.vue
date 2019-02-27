<template>
  <div id="menucontainer">
            <div id="welcometextcontainer"><p id="welcometext">Product price locator</p></div>
            
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

#menubutton{
    width: 100%;
    background-image: linear-gradient(-45deg, rgb(122, 235, 255), rgb(0, 195, 255));
    text-align: center;
    border: black solid 2px;
    color: white;
    font-family: monospace;
    font-size:30px;
}

#menubutton:hover{
    opacity: 0.8;
}

#welcometext{
    display: inline-block;
    vertical-align: middle;
    font-family: monospace;
    font-size:30px;
}

#welcometextcontainer{
    width: 40%;
    margin-left: 10px;
    display: flex;
    justify-content: center; /* align horizontal */
    align-items: center; /* align vertical */
}

#buttoncontainer{
    width: 15%;
    display: flex;
    padding: 10px 10px 10px 10px;

}

#menucontainer{
  background-color: silver;
  display: flex;
  flex-direction: row;
  margin-bottom: 20px;
}

</style>
