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
            <music-albums :albums="albums" />
            <base-button block type="primary" v-if="!isMember" @click="modal = true">
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
                        v-model="musicAlbum.barCode"
                />
              </div>
              <template v-slot:footer>
                <base-button size="lg"  @click="add_music_album(musicAlbum.title, musicAlbum.price, musicAlbum.barCode, musicAlbum.numberOfSongs, musicAlbum.numberOfPages, musicAlbum.author, musicAlbum.dateOfRelease, musicAlbum.totalLength)">Add</base-button>
              </template>
            </modal>
          </card>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import MusicAlbums from "@/components/MusicAlbums";
export default {
  name: "music-albums-view",
  components: { MusicAlbums },
  data() {
    return {
      isMember: false,
      modal: false,
      albums: [
        {
          title: "Kamikaze",
          author: "Eminem",
          dateOfRelease: "08/31/18",
          numberOfSongs: "13",
          totalLength: "45:49",
        },
        {
          title: "Music to Be Murdered By",
          author: "Eminem",
          dateOfRelease: "01/17/20",
          numberOfSongs: "13",
          totalLength: "62:23",
        },
        {
          title: "Revival",
          author: "Eminem",
          dateOfRelease: "12/15/17",
          numberOfSongs: "18",
          totalLength: "77:39",
        },
        {
          title: "+ (Plus) Deluxe",
          author: "Ed Sheeran",
          dateOfRelease: "09/09/11",
          numberOfSongs: "12",
          totalLength: "65:35",
        },
      ],
    };
  },
};
</script>
