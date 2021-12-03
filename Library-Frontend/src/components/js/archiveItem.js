import axios from "axios";
import JQuery from "jquery";

let $ = JQuery;
let config = require("../../../config");

let backend = function () {
  switch (process.env.NODE_ENV) {
    case "development":
      return "http://" + config.dev.backendHost + ":" + config.dev.backendPort;
    case "production":
      return (
        "https://" + config.build.backendHost + ":" + config.build.backendPort
      );
  }
};

let frontend = function () {
  switch (process.env.NODE_ENV) {
    case "development":
      return "http://" + config.dev.host + ":" + config.dev.port;
    case "production":
      return "https://" + config.build.host + ":" + config.build.port;
  }
};
let backendUrl = backend();
let frontendUrl = frontend();

let AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { "Access-Control-Allow-Origin": frontendUrl },
});

import ImmovableItems from "@/components/ImmovableItems";
import swal from "sweetalert";

export default {
  name: "archive-view",
  components: { ImmovableItems },
  data() {
    return {
      isMember: window.localStorage.getItem("userType") === "member",
      modal: false,
      errorEvent: "",
      archives: [],
      archive: {
        title: "",
        date: "",
        numberOfPages: "",
      },
    };
  },

  created: function () {
    this.getArchives();
  },

  methods: {
    getArchives() {
      AXIOS.get("/archives")
        .then((response) => {
          this.archives = response.data;
        })
        .catch((e) => {
          this.errorEvent = e;
        });
    },

    addNewspaper(date, numberOfPages, title) {
      console.log(title + numberOfPages + date);
      AXIOS.post(
        "/create_archive/",
        $.param({ date: date, numofPages: numberOfPages, title: title })
      )
        .then((response) => {
          console.log(response);
          console.log(response.status === 201);
          // if (response.status === 201) {
          //     swal("SUCCESS", response.data);
          // }
        })
        .catch((e) => {
          swal("ERROR", e.response.data);
        });
    },
  },
  beforeMount() {
    this.getArchives();
  },
};
