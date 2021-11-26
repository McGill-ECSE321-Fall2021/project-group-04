<template>
  <data-card :title="date" :sub-title="title" :reserveButton="false" class="mb-2">
    <template v-slot:footer>
      <span class="mr-2" v-if="numberOfPages">
        <i class="ni ni-book-bookmark"></i>
        {{ numberOfPages }} pages</span
      ><br />
    </template>
  </data-card>
</template>

<script>
import DataCard from "./DataCard.vue";

export default {
  name: "immovable-item-card",
  components: {
    DataCard,
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
