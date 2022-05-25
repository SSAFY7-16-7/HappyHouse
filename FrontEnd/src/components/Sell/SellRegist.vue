<template>
  <div class="mx-3 mt-3 border-radius-xl position-relative sell-regist">
    <main class="mt-0 main-content main-content-bg">
      <section>
        <div class="page-header min-vh-75">
          <div class="container">
            <b-card title="Card Title" no-body>
              <b-card-body>
                <b-form
                  @submit="onSubmit"
                  @reset="onReset"
                  v-if="show"
                  class="regist-form"
                >
                  <h2>매물 등록 하기</h2>
                  <div class="regist-input-group">
                    <div class="group-1">
                      <b-form-group
                        id="title-group"
                        label="제목"
                        label-for="type"
                      >
                        <b-form-input
                          id="title"
                          v-model="form.title"
                          type="text"
                          placeholder="글 제목을 입력하세요."
                          required
                        ></b-form-input>
                      </b-form-group>

                      <b-form-group
                        id="content-group"
                        label="글 내용 작성 "
                        label-for="content"
                      >
                        <b-form-textarea
                          id="content"
                          v-model="form.content"
                          placeholder="글 내용을 작성 해주세요."
                          rows="4"
                          max-rows="7"
                          required
                        ></b-form-textarea>
                      </b-form-group>
                      <div class="type-group">
                        <b-form-group
                          id="type-group"
                          label="거래 방식"
                          label-for="type"
                        >
                          <b-form-select
                            id="type"
                            v-model="form.type"
                            :options="types"
                            required
                          ></b-form-select>
                        </b-form-group>
                        <b-form-group
                          id="room_type-group"
                          label="방 개수"
                          label-for="room_type"
                        >
                          <b-form-select
                            id="room_type"
                            name="방개수"
                            v-model="form.room_type"
                            :options="room_types"
                            required
                          ></b-form-select>
                        </b-form-group>
                      </div>

                      <b-form-group
                        id="image-group"
                        label="이미지 "
                        label-for="image"
                      >
                        <b-form-input
                          id="image"
                          v-model="form.image"
                          placeholder="이미지 첨부"
                          required
                        ></b-form-input>
                      </b-form-group>
                    </div>
                    <div class="group-2">
                      <b-form-group
                        id="price-group"
                        label="가격 "
                        label-for="price"
                      >
                        <b-form-input
                          id="price"
                          v-model="form.price"
                          placeholder="월세라면 보증금/월세 모두 기입"
                          required
                        ></b-form-input>
                      </b-form-group>

                      <b-form-group
                        id="admin_price-group"
                        label="관리비  "
                        label-for="admin_price"
                      >
                        <b-form-input
                          id="admin_price"
                          v-model="form.admin_price"
                          placeholder=""
                          required
                        ></b-form-input>
                      </b-form-group>

                      <b-form-group
                        id="admin_desc-group"
                        label="관리비 포함 항목  "
                        label-for="admin_desc"
                      >
                        <b-form-input
                          id="admin_desc"
                          v-model="form.admin_desc"
                          placeholder="예) 수도,전기,인터넷 등"
                          required
                        ></b-form-input>
                      </b-form-group>

                      <b-form-group
                        id="floor_now-group"
                        label="층수"
                        label-for="floor_now"
                      >
                        <b-form-input
                          id="floor_now"
                          v-model="form.floor_now"
                          placeholder="해당 매물의 층"
                          required
                        ></b-form-input>
                      </b-form-group>

                      <b-form-group
                        id="floor_tot-group"
                        label="건물 전체 층"
                        label-for="floor_tot"
                      >
                        <b-form-input
                          id="floor_tot"
                          v-model="form.floor_tot"
                          placeholder="건물의 전체 층수"
                          required
                        ></b-form-input>
                      </b-form-group>

                      <b-form-group
                        id="size-group"
                        label="크기"
                        label-for="size"
                      >
                        <b-form-input
                          id="size"
                          v-model="form.size"
                          placeholder=" 크기 (평)"
                          required
                        ></b-form-input>
                      </b-form-group>

                      <b-form-group
                        id="address-group"
                        label="주소 "
                        label-for="address1"
                      >
                        <div class="address-group">
                          <b-form-input
                            id="address1"
                            v-model="form.address"
                            placeholder="주소 (검색 버튼으로 검색)"
                            readonly
                          ></b-form-input>
                          <b-button @click="juso" class="btn-addrSearch"
                            ><i class="fa-solid fa-magnifying-glass"></i
                          ></b-button>
                        </div>

                        <b-form-input
                          id="address2"
                          v-model="address2"
                          placeholder="상세 주소 (동,호 등)"
                          required
                        ></b-form-input>
                      </b-form-group>

                      <b-form-group
                        id="keyword-group"
                        v-slot="{ ariaDescribedby }"
                      >
                        <b-form-checkbox-group
                          v-model="form.keyword"
                          id="keyword"
                          :aria-describedby="ariaDescribedby"
                          :options="keywords"
                          class="key-option"
                        >
                        </b-form-checkbox-group>
                      </b-form-group>
                    </div>
                  </div>

                  <div class="buttons">
                    <b-button type="submit" variant="primary">Submit</b-button>
                    <b-button type="reset" variant="danger">Reset</b-button>
                    <b-button varient="info" @click="setTest"
                      >test input</b-button
                    >
                  </div>
                </b-form>
              </b-card-body>
            </b-card>
          </div>
        </div>
      </section>
    </main>
  </div>
