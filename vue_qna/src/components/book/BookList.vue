<template>
  <div>
    <h2 class="text-center">SSAFY 도서 목록</h2>
    <div class="row">
      <div class="col-2"></div>
      <div class="col-2">
        <select class="form-control" v-model="key">
          <option value="all">--선택하세요</option>
          <option v-for="(k, index) in keys" :value="k" :key="index">
            {{ k }}
          </option>
        </select>
      </div>
      <div class="col-4">
        <input class="form-control" type="text" v-model="word" />
      </div>
      <button class="col-2 btn btn-primary" @click="searchBook">검색</button>
      <div class="col-2"></div>
    </div>
    <div>
      <table class="table table-bordered table-hover">
        <colgroup>
          <col width="15%" />
          <col width="20%" />
          <col width="30%" />
          <col width="20%" />
          <col width="15%" />
        </colgroup>
        <thead>
          <tr>
            <th>번호</th>
            <th>ISBN</th>
            <th>제목</th>
            <th>저자</th>
            <th>가격</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(book, index) in searchs" :key="book.isbn">
            <td>{{ index + 1 }}</td>
            <td>
              <router-link :to="'/book/bookdetail?isbn=' + book.isbn">{{
                book.isbn
              }}</router-link>
            </td>
            <td v-text="book.title"></td>
            <td v-text="book.author"></td>
            <td v-text="book.price"></td>
          </tr>
        </tbody>
      </table>
    </div>
    <!-- <div v-else>등록 된 책 목록이 없습니다.</div> -->
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";

export default {
  data() {
    return {
      word: "", //검색 할 단어
      keys: ["isbn", "title", "author"], //option에 표시할 데이타
      key: "all", //검색 조건
    };
  },
  computed: {
    ...mapGetters(["searchs"]),
  },
  created() {
    this.getBooks();
  },
  methods: {
    ...mapActions(["getBooks", "getSearchs"]),
    searchBook() {
      this.getSearchs({ key: this.key, word: this.word });
    },
  },
};
</script>

<style></style>
