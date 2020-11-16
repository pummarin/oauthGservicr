
<template>
  <v-col cols="12" md="5" sm="6">
    <v-alert prominent type="success" dismissible v-model="alertSuccess"
      ><v-row align-center>
        <v-col class="grow"> ลงคะแนนสำเร็จ... </v-col>
        <v-col class="shrink">
          <v-btn text>Go ot home page</v-btn>
        </v-col>
      </v-row></v-alert
    >
    <div>
      <div>
        <v-container fluid grid-list-md>
          <v-col v-for="i in candidate" v-bind:key="i.can_id">
            <v-card width="650" height="auto" v-if="loaded">
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
                <font color="black">
                  <pre>ชื่อ-นามสกุล: {{ i.c_name }}</pre>
                  <pre>Gpax: {{ i.grade }}</pre>
                </font>
              </v-card-text>

              <v-card-actions>
                <div>
                  <v-btn
                    class="ma-2"
                    outlined
                    color="indigo"
                    dark
                    @click="save2(i)"
                    >ลงคะแนน</v-btn
                  >
                </div>
              </v-card-actions>
            </v-card>
          </v-col>
          <!-- <v-btn x-large class="ma-2" outlined color="error" dark
                >ไม่ประสงค์ลงคะแนน</v-btn
              > -->
        </v-container>
      </div>
    </div>
  </v-col>
</template>

<script>
import Axios from "axios";
import Api from "../Api";
import sha256 from 'crypto-js/sha256';
import CryptoJS from 'crypto-js';

export default {
  name: "Vote",
  data() {
    return {
      loaded: false,
      photos: [],
      candidate: [],
      alertSuccess: false,
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

    clearAlert() {
      this.alertSuccess = false;
    },
    async getAllCandidate() {
      const student = await JSON.parse(localStorage.getItem("user"));
      // console.log(student.s_year)
      await Api.get(`/api/canprofile2?year=${student.s_year}`)
        .then((response) => {
          this.candidate = response.data;
          // console.log(JSON.parse(JSON.stringify(response.data)));
          // for(let i in this.candidate){
          //   console.log(i);
          // }
        })
        .catch((e) => {
          console.log(e);
        });
    },
    async save2(c) {
      if (confirm("Are you sure ?")) {
        const student = await JSON.parse(localStorage.getItem("user"));
        // console.log(student);
        var hashes = {
          can_id: c.can_id,
          students_id: student.id,
        };
        var hash_result = sha256(JSON.stringify(hashes));
        // console.log(hash_result.toString(CryptoJS.enc.Hex));
        let data = {
          can_id: c.can_id,
          students_id: student.id,
          hash: hash_result.toString(CryptoJS.enc.Hex),
        };
        console.log(data);
        Api.post("/api/vote/new", data)
          .then(() => {
            this.alertSuccess = true;
            setTimeout(() => {
              this.$router.push("/candidateDetail");
            }, 1000);
          })
          .catch((e) => {
            console.log(e);
          });
      }
      // console.log(c);
    },
    checkStudentAlreadyCandidate(){
      let user = JSON.parse(localStorage.getItem("user"));
      let body = {
        studentId: user.studentId
      }
      Api.post("/api/canp/student",JSON.stringify(body))
        .then((res) => {
          if(res.data === true){
            alert("นักศึกษาเป็นผู้ลงสมัคร");
            this.$router.push("/candidateDetail");
          }else{
            this.loaded = true;
          }
        })
        .catch((e) => {
          console.log(e);
        })
    },
  },
  mounted() {
    this.getPhotos();
    this.clearAlert();
    this.getAllCandidate();
    let user = JSON.parse(localStorage.getItem("user"));
      let body = {
        student_id: user.id,
      };
      // console.log(user.id);
      Api.post("/api/vote/student", JSON.stringify(body))
        .then((res) => {
          // console.log(res.data)
          if (res.data === true) {
            alert("นักศึกษาลงคะแนนไปแล้ว");
            
            this.$router.push("/candidateDetail");
            // this.$router.go();
          }else{
            this.checkStudentAlreadyCandidate();            
          } 
        })
        .catch((e) => {
          console.log(e);
        });

  },
};
</script>

<style>
</style>