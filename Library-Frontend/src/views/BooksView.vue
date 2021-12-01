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
            <h1 class="display-2 text-white">Books</h1>
            <p class="text-white mt-0 mb-5">Our vast collection of classics.</p>
          </div>
        </div>
      </div>
    </base-header>

    <div class="container-fluid mt--7">
      <div class="col justify-content-center">
        <div class="order-xl-1">
          <card shadow type="secondary">
            <books :books="books" />
          </card>
        </div>
      </div>
    </div>
  </div>
</template>

<script >
import Books from "@/components/Books";
import BookItem from "../components/js/bookItem.js"
export default {
  name: "books-view",
  components: { Books },
  data() {
    return {
      books : BookItem.data().books,
    };
  },
};
</script>
