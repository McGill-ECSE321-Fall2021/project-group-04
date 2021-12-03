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
            <h1 class="display-2 text-white">Music</h1>
            <p class="text-white mt-0 mb-5">Music to my ears.</p>
          </div>
        </div>
      </div>
    </base-header>

    <div class="container-fluid mt--7">
      <div class="col justify-content-center">
        <div class="order-xl-1">
          <card shadow type="secondary">
            <music-albums :albums="albums" :empty="!albums.length" />
            <base-button
              block
              type="primary"
              v-if="!isMember"
              @click="modal = true"
            >
              New Music Album
            </base-button>
            <modal v-model:show="modal">
              <template v-slot:header>
                <h3 class="modal-title lg">Add a New Book to the Library</h3>
              </template>
              <div class="text-left">
                <base-input
                  placeholder="Title"
                  input-classes="form-control-alternative"
                  v-model="musicAlbum.title"
                />
                <base-input
                  placeholder="Author"
                  input-classes="form-control-alternative"
                  v-model="musicAlbum.author"
                />
                <base-input
                  placeholder="Date of Release"
                  input-classes="form-control-alternative"
                  v-model="musicAlbum.dateOfRelease"
                />
                <base-input
                  placeholder="Price"
                  input-classes="form-control-alternative"
                  v-model="musicAlbum.price"
                />
                <base-input
                  placeholder="Number of Songs"
                  input-classes="form-control-alternative"
                  v-model="musicAlbum.numberOfSongs"
                />
                <base-input
                  placeholder="Total Length"
                  input-classes="form-control-alternative"
                  v-model="musicAlbum.totalLength"
                />
                <base-input
                  placeholder="Barcode"
                  input-classes="form-control-alternative"
                  v-model="musicAlbum.barcode"
                />
              </div>
              <template v-slot:footer>
                <base-button
                  size="lg"
                  @click="
                    add_music_album(
                      musicAlbum.title,
                      musicAlbum.price,
                      musicAlbum.barcode,
                      musicAlbum.numberOfSongs,
                      musicAlbum.author,
                      musicAlbum.dateOfRelease,
                      musicAlbum.totalLength
                    );
                    modal = false;
                  "
                  >Add</base-button
                >
              </template>
            </modal>
          </card>
        </div>
      </div>
    </div>
  </div>
</template>

<script src="../components/js/musicAlbumItem.js"></script>
