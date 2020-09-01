<template>
  <div class='page'>
    <div class='big'>
      <div class='articleEntier'>
        <h1 v-html="article.title" class='title'></h1>
        <div v-html="article.content" class='text'></div>
        <div class='tags'>
          <router-link class='tag' v-for="tag in article.categories" :key="tag.id" :to="{ name: 'Category', params: { id : tag.id }}">
            <tag :tag=tag ></tag>
          </router-link>
        </div>
      </div>
      <button class='like' @click="like()" >
        <img src='@/assets/greenLike.png'>
        {{article.views}}
      </button>
      <div class='author'> Written by
        <router-link class='link' :to="{ name: 'TheirProfile', params: { id : article.author.id }}">
          {{article.author.username}}
        </router-link>
        -{{article.createdAt.dayOfMonth}}.{{article.createdAt.monthOfYear}}.{{article.createdAt.year}}
      </div>
    </div>
  </div>
</template>

<script>
import tag from '@/components/Article/tag.vue'
import axios from 'axios'

export default {
  components: {
    tag
  },
  props: ['article'],
  data () {
    return {
      liked: false
    }
  },
  methods: {

    like () {
      if (this.liked) {
        axios
          .get(`${process.ENV.VUE_APP_API}/api/articles/${this.article.id}/deleteLike`)
      } else {
        axios
          .get(`${process.ENV.VUE_APP_API}/api/articles/${this.article.id}/addLike`)
      }
    }
  }
}
</script>

<style scoped>
body {
  margin:0px;
}
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
  color: black;
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
img {
  height: 50px;
  width: 50px;
  cursor: pointer;;
}
button {
    margin-top: 10px;
    height: 50px;
    width: 50px;
    border-radius: 50%;
    padding: 0px;
    border: none;
    background-color: transparent;
    cursor: pointer;
  }
</style>

<style>
  .ql-align-justify {
    text-align: justify;
  }
</style>
