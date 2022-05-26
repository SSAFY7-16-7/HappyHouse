<template>
  <div style="padding: 30px 400px">
    <h2 class="text-center" style="padding-bottom: 30px">질문 등록</h2>
    <div>
      <table class="table" style="table-layout: fixed">
        <thead></thead>
        <tbody>
          <tr>
            <td style="font-weight: bold; width: 10%">제목</td>
            <td>
              <textarea
                class="form-control"
                type="text"
                id="title"
                ref="title"
                v-model="qna.title"
                style="height: 150px"
              />
            </td>
          </tr>
          <tr>
            <td style="font-weight: bold">내용</td>
            <td>
              <textarea
                class="form-control"
                type="text"
                id="content"
                ref="content"
                v-model="qna.content"
                style="height: 150px"
              />
            </td>
          </tr>

          <tr>
            <td colspan="2">
              <div class="text-center">
                <button
                  v-if="this.key == 'modify'"
                  class="btn btn-primary"
                  @click="modifyHandler"
                >
                  수정
                </button>
                <button
                  v-if="this.key != 'modify'"
                  class="btn btn-primary"
                  @click="qRegistHandler"
                >
                  등록
                </button>
                <button class="btn btn-secondary" @click="moveHandler">
                  목록
                </button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import { apiInstance } from "@/api/index.js";
import { mapState } from "vuex";
const http = apiInstance();

export default {
  data() {
    return {
      qna: {
        userid: "",
        title: "",
        content: "",
      },
      key: "",
      // userid: "",
    };
  },
  computed: {
    ...mapState("memberStore", ["userInfo"]),
  },
  created() {
    // this.userid = sessionStorage.getItem("sessionid");
    // this.qna.userid = this.userid;
    this.idx = this.$route.query.idx;
    if (this.idx != null) {
      this.key = "modify";
      this.getDetail(this.idx);
    }
  },
  methods: {
    moveHandler() {
      this.$router.push({ name: "qna" });
    },
    getDetail() {
      const url = `/qna/detail/${this.idx}`;

      http
        .get(url)
        .then((response) => {
          //console.log(response);
          this.qna = response.data;
        })
        .catch((response) => {
          //console.log(response.data);
        });
    },
    qRegistHandler() {
      //console.log("userid:", this.userInfo.id);
      this.qna.userid = this.userInfo.id;
      //데이타 검증
      let err = false;

      let msg = "";

      //console.log(this.qna);
      !this.qna.title &&
        ((msg = "제목을 입력해 주세요"),
        (err = true),
        this.$refs.title.focus());
      !err &&
        !this.qna.content &&
        ((msg = "내용을 입력해 주세요"),
        (err = true),
        this.$refs.content.focus());

      if (err) {
        alert(msg);
      } else {
        // 등록 처리
        http
          .post("/qna/qregist", this.qna)
          .then((response) => {
            if (response.data == "success") {
              alert("등록 성공");
              this.moveHandler();
            } else {
              alert("등록 실패");
            }
          })
          .catch((error) => {
            alert(error);
          });
      }
    },
    modifyHandler() {
      http
        .put("/qna/modify", this.qna)
        .then((response) => {
          if (response.data == "success") {
            alert("수정 성공");
            this.moveHandler();
          } else {
            alert("수정 실패");
          }
        })
        .catch((error) => {
          alert(error);
        });
    },
  },
};
</script>

<style></style>
