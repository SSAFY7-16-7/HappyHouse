<template>
  <div>
    <div class="container">
      <b-card title="Card Title" no-body>
        <b-card-body class="sell-info-detail-area">
          <table class="table align-items-center justify-content-center mb-0">
            <!-- <thead></thead> -->
            <tbody>
              <tr>
                <td>거래 종류</td>
                <td>{{ this.item.type }}</td>
              </tr>
              <tr>
                <td>방 개수</td>
                <td>{{ this.item.room_type }}</td>
              </tr>
              <tr>
                <td>크기 (평)</td>
                <td>{{ this.item.size }}</td>
              </tr>
              <tr>
                <td>층 (매물 층/건물 전체 층)</td>
                <td>{{ this.item.floor_now }}/{{ this.item.floor_tot }}</td>
              </tr>
              <tr>
                <td>가격(만원)</td>
                <td>{{ this.item.price }}</td>
              </tr>

              <tr>
                <td>관리비(만원)</td>
                <td>{{ this.item.admin_price }}</td>
              </tr>
              <tr>
                <td>관리비 포함 항목</td>
                <td>{{ this.item.admin_desc }}</td>
              </tr>

              <tr>
                <td>키워드</td>
                <td>{{ this.item.keyword }}</td>
              </tr>
              <tr>
                <td>주소</td>
                <td>{{ this.item.address }}</td>
              </tr>

              <tr>
                <td>작성자</td>
                <td>{{ this.item.id }}</td>
              </tr>
              <tr>
                <td>작성일</td>
                <td>{{ this.item.date }}</td>
              </tr>
            </tbody>
          </table>
          <div class="sell_user_upload_info">
            <div class="sell_upload_img">
              <img :src="img_url" alt="Image 1" width="400" height="400" />
            </div>
            <div>
              <div class="sell-detail-title">
                <like-button
                  v-if="isLogin"
                  :isLiked="likeStatus"
                  @btnClick="setLike"
                  class="sellLikebtn"
                ></like-button>
                <h3>{{ this.item.title }}</h3>
                <p>{{ this.item.content }}</p>
              </div>
            </div>
          </div>
        </b-card-body>
      </b-card>
    </div>
  </div>
</template>

<script>
const http = apiInstance();
import { apiInstance } from "@/api/index.js";
import LikeButton from "@/components/Interest/LikeButton.vue";
import { ramdomImg } from "@/api/image.js";
import { mapState } from "vuex";
export default {
  name: "SellItemDetail",
  components: {
    LikeButton,
  },
  data() {
    return {
      item: {},
      likeStatus: false,
      category: "sell",
      img_url: "",
    };
  },
  computed: {
    ...mapState("memberStore", ["userInfo", "isLogin"]),
  },
  created() {
    this.img_url = ramdomImg();
    const sellCode = this.$route.query.no;
    http
      .get(`/sell/${sellCode}`)
      .then(({ data }) => {
        this.item = data;
      })
      .catch(() => {
        console.log("거래 매물 정보 가져오기오류 ");
      });

    http
      .post(`/interest/check/likeSell`, { code: sellCode, user_id: "ssafy" })
      .then(({ data }) => {
        if (data.isLiked === "Y") {
          this.likeStatus = true;
        } else {
          this.likeStatus = false;
        }
      });
  },
  methods: {
    setLike() {
      let param = { code: "", user_id: "ssafy" };
      if (this.category === "sell") {
        param.code = this.item.idx;
      } else {
        param.code = this.item.aptCode;
      }
      console.log(param);
      if (this.likeStatus) {
        http
          .post(`/interest/delete/${this.category}`, param)
          .then(() => {
            console.log("좋아요 취소 성공 ");
            this.likeStatus = !this.likeStatus;

            this.$emit("deleteLkie", this.index);
          })
          .catch(() => {
            console.log("좋아요 취소 실패 ");
          });
      } else {
        console.log("좋아요 설정");
        http
          .post(`/interest/${this.category}`, param)
          .then(() => {
            console.log("좋아요 성공 ");
            // this.likeStatus = !this.likeStatus;
          })
          .catch(() => {
            console.log("좋아요 실패 ");
          });
        this.likeStatus = !this.likeStatus;
      }
    },
  },
};
</script>

<style>
.sell-info-detail-area {
  height: 90vh;
  padding: 50px !important;

  display: flex;
  justify-content: space-around;
}
.sell_user_upload_info {
  display: flex;
  justify-content: flex-start;
  align-content: space-around;
  flex-direction: column;
  width: 90%;
  margin-left: 20px;
  border-left: 1px solid #cecece70;
}
.sell-detail-title {
  display: flex;
  align-items: center;
  margin-top: 20px;
  flex-direction: column;
}
.sellLikebtn {
  margin-right: 5px;
  height: 2em;
}
.sell_upload_img {
  display: flex;
  width: 500px;
  justify-content: center;
}
</style>
