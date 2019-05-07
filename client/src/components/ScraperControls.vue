<template>
  <div class="container">
    <div>
      <p class="h1">Värskenda andmeid</p>
      <div class="p-3">
        <p
          class="lead d-inline-block mr-3"
          :key="scraperStatus"
        >{{formatScraperStatus(scraperStatus)}}</p>
        <b-spinner
          v-if="scraperStatus == 'Scraper is running'"
          variant="danger"
          small
          class="mb-1"
        ></b-spinner>
        <b-button
          v-if="scraperStatus == 'Scraper is not running'"
          variant="outline-success"
          class="mb-1"
          @click="startScraper()"
        >
          <p class="small d-inline-block m-0">
            <b-spinner small type="grow" class="mr-2"></b-spinner>Käivita uuendus
          </p>
        </b-button>
      </div>
    </div>
    <div>
      <p class="h3">Rapordid</p>
      <b-table
        show-empty
        stacked="md"
        fixed
        striped
        borderless
        :items="scraperReports"
        :fields="fields"
        :current-page="currentPage"
        :per-page="perPage"
      >
        <template slot="startDate" slot-scope="row">{{row.item.startDate}} : {{row.item.startTime}}</template>
        <template slot="endDate" slot-scope="row">{{row.item.endDate}} : {{row.item.endTime}}</template>
        <template slot="exceptions" slot-scope="row">{{row.item.exceptions.length}}</template>
        <template slot="actions" slot-scope="row">
          <b-button
            size="sm"
            variant="link"
            block
            @click="row.toggleDetails"
          >{{ !row.detailsShowing ? "Vaata veateateid" : "Peida veateated"}}</b-button>
        </template>

        <template slot="row-details" slot-scope="row">
          <b-table
            show-empty
            stacked="md"
            fixed
            small
            dark
            hover
            borderless
            :fields="exceptionFields"
            :items="row.item.exceptions"
          >
            <template
              slot="causeReport"
              slot-scope="row"
            >{{row.item.causeReport.fileName}} {{row.item.causeReport.className}} {{row.item.causeReport.methodName}} {{row.item.causeReport.lineNumber}}</template>
          </b-table>
        </template>
      </b-table>
      <b-pagination
        align="center"
        size="sm"
        v-model="currentPage"
        :total-rows="totalRows"
        :per-page="perPage"
        class="my-0"
      ></b-pagination>
    </div>
  </div>
</template>

<script>
import { setInterval, clearInterval } from "timers";

export default {
  name: "ScraperControls",
  mounted() {
    this.totalRows = this.scraperReports.length;
    this.fetchReports();
    this.fetchScraperStatus();
    this.timer = setInterval(() => {
      this.fetchScraperStatus();
    }, 2500);
  },
  methods: {
    fetchReports: function() {
      this.$http({
        method: "get",
        url: this.$serverBaseUrl + "/scraper/report/all",
        withCredentials: true,
        auth: this.auth
      })
        .then(response => (this.scraperReports = response.data))
        .catch(error => console.log(error.response));
    },
    fetchScraperStatus: function() {
      this.$http({
        method: "get",
        url: this.$serverBaseUrl + "/scraper/status",
        withCredentials: true,
        auth: this.auth
      })
        .then(response => (this.scraperStatus = response.data))
        .catch(error => console.log(error.response));
    },
    startScraper: function() {
      this.$http({
        method: "get",
        url: this.$serverBaseUrl + "/scraper/update/start",
        withCredentials: true,
        auth: this.auth
      })
        .then(response => (this.scraperStartMessage = response.data))
        .catch(error => console.log(error.response));
      this.fetchScraperStatus();
    },
    stopScraper: function() {
      this.$http({
        method: "get",
        url: this.$serverBaseUrl + "/scraper/update/stop",
        withCredentials: true,
        auth: this.auth
      })
        .then(response => (this.scraperStopMessage = response.data))
        .catch(error => console.log(error.response));
      this.fetchScraperStatus();
    },
    formatScraperStatus: function(status) {
      if (status == "Scraper is not running") {
        return "Uuendus on ootel";
      } else {
        return "Uuendus käib";
      }
    }
  },
  data: function() {
    return {
      totalRows: 1,
      currentPage: 1,
      perPage: 5,
      sortBy: null,
      sortDesc: false,
      sortDirection: "asc",
      scraperStatus: "",
      scraperStartMessage: "",
      timer: "",
      fields: [
        { key: "id", label: "ID" },
        { key: "startDate", label: "Alguse aeg" },
        { key: "endDate", label: "lõpu aeg" },
        { key: "exceptions", label: "Veateated" },
        { key: "actions", label: "Tegevused" }
      ],
      exceptionFields: [
        { key: "time", label: "Aeg" },
        { key: "scraper", label: "Scraper" },
        { key: "url", label: "Url" },
        { key: "message", label: "Sõnum" },
        { key: "causeReport", label: "Vea tekkimise koht" }
      ],
      auth: {
        username: "admin",
        password: "admin"
      },
      scraperReports: [
        {
          id: Number,
          startDate: String,
          startTime: String,
          endDate: String,
          endTime: String,
          exceptions: [
            {
              time: String,
              scraper: String,
              url: String,
              message: String,
              causeReport: {
                fileName: String,
                className: String,
                methodName: String,
                lineNumber: Number
              }
            }
          ]
        }
      ]
    };
  }
};
</script>

<style scoped>
</style>

