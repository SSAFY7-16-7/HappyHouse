<template>
  <div style="padding: 0px 437px">
    <hr />
    <div class="qna-div">
      <h4 class="qna-type">Question</h4>
      <div>
        <div class="b-padding">
          <h3 v-text="qna.title" sFCardtyle="font-weight: bold" />
        </div>
        <div class="b-padding">
          <h6 v-text="qna.content" />
        </div>
        <div class="q-bottom">
          <div class="q-user">
            <div style="margin-right: 15px">
              <img src="@/assets/img/pyj/profile.png" class="profile" />
            </div>
            <div>{{ qna.userid }}</div>
          </div>
          <div>{{ qna.date }}</div>
        </div>
        <div class="q-button" v-if="isModifiable">
          <button class="btn btn-secondary" @click="updateHandler">수정</button>
          <button class="btn btn-secondary" @click="removeHandler">삭제</button>
        </div>
      </div>
    </div>
    <hr />
    <div class="qna-div">
      <h4 class="qna-type">Answer</h4>
      <div
        :class="{ answerInput: !isActive }"
        v-if="qna.answer == '' || qna.answer == null"
      >
        아직 등록된 답변이 없습니다.
      </div>

      <div :class="{ answerInput: isActive }">
        <textarea
          type="text"
          v-model="qna.answer"
          class="form-controll"
          cols="80"
          rows="10"
        />
        <button
          class="btn btn-secondary"
          style="width: 84px"
          @click="answerRegist"
        >
          완료
        </button>
      </div>

      <div :class="{ answerInput: !isActive }" v-if="qna.answer != null">
        <div class="b-padding">
          <h6 v-text="qna.answer" />
        </div>
        <div class="a-bottom">
          <div>{{ qna.answer_date }}</div>
        </div>
        <div class="q-button" v-if="isAdmin">
          <button class="btn btn-secondary" @click="openInput">수정</button>
          <button class="btn btn-secondary" @click="answerRemove">삭제</button>
        </div>
      </div>
      <div
        class="q-button"
        :class="{ answerInput: !isActive }"
        v-if="qna.answer == null && isAdmin"
      >
        <button class="btn btn-secondary" @click="openInput">글쓰기</button>
      </div>
    </div>
    <hr />
    <div class="qna-div text-center">
      <button class="btn btn-info" @click="goList">목록</button>
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
      qna: {},
      idx: "",
      isActive: "",

      //   sessionid: "",
    };
  },
  created() {
    // this.sessionid = sessionStorage.getItem("sessionid");
    this.idx = this.$route.query.idx;
    this.getDetail(this.idx);
    if (this.qna.answer == null) {
      this.isActive = true;
    } else {
      this.isActive = false;
    }
  },
  computed: {
    ...mapState("memberStore", ["isLogin", "userInfo", "isAdmin"]),
    isModifiable() {
      return (
        (this.userInfo && this.userInfo.id === this.qna.userid) || this.isAdmin
      );
    },
  },
  methods: {
    getDetail() {
      const url = `/qna/detail/${this.idx}`;

      http
        .get(url)
        .then((response) => {
          this.qna = response.data;
          console.log("질문", this.qna);
        })
        .catch((response) => {
          console.log(response.data);
        });
    },
    goList() {
      this.$router.push({ name: "qna" });
    },
    updateHandler() {
      this.$router.push({ name: "QnaRegist", query: { idx: this.qna.idx } });
    },
    openInput() {
      this.isActive = !this.isActive;
    },
    answerRegist() {
      http
        .put(`/qna/aregist`, this.qna)
        .then((response) => {
          if (response.data == "success") {
            alert("답변 성공");
          } else {
            alert("답변 실패");
          }
        })
        .catch((error) => {
          alert(error);
        });
      this.isActive = !this.isActive;
    },
    removeHandler() {
      http
        .delete(`/qna/qdelete/${this.idx}`)
        .then((response) => {
          if (response.data == "success") {
            alert("삭제 성공");
            this.goList();
          } else {
            alert("삭제 실패");
          }
        })
        .catch((error) => {
          alert(error);
        });
    },
    answerRemove() {
      http
        .put(`/qna/adelete/${this.qna.idx}`)
        .then((response) => {
          if (response.data == "success") {
            this.qna.answer = null;
            alert("삭제 성공");
          } else {
            alert("삭제 실패");
          }
        })
        .catch((error) => {
          alert(error);
        });
      this.$router.go();
    },
  },
};
</script>

<style>
hr {
  margin: 0 128px 0 128px;
  border: 0;
  border-top: 2px solid rgba(0, 0, 0, 0.1);
}

img.profile {
  width: 46px;
}

.qna-div {
  padding: 20px 76px;
}

h4.qna-type {
  color: #928c8c;
  font-size: 1.3em;
  padding-bottom: 30px;
}

.b-padding {
  padding-bottom: 15px;
}

.a-bottom {
  text-align: end;
}

.q-bottom {
  display: flex;
  justify-content: space-between;
  align-items: flex-end;
  padding-top: 30px;
}

.q-button {
  display: flex;
  justify-content: flex-end;
}

.q-user {
  display: flex;
  align-items: center;
}

.btn {
  margin-top: 30px;
  margin-right: 10px;
}

.answerInput {
  display: none;
}
</style>
