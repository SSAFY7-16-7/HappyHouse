<template>
  <div>
    <h2 class="text-center">SSAFY 도서 정보</h2>
    <div>
      <table class="table table-bordered">
        <thead></thead>
        <tbody>
          <tr>
            <td>제목</td>
            <td v-text="book.isbn"></td>
          </tr>
          <tr>
            <td>catalogue</td>
            <td v-text="book.catalogue"></td>
          </tr>
          <tr>
            <td>국가</td>
            <td v-text="book.nation"></td>
          </tr>
          <tr>
            <td>출판일</td>
            <td v-text="book.publish_date"></td>
          </tr>
          <tr>
            <td>출판사</td>
            <td v-text="book.publisher"></td>
          </tr>
          <tr>
            <td>저자</td>
            <td v-text="book.author"></td>
          </tr>
          <tr>
            <td>가격</td>
            <td v-text="book.price"></td>
          </tr>
          <tr>
            <td>화폐</td>
            <td v-text="book.currency"></td>
          </tr>
          <tr>
            <td>설명</td>
            <td v-text="book.description"></td>
          </tr>
          <tr>
            <td colspan="2">
              <div class="text-center">
                <button class="btn btn-primary" @click="movePage">목록</button>
                <!-- <router-link :to="'/updatebook?isbn=' + isbn"> -->
                <button class="btn btn-primary" @click="updateHandler">
                  수정
                </button>
                <!-- </router-link> -->
                <button class="btn btn-primary" @click="removeHandler">
                  삭제
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
import { mapActions, mapGetters } from "vuex";

export default {
  data() {
    return {
      isbn: "",
    };
  },
  created() {
    this.isbn = this.$route.query.isbn;
    this.getBook(this.isbn);
  },
  computed: {
    ...mapGetters(["book"]),
  },
  methods: {
    ...mapActions(["getBook"]),
    movePage() {
      this.$router.push({ name: "BookList" });
    },
    updateHandler() {
      this.$router.push({ name: "BookModify", query: { isbn: this.isbn } });
      // location.href = `modify.html?isbn=${this.isbn}`;
    },
    removeHandler() {
      http
        .delete(`/book/${this.isbn}`)
        .then((response) => {
          if (response.data == "success") {
            alert("삭제 성공");
            this.movePage();
          } else {
            alert("삭제 실패");
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
