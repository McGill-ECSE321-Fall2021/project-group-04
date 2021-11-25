<template>
  <stats-card :title="author" :sub-title="title" class="mb-2">
    <template v-slot:footer>
      <span class="mr-2" v-if="dateOfRelease">
        <i class="ni ni-calendar-grid-58"></i>
        {{ dateOfRelease }} </span
      ><br />
      <span class="mr-2" v-if="length">
        <i class="ni ni-watch-time"></i>
        {{ length }} minutes
      </span>
    </template>
  </stats-card>
</template>

<script>
import StatsCard from "./StatsCard.vue";

export default {
  name: "movie-card",
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
    length: String,
  },
};
</script>
<style></style>