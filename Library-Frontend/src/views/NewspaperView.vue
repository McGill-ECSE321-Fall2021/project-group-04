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
            <h1 class="display-2 text-white">Newspapers</h1>
            <p class="text-white mt-0 mb-5">
              Lots o-news.
            </p>
          </div>
        </div>
      </div>
    </base-header>

    <div class="container-fluid mt--7">
      <div class="col justify-content-center">
        <div class="order-xl-1">
          <card shadow type="secondary">

            <ImmovableItems :immovable-items="newspaper"/>

          </card>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import ImmovableItems from "@/components/ImmovableItems";
export default {
  name: "newspaper-view",
  components: { ImmovableItems  },
  data() {
    return {
      newspaper: [
        {
          title: "Charlie and the Chocolate Factory",
          date: "07/10/05",
          numberOfPages: "11",
        },
        {
          title: "The Maze Runner",
          date: "09/19/14",
          numberOfPages: "11",
        },
        {
          title: "Red Notice",
          date: "11/05/21",
          numberOfPages: "11",
        },
        {
          title: "Fast Five",
          date: "04/15/11",
          numberOfPages: "11",
        },
      ],
    };
  }
}
</script>