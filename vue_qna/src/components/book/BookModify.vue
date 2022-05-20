<template>
  <div>
    <h2 class="text-center">도서 정보 수정</h2>
    <div>
      <table class="table table-bordered">
        <thead></thead>
        <tbody>
          <tr>
            <td>책 번호</td>
            <td>
              <input type="text" id="isbn" ref="isbn" v-model="book.isbn" />
            </td>
          </tr>
          <tr>
            <td>제목</td>
            <td>
              <input type="text" id="title" ref="title" v-model="book.title" />
            </td>
          </tr>
          <tr>
            <td>catalogue</td>
            <td>
              <input
                type="text"
                id="catalogue"
                ref="catalogue"
                v-model="book.catalogue"
              />
            </td>
          </tr>
          <tr>
            <td>국가</td>
            <td>
              <input
                type="text"
                id="nation"
                ref="nation"
                v-model="book.nation"
              />
            </td>
          </tr>
          <tr>
            <td>출판일</td>
            <td>
              <input
                type="text"
                id="publish_date"
                ref="publish_date"
                v-model="book.publish_date"
              />
            </td>
          </tr>
          <tr>
            <td>출판사</td>
            <td>
              <input
                type="text"
                id="publisher"
                ref="publisher"
                v-model="book.publisher"
              />
            </td>
          </tr>
          <tr>
            <td>저자</td>
            <td>
              <input
                type="text"
                id="author"
                ref="author"
                v-model="book.author"
              />
            </td>
          </tr>
          <tr>
            <td>가격</td>
            <td>
              <input type="text" id="price" ref="price" v-model="book.price" />
            </td>
          </tr>
          <tr>
            <td>화폐</td>
            <td>
              <input
                type="text"
                id="currency"
                ref="currency"
                v-model="book.currency"
              />
            </td>
          </tr>
          <tr>
            <td>설명</td>
            <td>
              <input
                type="text"
                id="description"
                ref="description"
                v-model="book.description"
              />
            </td>
          </tr>
          <tr>
            <td colspan="2">
              <div class="text-center">
                <button class="btn btn-primary" @click="updateHandler">
                  수정
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
import { mapActions, mapGetters } from "vuex";

export default {
  data() {
    return {
      isbn: "",
    };
  },
  computed: {
    ...mapGetters(["book"]),
  },
  created() {
    this.isbn = this.$route.query.isbn;

    this.getBook(this.isbn);
  },
  methods: {
    ...mapActions(["getBook"]),
    moveHandler() {
      this.$router.push({ name: "BookList" });
    },
    updateHandler() {
      //데이터 검증
      let err = false;
      let msg = "";

      !this.book.isbn &&
        ((msg = "책 번호를 입력해주세요"),
        (err = true),
        this.$refs.isbn.focus());
      !err &&
        !this.book.title &&
        ((msg = "제목을 입력해주세요"), (err = true), this.$refs.title.focus());
      !err &&
        !this.book.author &&
        ((msg = "저자를 입력해주세요"),
        (err = true),
        this.$refs.author.focus());
      !err &&
        !this.book.price &&
        ((msg = "가격을 입력해주세요"), (err = true), this.$refs.price.focus());

      if (err) {
        alert(msg);
      } else {
        //수정 처리
        http
          .put("/book", this.book)
          .then((response) => {
            if (response.data == "success") {
              alert("수정 성공");
              this.$router.push({
                path: "/book/bookdetail",
                query: { isbn: this.isbn },
              });
            } else {
              alert("수정 실패");
            }
          })
          .catch((error) => {
            alert(error);
          });
      }
    },
  },
};
</script>

<style></style>
