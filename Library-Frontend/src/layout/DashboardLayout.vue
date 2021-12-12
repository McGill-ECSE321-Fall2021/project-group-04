<template>
  <div class="wrapper" :class="{ 'nav-open': $sidebar.showSidebar }">
    <side-bar :background-color="sidebarBackground">
      <template v-slot:links>
        <sidebar-item
          :link="{
            name: 'Dashboard',
            icon: 'ni ni-tv-2 text-red',
            path: '/dashboard',
          }"
        />
        <sidebar-item
          :link="{
            name: 'Maps',
            icon: 'ni ni-pin-3 text-red',
            path: '/maps',
          }"
        />
        <sidebar-item
          :link="{
            name: 'User Profile',
            icon: 'ni ni-single-02 text-red',
            path: '/profile',
          }"
        />
        <sidebar-item
          :link="{
            name: 'Books',
            icon: 'ni ni-books text-red',
            path: '/books',
          }"
        />
        <sidebar-item
          :link="{
            name: 'Movies',
            icon: 'ni ni-button-play text-red',
            path: '/movies',
          }"
        />
        <sidebar-item
          :link="{
            name: 'Music Albums',
            icon: 'ni ni-note-03 text-red',
            path: '/music-albums',
          }"
        />
        <sidebar-item
          :link="{
            name: 'Newspapers',
            icon: 'ni ni-align-left-2 text-red',
            path: '/newspapers',
          }"
        />
        <sidebar-item
          :link="{
            name: 'Archives',
            icon: 'ni ni-archive-2 text-red',
            path: '/archives',
          }"
        />
        <sidebar-item
            v-if="!isMember"
            :link="{
            name: 'Bookings',
            icon: 'ni ni-cart text-red',
            path: '/bookings',
          }"
        />
        <sidebar-item
          v-if= "isHeadLibrarian"
          :link="{
            name: 'Librarian Schedule',
            icon: 'ni ni-time-alarm text-red',
            path: '/librarian_scheduler',
          }"
        />
        <sidebar-item
          v-if= "isHeadLibrarian"
          :link="{
            name: 'Create User',
            icon: 'ni ni-fat-add text-red',
            path: '/register',
          }"
        />
        <sidebar-item
          :link="{
            name: 'About Us',
            icon: 'ni ni-world text-red',
            path: '/about',
          }"
        />
      </template>
    </side-bar>
    <div class="main-content" :data="sidebarBackground">
      <dashboard-navbar></dashboard-navbar>

      <div @click="toggleSidebar">
        <router-view></router-view>
        <content-footer v-if="!$route.meta.hideFooter"></content-footer>
      </div>
    </div>
  </div>
</template>
<script>
import DashboardNavbar from "./DashboardNavbar.vue";
import ContentFooter from "./ContentFooter.vue";

export default {
  components: {
    DashboardNavbar,
    ContentFooter,
  },
  data() {
    return {
      sidebarBackground: "vue", //vue|blue|orange|green|red|primary
      isMember: window.localStorage.getItem("userType") === "member",
      isHeadLibrarian : window.localStorage.getItem("userType") == "head_librarian"
    };
  },
  methods: {
    toggleSidebar() {
      if (this.$sidebar.showSidebar) {
        this.$sidebar.displaySidebar(false);
      }
    },
  },
};
</script>
<style lang="scss"></style>
