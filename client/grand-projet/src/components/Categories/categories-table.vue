<template>
  <div class='wrapper'>
    <div class='table-header'>
      <strong> Name </strong>
      <strong> Number of Articles </strong>
    </div>
    <div v-for="category in categories" :key="category.id">
      <table-line :category=category ></table-line>
    </div>
  </div>
</template>

<script>

import tableLine from '@/components/Categories/categories-table-line.vue'

import axios from 'axios'

export default {
  data: function () {
    return {
      categories: []
    }
  },
  components: {
    tableLine
  },
  mounted () {
    axios
      .get(`${process.env.VUE_APP_API}/api/categories`)
      .then(response => (
        this.categories = response.data
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
    color: white;
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
