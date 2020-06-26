<template>
  <div>
    <div id="nav">
      <router-link to="/" class="navItem">Home</router-link> |
      <router-link to="/categories" class="navItem">Categories</router-link> |
      <router-link to="/articlesByDate" class="navItem">Articles by Date</router-link> |
      <router-link to="/theirProfile" class="navItem">Their Profile</router-link> |
      <router-link to="/myProfile" class="navItem">My Profile</router-link> |
      <router-link to="/createArticle" class="navItem">Create Article</router-link>
    </div>
    <div class="wrapper">
      <infos-card :user=user></infos-card>
      <article-list id="articleCard" :articles=articles></article-list>
    </div>
  </div>
</template>

<script>

import articleList from '@/components/TheirProfile/article-list.vue'
import infosCard from '@/components/TheirProfile/infos-card.vue'

export default {
  data: function () {
    return {
      user: {
        id: 1, username: 'Jean-Ivre'
      },
      articles: [
        { id: 1, title: 'ACAB' },
        { id: 2, title: 'Atomic Design' },
        { id: 3, title: 'La place des poules' }
      ]
    }
  },
  components: {
    articleList,
    infosCard
  }
}

</script>

<style lang="scss" scoped>
#nav {
  padding: 30px;
  color: #05FFFF;
  a {
    font-weight: bold;
    color: #05FFFF;

    &.router-link-exact-active {
      color: black;
      background-color :#05FFFF ;
    }
  }
}

.navItem {
  padding: 5px;
  border: 3px solid #05FFFF;
  border-radius: 10px;

}
.wrapper {
  display: flex;
}
#articleCard {
  border: 5px solid #05FFFF;
  background-color: white;
  border-radius: 20px;
}

</style>
