<template>
  <div class="row" v-if="librarians">
    <div class="col-lg-4" v-for="librarian in librarians" :key="librarian.username">
      <librarian-card
        :username="librarian.username"
        :workHours="librarian.workHours"
        :scheduleButton="true"
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
    librarians: Array,
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
