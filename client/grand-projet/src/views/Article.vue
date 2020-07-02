<template>
  <div>
    <div id="nav">
        <router-link to="/" class="navItem">Home</router-link> |
        <router-link to="/categories" class="navItem">Categories</router-link> |
        <router-link to="/articlesByDate" class="navItem">Articles by Date</router-link> |
        <router-link to="/myProfile" class="navItem">Profile</router-link> |
    </div>
    <div class="wrapper">
      <myArticle :article=article ></myArticle>
      <comments :comments=comments ></comments>
    </div>
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
      id: this.$route.params.id,
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

</style>
