<template>
  <div>
    <div id="nav">
      <router-link to="/" class="navItem">Home</router-link> |
      <router-link to="/categories" class="navItem">Categories</router-link> |
      <router-link to="/articlesByDate" class="navItem">Articles by Date</router-link> |
      <router-link to="/myProfile" class="navItem">Profile</router-link> |
    </div>
    <div>
      <tableau></tableau>
    </div>
  </div>
</template>

<script>

import tableau from '@/components/Categories/categories-table.vue'

export default {
  components: {
    tableau
  }
}
</script>

<style lang="scss" scoped>
#nav {
  padding: 30px;
  color: #F51FFF;
  a {
    font-weight: bold;
    color: #F51FFF;

    &.router-link-exact-active {
      color: black;
      background-color :#F51FFF;
    }
  }
}

.navItem {
  padding: 5px;
  border: 3px solid #F51FFF;
  border-radius: 10px;

}
</style>
