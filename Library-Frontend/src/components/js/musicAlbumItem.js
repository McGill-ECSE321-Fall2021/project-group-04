import axios from "axios";
import JQuery from "jquery";
//import swal from "sweetalert";

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
import swal from "sweetalert";
export default {
    components: {
        BaseAlert,
        BookCard,
        Books
    },
    name: "MusicAlbumItem",
    data() {
        return {
            musicAlbum: {
                title: "",
                author: "",
                dateOfRelease: "",

            },
            isMember: false,
            musicAlbums: [],
            errorLogin: "",
            response: [],
            modal: false,
            albums: [
                {
                    title: "Kamikaze",
                    author: "Eminem",
                    dateOfRelease: "08/31/18",
                    numberOfSongs: "13",
                    totalLength: "45:49",
                },
                {
                    title: "Music to Be Murdered By",
                    author: "Eminem",
                    dateOfRelease: "01/17/20",
                    numberOfSongs: "13",
                    totalLength: "62:23",
                },
                {
                    title: "Revival",
                    author: "Eminem",
                    dateOfRelease: "12/15/17",
                    numberOfSongs: "18",
                    totalLength: "77:39",
                },
                {
                    title: "+ (Plus) Deluxe",
                    author: "Ed Sheeran",
                    dateOfRelease: "09/09/11",
                    numberOfSongs: "12",
                    totalLength: "65:35",
                },
            ],
        };
    },

    created: function () {
        // Initializing user
        // See: was done above

        // Initializing books
        AXIOS.get('/musicAlbums').then(response => {this.albums = response.data}).catch(e => {this.errorEvent = e});
    },

    methods: {
        add_music_album(title, price, barCode, numberOfSongs, numberOfPages, author, dateOfRelease, totalLength){
            console.log(title + price + barCode + numberOfPages + author + dateOfRelease )
            AXIOS.post(
                "/create_music_album/",
                $.param({barCode: barCode, title: title, author: author, dateOfRelease: dateOfRelease, price: price, numberOfSongs: numberOfSongs, totalLength: totalLength})
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

    },

};
