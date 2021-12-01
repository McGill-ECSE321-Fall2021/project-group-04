<template>
  <div class="row justify-content-center">
    <div class="col-lg-5 col-md-7">
      <div class="card bg-secondary shadow border-0">
        <div class="card-body px-lg-5 py-lg-5">
          <div class="text-center text-muted mb-4">
            <small>Login with credentials</small>
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
              formClasses="input-group-alternative mb-3"
              placeholder="Password"
              type="password"
              addon-left-icon="ni ni-lock-circle-open"
              v-model="user.password"
            >
            </base-input>

            <!-- <base-checkbox class="custom-control-alternative">
              <span class="text-muted">Remember me</span>
            </base-checkbox> -->
            <div class="text-center">
              <base-button type="primary" class="my-4" @click="login(user.username, user.password)">
                Sign in
              </base-button>
            </div>

<!--            <base-alert type="danger" v-if="!errorLogin">-->
<!--              <strong>Wrong Credentials</strong>-->
<!--            </base-alert>-->

          </form>
        </div>
      </div>
      <div class="row mt-3">
        <div class="col-6">
          <a href="#" class="text-light"><small></small></a>
        </div>
        <div class="col-6 text-right">
          <router-link to="/register" class="text-light"
            ><small>Create new account</small></router-link
          >
        </div>
      </div>
    </div>
  </div>
</template>
<script src="../components/js/login.js" />
<style></style>
