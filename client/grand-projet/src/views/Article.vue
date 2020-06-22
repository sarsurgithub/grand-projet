<template>
  <div class="wrapper">
    <myArticle :article=article ></myArticle>
    <comments :comments=comments ></comments>
  </div>
</template>

<script>

import myArticle from '@/components/Article/article.vue'
import comments from '@/components/Article/comment-section'
import axios from 'axios'

export default {
  data: function () {
    return {
      article: {},
      id: 1,
      comments: []
    }
  },
  components: {
    myArticle,
    comments

  },
  mounted () {
    axios
      .get(`http://localhost:8081/api/articles/${this.id}`)
      .then(response => {
        this.article = response.data
        this.comments = response.data.comments
      }
      )
  }
}

</script>

<style scoped>
.wrapper {
  padding: 15px 20% 15px 20%;
}
</style>
