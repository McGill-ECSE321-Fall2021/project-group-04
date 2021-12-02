<template v-if="name">
  <data-card :sub-title="name" :button="false" class="mb-2">
    <template v-slot:footer v-if="workHours">
      <div v-for="hour in workHours" v-bind:key="hour.day">
        <span class="mr-2">
          {{ hour.day }}: {{ hour.start }}-{{ hour.end }} </span
        ><br />
      </div>
    </template>
  </data-card>
  <base-button block type="primary" v-if="isMember">
    Reserve Library
  </base-button>
</template>

<script>
import DataCard from "./DataCard.vue";

export default {
  name: "library-card",
  components: {
    DataCard,
  },
  props: {
    name: String,
    workHours: Array,
  },
  data() {
    return {
      isMember: window.localStorage.getItem("userType") === "member",
    };
  },
};
</script>
<style></style>
