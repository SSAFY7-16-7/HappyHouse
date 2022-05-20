<template>
  <div>
    <div class="row">
      <div class="col-2"></div>
      <div class="col-2">
        <select class="form-control" v-model="key">
          <option value="all">선택</option>
          <option v-for="(k, index) in keys" :value="k.value" :key="index">
            {{ k.value }}
          </option>
        </select>
      </div>
      <div class="col-4">
        <input class="form-control" type="text" v-model="word" />
      </div>
      <button
        class="col-2 btn btn-primary"
        @click="searchQna"
        style="margin-top: 0px"
      >
        검색
      </button>
    </div>
    <div v-if="qnas.length > 0">
      <table class="table table-bordered table-hover">
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
</template>

<script>
import http from "@/api/http";

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
  methods: {
    /* goDetail(item) {}, */
    searchQna() {
      const url = `qna/list?key=${this.key}&pageNo=1&word=${this.word}`;

      http
        .get(url)
        .then((response) => {
          console.log(response);
          this.qnas = response.data;
        })
        .catch((response) => {
          console.log(response.data);
        });
    },
    goDetail(idx) {
      this.$router.push({
        name: "QnaDetail",
        query: { idx: idx },
      });
    },
  },
};
</script>

<style>
.row {
  margin-bottom: 50px;
}
</style>
