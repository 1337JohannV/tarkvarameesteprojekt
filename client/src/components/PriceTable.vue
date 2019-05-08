<template>
  <table class="table table-striped">
  <thead id="tablehead">
    <tr>
      <th id="tdhead" scope="col">Pood</th>
      <th id="tdhead" scope="col">Hind</th>
      <th id="tdhead" scope="col">Soodus hind</th>
      <th id="tdhead" scope="col">Ühikhind</th>
    </tr>
  </thead>
  <tbody>
    <tr v-for="(price, index) in prices" :key="index">
      <td> <a :href="price.url">{{price.store}}</a> </td>
      <td>{{price.regularPrice.amount}} {{price.regularPrice.currency}}</td>
      <td
        v-if="price.specialPrice != null"
      >{{price.specialPrice.amount}} {{price.specialPrice.currency}}</td>
      <td v-else>Kliendikaardi pakkumist pole</td>
      <td>{{price.unitPrice.amount}} {{price.unitPrice.currency}}/{{price.unitPrice.perUnit}}</td>
    </tr>
  </tbody>
</table>
</template>

<script>
export default {
  name: "PriceTable",
  props: ["productPrices"],
  data: function() {
    return {
      prices: this.productPrices
    };
  }
};
</script>

<style scoped>

#tablehead {
  background-color: #02c39a;
}

#tdhead {
  color: white;
}

</style>


