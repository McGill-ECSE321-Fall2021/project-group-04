<template>
  <div class="row" v-if="books">
    <div class="col-lg-4" v-for="book in books" :key="book.title">
      <book-card
          :title="book.title"
          :author="book.author"
          :dateOfRelease="book.dateOfRelease"
          :isbn="book.isbn"
          :numberOfPages="book.numberOfPages"
          :reservedBy= "book.booking==null ? undefined : book.booking.user.username"
          :booking= book.booking
        :reservable="reservable"
        :checkoutable="checkoutable"
      >
      </book-card>
    </div>
  </div>
</template>

<script>
import BookCard from "@/components/BookCard";



export default {
  name: "books",
  components: { BookCard },
  props: {
    books: Array,
    reservable: {
      type: Boolean,
      default: true,
    },
    checkoutable: {
      type: Boolean,
      default: false,
    },
  },
};
</script>
