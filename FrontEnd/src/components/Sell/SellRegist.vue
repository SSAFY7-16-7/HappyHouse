<template>
  <div>
    <div class="mx-3 mt-3 border-radius-xl position-relative">
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
                            placeholder="Enter title"
                            required
                          ></b-form-input>
                        </b-form-group>

                        <b-form-group
                          id="content-group"
                          label="글 내용 작성 "
                          label-for="content"
                        >
                          <b-form-input
                            id="content"
                            v-model="form.content"
                            placeholder="Enter content"
                            required
                          ></b-form-input>
                        </b-form-group>
                        <div class="type-group">
                          <b-form-group
                            id="type-group"
                            label="type:"
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
                            label="room_type:"
                            label-for="room_type"
                          >
                            <b-form-select
                              id="room_type"
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
                            placeholder="Enter image"
                            required
                          ></b-form-input>
                        </b-form-group>
                      </div>
                      <div class="group-2">
                        <b-form-group
                          id="price-group"
                          label="price :  "
                          label-for="price"
                        >
                          <b-form-input
                            id="price"
                            v-model="form.price"
                            placeholder="Enter price"
                            required
                          ></b-form-input>
                        </b-form-group>

                        <b-form-group
                          id="admin_price-group"
                          label="admin_price :  "
                          label-for="admin_price"
                        >
                          <b-form-input
                            id="admin_price"
                            v-model="form.admin_price"
                            placeholder="Enter admin_price"
                            required
                          ></b-form-input>
                        </b-form-group>

                        <b-form-group
                          id="admin_desc-group"
                          label="admin_desc :  "
                          label-for="admin_desc"
                        >
                          <b-form-input
                            id="admin_desc"
                            v-model="form.admin_desc"
                            placeholder="Enter admin_desc"
                            required
                          ></b-form-input>
                        </b-form-group>

                        <b-form-group
                          id="floor_now-group"
                          label="floor_now :  "
                          label-for="floor_now"
                        >
                          <b-form-input
                            id="floor_now"
                            v-model="form.floor_now"
                            placeholder="Enter floor_now"
                            required
                          ></b-form-input>
                        </b-form-group>

                        <b-form-group
                          id="floor_tot-group"
                          label="floor_tot :  "
                          label-for="floor_tot"
                        >
                          <b-form-input
                            id="floor_tot"
                            v-model="form.floor_tot"
                            placeholder="Enter floor_tot"
                            required
                          ></b-form-input>
                        </b-form-group>

                        <b-form-group
                          id="size-group"
                          label="size :  "
                          label-for="size"
                        >
                          <b-form-input
                            id="size"
                            v-model="form.size"
                            placeholder="Enter size"
                            required
                          ></b-form-input>
                        </b-form-group>

                        <b-form-group
                          id="address-group"
                          label="address :  "
                          label-for="address"
                        >
                          <b-form-input
                            id="address"
                            v-model="form.address"
                            placeholder="Enter address"
                            required
                          ></b-form-input>
                        </b-form-group>

                        <b-form-group
                          id="dong-group"
                          label="dong :  "
                          label-for="dong"
                        >
                          <b-form-input
                            id="dong"
                            v-model="form.dong"
                            placeholder="Enter dong"
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
                          >
                          </b-form-checkbox-group>
                        </b-form-group>
                      </div>
                    </div>

                    <div class="buttons">
                      <b-button type="submit" variant="primary"
                        >Submit</b-button
                      >
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
  </div>
</template>

<script>
export default {
  name: "sellRegist",
  data() {
    return {
      form: {
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
        address: "", //주소
        dong: "", //동
      },
      types: [{ text: "거래방식", value: null }, "매매", "전세", "월세"],
      room_types: [{ text: "방 개수", value: null }, "원룸", "투룸", "쓰리룸+"],
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
  methods: {
    onSubmit(event) {
      event.preventDefault();
      console.log(JSON.stringify(this.form));
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
        id: "ssafy", // 유저 id
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
        address: "주소", //주소
        dong: "휘경종", //동
      };
    },
  },
};
</script>

<style>
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
}

.regist-form {
  display: flex;
  flex-direction: column;
  align-items: center;
}
#keyword {
  display: flex;
  flex-wrap: wrap;
}
</style>
