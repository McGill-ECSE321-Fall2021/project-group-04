<template v-if="name">reserveButton
  <data-card
    :sub-title="name" :button="false" class="mb-2"
  >
    <template v-slot:footer v-if="workHours">
      <div v-for="hour in workHours" v-bind:key="hour.day">
        <span class="mr-2">
          {{hour.day}}: {{hour.start}}-{{hour.end}}
        </span><br/>
      </div>
    </template>
  </data-card>
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
};
</script>
<style></style>
