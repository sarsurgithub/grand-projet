<template>
  <div class='wrapper'>
    <h1> Articles in "{{this.category}}" </h1>
    <div class='table-header'>
      <strong> Date </strong>
      <strong> Author </strong>
      <strong> Title </strong>
    </div>
    <div v-for="article in articles" :key="article.id">
      <table-line :article=article></table-line>
    </div>
  </div>
</template>

<script>

import tableLine from '@/components/ArticlesByDate/byDate-table-line.vue'

import axios from 'axios'

export default {
  data: function () {
    return {
      articles: [],
      category: null
    }
  },
  components: {
    tableLine
  },
  mounted () {
    axios
      .get(`${process.ENV.VUE_APP_API}/api/articles/byCategories?categories=${this.$route.params.id}`)
      .then(response => (
        this.articles = response.data
      )
      )
    axios
      .get(`${process.ENV.VUE_APP_API}/api/categories/${this.$route.params.id}`)
      .then(response => (
        this.category = response.data.name
      )
      )
  }
}
</script>

<style scoped>
  .wrapper {
    margin-right: 20%;
    margin-left: 20%;
  }
  .table-header {
    display: flex;
    justify-content: space-between;
    padding: 7px;
    align-content:left;
  }
  strong {
    padding: 5px;
    text-align: left;
    flex-basis: 0;
    flex-grow: 1;
    min-width: 0;
    max-width: 100%;
  }
</style>
