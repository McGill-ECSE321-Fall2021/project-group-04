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
  },
};
</script>
