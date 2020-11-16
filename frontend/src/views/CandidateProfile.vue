<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex xs3 sm6 md9 lg12>
        <v-row align="center" justify="center">
          <v-col cols="12" sm="8" md="6">
            <v-alert type="success" dismissible v-model="alertSuccess">บันทึกข้อมูลสำเร็จ</v-alert>
            <v-alert type="error" dismissible v-model="alertFailed">{{alertmsg}}!</v-alert>
          </v-col>
        </v-row>
        <br />
        <h1 class="display-2 font-weight-bold mb-3">ข้อมูลผู้สมัครสมาชิกสภานักศึกษา</h1>


          <v-row justify="center">
        <v-col cols="12" sm="6" md="2">
          <v-text-field
            label="หมายเลขผู้สมัคร"
            placeholder=" "
            outlined
            v-model="fillCanNumber"
          ></v-text-field>
        </v-col>
        </v-row>

          <v-row justify="center">
        <v-col cols="12" sm="6" md="2">
          <v-text-field
            label="คำนำหน้าชื่อ"
            placeholder=" "
            outlined
            v-model="fillTitleName"
          ></v-text-field>
        </v-col>

        
        <v-col cols="12" sm="6" md="4">
          <v-text-field
            label="ชื่อ-สกุล"
            placeholder=" "
            outlined
            v-model="fillName"
          ></v-text-field>
        </v-col>
        </v-row>

        <v-row justify="center">
         <v-col cols="6" sm="2">
            <v-select class="pa-0 ma-0" label="เพศ" v-model="selectGender"
              :items="genders" item-text="gender" item-value="gender_id" />
          </v-col>
        
        <v-col cols="12" sm="6" md="2">
          <v-text-field
            label="วัน-เดือน-ปีเกิด"
            placeholder=" "
            outlined
            v-model="fillBirthday"
          ></v-text-field>
        </v-col>

        <v-col cols="12" sm="6" md="2">
          <v-text-field
            label="เบอร์โทรศัพท์"
            placeholder=" "
            outlined
            v-model="fillTelephone"
          ></v-text-field>
          </v-col>
        </v-row>

        <v-row justify="center">
        <v-col cols="12" sm="6" md="2">
          <v-text-field
            label="รหัสนักศึกษา"
            placeholder=" "
            outlined
            v-model="fillStudentId"
          ></v-text-field>
        </v-col>

        <v-col cols="12" sm="6" md="2">
          <v-text-field
            label="ชั้นปี"
            placeholder=" "
            outlined
            v-model="fillYear"
          ></v-text-field>
        </v-col>

            <v-col cols="12" sm="6" md="2">
          <v-text-field
            label="คะแนนเฉลี่ยสะสม(GPAX)"
            placeholder=" "
            outlined
            v-model="fillGrade"
          ></v-text-field>
        </v-col>
        </v-row>

          <v-row justify="center">
          <v-col cols="6">
            <v-select class="pa-0 ma-0" label="สำนักวิชา-สาขาวิชา" v-model="selectMajor"
              :items="majors" item-text="major" item-value="major_id" />
          </v-col>
          </v-row>

        <v-row justify="center">
        <v-col cols="12" sm="6" md="6">
          <v-text-field
            label="กิจกรรมที่เคยร่วม"
            placeholder=" "
            outlined
            v-model="fillArchivement"
          ></v-text-field>
        </v-col>
        </v-row>

        <v-row justify="center">
        <v-col cols="12" sm="6" md="6">
          <v-text-field
            label="จุดมุ่งหมายในการสมัครครั้งนี้"
            placeholder=" "
            outlined
            v-model="fillPurpose"
          ></v-text-field>
        </v-col>
        </v-row>

        <!-- <div>
        <v-row justify="center">
        <v-col cols="12" sm="6" md="6">
        <v-file-input 
          :rules="rules"
          accept="image/png, image/jpeg, image/bmp"
          v-model="files"
          color="deep-purple accent-4"
          
          label="รูปภาพ"
          placeholder="  "
          prepend-icon="mdi-camera"
          outlined :show-size="6"
        ></v-file-input>
        </v-col>
        </v-row>
        <<div-->

           
          <v-row justify="center">
          <v-col cols="6">
            <v-select
              class="pa-0 ma-0"
              label="ผู้กรอกข้อมูล"
              v-model="selectAdmin"
              :items="admins"
              item-text="name"
              item-value="admin_id"
            />
          </v-col>
        </v-row>
       

        <v-row justify="center">
          <v-col cols="6" class="pa-2 mx-3">
            <v-btn @click="checksave" class="light-blue accent-4">บันทึก</v-btn>
            &nbsp;
            <v-btn class="light-red accent-4" router-link to="/">ยกเลิก</v-btn>
         
          </v-col>
         </v-row>

      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import api from "../Api.js";
