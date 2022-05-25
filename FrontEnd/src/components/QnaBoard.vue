<template>
  <div>
    <div class="container board">
      <div class="row">
        <div class="col-3">
          <select class="form-control" v-model="key">
            <option value="all">선택</option>
            <option v-for="(k, index) in keys" :value="k.value" :key="index">
              {{ k.value }}
            </option>
          </select>
        </div>
        <div class="col-5">
          <input class="form-control" type="text" v-model="word" />
        </div>
        <div class="col-2">
          <button
            class="btn btn-primary"
            @click="searchQna"
            style="display: flex; justify-content: center"
          >
            검색
          </button>
        </div>
        <div class="col-2" style="display: flex; justify-content: center">
          <button v-if="isLogin" class="btn btn-primary" @click="qnaRegist">
            글쓰기
          </button>
        </div>
      </div>
      <div v-if="qnas.length > 0">
        <table class="table table-bordered table-hover" style="width: 55vw">
          <colgroup>
            <col width="10%" />
            <col width="15%" />
            <col width="20%" />
            <col width="30%" />
          </colgroup>
          <thead>
            <tr>
              <th>No</th>
              <th>아이디</th>
              <th>제목</th>
              <th>내용</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(qna, index) in qnas" :key="qna.idx">
              <td>{{ index + 1 }}</td>
              <td v-text="qna.userid"></td>
              <td v-text="qna.title" @click="goDetail(qna.idx)"></td>
              <td v-text="qna.content"></td>
            </tr>
          </tbody>
        </table>
      </div>
      <div v-else>등록된 질문이 없습니다.</div>
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
      qnas: [],
      word: "", //검색 할 단어
      keys: [
        { value: "title", text: "제목" },
        { value: "content", text: "내용" },
        { value: "userid", text: "아이디" },
      ], //option에 표시할 데이타
      key: "all", //검색 조건
    };
  },
  created() {
    this.searchQna();
  },
  computed: {
    ...mapState("memberStore", ["isLogin", "userInfo", "isAdmin"]),
  },
  methods: {
    /* goDetail(item) {}, */
    searchQna() {
      const url = `qna/list?key=${this.key}&pageNo=1&word=${this.word}`;

      http
        .get(url)
        .then((response) => {
          this.qnas = response.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    goDetail(idx) {
      this.$router.push({
        name: "QnaDetail",
        query: { idx: idx },
      });
    },
    qnaRegist() {
      this.$router.push({ name: "QnaRegist" });
    },
  },
};
</script>

<style>
.container.board {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 50px 150px;
}

.container.board .row {
  width: 48vw;
  margin-bottom: 50px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.qna-img {
  z-index: -1;
}
.board {
  width: 80%;
}
</style>
