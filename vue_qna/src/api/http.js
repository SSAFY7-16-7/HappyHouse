import axios from "axios";

export default axios.create({
  baseURL: "http://localhost/happyhouse/",
  headers: {
    "Content-Type": "application/json",
  },
});
