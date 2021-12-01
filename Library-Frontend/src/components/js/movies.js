import axios from "axios";
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
export default {
    name: "movies-view",
    components: { Movies },
    data() {
        return {
            isMember: false,
            modal: false,
            movies: [
                {
                    title: "Charlie and the Chocolate Factory",
                    author: "Tim Burton",
                    dateOfRelease: "07/10/05",
                    length: "115",
                },
                {
                    title: "The Maze Runner",
                    author: "Wes Ball",
                    dateOfRelease: "09/19/14",
                    length: "113",
                },
                {
                    title: "Red Notice",
                    author: "Rawson Marshall Thurber",
                    dateOfRelease: "11/05/21",
                    length: "118",
                },
                {
                    title: "Fast Five",
                    author: "Justin Lin",
                    dateOfRelease: "04/15/11",
                    length: "130",
                },
            ],
        };
    },

    methods:{
        getMovies() {
            AXIOS.post("/movies").then(response => {
                this.movies = response.data;
            })
        }
    },
    beforeMount(){
        this.getMovies()
    },
};