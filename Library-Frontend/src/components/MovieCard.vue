<template>
  <data-card
    :title="author" :sub-title="title" :button="reservable" class="mb-2"
  >
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
    <template v-slot:modal>
      You have successfully reserved {{title}}
    </template>
  </data-card>
</template>

<script>
import DataCard from "./DataCard.vue";

export default {
  name: "movie-card",
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
    length: String,
  },
};
</script>
<style></style>
