<template>
  <div>
    <h2 class="text-center">SSAFY 도서 등록</h2>
    <div>
      <table class="table">
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
            <td>분류</td>
            <td>
              <input type="text" name="catalogue" v-model="book.catalogue" />
            </td>
          </tr>
          <tr>
            <td>국가</td>
            <td><input type="text" name="nation" v-model="book.nation" /></td>
          </tr>
          <tr>
            <td>출판일</td>
            <td>
              <input
                type="text"
                name="publish_date"
                v-model="book.publish_date"
              />
            </td>
          </tr>
          <tr>
            <td>출판사</td>
            <td>
              <input type="text" name="publisher" v-model="book.publisher" />
            </td>
          </tr>
          <tr>
            <td>화폐단위</td>
            <td>
              <input type="text" name="currency" v-model="book.currency" />
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
            <td>내용</td>
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
                <button class="btn btn-primary" @click="createHandler">
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
      book: {
        author: "",
        catalogue: "",
        currency: "",
        description: "",
        isbn: "",
        nation: "",
        price: 0,
        publish_date: "",
        publisher: "",
        title: "",
      },
    };
  },
  methods: {
    moveHandler() {
      this.$router.push({ name: "BookList" });
    },
    createHandler() {
      //데이타 검증
      let err = false;

      let msg = "";

      !this.book.isbn &&
        ((msg = "책번호를 입력해 주세요"),
        (err = true),
        this.$refs.isbn.focus());
      !err &&
        !this.book.title &&
        ((msg = "제목를 입력해 주세요"),
        (err = true),
        this.$refs.title.focus());
      !err &&
        !this.book.author &&
        ((msg = "저자를 입력해 주세요"),
        (err = true),
        this.$refs.author.focus());
      !err &&
        !this.book.price &&
        ((msg = "가격를 입력해 주세요"),
        (err = true),
        this.$refs.price.focus());

      if (err) {
        alert(msg);
      } else {
        //등록 처리
        //localstorage에 이미 저장된 책 목록이 있는지 검사
        http
          .post("/book", this.book)
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
  },
};
</script>

<style></style>
