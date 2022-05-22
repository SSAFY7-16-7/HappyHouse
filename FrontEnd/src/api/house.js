import { apiInstance } from "./index.js";

const api = apiInstance();

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

export { sidoList, gugunList, dongList, houseList, housDeal };
