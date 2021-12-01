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
            <h1 class="display-2 text-white">Archives</h1>
            <p class="text-white mt-0 mb-5">Dig past the past.</p>
          </div>
        </div>
      </div>
    </base-header>

    <div class="container-fluid mt--7">
      <div class="col justify-content-center">
        <div class="order-xl-1">
          <card shadow type="secondary">
            <ImmovableItems :immovable-items="archives" />
            <base-button block type="primary" v-if="!isMember" @click="modal = true">
              New Archive
            </base-button>
            <modal v-model:show="modal">
              <template v-slot:header>
                <h3 class="modal-title lg">Add a New Book to the Library</h3>
              </template>
              <div class="text-left">
                <base-input
                    placeholder="Title"
                    input-classes="form-control-alternative"
                />
                <base-input
                    placeholder="Date"
                    input-classes="form-control-alternative"
                />
                <base-input
                    placeholder="Number Of Pages"
                    input-classes="form-control-alternative"
                />
              </div>
              <template v-slot:footer>
                <base-button size="lg" @click="modal = false">Add</base-button>
              </template>
            </modal>
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
  components: { ImmovableItems },
  data() {
    return {
      isMember: false,
      modal: false,
      archives: [
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
  },
};
</script>
