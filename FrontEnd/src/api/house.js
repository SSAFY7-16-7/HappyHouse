import { apiInstance, detailInstance } from "./index.js";

const api = apiInstance();
const detail = detailInstance();

function sidoList(success, fail) {
  api.get(`/address/sido`).then(success).catch(fail);
}

function gugunList(params, success, fail) {
  api.get(`/address/gugun`, { params: params }).then(success).catch(fail);
}

function dongList(params, success, fail) {
  api.get(`/address/dong`, { params: params }).then(success).catch(fail);
}

function houseList(params, success, fail) {
  api.get(`/deal/dong`, { params: params }).then(success).catch(fail);
}

function housDeal(params, success, fail) {
  api.get(`/deal/deal`, { params: params }).then(success).catch(fail);
}

function houseAddress(params, success, fail) {
  api.get(`/deal/address`, { params: params }).then(success).catch(fail);
}

function houseName(params, success, fail) {
  api.get(`/deal/name`, { params: params }).then(success).catch(fail);
}

function getKapt(params, success, fail) {
  console.log("houseinfo", params);
  api.get(`/deal/kapt`, { params: params }).then(success).catch(fail);
}

function houseDetail(params, success, fail) {
  detail.get(``, { params: params }).then(success).catch(fail);
}

export {
  sidoList,
  gugunList,
  dongList,
  houseList,
  housDeal,
  houseAddress,
  houseName,
  getKapt,
  houseDetail,
};
