<template>
  <div id="menucontainer">
            <div id="welcometextcontainer"><p id="welcometext">The future of product price locating is here!</p></div>
            
            <div id="buttoncontainer" v-for="item in buttonText" :key="item">
                <button id="menubutton">
                    <p>{{ item }}</p>
                </button>
            </div>
        </div>
</template>

<script>
export default {
  name: 'TopBar',
  data() {
    return {
      buttonText: ["Tere", "Tere2", "Tere3","Tere4"]
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style src="./TopBar.css"scoped></style>
