<template>
  <div>
    <div id="nav">
      <router-link to="/" class="navItem">Home</router-link> |
      <router-link to="/categories" class="navItem">Categories</router-link> |
      <router-link to="/articlesByDate" class="navItem">Articles by Date</router-link> |
      <router-link to="/myProfile" class="navItem">Profile</router-link> |
    </div>
    <div class="wrapper">
      <infos-card :user=user></infos-card>
      <article-list :articles=articles></article-list>
    </div>
    <router-link class='link' :to="{ name: 'CreateArticle', params: { id : user.id}}">
    <button>
      Create an article
    </button>
    </router-link>
  </div>
</template>

<script>

import articleList from '@/components/myProfile/myArticles.vue'
import infosCard from '@/components/myProfile/myInfos.vue'

export default {
  data: function () {
    return {
      user: {
        id: 1, username: 'Jean-Ivre', firstName: 'Jean-Yve', lastName: 'Coco', eMail: 'coco@email.com', password: '**********'
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
</style>
