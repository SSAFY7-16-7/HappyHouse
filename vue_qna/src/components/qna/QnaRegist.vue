<template>
  <div>
    <h2 class="text-center">질문 등록</h2>
    <div>
      <table class="table">
        <thead></thead>
        <tbody>
          <tr>
            <td>제목</td>
            <td>
              <input type="text" id="title" ref="title" v-model="qna.title" />
            </td>
          </tr>
          <tr>
            <td>내용</td>
            <td>
              <input
                type="text"
                id="content"
                ref="content"
                v-model="qna.content"
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
                <button class="btn btn-primary" @click="moveHandler">
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
import http from "@/api/http";

export default {
  data() {
    return {
      qna: {},
      key: "",
      userid: "",
    };
  },
  created() {
    this.userid = sessionStorage.getItem("sessionid");
    this.qna.userid = this.userid;
    this.idx = this.$route.query.idx;
    if (this.idx != null) {
      this.key = "modify";
      this.getDetail(this.idx);
    }
  },
  methods: {
    moveHandler() {
      this.$router.push({ name: "QnaList" });
    },
    getDetail() {
      const url = `/qna/detail/${this.idx}`;

      http
        .get(url)
        .then((response) => {
          console.log(response);
          this.qna = response.data;
        })
        .catch((response) => {
          console.log(response.data);
        });
    },
    qRegistHandler() {
      //데이타 검증
      let err = false;

      let msg = "";

      console.log(this.qna);
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
        //등록 처리
        console.log(this.qna);
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
