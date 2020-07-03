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
      <router-link class='link' :to="{ name: 'CreateArticle', params: { id : user.id}}">
        <button>
          Create an article
        </button>
      </router-link>
    </div>
  </div>
</template>

<script>

import articleList from '@/components/myProfile/myArticles.vue'
import infosCard from '@/components/myProfile/myInfos.vue'
import axios from 'axios'

export default {
  data: function () {
    return {
      connectedUserId: 1,
      user: {},
      articles: []
    }
  },
  components: {
    articleList,
    infosCard
  },
  mounted () {
    axios
      .get(`http://localhost:8081/api/users/${this.connectedUserId}`)
      .then(response => {
        console.log(response)
        this.user = response.data
      })
    axios
      .get(`http://localhost:8081/api/articles/byUser?user=${this.connectedUserId}`)
      .then(response => {
        console.log(response)
        this.articles = response.data
      })
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
  align-items: flex-start;
  margin: 60px 10%;
}

button {
    margin-top: 10px;
    color: #05FFFF;
    padding: 5px;
    border: 2px solid #05FFFF;
    background-color: black;
    border-radius: 10px;
    font-weight: bold;
    font-size: 16px;
  }
  button:hover {
    color: black;
    background-color: #05FFFF;
  }
</style>
