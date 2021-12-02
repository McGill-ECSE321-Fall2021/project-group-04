<template>
  <data-card
    :title="author"
    :sub-title="title"
    :booking-id= "booking==undefined ? booking : booking.id"
    :button="reservable"
    :button2="checkoutable"
    :reserved-by="reservedBy"
    :type="'Book'"
    class="mb-2"
  >
    <template v-slot:footer>
      <span class="mr-2" v-if="dateOfRelease">
        <i class="ni ni-calendar-grid-58"></i>
        {{ dateOfRelease }} </span
      ><br />
      <span class="mr-2" v-if="numberOfPages">
        <i class="ni ni-book-bookmark"></i>
        {{ numberOfPages }} pages </span
      ><br />
      <span class="mr-2" v-if="isbn">
        <i class="ni ni-badge"></i>
        ISBN: {{ isbn }}
      </span>
      <br />
      <span class="mr-2" v-if="!isMember">
        <i class="ni ni-badge"></i>
        Reservee: {{ reservedBy }}
      </span>
    </template>
    <template v-slot:modal>
      You have successfully reserved {{ title }}
    </template>
  </data-card>
</template>

<script>
import DataCard from "./DataCard.vue";

export default {
  name: "book-card",
  components: {
    DataCard,
  },
  data(){
    return{
      isMember: window.localStorage.getItem('userType') === 'member',
    }
  },
  props: {
    type: {
      type: String,
      default: "primary",
    },
    reservable: {
      type: Boolean,
      default: true,
    },
    checkoutable: {
      type: Boolean,
      default: false,
    },
    title: String,
    author: String,
    dateOfRelease: String,
    isbn: String,
    numberOfPages: String,
    booking: String,
    reservedBy: String,
    bookingType: String,

  },
};
</script>
<style></style>
