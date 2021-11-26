<template>
  <data-card :title="author" :sub-title="title" :reserveButton="reservable" class="mb-2">
    <template v-slot:footer>
      <span class="mr-2" v-if="dateOfRelease">
        <i class="ni ni-calendar-grid-58"></i>
        {{ dateOfRelease }} </span
      ><br />
      <span class="mr-2" v-if="numberOfSongs">
        <i class="ni ni-collection"></i>
        {{ numberOfSongs }} songs </span
      ><br />
      <span class="mr-2" v-if="totalLength">
        <i class="ni ni-watch-time"></i>
        Length: {{ totalLength }}
      </span>
    </template>
  </data-card>
</template>

<script>
import DataCard from "./DataCard.vue";

export default {
  name: "music-album-card",
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
    numberOfSongs: String,
    totalLength: String,
  },
};
</script>
<style></style>
