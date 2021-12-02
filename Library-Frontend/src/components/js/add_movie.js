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

import Movies from "@/components/Movies";
import MovieItem from "../js/movieItem"
import swal from "sweetalert";
export default {
    name: "movies-view",
    components: { Movies },
    //String barCode, String title, String author,
    //String dateOfRelease, String price, String length
    data() {
        return {
            movie: {
                title: "",
                author: "",
                length: "",
                dateOfRelease: "",
                barCode: "",
                price: ""
            },
            isMember: false,
            modal: false,
            movies : MovieItem.data().movies,
        };

    },
    methods: {

        addMovie(title, price, barCode, length, author, dateOfRelease){
            console.log(title + price + barCode + length + author + dateOfRelease)
            AXIOS.post(
                "/create_movie/",
                $.param({barCode: barCode, title: title, author: author, dateOfRelease: dateOfRelease, price: price, length: length})
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