<template v-if="username">
  <data-card
    :sub-title="username"
    :button="true"
    class="mb-2"
    buttonText="Schedule"
    modalTitle="Schedule Librarian"
  >
    <template v-slot:footer v-if="workHours">
      <div v-for="hour in workHours" v-bind:key="hour.day">
        <span class="mr-2">
          {{hour.day}}: {{hour.start}}-{{hour.end}}
        </span><br/>
      </div>
    </template>
    <template v-slot:modal>
      <p>You are assigning hours to {{username}}</p>
      <div class="row" v-for="day in daysOfWeek" v-bind:key="day">
        <div class="col-lg-6">
          <base-input
            :placeholder="day + ' Start'"
            input-classes="form-control-alternative"
          />
        </div>
        <div class="col-lg-6">
          <base-input
              :placeholder="day + ' End'"
              input-classes="form-control-alternative"
          />
        </div>
      </div>

    </template>
  </data-card>
</template>

<script>
import DataCard from "./DataCard.vue";
import BaseInput from "@/components/BaseInput";

export default {
  name: "librarian-card",
  components: {
    BaseInput,
    DataCard,
  },
  props: {
    username: String,
    workHours: Array,
  },
  data() {
    return {
      daysOfWeek: [
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday",
        "Saturday",
        "Sunday",
      ],
    };
  }
};
</script>
<style></style>
