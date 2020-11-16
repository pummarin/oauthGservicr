<template>
  <v-container>
    <div>
      <v-row>
        <v-col
          cols="12"
          md="6"
          sm="6"
          v-for="i in candidate"
          v-bind:key="i.can_id"
        >
          <v-card width="700" height="auto">
            <v-card-title primary-title>
              <font size="auto">หมายเลขผู้สมัคร: {{ i.c_number }}</font>
            </v-card-title>

            <v-card-text class="text-center">
              <v-img v-if="photos[29]" :src="photos[29].download_url"></v-img>
              <v-progress-circular
                v-if="!photos[29]"
                indeterminate
                color="primary"
              ></v-progress-circular>
            </v-card-text>

            <v-card-text>
              <font color="black" >
                <pre>ชื่อ-นามสกุล: {{i.title_name}}{{ i.c_name }}</pre>
                <pre>Gpax: {{ i.grade }}</pre>
                <pre>กิจกรรมที่เข้าร่วม: {{ i.archivement }}</pre>
              </font>
            </v-card-text>
            <v-card-text>
              <font color="blue">
                <pre><h1>คะแนนที่ได้: {{i.points}} </h1> </pre>
              </font>
            </v-card-text>
          </v-card>
        </v-col>
      </v-row>
    </div>
  </v-container>
</template>

<script>
import Axios from "axios";
import Api from "../Api";
export default {
  name: "Vote",
  data() {
    return {
      photos: [],
      candidate: [],
    };
  },
  methods: {
    async getPhotos() {
      this.photos = await Axios.get("https://picsum.photos/v2/list").then(
        (Response) => {
          // console.log(Response.data);
          // this.photos = Response.data;
          return Response.data;
        }
      );
    },
    async getAllCandidate() {
      //   const student = await JSON.parse(localStorage.getItem("user"));
      // console.log(student.s_year)
      await Api.get("/api/canprofile")
        .then((response) => {
          this.candidate = response.data;
          console.log(JSON.parse(JSON.stringify(response.data)));
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },
  mounted() {
    this.getPhotos();
    this.getAllCandidate();
  },
};
</script>

<style>
h1 {
  font-size: 20px;
}
</style>