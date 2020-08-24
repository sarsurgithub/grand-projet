<template>
  <div>
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

.wrapper {
  display: flex;
  align-items: flex-start;
  margin: 60px 10%;
}

</style>
