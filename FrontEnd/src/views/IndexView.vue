<template>
  <div class="main-bg">
    <section class="logo-section">
      <div class="HappyHouse">
        <div class="text">Happy House</div>
      </div>
    </section>
    <!-- <section class="main-section0"></section> -->
    <section class="main-section1">
      <div class="glassBox">
        <div class="glassBox__imgBox">
          <img
            src="https://www.pngmart.com/files/1/House-Transparent-PNG.png"
            alt=""
          />
          <h3 class="glassBox__title">매물 보기</h3>
        </div>
      </div>
      <div class="glassBox">
        <div class="glassBox__imgBox">
          <img
            src="https://www.pngfind.com/pngs/m/1-16561_business-growth-chart-png-transparent-images-graph-clipart.png"
            alt=""
          />
          <h3 class="glassBox__title">거래 내역 정보</h3>
        </div>
        <div class="glassBox__content">
          Lorem, ipsum dolor sit amet consectetur adipisicing elit. Beatae sunt
          veniam adipisci fugit qui quaerat!
        </div>
      </div>
      <div class="glassBox">
        <div class="glassBox__imgBox">
          <img src="https://i.ibb.co/s5phbkg/shoe-golden.png" alt="" />
          <h3 class="glassBox__title">주변 상권</h3>
        </div>
        <div class="glassBox__content">
          Lorem, ipsum dolor sit amet consectetur adipisicing elit. Beatae sunt
          veniam adipisci fugit qui quaerat!
        </div>
      </div>
    </section>
    <section class="main-section">
      <div class="drop-shadow">
        <div class="glass"></div>
        <span>GLASS</span>
      </div>
    </section>
    <section class="main-section">
      <div class="drop-shadow">
        <div class="glass"></div>
        <span>GLASS</span>
      </div>
    </section>
  </div>
</template>

<script>
import Navbar from "@/components/PageLayout/Navbar.vue";
import AppFooter from "@/components/PageLayout/Footer.vue";
import VsudInput from "@/components/soft-ui-components/VsudInput.vue";
import VsudSwitch from "@/components/soft-ui-components/VsudSwitch.vue";
import VsudButton from "@/components/soft-ui-components/VsudButton.vue";
const body = document.getElementsByTagName("body")[0];
import { mapMutations } from "vuex";

export default {
  name: "HomeView",
  components: {
    Navbar,
    AppFooter,
    VsudInput,
    VsudSwitch,
    VsudButton,
  },
};
</script>

<style lang="scss">
@import url("https://fonts.googleapis.com/css?family=Rajdhani:300&display=swap");

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
    transform: translateY(-50px);
  }
  &:hover &__imgBox img {
    transform: translate(-20px, -40px) rotate(-15deg) scale(1.4);
  }
  &:hover &__title {
    //margin-top: 40px;
  }
}
</style>
