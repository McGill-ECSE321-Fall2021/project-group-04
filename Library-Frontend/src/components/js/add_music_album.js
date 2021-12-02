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

import MusicAlbums from "@/components/MusicAlbums";
import MusicAlbum from "../js/musicAlbumItem"
import swal from "sweetalert";
export default {
    name: "music-album-view",
    components: { MusicAlbums },
    data() {
        return {
            musicAlbum: {
                title: "",
                author: "",
                numberOfSongs: "",
                dateOfRelease: "",
                totalLength: "",
                barCode: "",
                price: ""
            },
            isMember: false,
            modal: false,
            musicAlbums : MusicAlbumItem.data().musicAlbums,
        };

    },
    methods: {
        // @RequestParam String barCode, @RequestParam String title, @RequestParam String author,
        // @RequestParam String dateOfRelease, @RequestParam String price, @RequestParam String isbn, @RequestParam String numberOfPages
        addMusicAlbum(title, price, barCode, numberOfSongs, numberOfPages, author, dateOfRelease, totalLength){
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
    }
};