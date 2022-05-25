<template>
  <div class="card mb-4">
    <div class="card-header pb-0">
      <h6>회원관리 테이블</h6>
    </div>
    <div class="card-body px-0 pt-0 pb-2">
      <div class="table-responsive p-0">
        <table class="table align-items-center mb-0">
          <thead>
            <tr>
              <th class="text-secondary opacity-7"></th>
              <th
                class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7"
              >
                아이디
              </th>
              <th
                class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7"
              >
                비밀번호
              </th>
              <th
                class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2"
              >
                이메일
              </th>
              <th
                class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2"
              >
                이름
              </th>
              <th
                class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7"
              >
                나이
              </th>

              <th class="text-secondary opacity-7"></th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(user, index) in users" :key="index">
              <td></td>
              <td>
                <div class="d-flex px-2 py-1">
                  <div></div>
                  <div class="d-flex flex-column justify-content-center">
                    <h6 class="mb-0 text-sm">{{ user.id }}</h6>
                  </div>
                </div>
              </td>
              <td>
                <p class="text-xs font-weight-bold mb-0">{{ user.password }}</p>
              </td>
              <td>
                <p class="text-xs font-weight-bold mb-0">{{ user.email }}</p>
              </td>
              <td>
                <p class="text-xs font-weight-bold mb-0">{{ user.name }}</p>
              </td>
              <td class="align-middle text-center">
                <span class="text-secondary text-xs font-weight-bold">{{
                  user.age
                }}</span>
              </td>

              <td class="align-middle">
                <b-button
                  size="sm"
                  v-b-modal.modal-center
                  variant="info"
                  @click="setUser(user, index)"
                  >수정</b-button
                >
                <b-button
                  size="sm"
                  variant="secondary"
                  @click="delteUser(user, index)"
                  >삭제</b-button
                >
              </td>
            </tr>
          </tbody>
        </table>
        <div>
          <b-modal id="modal-center" centered title="회원 정보 수정 ">
            <form role="form">
              <p>User ID : {{ user.id }}</p>
              <div class="mb-3">
                <label for="type - password">Password</label>
                <b-input
                  v-model="user.password"
                  id="password"
                  aria-label="Password"
                />
              </div>

              <div class="mb-3">
                <label for="type - name">Name</label>
                <b-input v-model="user.name" id="name" />
              </div>
              <div class="mb-3">
                <label for="type - email">Email</label>
                <b-input v-model="user.email" id="email" />
              </div>
              <div class="mb-3">
                <label for="type - age">Age</label>
                <b-input v-model="user.age" id="number" />
              </div>

              <div class="text-center">
                <b-button
                  @click="modify()"
                  color="dark"
                  full-width
                  variant="success"
                  class=""
                  size="lg"
                  >정보 수정
                </b-button>
              </div>
            </form>
          </b-modal>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import VsudBadge from "@/components/soft-ui-components/VsudBadge.vue";
import { apiInstance } from "@/api/index.js";
const http = apiInstance();
export default {
  name: "UserList",
  data() {
    return {
      users: [
        {
          userid: "ssafy",
          email: "test@aaa.aaa",
          name: "김싸피",
          age: 12,
          isAdmin: true,
        },
        {
          userid: "ggg",
          email: "test@aaa.aaa",
          name: "비밀임",
          age: 12,
          isAdmin: false,
        },
        {
          userid: "2222",
          email: "test@aaa.aaa",
          name: "ㅎㅎ",
          age: 12,
          isAdmin: true,
        },
      ],
      user: {},
      useridx: "",
    };
  },
  components: { VsudBadge },
  created() {
    http.get("/user/user").then(({ data }) => {
      this.users = data;
    });
  },
  methods: {
    modify() {
      http.put("/user/update", this.user).then((res) => {
        console.log(res);
      });
      this.$set(this.users, this.useridx, this.user);
    },
    setUser(user, idx) {
      console.log("클릭");
      this.useridx = idx;
      this.user = user;
    },
    delteUser(user, idx) {
      const isDelete = confirm("회원을 삭제 하시겠습니까?");
      if (isDelete) {
        this.setUser(user, idx);
        console.log({ ...this.user });
        http
          .post("/user/remove", { ...this.user })
          .then(() => console.log("회원 삭제 완료 "));
        this.users.splice(idx, 1);
      }
    },
  },
};
</script>
