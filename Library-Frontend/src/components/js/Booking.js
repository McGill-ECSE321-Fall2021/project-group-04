import axios from 'axios'
import JQuery from 'jquery'

let $ = JQuery
var config = require('../../../config')

var backendConfigurer = function () {
    switch (process.env.NODE_ENV) {
        case 'development':
            return 'http://' + config.dev.backendHost + ':' + config.dev.backendPort;
        case 'production':
            return 'https://' + config.build.backendHost + ':' + config.build.backendPort;
    }
};

var frontendConfigurer = function () {
    switch (process.env.NODE_ENV) {
        case 'development':
            return 'http://' + config.dev.host + ':' + config.dev.port;
        case 'production':
            return 'https://' + config.build.host + ':' + config.build.port;
    }
};

var backendUrl = backendConfigurer();
var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function BookingDto(aBookingId, aBookingDate, aUser, aBookingType){
this.id = aBookingId
this.bookingDate = aBookingDate
this.user = aUser
this.bookingType = aBookingType
}


export default {
    name: "booking",
    data() {
        return {
            user: '',
            bookingType: '',
            bookingDate: '',
            newBooking: '',
            errorBooking: '',
            bookings: [],
            response: []
        }
    },


created: function () {
    // Initializing user
    // See: was done above

    // Initializing bookings
    AXIOS.get('/bookings').then(response => {this.bookings = response.data}).catch(e => {this.errorEvent = e});
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
AXIOS.post('/return/itemType'.concat(itemType).concat('/itemId/').concat(itemId), {}, {})
        .then(response => {
        // JSON responses are automatically parsed.
        //should removeItem from reserved list
        })
        .catch(e => {
          var errorMsg = e.response.data.message
          console.log(errorMsg)
          this.errorBooking = errorMsg
        })
},

checkoutItem(bookingId){
AXIOS.post('/checkout/username'.concat(localStorage.getItem('username')).concat('/bookingId/').concat(bookingId), {}, {})
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
}

}