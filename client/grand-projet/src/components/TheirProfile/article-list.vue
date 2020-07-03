<template>
  <div class='boite'>
    <div class="articlesTitle"> Articles
      </div>
    <div class='article' v-for="article in articles" :key="article.id">
      <router-link class='link' :to="{ name: 'Article', params: { id : article.id }}">
      {{article.title}}
      </router-link>
    </div>
  </div>
</template>

<script>
export default {
  props: ['articles']
}
</script>

<style scoped>
  .articlesTitle {
    color: black;
    font-size: 16px;
    font-weight: bold;
    padding: 10px 5px 10px 5px;

  }
  .article{
    padding-top: 5px;
    padding-bottom: 5px;
    border-top: 3px solid #05FFFF;
    padding-left: 150px;
    padding-right: 150px;
    color: black;
  }
  .boite {
    border: 5px solid #05FFFF;
    background-color: white;
    border-radius: 20px;
    display: flex;
    flex-direction: column ;
    width: 717px;
    min-height: 571px;
  }
</style>
