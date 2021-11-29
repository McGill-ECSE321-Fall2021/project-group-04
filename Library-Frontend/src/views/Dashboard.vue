<template>
  <div>
    <base-header type="gradient-success" class="pb-6 pb-8 pt-5 pt-md-8">
      <div class="row">
        <div class="col-lg-7 col-md-10">
          <h1 class="display-1 text-white text-left"><strong>Welcome!</strong></h1>
          <p class="text-white mt-0 mb-5 text-left">
            <i class="ni ni-books"></i>
            Libraries remind us that the world is still beautiful
          </p>
        </div>
      </div>
    </base-header>
    <div class="container-fluid mt--7" style="padding-top: 110px">
      <div class="col justify-content-around">
        <div class=" order-xl-1" >
          <card shadow type="secondary">
            <template v-slot:header >
              <div class="bg-white border-0">
                <div class="row align-items-around">
                  <div class="col-8">
                    <h3 class="mb-0">What we Offer</h3>
                  </div>
                </div>
              </div>
            </template>
            <section>
              <div class="row align-items-center">
                <div class="col-xl-3 col-sm-6 col-12 mb-4">
                  <a href="/books">
                    <div class="card">
                      <div class="card-body">
                        <div class="d-flex justify-content-around px-md-1">
                          <div class="align-self-center">
                            <i class="ni ni-books text-info ni-3x"></i>
                          </div>
                          <div>
                            <h3 class="text-info ">Books</h3>
<!--                            <p class="mb-0">Enjoy</p>-->
                          </div>
                        </div>
                      </div>
                    </div>
                  </a>
                </div>
                <div class="col-xl-3 col-sm-6 col-12 mb-4">
                  <a href="/movies">
                    <div class="card">
                      <div class="card-body">
                        <div class="d-flex justify-content-around px-md-1">
                          <div class="align-self-center">
                            <i class="ni ni-button-play text-warning ni-3x"></i>
                          </div>
                          <div>
                            <h3 class="text-warning">Movies</h3>
<!--                            <p class="mb-0 "></p>-->
                          </div>
                        </div>
                      </div>
                  </div>
                  </a>
                </div>
                <div class="col-xl-3 col-sm-6 col-12 mb-4">
                  <a href="/albums">
                    <div class="card">
                      <div class="card-body">
                        <div class="d-flex justify-content-around px-md-1">
                          <div class="align-self-center">
                            <i class="ni ni-note-03 text-primary ni-3x"></i>
                          </div>
                          <div>
                            <h3 class="text-primary">Music Albums</h3>
  <!--                          <p class="mb-0">better than spotify</p>-->
                          </div>
                        </div>
                      </div>
                    </div>
                  </a>
                </div>
              </div>
              <div class="row align-items-center">
                <div class="col-xl-3 col-sm-6 col-12 mb-4">
                  <a href="/newspapers">
                    <div class="card">
                      <div class="card-body">
                        <div class="d-flex justify-content-around px-md-1">
                          <div class="align-self-center">
                            <i class="ni ni-align-left-2 text-default ni-3x"></i>
                          </div>
                          <div>
                            <h3 class="text-default">Newspapers</h3>
  <!--                          <p class="mb-0">keep updated</p>-->
                          </div>
                        </div>
                      </div>
                    </div>
                  </a>
                </div>
                <div class="col-xl-3 col-sm-6 col-12 mb-4">
                  <a href="/archives">
                    <div class="card">
                      <div class="card-body">
                        <div class="d-flex justify-content-around px-md-1">
                          <div class="align-self-center">
                            <i class="ni ni-archive-2 text-success ni-3x"></i>
                          </div>
                          <div >
                            <h3 class ="text-success">Archives</h3>
  <!--                          <p class="mb-0">anything anytime</p>-->
                          </div>
                        </div>
                      </div>
                    </div>
                  </a>
                </div>
                <div class="col-xl-3 col-sm-6 col-12 mb-4">
                    <div class="card">
                      <div class="card-body">
                        <div class="d-flex justify-content-around px-md-1">
                          <div class="align-self-center">
                            <i class="ni ni-pin-3 text-danger ni-3x"></i>
                          </div>
                          <div>
                            <h3 class="text-danger">Events</h3>
  <!--                          <p class="mb-0">social events</p>-->
                          </div>
                        </div>
                      </div>
                    </div>
                </div>
              </div>
            </section>
          </card>
        </div>
      </div>
    </div>
    <!--Tables-->
      <div
        class="container-fluid mt--7"
        v-if="librarians"
        style="padding-top: 110px"
      >
        <div class="col justify-content-center">
          <div class="order-xl-1">
            <card shadow type="secondary">
              <template v-slot:header>
                <div class="bg-white border-0">
                  <div class="row align-items-center">
                    <div class="col-8">
                      <h3 class="mb-0">Schedule</h3>
                    </div>
                  </div>
                </div>
              </template>

              <tabs fill class="flex-column flex-md-row">
                <card shadow>
                  <tab-pane title="Librarians" v-if="librarians">
                    <p class="description">
                      These are all our librarians.
                    </p>
                    <div class="row" v-if="librarians">
                      <div class="col-lg-3" v-for="lib in librarians" v-bind:key="lib.username">
                        <librarian-card :username="lib.username" :workHours="lib.workHours"/>
                      </div>
                      <div class="col-lg-3" v-if="headLibrarian">
                        <librarian-card :username="headLibrarian.username" :workHours="headLibrarian.workHours"/>
                      </div>
                    </div>
                  </tab-pane>
                  <tab-pane title="Library">
                    <p class="description">
                      The library.
                    </p>
                    <div>
                      <library-card :name="library.name" :workHours="library.workHours"/>
                    </div>
                  </tab-pane>
                </card>
              </tabs>

            </card>
          </div>
        </div>
      </div>

  </div>
