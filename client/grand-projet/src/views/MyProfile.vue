<template>
  <div>
    <div class="wrapper">
      <infos-card v-if="!this.edition"  :user=user></infos-card>
      <updateInfos v-if="this.edition" :user=user></updateInfos>
      <button @click="activeEdition()">
          <img src='@/assets/edit-bleu.png'>
        </button>
      <article-list :articles=articles></article-list>
      <router-link class='link' :to="{ name: 'CreateArticle', params: { id : user.id}}">
        <button>
          <img src='@/assets/croix-bleue.png'>
        </button>
      </router-link>
    </div>
  </div>
</template>

<script>

import articleList from '@/components/myProfile/myArticles.vue'
import infosCard from '@/components/myProfile/myInfos.vue'
import updateInfos from '@/components/myProfile/updateProfile.vue'
import axios from 'axios'

export default {
  data: function () {
    return {
      user: {},
      articles: [],
      edition: false
    }
  },
  components: {
    articleList,
    infosCard,
    updateInfos
  },
  methods: {
    activeEdition () {
      if (this.edition === false) {
        this.edition = true
      } else {
        this.edition = false
      }
    }
  },
  mounted () {
    axios
      .get(`${process.env.VUE_APP_API}/api/users/${this.$store.getters.GET_CONNECTED_USER}`)
      .then(response => {
        this.user = response.data
      })
    axios
      .get(`${process.env.VUE_APP_API}/api/articles/byUser?user=${this.$store.getters.GET_CONNECTED_USER}`)
      .then(response => {
        this.articles = response.data
      })
  }
}

</script>
<style lang="scss" scoped>

.wrapper {
  display: flex;
  align-items: flex-start;
  margin: 60px 10%;
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
  .link {
    cursor: pointer;
  }

</style>
