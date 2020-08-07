<template>
  <div>
    <div id="nav">
        <router-link to="/" class="navItem">Home</router-link> |
        <router-link to="/categories" class="navItem">Categories</router-link> |
        <router-link to="/articlesByDate" class="navItem">Articles by Date</router-link> |
        <router-link to="/myProfile" class="navItem">Profile</router-link>
      <router-link to="/Auth" class="navItem" v-if='this.$store.getters.GET_CONNECTED_USER === false'> | Login</router-link>
    </div>
    <div class="wrapper">
      <button @click="activeEdition()" class='updateArticle' v-if="article.author.id == this.$store.getters.GET_CONNECTED_USER">
      {{buttonContent}}
      </button>
      <myArticle v-if="!this.articleEdition" :article=article ></myArticle>
      <articleEditor v-if="this.articleEdition" :article=article></articleEditor>
      <comments :comments=comments ></comments>
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
      comments: [],
      buttonContent: 'modifier article'
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
        this.buttonContent = 'valider'
      } else {
        this.articleEdition = false
        this.buttonContent = 'modifier article'
      }
    }
  },
  mounted () {
    axios
      .get(`http://localhost:8081/api/articles/${this.id}`)
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
#nav {
  padding: 30px;
  color: #05FFA1;
  a {
    font-weight: bold;
    color: #05FFA1;

    &.router-link-exact-active {
      color: black;
      background-color :#05FFA1 ;
    }
  }
}

.navItem {
  padding: 5px;
  border: 3px solid #05FFA1;
  border-radius: 10px;

}

.wrapper {
  margin-bottom: 10%;
}
.updateArticle {
  position: absolute;
  top: 360px;
  left: 1429px;
  color: #05FFA1;
  padding: 5px;
  border: 2px solid #05FFA1;
  background-color: black;
  border-radius: 5px;
  font-weight: bold;
  font-size: 16px;
}
.updateArticle:hover {
  color: black;
  background-color: #05FFA1;
}
</style>
