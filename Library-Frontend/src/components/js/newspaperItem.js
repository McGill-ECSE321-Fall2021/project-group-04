import axios from "axios";
import JQuery from "jquery";

let $ = JQuery;
let config = require("../../../config");

let backend = function () {
  switch (process.env.NODE_ENV) {
    case "development":
      return "http://" + config.dev.backendHost //+ ":" + config.dev.backendPort;
    case "production":
      return (
        "https://" + config.build.backendHost //+ ":" + config.build.backendPort
      );
  }
};

let frontend = function () {
  switch (process.env.NODE_ENV) {
    case "development":
      return "http://" + config.dev.host //+ ":" + config.dev.port;
    case "production":
      return "https://" + config.build.host //+ ":" + config.build.port;
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
  components: {
    ImmovableItems,
  },
  name: "newspaper-view",
  data() {
    return {
      newspaper: {
        title: "",
        date: "",
        numberOfPages: "",
      },
      isMember: window.localStorage.getItem("userType") === "member",
      modal: false,
      newspapers: [],
      newspapersHardCoded: [
        {
          title: "War against Drugs",
          date: "07/10/70",
          numberOfPages: "11",
        },
        {
          title: "Twin Towers",
          date: "09/19/14",
          numberOfPages: "11",
        },
        {
          title: "Rise of Fear",
          date: "11/05/21",
          numberOfPages: "11",
        },
        {
          title: "Vietnam War",
          date: "04/15/11",
          numberOfPages: "11",
        },
      ],
      errorEvent: "",
      response: [],
    };
  },

  created: function () {
    this.getNewspapers();
  },

  methods: {
    getNewspapers() {
      AXIOS.get("/newspapers")
        .then((response) => {
          this.newspapers = response.data;
        })
        .catch((e) => {
          this.errorEvent = e;
        });
    },

    addNewspaper(date, numberOfPages, title) {
      console.log(title + numberOfPages + date);
      AXIOS.post(
        "/create_newspaper/",
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
};
