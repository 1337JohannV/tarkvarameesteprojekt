<template>
  <div id="filterBar" class="container pt-3 pb-3 mt-3">
    <button
      id="sideButton"
      class="btn btn-primary btn-block"
      v-for="n in 11"
      :key="n"
      v-bind:style=" buttons[n-1].change ? 'background-color: #F0F3BD; color:black; outline:0; border:0;' : '{#menubuttonAlter}'"
      v-on:click.prevent="filterByCategory(n-1)"
    >{{buttons[n-1].name}}</button>
  </div>
</template>

<script>
export default {
  name: "FilterBar",
  data: function() {
    return {
      buttons: [
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
        }
      ]
    };
  },
  methods: {
    filterByCategory(catNr) {
      this.buttons.forEach(element => {
        element.change = false;
      });
      this.buttons[catNr].change = true;

      this.$emit("category", this.buttons[catNr].value);
    }
  },
  props: {
    noFilter: {
      type: Boolean
    }
  },
  watch: {
    noFilter: function() {
      if (this.noFilter) {
        this.buttons.forEach(element => {
          element.change = false;
        });
      }
    }
  }
};
</script>

<style>
#filterBar {
  background: #05668d;
  position: sticky;
  top: 5%;
  display: flex;
  flex-direction: column;
}

#sideButton {
  color: white;
  background-color: #02c39a;
  transition-duration: 0.3s;
  border: 0;
  outline: 0;
  box-shadow: none;
}

#sideButton:hover {
  background-color: #00a896;
}

#sideButton:active {
  background-color: #02c39a;
  transition-duration: 0.1s;
  
}
</style>
