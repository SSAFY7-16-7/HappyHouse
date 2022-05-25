import axios from "axios";

import {
  API_BASE_URL,
  APT_DEAL_URL,
  APT_DETAIL_URL1,
  APT_DETAIL_URL2,
} from "@/config/index.js";

// axios 객체 생성
function apiInstance() {
  const instance = axios.create({
    baseURL: API_BASE_URL,
    headers: {
      "Content-type": "application/json",
    },
  });
  return instance;
}

function houseInstance() {
  const instance = axios.create({
    baseURL: APT_DEAL_URL,
    headers: {
      "Content-type": "application/json",
    },
  });
  return instance;
}
const KAKAO_SERVICE_KEY = process.env.VUE_APP_KAKAO_API_KEY;
function kakaoInstance() {
  const instance = axios.create({
    baseURL: "https://dapi.kakao.com/v2/local",
    headers: {
      "Content-type": "application/json",
      Authorization: `KakaoAK ${KAKAO_SERVICE_KEY}`,
    },
  });
  return instance;
}

const SERVICE_KEY = process.env.VUE_APP_APT_DETAIL_API_KEY;
function detailInstance1() {
  const instance = axios.create({
    baseURL: APT_DETAIL_URL1 + "?serviceKey=" + SERVICE_KEY,
    headers: {
      "Content-type": "application/json",
    },
  });
  return instance;
}
function detailInstance2() {
  const instance = axios.create({
    baseURL: APT_DETAIL_URL2 + "?serviceKey=" + SERVICE_KEY,
    headers: {
      "Content-type": "application/json",
    },
  });
  return instance;
}

export {
  apiInstance,
  houseInstance,
  kakaoInstance,
  detailInstance1,
  detailInstance2,
};
