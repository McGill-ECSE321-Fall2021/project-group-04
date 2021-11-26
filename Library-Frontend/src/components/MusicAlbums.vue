<template>
  <div class="row" v-if="albums">
    <div class="col-lg-4" v-for="album in albums" :key="album.title">
      <music-album-card
        :title="album.title"
        :author="album.author"
        :dateOfRelease="album.dateOfRelease"
        :numberOfSongs="album.numberOfSongs"
        :totalLength="album.totalLength"
      >
      </music-album-card>
    </div>
  </div>
</template>

<script>
import MusicAlbumCard from "@/components/MusicAlbumCard";
export default {
  name: "music-albums",
  components: { MusicAlbumCard },
  props: {
    albums: Array,
  },
};
</script>
