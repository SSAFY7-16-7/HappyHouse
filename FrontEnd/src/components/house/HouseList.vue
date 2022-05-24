<template>
  <div v-if="houses && houses.length != 0">
    <house-list-item
      v-for="(house, index) in houses"
      :key="index"
      :house="house"
    />
  </div>
  <div v-else>
    <h4>주택 목록이 없습니다.</h4>
  </div>
</template>

<script>
import HouseListItem from "@/components/house/HouseListItem.vue";
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseList",
  components: {
    HouseListItem,
  },
  computed: {
    ...mapState(houseStore, ["houses"]),
  },
};
</script>

<style></style>
