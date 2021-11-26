<template>
  <data-card :title="author" :sub-title="title" :reserveButton="reservable" class="mb-2">
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
  props: {
    type: {
      type: String,
      default: "primary",
    },
    reservable: {
      type: Boolean,
      default: true,
    },
    title: String,
    author: String,
    dateOfRelease: String,
    isbn: String,
    numberOfPages: String,
  },
};
</script>
<style></style>
