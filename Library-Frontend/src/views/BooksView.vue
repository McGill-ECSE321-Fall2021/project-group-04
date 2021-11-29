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

<script>
import Books from "@/components/Books";
export default {
  name: "books-view",
  components: { Books },
  data() {
    return {
      books: [
        {
          title: "Percy Jackson & the Olympians: The Lightning Thief",
          author: "Rick Riordan",
          dateOfRelease: "06/28/05",
          isbn: "0-7868-5629-7",
          numberOfPages: "377",
        },
        {
          title: "Percy Jackson & the Olympians: The Sea of Monsters",
          author: "Rick Riordan",
          dateOfRelease: "04/01/06",
          isbn: "0-7868-5686-6",
          numberOfPages: "279",
        },
        {
          title: "Percy Jackson & the Olympians: The Titan’s Curse",
          author: "Rick Riordan",
          dateOfRelease: "05/01/07",
          isbn: "978-1-4231-0145-1",
          numberOfPages: "312",
        },
        {
          title: "Percy Jackson & the Olympians: The Battle of the Labyrinth",
          author: "Rick Riordan",
          dateOfRelease: "05/06/08",
          isbn: "9781423101468",
          numberOfPages: "361",
        },
        {
          title: "Percy Jackson & the Olympians: The Last Olympian",
          author: "Rick Riordan",
          dateOfRelease: "05/05/09",
          isbn: "978-1-4231-0147-5",
          numberOfPages: "381",
        },
      ],
    };
  },
};
</script>
