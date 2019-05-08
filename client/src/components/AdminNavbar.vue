<template>
  <div>
    <b-navbar toggleable type="light" variant="faded" class="p-0 border-bottom">
      <div class="container">
        <b-nav-text>
          <b-button variant="link" class="text-dark" @click="exitAdminPage()">
            <font-awesome-icon icon="sign-out-alt"></font-awesome-icon>
          </b-button>
        </b-nav-text>
        <b-navbar-brand>
          <font-awesome-icon icon="eye"/>
        </b-navbar-brand>
      </div>
    </b-navbar>
  </div>
</template>

<script>
export default {
  name: "AdminNavbar",
  methods: {
    exitAdminPage() {
      this.$router.push({ name: "home" });
    }
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