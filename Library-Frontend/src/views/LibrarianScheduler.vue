<template>
  <div>
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
            <h1 class="display-2 text-white">Librarian Scheduler</h1>
            <p class="text-white mt-0 mb-5">Manage your librarians.</p>
          </div>
        </div>
      </div>
    </base-header>

    <div class="container-fluid mt--7">
      <div class="col justify-content-center">
        <div class="order-xl-1">
          <card shadow type="secondary">
            <template v-slot:header>
              <div class="bg-white border-0">
                <div class="row align-items-center">
                  <v class="col-8">
                    <h3 class="mb-0">Librarians</h3>
                  </v>
                </div>
              </div>
            </template>

            <tabs fill class="flex-column flex-md-row">
              <card shadow>
                <tab-pane icon="ni ni-books" title="Librarians">
                  <p class="description">
                    All your librarians.
                  </p>
                  <librarians
                      v-if="!isMember"
                      :librarians="librarians"
                  />
                </tab-pane>
              </card>
            </tabs>
          </card>
        </div>
      </div>
    </div>

  </div>
</template>
<script src="../components/js/librarian.js"></script>
<style></style>
