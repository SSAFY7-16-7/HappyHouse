<template>
  <div class="">
    <canvas id="chart" width="350" height="350"></canvas>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  name: "DealChart",
  computed: {
    ...mapState("houseStore", ["deals"]),
  },
  data() {
    return {
      context: null,
    };
  },
  methods: {
    makeData() {
      let labels = [];
      this.deals.forEach((deal) => {
        labels.push({
          x: deal.dealYear + "." + deal.dealMonth,
          y: parseInt(deal.dealAmount.split(",").join("")),
        });
      });
      return labels.reverse(); // 과거 -> 최신순으로 보여주기 위해서 reverse
    },
    makeChart() {
      const myChart = new Chart(this.context, {
        type: "line",
        data: {
          // labels: this.makeLables(this.deals),
          datasets: [
            {
              label: "# of ",
              data: this.makeData(),
              backgroundColor: ["rgba(54, 162, 235, 0.2)"],
              borderColor: ["rgba(75, 192, 192, 1)"],
              borderWidth: 1,
              fill: "start",
            },
          ],
        },
        options: {
          scales: {
            y: {
              beginAtZero: true,
            },
          },
        },
      });
    },
  },
  mounted() {
    //console.log("chart");
    this.context = document.getElementById("chart");

    //console.log(this.context);
    this.makeChart();
  },
};
</script>

<style></style>
