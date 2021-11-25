<template>
  <stats-card :title="author" :sub-title="title" class="mb-2">
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
  </stats-card>
</template>

<script>
import StatsCard from "./StatsCard.vue";

export default {
  name: "book-card",
  components: {
    StatsCard,
  },
  props: {
    type: {
      type: String,
      default: "primary",
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
