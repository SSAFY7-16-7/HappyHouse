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
import { BUS } from "@/store/modules/EventBus";
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
    BUS.$on(
      "deleteLike",
      function (code) {
        console.log("delete", this.category, code);
        if (this.category === "apt") {
          this.list.forEach((item, index) => {
            if (item.aptCode === code) {
              this.list.splice(index, 1);
              return;
            }
          });
        } else {
          this.list.forEach((item, index) => {
            if (item.idx === code) {
              this.list.splice(index, 1);
              return;
            }
          });
        }
      }.bind(this)
    );
    BUS.$on(
      "addLike",
      function (house) {
        console.log("addlike");
        console.log(house);
        this.list.push(house);
      }.bind(this)
    );
  },
};
</script>

<style></style>
