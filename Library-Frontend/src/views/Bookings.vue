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
            <h1 class="display-2 text-white">Reservations and Checkouts</h1>
            <p class="text-white mt-0 mb-5">What people are currently enjoying.</p>
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
                    <h3 class="mb-0">Reservations</h3>
                  </v>
                </div>
              </div>
            </template>

            <tabs fill class="flex-column flex-md-row">
              <card shadow>
                <tab-pane icon="ni ni-books" title="Books">
                  <p class="description">
                    All the books people have reserved.
                  </p>
                  <books
                    v-if="!isMember"
                    :books="allReservations"
                    :reservable="false"
                    :checkoutable="true"
                  />
                  <books
                    v-if="isMember"
                    :books="reservations"
                    :reservable="false"
                    :checkoutable="false"
                  />
                </tab-pane>
                <tab-pane icon="ni ni-button-play" title="Movies">
                  <p class="description">
                    All the movies people have reserved.
                  </p>
                  <movies :movies="movies" :reservable="false" />
                </tab-pane>
                <tab-pane icon="ni ni-note-03" title="Music Albums">
                  <p class="description">
                    All the music albums people have reserved.
                  </p>
                  <music-albums :albums="albums" :reservable="false" />
                </tab-pane>
              </card>
            </tabs>
          </card>
        </div>
      </div>
    </div>

    <div class="container-fluid mt--7" style="padding-top: 110px">
      <div class="col justify-content-center">
        <div class="order-xl-1">
          <card shadow type="secondary">
            <template v-slot:header>
              <div class="bg-white border-0">
                <div class="row align-items-center">
                  <div class="col-8">
                    <h3 class="mb-0">Lendings</h3>
                  </div>
                </div>
              </div>
            </template>

            <tabs fill class="flex-column flex-md-row">
              <card shadow>
                <tab-pane icon="ni ni-books" title="Books">
                  <p class="description">
                    All books people have lent out.
                  </p>
                  <books
                    v-if="isMember"
                    :books="lendings"
                    :reservable="false"
                  />
                  <books
                    v-if="!isMember"
                    :books="allLendings"
                    :reservable="false"
                  />
                </tab-pane>
                <tab-pane icon="ni ni-button-play" title="Movies">
                  <p class="description">
                    All movies you have lent out.
                  </p>
                  <movies :movies="movies" :reservable="false" />
                </tab-pane>
                <tab-pane icon="ni ni-note-03" title="Music Albums">
                  <p class="description">
                    All music albums you have lent out.
                  </p>
                  <music-albums :albums="albums" :reservable="false" />
                </tab-pane>
              </card>
            </tabs>
          </card>
        </div>
      </div>
    </div>
  </div>
</template>
<script src="../components/js/Booking.js"></script>
<style></style>
