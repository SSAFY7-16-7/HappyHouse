<template>
  <div class="card mb-4">
    <div class="card-haeder pb-0">
      <h6>{{ category }}</h6>
    </div>
    <div class="card-box px-0 pt-0 pb-2">
      <table class="table align-items-center mb-0">
        <thead>
          <tr>
            <th
              class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7"
            >
              제목
            </th>
            <th
              class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2"
            >
              내용
            </th>
            <th
              class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7"
            >
              날짜
            </th>
            <th
              class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7"
            >
              작성자
            </th>
          </tr>
        </thead>
        <tbody>
          <boardlist-item></boardlist-item>
          <boardlist-item></boardlist-item>
          <boardlist-item></boardlist-item>
          <boardlist-item></boardlist-item>
          <boardlist-item></boardlist-item>
          <boardlist-item></boardlist-item>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import BoardlistItem from "@/components/Board/BoardlistItem.vue";
export default {
  props: ["category"],
  components: {
    BoardlistItem,
  },
};
</script>

<style></style>
