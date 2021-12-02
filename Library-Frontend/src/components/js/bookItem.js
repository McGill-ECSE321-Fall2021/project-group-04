import axios from "axios";
//import JQuery from "jquery";
//import swal from "sweetalert";

//let $ = JQuery;
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
    name: "books-view",
    data() {
        return {
            book: {
                title: "",
                author: "",
                isbn: "",
                dateOfRelease: "",
                numberOfPages: "",
            },
            isMember: false,
            modal: false,
            books: [],
            isLoaded: false,
            errorLogin: "",
            response: [],
        };
    },

    created: function () {
        // Initializing user
        // See: was done above

        // Initializing books
        AXIOS.get('/books').then(response => {this.books = response.data}).catch(e => {this.errorEvent = e});
    },

    methods: {


        getBooks(){

            console.log("Getting all Books")

            AXIOS.get('/books').then(response => {
                this.books = response.data

            }).catch(e => {this.errorEvent = e});

            this.isLoaded = true;
            console.log(this.isLoaded)

            return this.books
        }

    },
};