export default {

  mounted() {
    this.getAllAdmin();
    this.getAllGender();
    this.getAllMajor();
   
  },
  data() {
    return {
    
        fillTitleName: undefined,
        fillName: undefined,
        fillBirthday: undefined,
        fillTelephone: undefined,
        fillStudentId: undefined,
        fillYear: undefined,
        fillGrade: undefined,
        fillArchivement: undefined,
        fillCanNumber: undefined,
        fillPurpose: undefined,

        majors: [],
        selectMajor: undefined,

        genders: [],
        selectGender: undefined,
        
        admins: [],
        selectAdmin: undefined,

      
        //files: [],
        
        alertFailed: false,
        alertSuccess: false,
        alertmsg: undefined
    };
  },
  
  methods: {
    getAllAdmin() {
      api
        .get("/api/admins/")
        .then(response => {
          this.admins = response.data;
          console.log(JSON.parse(JSON.stringify(response.data)));
        })
        .catch(e => {
          console.log(e);
        });
      },

    getAllGender() {
        api
        .get("/gender/")
        .then(response => {
          this.genders = response.data;
          console.log(JSON.parse(JSON.stringify(response.data)));
        })
        .catch(e => {
          console.log(e);
        });
    },
    getAllMajor() {
        api
        .get("/major/")
        .then(response => {
          this.majors = response.data;
          console.log(JSON.parse(JSON.stringify(response.data)));
        })
        .catch(e => {
          console.log(e);
        });
    },
       clearAlert() {
        this.alertmsg = false;
        this.alertFailed = false;
        this.alertSuccess = false;
      
    },
    checksave() {
      let data = {
        title_name: this.fillTitleName,
        c_name: this.fillName,
        birthday: this.fillBirthday,
        telephone: this.fillTelephone,
        student_id: this.fillStudentId,
        year: this.fillYear,
        grade: this.fillGrade,
        archivement: this.fillArchivement,
        c_number: this.fillCanNumber,
        purpose: this.fillPurpose,

       
        major_id: this.selectMajor,
        gender_id: this.selectGender,
        admin_id: this.selectAdmin
      };
      console.log(data);
      api
        .post("/api/canp", data)
        .then(() => {
          this.clearAlert();
          this.alertSuccess = true;

          this.selectAdmin = null;
          this.selectMajor = null;
          this.selectGender = null;

          this.fillTitleName = null;
          this.fillName = null;
          this.fillBirthday = null;
          this.fillTelephone = null;
          this.fillStudentId = null;
          this.fillYear = null;
          this.fillGrade = null;
          this.fillArchivement = null;
          this.fillCanNumber = null;
          this.fillPurpose = null;

        })
        .catch(e => {
          console.log(e);
        });
    },
    save() {
      
      if (
        !this.selectAdmin ||
        !this.selectMajor ||
        !this.selectGender ||
        !this.fillTitleName ||
        !this.fillName ||
        !this.fillBirthday ||
        !this.fillTelephone ||
        !this.fillStudentId ||
        !this.fillYear ||
        !this.fillGrade ||
        !this.fillCanNumber ||
        !this.fillPurpose ||
        !this.fillArchivement
      ) {
        this.clearAlert();
        this.alertmsg = "กรุณากรอกข้อมูลให้ครบ";
        this.alertFailed = true;
      } else {
        this.checksave();
      }
    }
  }
};

</script>