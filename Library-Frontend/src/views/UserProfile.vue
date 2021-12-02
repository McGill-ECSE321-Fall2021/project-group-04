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
          <div class="col-lg-7 col-md-10">
            <h1 class="display-2 text-white">Hello {{ model.username }}</h1>
            <p class="text-white mt-0 mb-5">
              This is your profile page. You can see all your personal
              information, your reservations and lendings.
            </p>
            <!-- <a href="#!" class="btn btn-info">Edit profile</a> -->
          </div>
          <div class="col-xl-4 order-xl-2 mb-5 mb-xl-0">
            <div class="card card-profile shadow">
              <div class="row justify-content-center">
                <div class="col-lg-3 order-lg-2">
                  <div class="card-profile-image">
                    <a href="#">
                      <img src="img/theme/cat.jpg" class="rounded-circle" />
                    </a>
                  </div>
                </div>
              </div>
              <div
                class="card-header text-center border-0 pt-8 pt-md-4 pb-0 pb-md-4"
              ></div>
              <div class="card-body pt-0 pt-md-4">
                <div class="row">
                  <div class="col">
                    <div
                      class="card-profile-stats d-flex justify-content-center mt-md-5"
                    ></div>
                  </div>
                </div>
                <div class="text-center">
                  <!-- <i class="ni ni-circle-08"></i> -->
                  <h3>{{ model.username }}</h3>
                  <div class="h5 font-weight-300">
                    <i class="ni location_pin mr-2"></i>
                    {{ model.address }}
                  </div>
                </div>
              </div>
            </div>
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
                  <div class="col-8">
                    <h3 class="mb-0">My account</h3>
                  </div>
                </div>
              </div>
            </template>

            <form>
              <h6 class="heading-small text-muted mb-4">User information</h6>
              <div class="pl-lg-4">
                <div class="row">
                  <div :class="model.isMember ? 'col-lg-6' : 'col-lg-12'">
                    <base-input
                      alternative=""
                      label="Username"
                      placeholder="username"
                      input-classes="form-control-alternative"
                      v-model="model.username"
                      :readonly="true"
                    />
                  </div>
                  <div class="col-lg-3" v-if="model.isMember">
                    <base-input
                      alternative=""
                      label="Start Date"
                      placeholder="10/10/10"
                      input-classes="form-control-alternative"
                      v-model="model.startDate"
                      :readonly="true"
                    />
                  </div>
                  <div class="col-lg-3" v-if="model.isMember">
                    <base-input
                      alternative=""
                      label="Montly Fee"
                      placeholder="$0"
                      input-classes="form-control-alternative"
                      v-model="model.monthlyFee"
                      :readonly="true"
                    />
                  </div>
                </div>
              </div>
              <hr class="my-4" />
              <!-- Address -->
              <h6 class="heading-small text-muted mb-4">Contact information</h6>
              <div class="pl-lg-4">
                <div class="row">
                  <div class="col-md-12">
                    <base-input
                      :readonly="true"
                      alternative=""
                      label="Address"
                      placeholder="1234 University, Montreal, Canada"
                      input-classes="form-control-alternative"
                      v-model="model.address"
                    />
                  </div>
                </div>
              </div>
            </form>
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
                    These are all the books you have reserved.
                  </p>
                  <books :books="reservations" :reservable="false" />
                </tab-pane>
                <tab-pane icon="ni ni-button-play" title="Movies">
                  <p class="description">
                    These are all the movies you have reserved.
                  </p>
                  <movies :movies="movies" :reservable="false" />
                </tab-pane>
                <tab-pane icon="ni ni-note-03" title="Music Albums">
                  <p class="description">
                    These are all the music albums you have reserved.
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
                  <p class="description">These are all the books you have.</p>
                  <books :books="lendings" :reservable="false" />
                </tab-pane>
                <tab-pane icon="ni ni-button-play" title="Movies">
                  <p class="description">These are all the movies you have.</p>
                  <movies :movies="movies" :reservable="false" />
                </tab-pane>
                <tab-pane icon="ni ni-note-03" title="Music Albums">
                  <p class="description">
                    These are all the music albums you have.
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
<script src="../components/js/Booking.js">

</script>
<style></style>
