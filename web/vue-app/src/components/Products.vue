<template>
  <div id="contentcontainer">
            <p>SIIN ON TOOTED</p>
            <div id="products">
              <div id="toode" v-for="n in 9" :key="n"><p>{{info}}</p></div>
            </div>
            <button id="buttonNext">Next</button> <button id="buttonPrevious">Previous</button>
        </div>
</template>

<script>
export default {
  name: 'Products',
  data () {
    return {
      info:''
    }
  },
  methods: {
    initialLoad(){
      fetch('http://localhost:8080/Product')
          .then(function(response) {

          return response.text();
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
