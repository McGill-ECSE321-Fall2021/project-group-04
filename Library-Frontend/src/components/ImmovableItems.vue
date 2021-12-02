<template>
  <div class="row" v-if="immovableItems">
    <div class="col-lg-4" v-for="item in immovableItems" :key="item.date">
      <immovable-item-card
        :title="item.title"
        :date="item.date"
        :numberOfPages="item.numberOfPages"
      >
      </immovable-item-card>
      <br />
    </div>
  </div>
  <div v-if="empty && showAlert">
    <base-alert type="danger">
      <strong>Nothing to see here.</strong>
    </base-alert>
  </div>
</template>

<script>
import ImmovableItemCard from "@/components/ImmovableItemCard";
import BaseAlert from "@/components/BaseAlert";
export default {
  name: "immovable-items",
  components: { ImmovableItemCard, BaseAlert },
  props: {
    immovableItems: Array,
    empty: {
      type: Boolean,
      default: false,
    },
  },
  data() {
    return {
      showAlert: false,
    };
  },
  methods: {
    delay() {
      let delay = 500;
      setTimeout(() => {
        this.showAlert = true;
      }, delay);
    },
  },
  mounted() {
    this.delay();
  },
};
</script>
