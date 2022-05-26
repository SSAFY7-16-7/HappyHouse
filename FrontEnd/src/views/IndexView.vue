<template>
  <div class="main-bg">
    <section class="logo-section">
      <div class="HappyHouse">
        <img src="@/assets/img/pyj/편집 배너.png" style="width: 1300px" />
      </div>
    </section>
    <!-- <section class="main-section0"></section> -->
    <section class="main-section1" style="flex-direction: column">
      <div class="main-info-title">
        <span class="impress">편한</span> 집을
        <span class="impress">편하게</span> 찾는법?
      </div>
      <div class="main-info-icon" style="display: flex">
        <div class="main-info-div">
          <div class="main-info-img-div">
            <img
              src="@/assets/img/icons/house_marker.png"
              alt=""
              class="main-info-img"
            />
          </div>
          <div class="main-info-text">
            <div><h3>지도 검색</h3></div>
            <div>
              <span class="main-info-desc"
                >움직이면 보이는<br />
                실시간 마커로 빠르게!</span
              >
            </div>
          </div>
        </div>
        <div class="main-info-div">
          <div>
            <img
              src="@/assets/img/icons/like.png"
              alt=""
              class="main-info-img"
            />
          </div>
          <div class="main-info-text">
            <div><h3>관심 설정</h3></div>
            <div>
              <span class="main-info-desc"
                >내 취향의 맞는 집은<br />
                바로 저장 꾹~</span
              >
            </div>
          </div>
        </div>

        <div class="main-info-div">
          <div>
            <img
              src="@/assets/img/icons/add.png"
              alt=""
              class="main-info-img"
              style="width: 91px; padding-bottom: 10px"
            />
          </div>
          <div class="main-info-text">
            <div><h3>매물 등록</h3></div>
            <div>
              <span class="main-info-desc"
                >유저들간의 집거래도<br />
                도와줄게요</span
              >
            </div>
          </div>
        </div>
      </div>
    </section>
    <section class="section-list">
      <div class="main-list">
        <div class="sell list-div">
          <div class="list-title">
            <div><h3>매물</h3></div>
            <div>
              <button class="btn btn-secondary" @click="moveSell">
                더보기
              </button>
            </div>
          </div>
          <div class="info-list">
            <div v-for="(item, index) in list" :key="index" v-if="index <= 1">
              <sell-list-item :item="item"></sell-list-item>
            </div>
          </div>
        </div>

        <div class="qna list-div">
          <div class="list-title">
            <div><h3>뉴스</h3></div>
            <div>
              <button class="btn btn-secondary" @click="moveNews">
                더보기
              </button>
            </div>
          </div>
          <div class="info-list">
            <div v-for="(item, index) in news" :key="index" v-if="index <= 1">
              <news-list-item :item="item"></news-list-item>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import { apiInstance } from "@/api/index.js";
import Navbar from "@/components/PageLayout/Navbar.vue";
import AppFooter from "@/components/PageLayout/Footer.vue";
import VsudInput from "@/components/soft-ui-components/VsudInput.vue";
import VsudSwitch from "@/components/soft-ui-components/VsudSwitch.vue";
import VsudButton from "@/components/soft-ui-components/VsudButton.vue";
import { getNews } from "@/api/news.js";
import NewsListItem from "@/components/Information/SellListItem.vue";
import SellListItem from "@/components/Sell/SellListItem.vue";
const http = apiInstance();

export default {
  name: "HomeView",
  components: {
    Navbar,
    AppFooter,
    VsudInput,
    VsudSwitch,
    VsudButton,
    SellListItem,
    NewsListItem,
  },
  data() {
    return {
      news: null,
      list: [],
    };
  },
  mounted() {
    http
      .get(`/sell?pageNo=1`)
      .then(({ data }) => {
        this.list = data;
        console.log(this.list);
      })
      .catch(() => {
        console.log("거래 매물 정보 가져오기오류 ");
      });
  },
  created() {
    this.news = getNews();
    console.log(this.news);
  },
  methods: {
    moveNews() {
      this.$router.push("/information");
    },
    moveSell() {
      this.$router.push("/sell");
    },
  },
};
</script>

<style lang="scss" scoped>
@import url("https://fonts.googleapis.com/css?family=Rajdhani:300&display=swap");
.sell .sell-card {
  margin: 0;
  width: 274px;
  margin-right: 5px;
}

.sell-card {
  background-color: #ffffff6b;
}

.news-card {
  margin: 0;
}
.info-list {
  display: flex;
  align-items: center;
  justify-content: flex-start;
  width: 550px;
}
.card.news-card {
  width: 270px;
  background-color: #ffffff6b;
  margin-right: 5px;
  margin-top: 10x;
}
section.section-list {
  padding: 100px 246px;
}
.section-list {
  display: flex;
  justify-content: center;
  padding: 68px;
}
.main-list {
  display: flex;
  justify-content: space-between;
}
.list-div {
  width: 550px;
  margin-right: 50px;
}
.list-title {
  display: flex;
  justify-content: space-between;
  align-items: flex-end;
  border-bottom: 2px solid #344767;
}

