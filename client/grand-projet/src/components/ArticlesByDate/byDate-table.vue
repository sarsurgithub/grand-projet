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

export default {
  data: function () {
    return {
      articles: [
        { id: 1, title: 'ACAB', date: '01.01.01', author: 'Aphaia' },
        { id: 2, title: 'Atomic Design', date: '02.02.02', author: 'BandeletteQueen' },
        { id: 3, title: 'La place des poules', date: '03.03.03', author: 'Sarah' }
      ]
    }
  },
  components: {
    tableLine
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
