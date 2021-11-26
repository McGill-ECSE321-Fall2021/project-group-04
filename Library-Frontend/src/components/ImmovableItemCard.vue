<template>
  <stats-card :title="date" :sub-title="title" class="mb-2">
    <template v-slot:footer>
      <span class="mr-2" v-if="numberOfPages">
        <i class="ni ni-book-bookmark"></i>
        {{ numberOfPages }} pages</span
      ><br />
    </template>
  </stats-card>
</template>

<script>
import StatsCard from "./StatsCard.vue";

export default {
  name: "immovable-item-card",
  components: {
    StatsCard,
  },
  props: {
    type: {
      type: String,
      default: "primary",
    },
    title: String,
    date: String,
    numberOfPages: String,
  },
};
</script>
<style></style>