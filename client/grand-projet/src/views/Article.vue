<template>
  <div>
    <div class="wrapper">
      <button @click="activeEdition()" class='updateArticle' v-if="article.author.id == this.$store.getters.GET_CONNECTED_USER">
        <img v-if="this.articleEdition === false" src="@/assets/edit-vert.png"/>
      </button>
      <myArticle v-if="!this.articleEdition" :article=article ></myArticle>
      <articleEditor v-if="this.articleEdition" :article=article></articleEditor>
      <comments v-if="!this.articleEdition" :comments=comments ></comments>
    </div>
  </div>
</template>

<script>
import myArticle from '@/components/Article/article.vue'
import comments from '@/components/Article/comment-section'
import axios from 'axios'
import articleEditor from '@/components/Article/article-editor'

export default {
  data: function () {
    return {
      articleEdition: false,
      article: {},
      id: this.$route.params.id,
      comments: []
    }
  },
  components: {
    myArticle,
    comments,
    articleEditor

  },
  methods: {
    activeEdition () {
      if (this.articleEdition === false) {
        this.articleEdition = true
      } else {
        this.articleEdition = false
      }
    }
  },
  mounted () {
    axios
      .get(`${process.env.VUE_APP_API}/api/articles/${this.id}`)
      .then(response => {
        console.log(response)
        this.article = response.data
        this.comments = response.data.comments
      }
      )
  }
}

</script>

<style lang="scss" scoped>
.wrapper {
  margin-bottom: 10%;
}

img {
  height: 50px;
  width: 50px;
  cursor: pointer;;
}
button {
  height: 50px;
  width: 50px;
  border-radius: 50%;
  padding: 0px;
  border: none;
  background-color: transparent;
  margin-left: 10px;
  left: 1429px;
  cursor: pointer;
}
.updateArticle {
  position: absolute;
  top: 360px;
  margin-top: 10px;
}

</style>
