import axios from "axios";
import JQuery from "jquery";

let $ = JQuery;
let config = require("../../../config");

let backend = function () {
  switch (process.env.NODE_ENV) {
    case "development":
      return "http://" + config.dev.backendHost //+ ":" + config.dev.backendPort;
    case "production":
      return (
        "https://" + config.build.backendHost //+ ":" + config.build.backendPort
      );
  }
};

let frontend = function () {
  switch (process.env.NODE_ENV) {
    case "development":
      return "http://" + config.dev.host //+ ":" + config.dev.port;
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

import BaseAlert from "@/components/BaseAlert";
import BookCard from "@/components/BookCard";
import Books from "@/components/Books";
import swal from "sweetalert";

export default {
  components: {
    BaseAlert,
    BookCard,
    Books,
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
        price: "",
        barcode: "",
      },
      isMember: window.localStorage.getItem("userType") === "member",
      modal: false,
      books: [],
      isLoaded: false,
      errorLogin: "",
      response: [],
    };
  },

  created: function () {
    this.getBooks();
  },

  methods: {
    getBooks() {
      console.log("Getting all Books");

      AXIOS.get("/books")
        .then((response) => {
          this.books = response.data;
        })
        .catch((e) => {
          this.errorEvent = e;
        });

      return this.books;
    },
    addBook(title, price, barCode, isbn, numberOfPages, author, dateOfRelease) {
      console.log(
        title + price + barCode + numberOfPages + author + dateOfRelease + isbn
      );
      AXIOS.post(
        "/create_book/",
        $.param({
          barCode: barCode,
          title: title,
          author: author,
          dateOfRelease: dateOfRelease,
          price: price,
          isbn: isbn,
          numberOfPages: numberOfPages,
        })
      )
        .then((response) => {
          console.log(response);
          console.log(response.status === 201);
          // if (response.status === 201) {
          //     swal("SUCCESS", response.data);
          // }
        })
        .catch((e) => {
          swal("ERROR", e.response.data);
        });
    },
  },
  beforeMount() {
    this.getBooks();
  },
};
