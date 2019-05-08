<template>
  <div>
    <AdminNavbar/>
    <div class="container p-0 mt-1">
      <b-tabs content-class="p-1" justified>
        <b-tab title="Uuendus" active>
          <ScraperControls/>
        </b-tab>
        <b-tab title="Tooted">
          <AdminProductView/>
        </b-tab>
      </b-tabs>
    </div>
  </div>
</template>

<script>
import AdminNavbar from "@/components/AdminNavbar.vue";
import ScraperControls from "@/components/ScraperControls.vue";
import AdminProductView from "@/components/AdminProductView.vue"

export default {
  name: "AdminPage",
  components: {
    AdminNavbar,
    ScraperControls,
    AdminProductView
  }
};
</script>

<style scoped>
@media (max-width: 1200px) {
  .container {
    width: 100%;
    max-width: none;
  }
}

</style>

