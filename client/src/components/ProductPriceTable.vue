<template>
  <div>
    <b-table striped hover small stacked="md" :items="productPrices" :fields="fields">
      <template slot="store" slot-scope="row">
        <a :href="row.item.url">{{row.item.store}}</a>
      </template>
      <template
        slot="regularPrice"
        slot-scope="row"
      >{{row.item.regularPrice.amount}} {{row.item.regularPrice.currency}}</template>
      <template slot="specialPrice" slot-scope="row">
        <div
          v-if="row.item.specialPrice != null"
        >{{row.item.specialPrice.amount}} {{row.item.specialPrice.currency}}</div>
        <div v-else>Kliendikaari pakkumine puudub</div>
      </template>
      <template
        slot="unitPrice"
        slot-scope="row"
      >{{row.item.unitPrice.amount}} {{row.item.unitPrice.currency}}/{{row.item.unitPrice.perUnit}}</template>
    </b-table>
  </div>
</template>

<script>
export default {
  name: "ProductPriceTable",
  props: {
    productPrices: {
      type: Array
    }
  },
  data: function() {
    return {
      fields: [
        { key: "store", label: "Pood" },
        { key: "regularPrice", label: "Hind" },
        { key: "specialPrice", label: "Hind kliendikaardiga" },
        { key: "unitPrice", label: "Ühikhind" }
      ]
    };
  }
};
</script>
