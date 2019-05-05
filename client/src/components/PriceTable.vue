<template>
  <table>
    <tr>
      <th>Pood</th>
      <th>Hind</th>
      <th>Hind kliendikaardiga</th>
      <th>Ühikhind</th>
    </tr>
    <tr v-for="(price, index) in prices" :key="index">
      <td>
        <a :href="price.url">{{price.store}}</a>
      </td>
      <td>{{price.regularPrice.amount}} {{price.regularPrice.currency}}</td>
      <td
        v-if="price.specialPrice != null"
      >{{price.specialPrice.amount}} {{price.specialPrice.currency}}</td>
      <td v-else>Kliendikaari pakkumist pole</td>
      <td>{{price.unitPrice.amount}} {{price.unitPrice.currency}}/{{price.unitPrice.perUnit}}</td>
    </tr>
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

table,
td,
th {
  border: 1px solid #ddd;
  text-align: left;
}

table {
  border-collapse: collapse;
  width: 100%;
  margin-bottom: 2rem;
}

th,
td {
  padding: 15px;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}

th {
  background-color: #4caf50;
  color: white;
}
</style>


