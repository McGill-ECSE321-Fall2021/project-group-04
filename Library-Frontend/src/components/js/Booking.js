import axios from "axios";
//import JQuery from "jquery";
//import swal from "sweetalert"

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
    headers: {"Access-Control-Allow-Origin": frontendUrl},
});



import Card from "@/components/Card.vue";
import Books from "@/components/Books";
import Movies from "@/components/Movies";
import MusicAlbums from "@/components/MusicAlbums";

export default {
    components: { MusicAlbums, Movies, Books, Card },
    name: "user-profile",
    data() {
        return {
            model: {
                isMember: window.localStorage.getItem('userType') === 'member',
                username: window.localStorage.getObject("user").username,
                address: window.localStorage.getObject("user").address,
                startDate: window.localStorage.getObject("user").startDate,
                monthlyFee: window.localStorage.getObject("user").monthlyFee,
            },
            user: '',
            bookingType: '',
            bookingDate: '',
            newBooking: '',
            errorBooking: '',
            bookings: [],
            reservations: [],
            movies: [{
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
                },],
            albums: [],
            lendings: [],
            response: []
        }
    },


created: function () {
    // Initializing user
    // See: was done above

    // Initializing bookings
    AXIOS.get('/bookings').then(response => {this.bookings = response.data}).catch(e => {this.errorEvent = e});

    AXIOS.get('/member_bookReservations/'.concat(window.localStorage.getItem('username'))).then(response => {this.reservations = response.data}).catch(e => {this.errorEvent = e});

    AXIOS.get('/member_bookLendings/'.concat(window.localStorage.getItem('username'))).then(response => {this.lendings = response.data}).catch(e => {this.errorEvent = e});
  },


methods: {

createBooking: function (itemType, itemId){
 AXIOS.post('/booking/'.concat(localStorage.getItem('username')).concat('/itemType/').concat(itemType).concat('/itemId/').concat(itemId), {}, {})
        .then(response => {
        // JSON responses are automatically parsed.
          this.bookings.push(response.data)
          this.errorBooking = ''
          this.newBooking = response.data
          this.user = localStorage.getItem('username')

        })
        .catch(e => {
          var errorMsg = e.response.data.message
          console.log(errorMsg)
          this.errorBooking = errorMsg

        })

},

returnItem(itemType, itemId){
AXIOS.post('/return/itemType/'.concat(itemType).concat('/itemId/').concat(itemId), {}, {})
        .then(response => {
        // JSON responses are automatically parsed.
        //should removeItem from reserved list
            this.response = response.data
        })
        .catch(e => {
          var errorMsg = e.response.data.message
          console.log(errorMsg)
          this.errorBooking = errorMsg
        })
},

checkoutItem(bookingId){
AXIOS.post('/checkout/username/'.concat(localStorage.getItem('username')).concat('/bookingId/').concat(bookingId), {}, {})
        .then(response => {
        // JSON responses are automatically parsed.
        //alters booking type in the booking
        this.errorBooking = ''
        this.newBooking = response.data
        this.user = localStorage.getItem('username')
        })
        .catch(e => {
          var errorMsg = e.response.data.message
          console.log(errorMsg)
          this.errorBooking = errorMsg
        })
},

getReservations(username){
    AXIOS.get('/member_reservations/'.concat(username)).then(response => {this.reservations = response.data}).catch(e => {this.errorEvent = e});
}

}}