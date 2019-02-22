new Vue({
    el: '#menuelement',
    data: {
        buttontext: ["Tere","Tere2","Tere3","Tere4"]
    },
    template :
        `<div id="menucontainer">
            <div id="welcometextcontainer"><p id="welcometext">The future of product price locating is here!</p></p></div>
            
            <div id="buttoncontainer" v-for="item in buttontext">
                <button id="menubutton">
                    <p>{{ item }}</p>
                </button>
            </div>
        </div>
        `
})


new Vue({
    el: '#sidemenuelement',
    data: {
    },
    template :
        `<div id="sidemenucontainer">
            <p>SIIN ON FILTER</p>
        </div>
        `
})

new Vue({
    el: '#contentelement',
    data: {
    },
    template :
        `<div id="contentcontainer">
            <p>SIIN ON TOOTED</p>
            <a href="https://www.reddit.com/r/anime">weebs</a>
        </div>
        `
})
