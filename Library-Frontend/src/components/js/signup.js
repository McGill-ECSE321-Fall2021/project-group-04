import axios from "axios";
import JQuery from "jquery";
import swal from "sweetalert";

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

import BaseAlert from "@/components/BaseAlert";

export default {
  components: {
    BaseAlert,
  },
  name: "sign_up",
  data() {
    return {
      user: {
        type: "",
        username: "",
        password: "",
        address: "",
      },
      errorSignup: "",
      response: [],
    };
  },
  methods: {
    print_info(username) {
      console.log(username);
      window.location.href = "/#/login";
    },
    /**
     * @author alymo
     * @param username
     * @param password
     * gets user input from frontend and logs in using controller method login
     */
    sign_up(username, password, address) {
      console.log("signing up");
      console.log(username);
      console.log(password);
      console.log(address);

      var input =
        "/member_sign_up?" +
        $.param({
          username: username,
          password: password,
          address: address,
          member_type: "Local",
          member_status: "Active",
        });
      console.log(input);
      AXIOS.post(input)
        .then((response) => {
          console.log(response);
          console.log(response.status === 201);
          if (response.status === 201) {
            this.user.type = "member";
            this.user.username = username;
            this.user.password = password;

            console.log("type:", this.user.type);

            // swal("SUCCESS", response.data);
            window.localStorage.setItem("userType", this.user.type);
            window.localStorage.setObject("user", response.data);
            window.localStorage.setItem("username", this.user.username);
            window.location.href = "/#/dashboard";
            location.reload();
          }
        })
        .catch((e) => {
          console.error(e);
          swal("ERROR", e.response.data);
        });
    },
  },
};
