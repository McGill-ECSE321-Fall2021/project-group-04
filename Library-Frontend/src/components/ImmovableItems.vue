<template>
  <div class="row" v-if="immovableItems">
    <div class="col-lg-4" v-for="item in immovableItems" :key="item.date">
      <immovable-item-card
        :title="item.title"
        :date="item.date"
        :numberOfPages="item.numberOfPages"
      >
      </immovable-item-card>
    </div>
  </div>
</template>

<script>
import ImmovableItemCard from "@/components/ImmovableItemCard";
export default {
  name: "immovable-items",
  components: { ImmovableItemCard },
  props: {
    immovableItems: Array,
  },
};
</script>
