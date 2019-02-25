<template>
  <div id="contentcontainer">
            <p>SIIN ON TOOTED</p>
            <div id="products">
              <div id="toode" v-for="n in 9" :key="n"></div>
            </div>
            <button id="buttonNext">Next</button> <button id="buttonPrevious">Previous</button>
        </div>
</template>

<script>
export default {
  name: 'Products',
  data () {
    return {
      info:null
    }
  },
  methods: {
    initialLoad(){

      fetch('http://localhost:8080/Product', {
        method: "GET",
        mode: "cors",
      }).then(function(response) {
          console.log(response);
          return response;
          })
          .then(function(response) {
            console.log(response);
            });
    }
  },
  created(){
    return this.initialLoad();
  }
  
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style src="./Products.css" scoped></style>
