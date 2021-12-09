<template>
  <div class="row" v-if="movies">
    <div class="col-lg-4" v-for="movie in movies" :key="movie.title">
      <movie-card
        :title="movie.title"
        :author="movie.author"
        :dateOfRelease="movie.dateOfRelease"
        :length="movie.length"
        :reservable="reservable"
      >
      </movie-card>
    </div>
  </div>
  <div v-if="empty && showAlert">
    <base-alert type="danger">
      <strong>Nothing to see here.</strong>
    </base-alert>
  </div>
</template>

<script>
import MovieCard from "@/components/MovieCard";
export default {
  name: "movies",
  components: { MovieCard },
  props: {
    movies: Array,
    reservable: {
      type: Boolean,
      default: true,
    },
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
