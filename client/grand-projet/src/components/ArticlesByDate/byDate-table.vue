<template>
  <div class='wrapper'>
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
      articles: []
    }
  },
  components: {
    tableLine
  },
  mounted () {
    axios
      .get(`${process.env.VUE_APP_API}/api/articles`)
      .then(response => (
        this.articles = response.data
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
