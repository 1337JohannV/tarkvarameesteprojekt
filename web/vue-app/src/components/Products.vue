<template>
  <div id="contentcontainer">
            <p>SIIN ON TOOTED</p>
            <div id="products" v-if="ajaxDone">
              <div id="toode"  v-for="n in 9" :key="n">
 
                <p>Toote nimi: {{LIHA_JA_KALA[n].name}}</p>
                <p>Toote tavahind: {{LIHA_JA_KALA[n].productPrices[0].regularPrice.currency}}</p>
                <p>Pood: {{LIHA_JA_KALA[n].productPrices[0].store}}</p>
                <p>Ühik: {{LIHA_JA_KALA[n].quantity.unit}} ja selle kogus {{LIHA_JA_KALA[0].quantity.value}}</p>
                
                </div>
            </div> 
            <button id="buttonNext" v-on:click="showLiha">Next</button> <button id="buttonPrevious">Previous</button>
        </div>
</template>

<script>
export default {
  name: 'Products',
  data () {
    return {
      ajaxDone: false,
      LIHA_JA_KALA: [ 
        {
        category: '',
        ean: '',
        id: 0,
        name: '',
        origin: '',
        producer: '',
        productPrices:[
          {
            regularPrice: {
              amount: 0,
              currency: '',
            },
            specialPrice: null,
            store: '',
            unitPrice:{
              amount: 0,
              currency: '',
              perUnit: ''
            },
            url: '',
          }
        ],
        quantity: {
          unit: '',
          value: 0,
        }
      }
    ]
    }
  },
  methods:{
    showLiha(){
      console.log("Working test");
      console.log(this.LIHA_JA_KALA);
    }
  },
  created(){
    let self = this;

    fetch('http://localhost:8080/products/')
          .then(function(response) {
          return response.json();
          })
          .then(function(response) {
            self.LIHA_JA_KALA = response.LIHA_JA_KALA;
            self.ajaxDone = true;
            });
  }
  
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style src="./Products.css" scoped>

</style>