</template>

<script>
import { apiInstance, kakaoInstance } from "@/api/index.js";
import { mapState } from "vuex";
const http = apiInstance();
const kakao = kakaoInstance();
export default {
  name: "sellRegist",
  data() {
    return {
      form: {
        idx: "",
        date: "",
        title: "", //글 제목
        image: "", // 이미지 : 여러개는 ,로 구분
        id: "", // 유저 id
        type: "", // 매매,전세,월세
        price: "", //월세면 보증금/월세(만원)
        admin_price: "", //관리비(만원)
        admin_desc: "", // 관리비포함내역 설명(ex.전기세, 관리비 포함)
        room_type: "", // 원룸/투룸/쓰리룸+
        floor_now: "", // 현재 층
        floor_tot: "", // 총 층수
        size: "", //
        content: "", // 글 내용
        keyword: [], //키워드 (3개까지 컴마로 작성 가능 )
        address: "", //주소 (api에서 정한 주소  )

        dong: "", //동
        lat: "",
        lng: "",
      },
      address2: "", //상세 주소 (사용자가 입력할 상세 주소 )
      types: [{ text: "거래방식", value: "null" }, "매매", "전세", "월세"],
      room_types: [
        { text: "방 개수", value: "방 개수" },
        "원룸",
        "투룸",
        "쓰리룸+",
      ],
      show: true,
      keywords: [
        { text: "안전", value: "안전" },
        { text: "깨끗", value: "깨끗" },
        { text: "조용", value: "조용" },
        { text: "뷰맛집", value: "뷰맛집" },
        { text: "역세권", value: "역세권" },
        { text: "신축", value: "신축" },
        { text: "갓성비", value: "갓성비" },
        { text: "인프라", value: "인프라" },
      ],
    };
  },
  computed: {
    ...mapState("memberStore", ["isLogin", "userInfo", "isAdmin"]),
  },
  created() {},
  methods: {
    getPosition() {
      kakao
        .get(
          `/search/address.json?analyze_type=exact&page=1&size=10&query=${this.form.address}`
        )
        .then(({ data }) => {
          const addr = data.documents[0];
          this.form.lng = addr.x;
          this.form.lat = addr.y;
          const form = {
            ...this.form,
            keyword: this.form.keyword.toString(),
            address: this.form.address + "," + this.address2,
            user: this.userInfo.id,
          };
          this.sendData(form);
        })
        .catch(() => {
          console.log("getPosition() 에러 ");
        });
    },
    sendData(form) {
      console.log("send:", form);

      http
        .post("/sell", form)
        .then((res) => {
          console.log("매물 등록 성공");
          this.$router.push({ name: "sellList" });
        })
        .catch(() => {
          console.log("매물 등록 실패 ");
        });
    },
    onSubmit(event) {
      event.preventDefault();
      this.getPosition();
    },
    onReset(event) {
      event.preventDefault();
      // Reset our form values
      this.form.title = "";
      this.form.image = "";
      this.form.id = "";
      this.form.type = "";
      this.form.price = "";
      this.form.admin_price = "";
      this.form.admin_desc = "";
      this.form.room_type = "";
      this.form.floor_now = "";
      this.form.floor_tot = "";
      this.form.size = "";
      this.form.content = "";
      this.form.keyword = [];
      this.form.address = "";
      this.form.dong = "";
      // Trick to reset/clear native browser form validation state
      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });
    },
    setTest() {
      // 샘플 테스트 입력
      this.form = {
        title: "제목", //글 제목
        image: "이미지", // 이미지 : 여러개는 ,로 구분
        id: this.userInfo.id, // 유저 id
        type: "전세", // 매매,전세,월세
        price: "4000", //월세면 보증금/월세(만원)
        admin_price: "33", //관리비(만원)
        admin_desc: "관리비 설명 ", // 관리비포함내역 설명(ex.전기세, 관리비 포함)
        room_type: "원룸", // 원룸/투룸/쓰리룸+
        floor_now: "3", // 현재 층
        floor_tot: "10", // 총 층수
        size: "4", //
        content: "내용테스트", // 글 내용
        keyword: ["안전", "역세권"], //키워드 (3개까지 컴마로 작성 가능 )
        address: "서울 강남구 테헤란로 212", //주소
        dong: "역삼동", //동
      };
      this.address2 = "1층 101호";
    },
    juso() {
      console.log(this);
      new daum.Postcode({
        oncomplete: (data) => {
          console.log(data);
          // 행정동코드 : bcode , 동이름 :bname
          this.form.address = data.address;
          this.form.dong = data.bname;
        },
      }).open();
    },
  },
};
</script>

<style lang="scss">
.form-group {
  display: flex;
  align-items: center;
  margin: auto;
}
.group-1 {
  margin: 10px;
}
.group-2 {
  margin: 10px;
}
.d-block {
  width: 100px;
}
.regist-input-group {
  display: flex;
  margin: 5px;
}

.regist-form {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.address-group {
  display: flex;
  align-items: center;
  margin-bottom: 5px;
}
#keyword {
  display: flex;
  flex-wrap: wrap;
}
.btn-addrSearch {
  margin: 0 0 0 3px !important ;
}

.key-option {
  & div {
    margin: 2px;
  }
}
.sell-regist {
  height: 90vh;
}
</style>