</template>

<script>

import LibrarianCard from "@/components/LibrarianCard";
import LibraryCard from "@/components/LibraryCard";
export default {
  components: {
    LibraryCard,
    LibrarianCard
  },
  data() {
    return {
      librarians: [
        {
          username: "aly",
          workHours: [
            {
              day: "Monday",
              start: "9:00",
              end: "5:00",
            },
            {
              day: "Tuesday",
              start: "9:00",
              end: "5:00",
            },
            {
              day: "Wednesday",
              start: "9:00",
              end: "5:00",
            },
            {
              day: "Thursday",
              start: "9:00",
              end: "5:00",
            },
            {
              day: "Friday",
              start: "9:00",
              end: "5:00",
            },
            {
              day: "Saturday",
              start: "9:00",
              end: "5:00",
            },
            {
              day: "Sunday",
              start: "",
              end: "",
            },
          ],
        },
        {
          username: "simo",
          workHours: [
            {
              day: "Monday",
              start: "9:00",
              end: "5:00",
            },
            {
              day: "Tuesday",
              start: "9:00",
              end: "5:00",
            },
            {
              day: "Wednesday",
              start: "9:00",
              end: "5:00",
            },
            {
              day: "Thursday",
              start: "9:00",
              end: "5:00",
            },
            {
              day: "Friday",
              start: "9:00",
              end: "5:00",
            },
            {
              day: "Saturday",
              start: "",
              end: "",
            },
            {
              day: "Sunday",
              start: "",
              end: "",
            },
          ],
        },
        {
          username: "saghar",
          workHours: [
            {
              day: "Monday",
              start: "9:00",
              end: "5:00",
            },
            {
              day: "Tuesday",
              start: "9:00",
              end: "5:00",
            },
            {
              day: "Wednesday",
              start: "9:00",
              end: "5:00",
            },
            {
              day: "Thursday",
              start: "9:00",
              end: "5:00",
            },
            {
              day: "Friday",
              start: "9:00",
              end: "5:00",
            },
            {
              day: "Saturday",
              start: "",
              end: "",
            },
            {
              day: "Sunday",
              start: "",
              end: "",
            },
          ],
        },
        {
          username: "zizou",
          workHours: [
            {
              day: "Monday",
              start: "9:00",
              end: "5:00",
            },
            {
              day: "Tuesday",
              start: "9:00",
              end: "5:00",
            },
            {
              day: "Wednesday",
              start: "9:00",
              end: "5:00",
            },
            {
              day: "Thursday",
              start: "9:00",
              end: "5:00",
            },
            {
              day: "Friday",
              start: "9:00",
              end: "5:00",
            },
            {
              day: "Saturday",
              start: "",
              end: "",
            },
            {
              day: "Sunday",
              start: "",
              end: "",
            },
          ],
        },
      ],
      headLibrarian: {
        username: "jewoo",
        workHours: [
          {
            day: "Monday",
            start: "9:00",
            end: "5:00",
          },
          {
            day: "Tuesday",
            start: "9:00",
            end: "5:00",
          },
          {
            day: "Wednesday",
            start: "9:00",
            end: "5:00",
          },
          {
            day: "Thursday",
            start: "9:00",
            end: "5:00",
          },
          {
            day: "Friday",
            start: "9:00",
            end: "5:00",
          },
          {
            day: "Saturday",
            start: "",
            end: "",
          },
          {
            day: "Sunday",
            start: "",
            end: "",
          },
        ],
      },
      library: {
        name: "Library",
        workHours: [
          {
            day: "Monday",
            start: "9:00",
            end: "5:00",
          },
          {
            day: "Tuesday",
            start: "9:00",
            end: "5:00",
          },
          {
            day: "Wednesday",
            start: "9:00",
            end: "5:00",
          },
          {
            day: "Thursday",
            start: "9:00",
            end: "5:00",
          },
          {
            day: "Friday",
            start: "9:00",
            end: "5:00",
          },
          {
            day: "Saturday",
            start: "",
            end: "",
          },
          {
            day: "Sunday",
            start: "",
            end: "",
          },
        ],
      }
    };
  },
};
</script>