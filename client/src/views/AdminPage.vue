<template>
  <div class="container-fluid p-0">
    <AdminNavbar/>
    <div class="container p-3">
      <b-tabs content-class="mt-4" justified>
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
import { setInterval, clearInterval } from "timers";
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

