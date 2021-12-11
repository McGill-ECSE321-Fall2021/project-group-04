<template>
  <card class="card-stats" :show-footer-line="true">
    <div class="row">
      <div class="col">
        <slot>
          <h5 class="card-title text-uppercase text-muted mb-0" v-if="title">
            {{ title }}
          </h5>
          <span class="h2 font-weight-bold mb-0" v-if="subTitle">
            {{ subTitle }}
          </span>
        </slot>
      </div>

      <div class="col-auto" v-if="$slots.icon || icon">
        <slot name="icon">
          <div
            class="icon icon-shape text-white rounded-circle shadow"
            :class="[`bg-${type}`, iconClasses]"
          >
            <i :class="icon"></i>
          </div>
        </slot>
      </div>
    </div>

    <p class="mt-3 mb-0 text-sm"><slot name="footer" /><br /></p>
    <div class="text-right pt-2" v-if="button2">
      <a>
        <base-button
          size="sm"
          @click="
            modals[1].methods.checkoutItem(this.reservedBy, this.bookingId)
          "
          >{{ button2Text }}</base-button
        >
      </a>
    </div>
    <div class="text-right pt-2" v-if="button">
      <a>
        <base-button
          size="sm"
          @click="modals[1].methods.scheduleOrBooking(this.buttonText, this.type, this.subTitle, this.username)"
          >{{ buttonText }}</base-button
        >

        <modal v-model:show="modals[0]">
          <template v-slot:header>
            <h3 class="modal-title lg">{{ modalTitle }}</h3>
          </template>
          <div class="text-left"><slot name="modal" /><br /></div>
          <template v-slot:footer>
            <base-button size="sm" @click="modals[0] = false">OK</base-button>
          </template>
        </modal>
      </a>
    </div>
  </card>
</template>
<script>
import Card from "./Card.vue";
import BaseButton from "@/components/BaseButton";
import Booking from "@/components/js/Booking";

export default {
  name: "data-card",
  components: {
    BaseButton,
    Card,
  },
  props: {
    type: {
      type: String,
      default: "primary",
    },
    button: {
      type: Boolean,
      default: true,
    },
    button2: {
      type: Boolean,
      default: false,
    },
    button3: {
      type: Boolean,
      default: false,
    },
    icon: String,
    title: String,
    username: String,
    subTitle: String,
    bookingId: String,
    reservedBy: String,
    iconClasses: [String, Array],
    successMessage: {
      type: String,
      default: "Success.",
    },
    modalTitle: {
      type: String,
      default: "Reservation Status",
    },
    buttonText: {
      type: String,
      default: "Reserve",
    },
    button2Text: {
      type: String,
      default: "Check Out",
    },

  },
  data() {
    return {
      modals: [false, Booking],
    };
  },
};
</script>
<style></style>
