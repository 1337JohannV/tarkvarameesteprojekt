<template>
    <div id=filterBar>
      <button id="menubutton" v-for="n in 11" :key="n" v-bind:style=" buttons[n-1].change ? 'background-color: #F0F3BD; color:black;' : '{#menubuttonAlter}'" v-on:click.prevent="filterByCategory(n-1)">{{buttons[n-1].name}}</button>
    </div>
</template>

<script>


export default {
  name: 'FilterBar',
  data: function(){
    return {
      buttons:[
        {
          name: "Kõik",
          value: "KOIK",
          change: true
          },
        {
          name: "Puu ja köögiviljad",
          value: "PUU_JA_KOOGIVILJAD",
          change: false
          },
        { 
          name: "Liha ja kala",
          value: "LIHA_JA_KALA",
          change: false
          },
        {
          name: "Piimatooted, munad, võid",
          value: "PIIMATOOTED_MUNAD_VOID",
          change: false
          },
        {
          name: "Juustud",
          value: "JUUSTUD",
          change: false
          },
        {
          name: "Leivad, saiad, kondiitritooted",
          value: "LEIVAD_SAIAD_KONDIITRITOOTED",
          change: false
          },
        {
          name: "Kuivained, hoidised",
          value: "KUIVAINED_HOIDISED",
          change: false
          },
        {
          name: "Kastmed, õlid",
          value: "KASTMED_OLID",
          change: false
          },
        {
          name: "Maiustused, küpsised, naksid",
          value: "MAIUSTUSED_KUPSISED_NAKSID",
          change: false
          
          },
        {
          name: "Külmutatud toidukaubad",
          value: "KULMUTATUD_TOIDUKAUBAD",
          change: false
          },
        {
          name: "Joogid",
          value: "JOOGID",
          change: false
          },
      ],
    };
  },
  methods: {
    filterByCategory(catNr){
      this.buttons.forEach(element => {
        element.change = false;
      });
      this.buttons[catNr].change = true;

        this.$emit("category", this.buttons[catNr].value);
    }
  },
  props:{
        noFilter:{
            type:Boolean
        }
    },
    watch:{
        noFilter: function(){
          if(this.noFilter){
             this.buttons.forEach(element => {
            element.change = false;
            });
          }
        }
    }
}
</script>

<style>
#filterBar {
    background: #05668D;
    border-radius: 10px;
    width: 30%;
    height: 100%;
    border: 2px solid #05668D;
    position: sticky;
    top: 10%;
    display: flex;
    flex-direction: column;
}

a:hover{
  text-decoration: underline;
}

#menubuttonAlter {
  align-self: center;
  margin-top: 10px;
  margin-bottom: 10px;
  width: 95%;
  height: 10%;

  border-radius: 10px;
  border: 0;
  outline: 0;

  color: white;
  border: 1px solid #02C39A;
  background-color: #F0F3BD;
  transition-duration: 0.3s;
  

  font-size: 30px;
  font-family: monospace;
}

#menubuttonAlter:hover{
  background-color: #02C39A;
}

#menubuttonAlter:active{
  background-color:#02C39A;
  transition-duration: 0.1s;
}

#menubutton {
  align-self: center;
  margin-top: 10px;
  margin-bottom: 10px;
  width: 95%;
  height: 10%;

  border-radius: 10px;
  border: 0;
  outline: 0;

  color: white;
  background-color: #02C39A;
  transition-duration: 0.3s;
  

  font-size: 30px;
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
