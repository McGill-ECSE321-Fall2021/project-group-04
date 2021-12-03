<template>
  <div class="row" v-if="albums">
    <div class="col-lg-4" v-for="album in albums" :key="album.title">
      <music-album-card
        :title="album.title"
        :author="album.author"
        :dateOfRelease="album.dateOfRelease"
        :numberOfSongs="album.numberOfSongs"
        :totalLength="album.totalLength"
        :reservable="reservable"
      >
      </music-album-card>
    </div>
  </div>
  <div v-if="empty && showAlert">
    <base-alert type="danger">
      <strong>Nothing to see here.</strong>
    </base-alert>
  </div>
</template>

<script>
import MusicAlbumCard from "@/components/MusicAlbumCard";
export default {
  name: "music-albums",
  components: { MusicAlbumCard },
  props: {
    albums: Array,
    reservable: {
      type: Boolean,
      default: true,
    },
    empty: {
      type: Boolean,
      default: false,
    },
  },
  data() {
    return {
      showAlert: false,
    };
  },
  methods: {
    delay() {
      let delay = 500;
      setTimeout(() => {
        this.showAlert = true;
      }, delay);
    },
  },
  mounted() {
    this.delay();
  },
};
</script>
