
<template>
  <div>
    <div id="nav">
      <router-link to="/" class="navItem">Home</router-link> |
      <router-link to="/categories" class="navItem">Categories</router-link> |
      <router-link to="/articlesByDate" class="navItem">Articles by Date</router-link> |
      <router-link to="/myProfile" class="navItem">Profile</router-link>
      <router-link to="/Auth" class="navItem" v-if='this.$store.getters.GET_CONNECTED_USER === false'> | Login</router-link>
    </div>
    <div>
      <articleEditor></articleEditor>
    </div>
  </div>
</template>

<script>

import articleEditor from '@/components/CreateArticle/createArticle.vue'
export default {
  components: {
    articleEditor
  }
}
</script>

<style lang="scss" scoped>
#nav {
  padding: 30px;
  color: #FFFE00;
  a {
    font-weight: bold;
    color: #FFFE00;

    &.router-link-exact-active {
      color: black;
      background-color :#FFFE00 ;
    }
  }
}

.navItem {
  padding: 5px;
  border: 3px solid #FFFE00;
  border-radius: 10px;

}
tags {
  align-self: center;
}
</style>
