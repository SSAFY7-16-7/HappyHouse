<template>
  <div>
    <board-list category="공지사항 게시판"></board-list>
  </div>
</template>

<script>
const body = document.getElementsByTagName("body")[0];
import BoardList from "@/components/Board/BoardList.vue";
import { mapMutations } from "vuex";
export default {
  components: {
    BoardList,
  },
};
</script>

<style></style>
