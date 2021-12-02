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
    headers: {"Access-Control-Allow-Origin": frontendUrl},
});

import Books from "@/components/Books";
import BookItem from "../js/bookItem"
import swal from "sweetalert";
export default {
    name: "books-view",
    components: { Books },
    data() {
        return {
            book: {
                title: "",
                author: "",
                isbn: "",
                dateOfRelease: "",
                numberOfPages: "",
                barCode: "",
                price: ""
            },
            isMember: false,
            modal: false,
            books : BookItem.data().books,
        };

    },
    methods: {
        // @RequestParam String barCode, @RequestParam String title, @RequestParam String author,
        // @RequestParam String dateOfRelease, @RequestParam String price, @RequestParam String isbn, @RequestParam String numberOfPages
        addBook(title, price, barCode, isbn, numberOfPages, author, dateOfRelease){
            console.log(title + price + barCode + numberOfPages + author + dateOfRelease + isbn)
            AXIOS.post(
                "/create_book/",
                $.param({barCode: barCode, title: title, author: author, dateOfRelease: dateOfRelease, price: price, isbn: isbn, numberOfPages: numberOfPages})
            ).then(response => {
                console.log(response)
                console.log(response.status === 201);
                if (response.status === 201) {
                    swal("SUCCESS", response.data);
                }

            }).catch((e) => {
                swal("ERROR", e.response.data);
            })
        }
    }
};