import { createRouter, createWebHashHistory } from "vue-router";

import DashboardLayout from "@/layout/DashboardLayout";
import AuthLayout from "@/layout/AuthLayout";

import Dashboard from "../views/Dashboard.vue";
import Maps from "../views/Maps.vue";
import Profile from "../views/UserProfile.vue";

import Login from "../views/Login.vue";
import Register from "../views/Register.vue";
import LibrarianRegister from "../views/LibrarianRegister"

import About from "../views/About.vue";
import BooksView from "@/views/BooksView";
import MoviesView from "@/views/MoviesView";
import MusicAlbumsView from "@/views/MusicAlbumsView";
import NewspaperView from "@/views/NewspaperView";
import ArchiveView from "@/views/ArchiveView";
import Bookings from "../views/Bookings"
import LibrarianScheduler from "../views/LibrarianScheduler"

const routes = [
  {
    path: "/",
    redirect: "login",
    component: AuthLayout,
    children: [
      {
        path: "/login",
        name: "login",
        components: { default: Login },
      },
      {
        path: "/register",
        name: "register",
        components: { default: Register },
      },
      {
        path: "/libregister",
        name: "libregister",
        components: { default: LibrarianRegister },
      },
    ],
  },
  {
    path: "/",
    redirect: "/dashboard",
    component: DashboardLayout,
    children: [
      {
        path: "/about",
        name: "about",
        components: { default: About },
      },
      {
        path: "/dashboard",
        name: "dashboard",
        components: { default: Dashboard },
      },
      {
        path: "/maps",
        name: "maps",
        components: { default: Maps },
      },
      {
        path: "/profile",
        name: "profile",
        components: { default: Profile },
      },
      {
        path: "/books",
        name: "books",
        components: { default: BooksView },
      },
      {
        path: "/movies",
        name: "movies",
        components: { default: MoviesView },
      },
      {
        path: "/music-albums",
        name: "music-albums",
        components: { default: MusicAlbumsView },
      },
      {
        path: "/newspapers",
        name: "newspapers",
        components: { default: NewspaperView },
      },
      {
        path: "/archives",
        name: "archives",
        components: { default: ArchiveView },
      },
      {
        path: "/bookings",
        name: "bookings",
        components: { default: Bookings },
      },
      {
        path: "/librarian_scheduler",
        name: "librarian_scheduler",
        components: { default: LibrarianScheduler },
      },
    ],
  },
];

const router = createRouter({
  history: createWebHashHistory(),
  linkActiveClass: "active",
  routes,
  // eslint-disable-next-line no-unused-vars
  scrollBehavior(to, from, savedPosition) {
    if (to.hash) {
      return {
        selector: to.hash,
        behavior: "smooth",
      };
    }
  },
});

export default router;
