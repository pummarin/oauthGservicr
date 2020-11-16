<template>
  <v-row align="center" justify="center">
    <v-col cols="12" sm="8" md="6">
      <v-card class="elevation-12">
        <v-toolbar color="#F16529" dark>
          <v-toolbar-title>เข้าสู่ระบบในฐานะผู้ดูแล</v-toolbar-title>
          <v-spacer />
        </v-toolbar>
        <v-card-text>
          <v-form>
                  <v-row justify="center">
              <v-col cols="12" sm="6" md="10">
            <v-text-field
              label="USERNAME"
              placeholder=" "
              outlined
              type="text"
              v-model="username"
            />

            <v-text-field
              label="PASSWORD"
              placeholder=" "
              outlined
              type="password"
              v-model="password"
              @keyup.enter="signin"
            /> 
            
            </v-col>
             </v-row>
          </v-form>
        </v-card-text>
        <v-card-actions>
          <v-spacer />
          <v-btn color="#FB8C00" dark @click="handleAdminSignin">เข้าสู่ระบบสำหรับที่นักศึกษา</v-btn>
          
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
      this.$router.push("/signin");
    },

    signin() {
          let user = {
        username: this.username,
        password: this.password
      };
      api
        .post("/auth/admin/signin", JSON.stringify(user))
        .then(res => {
          let user = res.data;
          // alert("Signin successfully..." + user.username);
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
      this.$router.push("/home");
    }
  }
};
</script>

<style></style>
