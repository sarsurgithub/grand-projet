<template>
  <div>
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
      .get(`http://localhost:8081/api/users/${this.$store.getters.GET_CONNECTED_USER}`)
      .then(response => {
        this.user = response.data
      })
    axios
      .get(`http://localhost:8081/api/articles/byUser?user=${this.$store.getters.GET_CONNECTED_USER}`)
      .then(response => {
        this.articles = response.data
      })
  }
}

</script>
<style lang="scss" scoped>

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
