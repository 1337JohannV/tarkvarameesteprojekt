<template>
    <div id=app>
      <TopBar/>
      <div id="products">
        <ProductView :cycle="currentPage" @statePrevious="statePrev" @stateNext="stateNext"/>
      </div>
      <div id="footer">
          <button id="previous" v-on:click.prevent="newProduct('previous')" v-if="enablePrevious">Previous</button>
          <button id="next" v-on:click.prevent="newProduct('next')" v-if="enableNext">Next</button>
      </div>
    </div>
</template>

<script>
import TopBar from './components/TopBar.vue'
import ProductView from './components/ProductView.vue'

export default {
  name: 'app',
  components: {
    TopBar,
    ProductView
  },
  data: function(){
    return {
      currentPage: 0,
      enablePrevious: false,
      enableNext: true,
    };
  },
  methods: {
    newProduct(variable){
      if(variable == "next") {
        this.currentPage = this.currentPage+1;
      } else {
        this.currentPage = this.currentPage-1;
      }
      
      
    },
    statePrev(state){
      console.log("worksPrev")
      this.enablePrevious = state;
    },
    stateNext(state){
      console.log("worksNext")
      this.enableNext = state;
    }
  }
}
</script>

<style>
* {
    margin: 0;
  }

body {
  height: 100%;
  margin: 0;
  background-color: rgb(206, 255, 255);
}

#app {
  height: 100%;
}

#products{
  margin-top: 1%; 
  display: flex;
  justify-content: center; 

  height: 100%;
  width: 100%;
}

#next{
  float: right;
  margin-right: 22.5%;
  margin-top: 25px;
  width: 100px;
  height: 50px;
}

#previous{
  float: left;
  margin-left: 23%;
  margin-top: 25px;
  width: 150px;
  height: 50px;
}

#previous, #next {
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

#previous:hover, #next:hover{
  background-color: white;
  color: black;
}

#previous:active, #next:active{
  background-color:aqua;
  opacity: 0.4;
  transition-duration: 0.1s;
}

#footer {
  background-color:rgb(148, 221, 221);
  height: 100px;
  overflow: hidden;
  
}

</style>
