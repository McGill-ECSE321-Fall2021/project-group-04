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
import MusicAlbums from "@/components/MusicAlbums";
import swal from "sweetalert";

export default {
  components: {
    BaseAlert,
    MusicAlbums,
  },
  name: "music-album-view",
  data() {
    return {
      musicAlbum: {
        title: "",
        author: "",
        dateOfRelease: "",
        price: "",
        numberOfSongs: "",
        totalLength: "",
        barcode: "",
      },
      isMember: window.localStorage.getItem("userType") === "member",
      musicAlbums: [],
      errorLogin: "",
      response: [],
      modal: false,
      albums: [],
      albumsHardCoded: [
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
    this.getAlbums();
  },

  methods: {
    getAlbums() {
      AXIOS.get("/musicAlbums")
        .then((response) => {
          this.albums = response.data;
          console.log(response.data);
        })
        .catch((e) => {
          this.errorEvent = e;
        });
    },

    add_music_album(
      title,
      price,
      barCode,
      numberOfSongs,
      author,
      dateOfRelease,
      totalLength
    ) {
      console.log(title + price + barCode + author + dateOfRelease);
      AXIOS.post(
        "/create_music_album/",
        $.param({
          barCode: barCode,
          title: title,
          author: author,
          dateOfRelease: dateOfRelease,
          price: price,
          numberOfSongs: numberOfSongs,
          totalLength: totalLength,
        })
      )
        .then((response) => {
          console.log(response);
          console.log(response.status === 201);
          // if (response.status === 201) {
          //     swal("Success", response.data);
          // }
        })
        .catch((e) => {
          swal("Error", e.response.data);
        });
    },
  },
  beforeMount() {
    this.getAlbums();
  },
};
