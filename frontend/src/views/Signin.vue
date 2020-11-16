<template>
  <v-row align="center" justify="center">
    <v-col cols="12" sm="8" md="6">
      <v-card class="elevation-12">
        <v-toolbar color="#FB8C00" dark>
          <v-toolbar-title>เข้าสู่ระบบในฐานะนักศึกษา</v-toolbar-title>
          <v-spacer />
        </v-toolbar>
        <v-card-text>
          <v-form>
            <v-text-field
              label="Login"
              name="login"
              prepend-icon="mdi-account"
              type="text"
              v-model="username"
            />

            <v-text-field
              id="password"
              label="Password"
              name="password"
              prepend-icon="mdi-lock"
              type="password"
              v-model="password"
            />
          </v-form>
        </v-card-text>
        <v-card-actions>
          <v-spacer />
          <v-btn color="#FB8C00" dark @click="handleAdminSignin">เข้าสู่ระบบในฐานะผู้ดูแล</v-btn>
          <v-btn color="#FB8C00" dark @click="signin">Login</v-btn>
        </v-card-actions>
      </v-card>
    </v-col>
  </v-row>
</template>

<script>
import api from "../Api.js";

export default {
  name: "sigin",
  data() {
    return {
      
      username: undefined,
      password: undefined
    };
  },
  methods: {
    handleAdminSignin() {
      this.$router.push("/admin/signin");
    },
    signin() {
      let user = {
        studentId: this.username,
        identifyNumber: this.password
      };
      api
        .post("/auth/login", JSON.stringify(user))
        .then(res => {
          let user = res.data;
          //TODO: Katalon Recorder ไม่ตรวจจับ alert() เลยเอาออก
          // alert("Signin successfully..." + user.username); alert ไม่ผ่าน
          // confirm("login OK" + user.fullName); alert ผ่านแต่ assert ไม่เจอ
          localStorage.setItem("user", JSON.stringify(user));
          this.$router.go("/home");
        })
        .catch(e => {
          alert(e);
        });
    }
  },
  mounted() {
    if (localStorage.getItem("user")) {
      this.$router.push("/candidateDetail");
    }
  }
};
</script>

<style></style>