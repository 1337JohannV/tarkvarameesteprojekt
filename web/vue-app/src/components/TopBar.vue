<template>
  <div id="menucontainer">
    <div id="welcometextcontainer">
      <p id="welcometext">Product price locator</p>
    </div>
    <div id="textcontainer">
      <textarea id="textbox" v-model="searchText" placeholder="Sisestage otsitav toode!"></textarea>
    </div>
    <div id="buttoncontainer">
      <button id="menubutton" v-on:click.prevent="searchDb">
        <p>{{ buttonText[0] }}</p>
      </button>
      {{searchText}}
    </div>
  </div>
</template>

<script>

export default {
  name: 'TopBar',
  data: function() {
    return {
      searchText: "",
      buttonText: ["Otsi"],
    }
  },
  methods:{
    searchDb(){
      console.log(this.searchText);
      this.$emit("search", searchText);
      this.searchText = "";
    }
  }
}
</script>

<style scoped>


#menucontainer{
  background-color: rgb(148, 221, 221);
  display: flex;
  width: 100%;
  height: 150px;
}

#textcontainer{
  display: flex;
  align-items: center; 
  height: 100%;
  width: 30%;
}

#textbox{
  width:100%;
  height:60%;
  float: right;
  resize: none;
}

#welcometextcontainer{
  float: left;
  height: 100%;
  width: 50%;
  
  display: flex;
  justify-content: center; 
  align-items: center; 
}

#buttoncontainer{
  height: 100%;
  width: 20%;
  float: left;
  
  display: flex; 
  align-items: center; 
}

#menubutton {
  width: 80%;
  height: 63.2%;

  padding: 0;
  margin: 0;

  border-radius: 0px;
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
