<template>
  <div class='wrapper'>
    <div class="articlesTitle"> My Articles
      </div>
    <div class='article' v-for="article in articles" :key="article.id">
      {{article.title}}
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
  .wrapper {
    top: 470px;
    left: 770px;
    width: 917px;
    border: 5px solid #05FFFF;
    background-color: white;
    border-radius: 20px;
    display: flex;
    flex-direction: column ;
    position: absolute;
  }
</style>
