<template>
  <div
    class="main-bg"
    :style="{
      backgroundImage: 'url(' + require('@/assets/img/apt/apt3.jpeg') + ')',
      backgroundSize: 'cover',
    }"
  >
    <div class="mx-3 mt-3 border-radius-xl position-relative">
      <main class="mt-7 main-content main-content-bg">
        <section>
          <div class="page-header min-vh-75">
            <div class="container">
              <div class="row">
                <div class="col-lg-7 mb-lg-0 mb-4">
                  <div class="card">
                    <div class="card-body p-3">
                      <div class="row">
                        <div class="col-lg-6">
                          <div class="d-flex flex-column h-100">
                            <p class="mb-1 pt-2 text-bold"></p>
                            <h5 class="font-weight-bolder">동네 추천 받기</h5>
                            <p class="mb-5">
                              원하는 조건을 설정하고 적합한 지역을 추천 받아
                              보세요
                            </p>
                            <a
                              class="text-body text-sm font-weight-bold mb-0 icon-move-right mt-auto"
                              href="javascript:;"
                            >
                              추천 받으로 가기
                              <i
                                class="fas fa-arrow-right text-sm ms-1"
                                aria-hidden="true"
                              ></i>
                            </a>
                          </div>
                        </div>
                        <div class="col-lg-5 ms-auto text-center mt-5 mt-lg-0">
                          <div
                            class="bg-gradient-success border-radius-lg h-100"
                          >
                            <img
                              src="../assets/img/shapes/waves-white.svg"
                              class="position-absolute h-100 w-50 top-0 d-lg-block d-none"
                              alt="waves"
                            />
                            <div
                              class="position-relative d-flex align-items-center justify-content-center h-100"
                            >
                              <img
                                class="w-100 position-relative z-index-2 pt-4"
                                src="../assets/img/illustrations/rocket-white.png"
                                alt="rocket"
                              />
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="col-lg-5">
                  <div class="card h-100 p-3">
                    <div
                      class="overflow-hidden position-relative border-radius-lg bg-cover h-100"
                      style="
                        background-image: url('https://demos.creative-tim.com/soft-ui-dashboard/assets/img/ivancik.jpg');
                      "
                    >
                      <span class="mask bg-gradient-dark"></span>
                      <div
                        class="card-body position-relative z-index-1 p-3 h-100"
                      >
                        <div class="d-flex flex-column h-100">
                          <h5 class="text-white font-weight-bolder mb-4 pt-2">
                            동별 검색
                          </h5>
                          <p class="text-white mb-5">
                            지도에서 거래 내역을 볼 수 있습니다.
                          </p>
                          <a
                            class="text-white font-weight-bold ps-1 mb-0 icon-move-left mt-auto"
                            href="javascript:;"
                          >
                            지도에서 검색 하기
                            <i
                              class="fas fa-arrow-right text-sm ms-1"
                              aria-hidden="true"
                            ></i>
                          </a>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div></div>
              <div class="mt-4 row">
                <div class="col-lg-5">
                  <div class="card h-100 p-3">
                    <div
                      class="overflow-hidden position-relative border-radius-lg bg-cover h-100"
                      style="
                        background-image: url('https://demos.creative-tim.com/soft-ui-dashboard/assets/img/ivancik.jpg');
                      "
                    >
                      <span class="mask bg-gradient-dark"></span>
                      <div
                        class="card-body position-relative z-index-1 p-3 h-100"
                      >
                        <div class="d-flex flex-column h-100">
                          <h5 class="text-white font-weight-bolder mb-4 pt-2">
                            관심 매물 보기
                          </h5>
                          <p class="text-white mb-5">
                            내 관심 매물 리스트를 확인하세요
                          </p>
                          <a
                            class="text-white font-weight-bold ps-1 mb-0 icon-move-left mt-auto"
                            href="javascript:;"
                          >
                            확인하러 가기
                            <i
                              class="fas fa-arrow-right text-sm ms-1"
                              aria-hidden="true"
                            ></i>
                          </a>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="col-lg-7 mb-lg-0 mb-4">
                  <div class="card">
                    <div class="card-body p-3">
                      <div class="row">
                        <div class="col-lg-6">
                          <div class="d-flex flex-column h-100">
                            <h5 class="font-weight-bolder">공지사항</h5>
                            <p class="mb-1 pt-2 text-bold">최근 공지</p>
                            <p class="mb-5">공지사항입니다.</p>
                            <a
                              class="text-body text-sm font-weight-bold mb-0 icon-move-right mt-auto"
                              href="javascript:;"
                            >
                              모든 공지사항 확인하기
                              <i
                                class="fas fa-arrow-right text-sm ms-1"
                                aria-hidden="true"
                              ></i>
                            </a>
                          </div>
                        </div>
                        <div class="col-lg-5 ms-auto text-center mt-5 mt-lg-0">
                          <div
                            class="bg-gradient-success border-radius-lg h-100"
                          >
                            <img
                              src="../assets/img/shapes/waves-white.svg"
                              class="position-absolute h-100 w-50 top-0 d-lg-block d-none"
                              alt="waves"
                            />
                            <div
                              class="position-relative d-flex align-items-center justify-content-center h-100"
                            >
                              <img
                                class="w-100 position-relative z-index-2 pt-4"
                                src="../assets/img/illustrations/rocket-white.png"
                                alt="rocket"
                              />
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </section>
      </main>
    </div>
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

<style>
.main-bg {
  height: 100vh;
  position: absolute;
  width: 100vw;
  top: 0;
}
</style>
