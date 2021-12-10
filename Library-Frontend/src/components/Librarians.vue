<template>
  <div class="row" v-if="librarians">
    <div class="col-lg-4" v-for="librarian in librarians" :key="librarian.username">
      <librarian-card
        :username="librarian.username"
        :workHours="librarian.workHours"
      >
      </librarian-card>
    </div>
  </div>
  <div v-if="empty && showAlert">
    <base-alert type="danger">
      <strong>Nothing to see here.</strong>
    </base-alert>
  </div>
</template>

<script>
import LibrarianCard from "@/components/LibrarianCard";

export default {
  name: "librarians",
  components: { LibrarianCard },
  props: {
    username: String,
    workHours: Array,
    empty: {
      type: Boolean,
      default: false,
    },
  },
  data() {
    return {
      showAlert: false,
      librarians: [
              {
                username: "Aly",
                workHours: [
                  {
                    day: "Monday",
                    start: "9:00",
                    end: "5:00",
                  },
                  {
                    day: "Tuesday",
                    start: "9:00",
                    end: "5:00",
                  },
                  {
                    day: "Wednesday",
                    start: "9:00",
                    end: "5:00",
                  },
                  {
                    day: "Thursday",
                    start: "9:00",
                    end: "5:00",
                  },
                  {
                    day: "Friday",
                    start: "9:00",
                    end: "5:00",
                  },
                  {
                    day: "Saturday",
                    start: "9:00",
                    end: "5:00",
                  },
                  {
                    day: "Sunday",
                    start: "",
                    end: "",
                  },
                ],
              },
              {
                username: "Simo",
                workHours: [
                  {
                    day: "Monday",
                    start: "9:00",
                    end: "5:00",
                  },
                  {
                    day: "Tuesday",
                    start: "9:00",
                    end: "5:00",
                  },
                  {
                    day: "Wednesday",
                    start: "9:00",
                    end: "5:00",
                  },
                  {
                    day: "Thursday",
                    start: "9:00",
                    end: "5:00",
                  },
                  {
                    day: "Friday",
                    start: "9:00",
                    end: "5:00",
                  },
                  {
                    day: "Saturday",
                    start: "",
                    end: "",
                  },
                  {
                    day: "Sunday",
                    start: "",
                    end: "",
                  },
                ],
              },
              {
                username: "Saghar",
                workHours: [
                  {
                    day: "Monday",
                    start: "9:00",
                    end: "5:00",
                  },
                  {
                    day: "Tuesday",
                    start: "9:00",
                    end: "5:00",
                  },
                  {
                    day: "Wednesday",
                    start: "9:00",
                    end: "5:00",
                  },
                  {
                    day: "Thursday",
                    start: "9:00",
                    end: "5:00",
                  },
                  {
                    day: "Friday",
                    start: "9:00",
                    end: "5:00",
                  },
                  {
                    day: "Saturday",
                    start: "",
                    end: "",
                  },
                  {
                    day: "Sunday",
                    start: "",
                    end: "",
                  },
                ],
              },
              {
                username: "Zizou",
                workHours: [
                  {
                    day: "Monday",
                    start: "9:00",
                    end: "5:00",
                  },
                  {
                    day: "Tuesday",
                    start: "9:00",
                    end: "5:00",
                  },
                  {
                    day: "Wednesday",
                    start: "9:00",
                    end: "5:00",
                  },
                  {
                    day: "Thursday",
                    start: "9:00",
                    end: "5:00",
                  },
                  {
                    day: "Friday",
                    start: "9:00",
                    end: "5:00",
                  },
                  {
                    day: "Saturday",
                    start: "",
                    end: "",
                  },
                  {
                    day: "Sunday",
                    start: "",
                    end: "",
                  },
                ],
              },
              {
                username: "Alex",
                workHours: [
                  {
                    day: "Monday",
                    start: "9:00",
                    end: "5:00",
                  },
                  {
                    day: "Tuesday",
                    start: "9:00",
                    end: "5:00",
                  },
                  {
                    day: "Wednesday",
                    start: "9:00",
                    end: "5:00",
                  },
                  {
                    day: "Thursday",
                    start: "9:00",
                    end: "5:00",
                  },
                  {
                    day: "Friday",
                    start: "9:00",
                    end: "5:00",
                  },
                  {
                    day: "Saturday",
                    start: "9:00",
                    end: "5:00",
                  },
                  {
                    day: "Sunday",
                    start: "",
                    end: "",
                  },
                ],
              },
            ],
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
