<template>
  <router-link class='link' :to="{ name: 'Category', params: { id : category.id, name: category.name }}">
  <div class = 'line'>
    <div class='column'> {{category.name}} </div>
    <div class='column'> {{articlesByCategory.length}} </div>
  </div>
  </router-link>
</template>

<script>
import axios from 'axios'
export default {
  data: function () {
    return {
      articlesByCategory: []
    }
  },
  props: ['category'],
  mounted () {
    axios
      .get(`${process.env.VUE_APP_API}/api/articles/byCategories?categories=${this.category.id}`)
      .then(response => (
        this.articlesByCategory = response.data
      )
      )
  }
}
</script>

<style scoped>
  .line {
    display: flex;
    background: #9D00FF 0% 0% no-repeat padding-box;
    border: 2px solid #F600FF;
    border-radius: 10px;
    margin: 6px;
    color: white;
    align-content:left;
  }
  .column {
    padding: 5px;
    text-align: left;
    flex-basis: 0;
    flex-grow: 1;
    min-width: 0;
    max-width: 100%;
  }
</style>
