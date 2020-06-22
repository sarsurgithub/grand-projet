<template>
  <div class="wrapper">
    <infos-card :user=user></infos-card>
    <article-list id="articleCard" :articles=articles></article-list>
  </div>
</template>

<script>

import articleList from '@/components/TheirProfile/article-list.vue'
import infosCard from '@/components/TheirProfile/infos-card.vue'

export default {
  data: function () {
    return {
      user: {
        id: 1, username: 'Jean-Ivre'
      },
      articles: [
        { id: 1, title: 'ACAB' },
        { id: 2, title: 'Atomic Design' },
        { id: 3, title: 'La place des poules' }
      ]
    }
  },
  components: {
    articleList,
    infosCard
  }
}

</script>

<style scoped>
.wrapper {
  display: flex;
}
#articleCard {
  border: 5px solid #05FFFF;
  background-color: white;
  border-radius: 20px;
}

</style>
