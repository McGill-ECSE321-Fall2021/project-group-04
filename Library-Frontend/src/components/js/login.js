import axios from "axios";
import JQuery from "jquery";
import swal from "sweetalert"

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
    headers: {"Access-Control-Allow-Origin": frontendUrl},
});

import BaseAlert from "@/components/BaseAlert";
export default {
    components: {
        BaseAlert
    },
    name: "login",
    data() {
        return {
            user: {
                type: "",
                username: "",
                password: "",
            },
            errorLogin: "",
            response: [],
        };
    },
    methods: {
        /**
         * @author alymo
         * @param username
         * @param password
         * gets user input from frontend and logs in using controller method login
         */
        login(username, password) {
            console.log("Logging in")

            let userTypes = ['member', 'librarian', 'head_librarian']

            AXIOS.post(
                "/" + userTypes[0] + "_login/",
                $.param({username: username, password: password})
            ).then(response => {
                console.log(response.status === 200);
                if (response.status === 200) {
                    this.user.type = userTypes[0];
                    this.user.username = username;
                    this.user.password = password;

                    console.log("type:", this.user.type);

                    window.localStorage.setItem("username", this.user.username);
                    window.location.href = "/#/dashboard";
                    location.reload();
                }
            }).catch((e) => {
                console.error(e)

                console.log("login lib")
                AXIOS.post(
                    "/" + userTypes[1] + "_login/",
                    $.param({username: username, password: password})
                ).then(response => {
                    console.log(response.status === 200);
                    if (response.status === 200) {
                        this.user.type = userTypes[1];
                        this.user.username = username;
                        this.user.password = password;

                        console.log("type:", this.user.type);

                        window.localStorage.setItem("username", this.user.username);
                        window.location.href = "/#/dashboard";
                        location.reload();
                    }
                }).catch((e) => {
                    console.error(e)
                    console.log("login head lib")
                    AXIOS.post(
                        "/" + userTypes[2] + "_login/",
                        $.param({username: username, password: password})
                    ).then(response => {
                        console.log(response.status === 200);
                        if (response.status === 200) {
                            this.user.type = userTypes[2];
                            this.user.username = username;
                            this.user.password = password;

                            console.log("type:", this.user.type);

                            window.localStorage.setItem("username", this.user.username);
                            window.location.href = "/#/dashboard";
                            location.reload();
                        }
                    }).catch((e) => {
                        console.error(e)
                        swal("ERROR", e.response.data, "error");
                    })
                })
            })

        },
    },
};
