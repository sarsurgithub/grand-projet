<template>
  <div class='big'>
    <div class='articleEntier'>
      <h1 v-html="article.title" class='title'></h1>
      <div v-html="article.content" class='text'></div>
      <div class='tags'>
        <div class='tag' v-for="tag in article.categories" :key="tag.id">
          <tag :tag=tag></tag>
        </div>
      </div>
    </div>
    <div class='author'> Written by
      <router-link class='link' :to="{ name: 'TheirProfile', params: { id : article.author.id }}">
        {{article.author.username}}
      </router-link>
    </div>
  </div>
</template>

<script>
import tag from '@/components/Article/tag.vue'
export default {
  components: {
    tag
  },
  props: ['article']
}
</script>

<style scoped>
.title {
  size: 16px;
  font-weight: bold;
  padding: 60px;
}
.author {
  color: black;
  text-align: right;
  padding: 10px;
}
.big {
  background-color: white;
    color: black;
    border: 5px solid #05FFA1;
    border-radius: 20px;
    margin: 15px 15% 15px 15%;
}
a {
  color: #05FFA1;
}
.tags {
  display: flex;
  align-items:center;
  justify-content: center;
  padding-bottom: 30px;
}

.text{
  padding: 0px 5% 30px 5%;
}

</style>

<style>
  .ql-align-justify {
    text-align: justify;
  }
</style>
