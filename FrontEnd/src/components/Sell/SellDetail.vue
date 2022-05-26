<template>
  <div v-if="house">
    <div class="close-div">
      <b-icon-x-lg class="close" @click="dispalynone" />
    </div>
    <div class="detail-name">
      <div class="title-area">
        <like-button
          v-if="isLogin"
          @btnClick="setLike"
          :isLiked="likeStatus"
          class="sellLikebtn"
        ></like-button>
        <h3>{{ house.title }}</h3>
      </div>
      <div class="detail-img-div">
        <img class="sell-thumbnail" :src="img_url" alt="Image 1" />
      </div>
      <table class="table table-bordered table-hover">
        <thead></thead>
        <tbody>
          <tr>
            <td class="th">가격</td>
            <td>{{ house.price }}</td>
          </tr>
          <tr>
            <td class="th">주소</td>
            <td>{{ house.address }}</td>
          </tr>
          <tr>
            <td class="th">층수</td>
            <td>{{ house.floor_now }}층/{{ house.floor_tot }}</td>
          </tr>
          <tr>
            <td class="th">타입</td>
            <td>{{ house.type }}</td>
          </tr>
          <tr>
            <td class="th">크기</td>
            <td>{{ house.size }}</td>
          </tr>
          <tr>
            <td class="th">설명</td>
            <td>{{ house.content }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import LikeButton from "@/components/Interest/LikeButton.vue";
import { BUS } from "@/store/modules/EventBus";
import { apiInstance } from "@/api/index.js";
import { ramdomImg } from "@/api/image.js";
const Store = "interestStore";
const http = apiInstance();
export default {
  name: "SellDetail",
  data() {
    return {
      overColumn: null,
      likeStatus: true,
      img_url: "",
      img_url2: "",
    };
  },
  components: {
    LikeButton,
  },
  computed: {
    ...mapState(Store, ["house", "deals", "none"]),
    ...mapState("memberStore", ["userInfo", "isLogin"]),
    // house() {
    //   return this.$store.state.house;
    // },
  },
  created() {
    this.img_url = ramdomImg();
    this.img_url2 = ramdomImg();
  },
  methods: {
    ...mapActions(Store, ["setNoneFalse"]),
    colorChange(flag, index) {
      if (flag) {
        this.overColumn = index;
      } else {
        this.overColumn = null;
      }
    },
    columnHoverCheck(index) {
      if (index === this.overColumn) {
        return true;
      } else {
        return false;
      }
    },
    dispalynone() {
      this.setNoneFalse(true);
    },
    setLike() {
      let param = { code: "", user_id: "ssafy" };

      param.code = this.house.idx;

      //console.log(param);
      if (this.likeStatus) {
        http
          .post(`/interest/delete/sell`, param)
          .then(() => {
            //console.log("좋아요 취소 성공 ");
            this.likeStatus = !this.likeStatus;

            BUS.$emit("deleteLike", param.code);
          })
          .catch(() => {
            //console.log("좋아요 취소 실패 ");
          });
      } else {
        //console.log("좋아요 설정");
        http
          .post(`/interest/sell`, param)
          .then(() => {
            //console.log("좋아요 성공 ");
            BUS.$emit("addLike", this.house);
          })
          .catch(() => {
            //console.log("좋아요 실패 ");
          });
        this.likeStatus = !this.likeStatus;
      }
    },
  },
};
</script>

<style>
.th {
  font-weight: bold;
}
.mouse-over-bgcolor {
  background-color: rgb(214, 211, 211);
}
.detail-row {
  /* text-align: end; */
  font-size: 19px;
  font-weight: bold;
  padding-top: 10px;
}

.house-detail {
  padding: 0px 30px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}

.detail-name {
  padding-top: 30px;
}

.detail-img-div img {
  padding: 11px 0px 40px 0;
  width: 328px;
  height: 300px;
}

.detail-info-div {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  width: 90%;
}

.detail-row {
  font-size: 19px;
  font-weight: bold;
  padding-top: 10px;
}

.dealList {
  border-top: 2px solid #aeb4bf;
  width: 323px;
  padding: 14px;
}
</style>
