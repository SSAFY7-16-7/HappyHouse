<template>
  <div>
    <div class="container">
      <b-card title="Card Title" no-body>
        <b-card-body>
          <div class="sell_user_upload_info">
            <div>
              <img
                src="https://picsum.photos/250/250/?image=54"
                alt="Image 1"
              />
            </div>
            <div>
              <div class="title-area">
                <like-button
                  :isLiked="likeStatus"
                  @btnClick="setLike"
                  class="sellLikebtn"
                ></like-button>
                <h3>{{ this.item.title }}</h3>
              </div>
              <p>{{ this.item.content }}</p>
            </div>
          </div>

          <table class="table align-items-center justify-content-center mb-0">
            <thead>
              <tr>
                <th></th>
                <th></th>
              </tr>
            </thead>
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
        </b-card-body>
      </b-card>
    </div>
  </div>
</template>

<script>
const http = apiInstance();
import { apiInstance } from "@/api/index.js";
import LikeButton from "@/components/Interest/LikeButton.vue";

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
    };
  },
  created() {
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
.sell_user_upload_info {
  display: flex;
  justify-content: space-around;
}
.title-area {
  display: flex;
  align-items: center;
  justify-content: space-evenly;
}
.sellLikebtn {
  margin-right: 5px;
  height: 2em;
}
</style>
