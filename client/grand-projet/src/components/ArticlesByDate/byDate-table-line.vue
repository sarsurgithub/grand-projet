<template>
<router-link class='link' :to="{ name: 'Article', params: { id : article.id }}">
  <div class = 'line'>
      <div class='column'> pas encore </div>
      <div class='column'> {{article.author.username}} </div>
      <div class='column' v-html="article.title"></div>
  </div>
  </router-link>
</template>

<script>
export default {
  props: ['article']
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
  p {
    margin: 0px;
    color: red;
  }
</style>

<style>
p {
  margin:0px;
}
</style>
