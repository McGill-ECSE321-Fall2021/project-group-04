import axios from "axios";
import JQuery from "jquery";
import swal from "sweetalert";

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

import BaseAlert from "@/components/BaseAlert";
import BookCard from "@/components/BookCard";
import Books from "@/components/Books";
export default {
    components: {
        BaseAlert,
        BookCard,
        Books
    },
    name: "Book",
    data() {
        return {
            book: {
                title: "",
                author: "",
                isbn: "",
                dateOfRelease: "",
            },
            books: [],
            errorLogin: "",
            response: [],
        };
    },
    methods: {


    },
};
