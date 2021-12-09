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
    <div class="container-fluid mt--7">
      <div class="col justify-content-center">
        <div class="order-xl-1">
          <card shadow type="secondary">
            <template v-slot:header>
              <div class="bg-white border-0">
                <div class="row align-items-center">
                  <div class="col-8">
                    <h3 class="mb-0">We are more than just a library</h3>
                  </div>
                </div>
              </div>
            </template>
            <section>
              <div class="card-deck">
                <div class="card">
                  <div class="card-body">
                    <div class="d-flex justify-content-around px-md-1">
                      <div class="align-self-center"></div>
                      <div>
                        <h3>Quiet and group study spaces</h3>
                        <p>
                          Our Library provides a variety of study spaces
                          including spaces for silent study, group study rooms,
                          and various collaborative spaces for quiet group work.
                          In collaborative spaces, speaking softly is permitted.
                          No talking, whispering or noise is permitted in areas
                          designated as silent study spaces. want to learn more?
                          contact us and our librarian would be happy to help
                          you.
                        </p>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="card">
                  <div class="card-body">
                    <div class="d-flex justify-content-around px-md-1">
                      <div class="align-self-center"></div>
                      <div>
                        <h3>Host Events</h3>
                        <p>
                          Our Library has multiple available spaces to host any
                          event including birthdays for kids, reading sessions
                          and even town halls. You can contact us by either
                          giving us a call or coming to our library and our
                          librarians will be happy to assist you according to
                          your needs. what else?! we offer free snacks for
                          events with more than 10 people.
                        </p>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="card">
                  <div class="card-body">
                    <div class="d-flex justify-content-around px-md-1">
                      <div class="align-self-center"></div>
                      <div>
                        <h3>Books,Music,Newspaper and many more</h3>
                        <p>
                          Our Library provides a variety of of books, music
                          albums, newspapers, movies and archieves. All these
                          items are available to you to use wither at the
                          library or to take them home! We constantly update our
                          items so you can have keep up with the rest of the
                          world. Our librarians are here to help you find your
                          favorite books and items. We also lend headphones in
                          case you decide to your favorite artists while
                          studying.
                        </p>
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
  </div>
</template>

<script>
export default {
  name: "about",
};
</script>
