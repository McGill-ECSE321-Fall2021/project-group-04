import signup from '../components/js/signup';
<template>
  <div class="row justify-content-center">
    <div class="col-lg-5 col-md-7">
      <div class="card bg-secondary shadow border-0">
        <div class="card-body px-lg-5 py-lg-5">
          <div class="text-center text-muted mb-4">
            <small>Sign up with credentials</small>
          </div>
          <form role="form">
            <base-input
              formClasses="input-group-alternative"
              placeholder="Username"
              addon-left-icon="ni ni-hat-3"
              v-model="user.username"
            >
            </base-input>

            <base-input
              formClasses="input-group-alternative"
              placeholder="Password"
              type="password"
              addon-left-icon="ni ni-lock-circle-open"
              v-model="user.password"
            >
            </base-input>

            <hr class="my-4" />

            <base-input
              formClasses="input-group-alternative"
              placeholder="Address"
              addon-left-icon="ni ni-building"
              v-model="user.address"
            >
            </base-input>

            <div class="text-center">
              <base-button
                type="primary"
                class="my-4"
                @click="sign_up(user.username, user.password, user.address)"
                >Create account</base-button
              >
            </div>
          </form>
        </div>
      </div>
      <div class="row mt-3">
        <div class="col-6">
          <a href="#" class="text-light">
            <small>Librarian Register</small>
          </a>
        </div>
        <div class="col-6 text-right">
          <router-link to="/login" class="text-light">
            <small>Login</small>
          </router-link>
        </div>
      </div>
    </div>
  </div>
</template>
<script src="../components/js/signup.js" />
<style></style>
