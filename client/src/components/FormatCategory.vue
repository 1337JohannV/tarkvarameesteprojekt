<template>
  <span class="m-0 p-0">{{formatCategory(this.category)}}</span>
</template>

<script>
export default {
  name: "FormatCategory",
  props: {
    category: String
  },
  methods: {
    formatCategory: function(category) {
        console.log(category)
      switch (category) {
        case "PUU_JA_KOOGIVILJAD":
          return "Puu ja köögiviljad";
        case "LIHA_JA_KALA":
          return "Liha ja kala";
        case "PIIMATOOTED_MUNAD_VOID":
          return "Piimatooted, munad, võid";
        case "JUUSTUD":
          return "Juustud";
        case "LEIVAD_SAIAD_KONDIITRITOOTED":
          return "Leivad, saiad, kondiitritooted";
        case "KUIVAINED_HOIDISED":
          return "Kuivained ja hoidised";
        case "KASTMED_OLID":
          return "Kastmed ja õlid";
        case "MAIUSTUSED_KUPSISED_NAKSID":
          return "Maiustused, küpsised, näksid";
        case "KULMUTATUD_TOIDUKAUBAD":
          return "Külmutatud toidukaubad";
        case "JOOGID":
          return "Joogid";
        default:
          return "";
      }
    }
  }
};
</script>

