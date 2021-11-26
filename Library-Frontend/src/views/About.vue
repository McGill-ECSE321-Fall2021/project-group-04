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
            <h1 class="display-2 text-white">Library</h1>
            <p class="text-white mt-0 mb-5">
              We may be the best library in town.
            </p>
          </div>
        </div>
      </div>
    </base-header>
  </div>
</template>

<script>
export default {
  name: "about",
}
</script>