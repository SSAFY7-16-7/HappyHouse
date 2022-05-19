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
              <th
                class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7"
              >
                아이디
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
              <th
                class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7"
              >
                회원 구분
              </th>
              <th class="text-secondary opacity-7"></th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(user, index) in users" :key="index">
              <td>
                <div class="d-flex px-2 py-1">
                  <div></div>
                  <div class="d-flex flex-column justify-content-center">
                    <h6 class="mb-0 text-sm">{{ user.userid }}</h6>
                  </div>
                </div>
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
              <td class="align-middle text-center text-sm">
                <vsud-badge
                  v-if="user.isAdmin"
                  color="success"
                  variant="gradient"
                  size="sm"
                  >관리자</vsud-badge
                >
                <vsud-badge
                  v-else
                  color="secondary"
                  variant="gradient"
                  size="sm"
                  >관리자</vsud-badge
                >
              </td>

              <td class="align-middle">
                <a
                  href="javascript:;"
                  class="text-secondary font-weight-bold text-xs"
                  data-toggle="tooltip"
                  data-original-title="Edit user"
                  >Edit</a
                >
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import VsudBadge from "@/components/soft-ui-components/VsudBadge.vue";
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
    };
  },
  components: { VsudBadge },
};
</script>
