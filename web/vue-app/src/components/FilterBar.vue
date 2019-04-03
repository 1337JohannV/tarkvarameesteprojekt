<template>
    <div id=filterBar>
      <button id="menubutton" v-for="n in 10" :key="n" v-on:click.prevent="filterByCategory(n-1)">{{buttons[n-1].name}}</button>
    </div>
</template>

<script>


export default {
  name: 'FilterBar',
  data: function(){
    return {
      buttons:[
        {
          name: "Puu ja köögiviljad",
          value: "PUU_JA_KOOGIVILJAD"
          },
        { 
          name: "Liha ja kala",
          value: "LIHA_JA_KALA"
          },
        {
          name: "Piimatooted, munad, võid",
          value: "PIIMATOOTED_MUNAD_VOID"
          },
        {
          name: "Juustud",
          value: "JUUSTUD"
          },
        {
          name: "Leivad, saiad, kondiitritooted",
          value: "LEIVAD_SAIAD_KONDIITRITOOTED"
          },
        {
          name: "Kuivained, hoidised",
          value: "KUIVAINED_HOIDISED"
          },
        {
          name: "Kastmed, õlid",
          value: "KASTMED_OLID"
          },
        {
          name: "Maiustused, küpsised, naksid",
          value: "MAIUSTUSED_KUPSISED_NAKSID"
          
          },
        {
          name: "Külmutatud toidukaubad",
          value: "KULMUTATUD_TOIDUKAUBAD"
          },
        {
          name: "Joogid",
          value: "JOOGID"
          },
      ]

    };
  },
  methods: {
    filterByCategory(catNr){
        this.$emit("category", this.buttons[catNr].value);
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