.main-info-div {
  margin: 18px;
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: #f6f6f6a8;
  width: 237px;
  padding: 41px 37px;
  border-radius: 20px;
  box-shadow: 0 20px 30px rgb(0 0 0 / 10%);
}
.main-info-img {
  width: 104px;
}
.main-info-text {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-top: 24px;
  text-align: center;
}
.main-info-title {
  font-size: 35px;
  margin-bottom: 20px;
}
span.impress {
  font-weight: bold;
  color: #2196f3;
}

.main-info-img:hover {
  transform: translateY(-20px);
  transition: 400ms;
}

$blur: 20px;
$shadow-opacity: 0.3;
$image: "@/assets/img/apt/apt1.png";
.HappyHouse {
  // position: absolute;
  // top: 0;
  // left: 0;
  // right: 0;
  // bottom: 0;

  width: 100%;
  // height: 90vh;
  display: flex;
  align-items: center;
  justify-content: center;
}
// End Codepen spesific styling

.text {
  color: mix(rgb(255, 255, 255), #9bc0f7, 85%);
  font-size: calc(1em + 10vw);
  font-weight: 900;
  text-shadow: -0.0075em 0.0075em 0 mix(rgb(100, 206, 255), #042d6c, 94%),
    0.005em 0.005em 0 mix(rgb(100, 206, 255), #042d6c, 60%),
    0.01em 0.01em 0 mix(rgb(100, 206, 255), #042d6c, 62%),
    0.015em 0.015em mix(rgb(100, 206, 255), #042d6c, 64%),
    0.02em 0.02em 0 mix(rgb(100, 206, 255), #042d6c, 66%),
    0.025em 0.025em 0 mix(rgb(100, 206, 255), #042d6c, 68%),
    0.03em 0.03em 0 mix(rgb(100, 206, 255), #042d6c, 70%),
    0.035em 0.035em 0 mix(rgb(100, 206, 255), #042d6c, 72%);
}

.main-bg {
  position: relative;
  background: linear-gradient(80deg, #ebfbd5, #ebe5eb, #dbeef9);

  // display: flex;
  // justify-content: center;
  // align-items: center;
}
.main-section {
  display: flex;
  // justify-content: center;
  // align-items: center;
  // background-image: url($image);
  background-size: cover;
  background-position: center;
  font-family: "Rajdhani", sans-serif;
}

.main-section0 {
  height: 30vh;
}
.logo-section {
  height: 80vh;
  display: flex;
  align-items: center;
  justify-content: cneter;
}
.main-section1 {
  display: flex;
  align-items: center;
  justify-content: center;
  & .glassBox {
    margin: 20px;
  }
}

*,
*:before,
*:after {
  box-sizing: border-box;
}

.glass {
  height: 100%;
  width: 100%;
  background-image: url($image);
  background-size: cover;
  background-position: center;
  clip-path: inset(10em);
  filter: blur(3px);
  display: flex;
  justify-content: center;
  align-items: center;
}

.drop-shadow {
  height: 800px;
  width: 800px;
  filter: drop-shadow(0px 20px 10px rgba(0, 0, 0, $shadow-opacity));
  display: flex;
  justify-content: center;
  align-items: center;
  &:before {
    display: block;
    content: "";
    position: absolute;
    top: 10em;
    height: calc(100% - 20em);
    width: calc(100% - 20em);
    border-top: 2px solid rgba(225, 225, 225, 0.2);
    border-left: 1px solid rgba(225, 225, 225, 0.1);
    border-right: 1px solid rgba(225, 225, 225, 0.3);
    z-index: 2;
    //  filter: blur(1px);
  }

  > span {
    position: absolute;
    z-index: 5;
    color: white;
    font-size: 4em;
    letter-spacing: 0.75em;
    padding-left: 0.375em;
  }
}

@media (max-width: 980px) {
  .glass {
    clip-path: inset(5em);
  }
  .drop-shadow {
    &:before {
      top: 5em;
      width: calc(100% - 10em);
    }
    > span {
      font-size: 4em;
    }
  }
}

@media (max-width: 640px) {
  .drop-shadow {
    > span {
      font-size: 2em;
    }
  }
}
@mixin flex-center {
  display: flex;
  justify-content: center;
  align-items: center;
}
/*
 * Start
*/
.glassBox {
  &,
  & * {
    box-sizing: border-box;
    transition: 400ms;
  }

  width: 100%;
  height: 400px;
  max-width: 300px;
  border-radius: 5px;
  // background: rgba(255, 255, 255, 0.249);
  backdrop-filter: blur(2px);

  // border: 1px solid rgba(255, 255, 255, 0.2);
  // border-right-color: rgba(255, 255, 255, 0.1);
  // border-bottom-color: rgba(255, 255, 255, 0.1);
  box-shadow: 0 20px 30px rgba(0, 0, 0, 0.1);
  padding: 15px;
  position: relative;
  @include flex-center;
  flex-direction: column;
  &__imgBox {
    img {
      display: block;
      width: 100%;
      height: auto;
    }
  }
  &__title {
    text-align: center;
    margin-top: 15px;
    color: #fff;
    font-size: 20px;
    font-weight: 400;
    font-family: "Lato";
  }
  &__content {
    position: absolute;
    right: 15px;
    bottom: 15px;
    left: 15px;
    text-align: center;
    color: #fff;
    font-size: 14px;
    font-family: "Lato";
    letter-spacing: 0.1em;
    opacity: 0;
  }
  &:hover &__imgBox {
    transform: translateY(-20px);
  }
}
</style>
