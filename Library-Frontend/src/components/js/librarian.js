import axios from "axios";
//import JQuery from "jquery";

//let $ = JQuery;
let config = require("../../../config");

let backend = function () {
    switch (process.env.NODE_ENV) {
        case "development":
            return "http://" + config.dev.backendHost + ":" + config.dev.backendPort;
        case "production":
            return (
                "https://" + config.build.backendHost //+ ":" + config.build.backendPort
            );
    }
};

let frontend = function () {
    switch (process.env.NODE_ENV) {
        case "development":
            return "http://" + config.dev.host + ":" + config.dev.port;
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

import Card from "@/components/Card.vue";
import Books from "@/components/Books";
import Movies from "@/components/Movies";
import MusicAlbums from "@/components/MusicAlbums";
import Librarians from "@/components/Librarians";

export default {
    components: { MusicAlbums, Movies, Books, Card, Librarians },
    name: "librarian_scheduler",
    data() {
        return {
            isMember: window.localStorage.getItem("userType") === "member",
            model: {
                username: window.localStorage.getObject("user").username,
                address: window.localStorage.getObject("user").address,

            },
            user: "",
            librarians: [],
            librarianExamples: [{
                username: "Aly",
                workHours: [
                    {
                        day: "Monday",
                        start: "9:00",
                        end: "5:00",
                    },
                    {
                        day: "Tuesday",
                        start: "9:00",
                        end: "5:00",
                    },
                    {
                        day: "Wednesday",
                        start: "9:00",
                        end: "5:00",
                    },
                    {
                        day: "Thursday",
                        start: "9:00",
                        end: "5:00",
                    },
                    {
                        day: "Friday",
                        start: "9:00",
                        end: "5:00",
                    },
                    {
                        day: "Saturday",
                        start: "9:00",
                        end: "5:00",
                    },
                    {
                        day: "Sunday",
                        start: "",
                        end: "",
                    },
                ],
            },],
        };
    },

    created: function () {
        this.getLibrarians()
    },

    methods: {
        getLibrarians() {
            AXIOS.get("/librarians/")
                .then((response) => {
                    this.librarians = response.data;
                })
                .catch((e) => {
                    this.errorEvent = e;
                });
        },
    },
};