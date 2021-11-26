<template>
  <div class="about">
    <base-header
      class="header pb-8 pt-5 pt-lg-8 d-flex align-items-center"
      style="
        min-height: 600px;
        background-size: cover;
        background-position: center top;
      "
    >
      <!-- Mask -->
      <span class="mask bg-gradient-success opacity-8"></span>
      <!-- Header container -->
      <div class="container-fluid d-flex justify-content-between">
        <div class="row justify-content-between">
          <div class="col-lg-12 col-md-10">
            <h1 class="display-2 text-white">Movies</h1>
            <p class="text-white mt-0 mb-5">
              Our vast collection of movies.
            </p>
          </div>
        </div>
      </div>
    </base-header>

    <div class="container-fluid mt--7">
      <div class="col justify-content-center">
        <div class="order-xl-1">
          <card shadow type="secondary">

            <movies :movies="movies"/>

          </card>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import Movies from "@/components/Movies";
export default {
  name: "movies-view",
  components: { Movies },
  data() {
    return {
      movies: [
        {
          title: "Charlie and the Chocolate Factory",
          author: "Tim Burton",
          dateOfRelease: "07/10/05",
          length: "115",
        },
        {
          title: "The Maze Runner",
          author: "Wes Ball",
          dateOfRelease: "09/19/14",
          length: "113",
        },
        {
          title: "Red Notice",
          author: "Rawson Marshall Thurber",
          dateOfRelease: "11/05/21",
          length: "118",
        },
        {
          title: "Fast Five",
          author: "Justin Lin",
          dateOfRelease: "04/15/11",
          length: "130",
        },
      ],
    };
  }
}
</script>