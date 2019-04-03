<template>
    <div id=filterBar>
      <button id="menubutton">PUU_JA_KOOGIVILJAD</button>
      <button id="menubutton">LIHA_JA_KALA</button>
      <button id="menubutton">PIIMATOOTED_MUNAD_VOID</button>
      <button id="menubutton">JUUSTUD</button>
      <button id="menubutton">LEIVAD_SAIAD_KONDIITRITOOTED</button>
      <button id="menubutton">KUIVAINED_HOIDISED</button>
      <button id="menubutton">KASTMED_OLID</button>
      <button id="menubutton">MAIUSTUSED_KUPSISED_NAKSID</button>
      <button id="menubutton">KULMUTATUD_TOIDUKAUBAD</button>
      <button id="menubutton">JOOGID</button>
    </div>
</template>

<script>


export default {
  name: 'FilterBar',
  data: function(){
    return {

    };
  },
  methods: {

  }
}
</script>

<style>
#filterBar {
    width: 30%;
    height: 100%;
    border: 2px solid black;
    position: sticky;
    top: 0px;
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
</style>
