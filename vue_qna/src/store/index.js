import Vue from "vue";
import Vuex from "vuex";
import http from "@/api/http";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    books: [], //전체도서 정보
    book: {}, //isb에 해당하는 도서 정보
    searchs: [],
  },
  getters: {
    books(state) {
      return state.books;
    },
    book(state) {
      return state.book;
    },
    searchs(state) {
      return state.searchs;
    },
  },
  mutations: {
    setBooks(state, payload) {
      state.books = payload;
    },
    setBook(state, payload) {
      state.book = payload;
    },
    setSearchs(state, payload) {
      state.searchs = payload;
    },
  },

  actions: {
    getSearchs({ commit, state }, payload) {
      console.log("[action]getSearchs....", payload);
      let newBooks = [];

      if (payload.key == "isbn") {
        for (let i = 0; i < state.books.length; i++) {
          if (state.books[i].isbn == payload.word) {
            newBooks.push(state.books[i]);
          }
        }
      } else if (payload.key == "author") {
        for (let i = 0; i < state.books.length; i++) {
          if (state.books[i].author == payload.word) {
            newBooks.push(state.books[i]);
          }
        }
      } else if (payload.key == "title") {
        for (let i = 0; i < state.books.length; i++) {
          if (state.books[i].title.indexOf(payload.word) >= 0) {
            newBooks.push(state.books[i]);
          }
        }
      } else if (payload.key == "all") {
        newBooks = state.books;
      }

      console.log("searchBooks.....", newBooks);
      commit("setSearchs", newBooks);
    },
    getBooks(context) {
      console.log("[action] getBooks............");
      http
        .get("book")
        .then(({ data }) => {
          console.log("books..........", data);
          context.commit("setBooks", data);
          context.commit("setSearchs", data);
        })
        .catch(({ response }) => {
          alert(response.data);
        });
    },
    getBook(context, payload) {
      console.log(payload);
      http
        .get("book/" + payload)
        .then(({ data }) => {
          console.log("books..........", data);
          context.commit("setBook", data);
        })
        .catch((response) => {
          alert(response);
        });
    },
  },
  modules: {},
});
