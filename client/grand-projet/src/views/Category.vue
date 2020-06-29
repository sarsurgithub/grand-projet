<template>
  <div>
    <div id="nav">
        <router-link to="/" class="navItem">Home</router-link> |
        <router-link to="/categories" class="navItem">Categories</router-link> |
        <router-link to="/articlesByDate" class="navItem">Articles by Date</router-link> |
        <router-link to="/myProfile" class="navItem">Profile</router-link> |
        <router-link to="/createArticle" class="navItem">Create Article</router-link>
    </div>
    <div class="wrapper">
      <articles></articles>
    </div>
  </div>
</template>
<script>
import articles from '@/components/Category/articles-byCategory'

export default {
  data: function () {
    return {
      article: {},
      id: this.$route.params.id,
      comments: []
    }
  },
  components: {
    articles
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
      background-color :#F51FFF ;
    }
  }
}

.navItem {
  padding: 5px;
  border: 3px solid #F51FFF;
  border-radius: 10px;

}
</style>
