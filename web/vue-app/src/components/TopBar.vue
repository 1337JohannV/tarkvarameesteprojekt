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
      buttonText: ["Selver", "Prisma", "Empty1","Empty2"]
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
