<template>
  <div>
    <div id="nav">
      <router-link to="/" class="navItem">Home</router-link> |
      <router-link to="/categories" class="navItem">Categories</router-link> |
      <router-link to="/articlesByDate" class="navItem">Articles by Date</router-link> |
      <router-link to="/myProfile" class="navItem">Profile</router-link>
      <router-link to="/Auth" class="navItem" v-if='this.$store.getters.GET_CONNECTED_USER === false'> | Login</router-link>
    </div>
    <div class="wrapper">
      <infos-card :user=user></infos-card>
      <article-list id="articleCard" :articles=articles></article-list>
    </div>
  </div>
</template>

<script>

import axios from 'axios'
import articleList from '@/components/TheirProfile/article-list.vue'
import infosCard from '@/components/TheirProfile/infos-card.vue'

export default {
  data: function () {
    return {
      user: {},
      articles: [
      ]
    }
  },
  components: {
    articleList,
    infosCard
  },
  mounted () {
    axios
      .get(`http://localhost:8081/api/users/${this.$route.params.id}`)
      .then(response => {
        console.log(response)
        this.user = response.data
      }
      )
    axios
      .get(`http://localhost:8081/api/articles/byUser?user=${this.$route.params.id}`)
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

</style>
