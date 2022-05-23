<template>
  <div v-if="list.length > 0">
    <interest-item
      @deleteLkie="deleteItem"
      v-for="(item, index) in list"
      :key="index"
      :item="item"
      :category="category"
      :index="index"
    ></interest-item>
  </div>
  <div v-else>찜 목록이 비었습니다.</div>
</template>

<script>
import InterestItem from "./InterestItem.vue";
export default {
  name: "InterestList",
  components: {
    InterestItem,
  },
  props: ["list", "category"],
  data() {
    return {};
  },
  methods: {
    deleteItem(index) {
      this.list.splice(index, 1);
    },
  },

  mounted() {
    // console.log(this.list);
  },
};
</script>

<style></style>
