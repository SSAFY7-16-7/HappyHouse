<template>
  <div>
    <div class="qna-header">
      <h2 class="text-center">QNA</h2>
      <div class="header-btn-div">
        <button class="btn btn-primary" @click="qnaRegist" style="width: 80px">
          글쓰기
        </button>
        <button class="btn btn-secondary" @click="goList">목록</button>
      </div>
    </div>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  data() {
    return {
      sessionid: "",
    };
  },
  created() {
    this.sessionid = sessionStorage.getItem("userid");
  },
  methods: {
    qnaRegist() {
      this.$router.push({ name: "QnaRegist" });
    },
    goList() {
      this.$router.push({ name: "QnaList" });
    },
  },
};
</script>

<style>
.qna-header {
  padding: 80px;
}
.header-btn-div {
  display: flex;
  justify-content: flex-end;
}
</style>
