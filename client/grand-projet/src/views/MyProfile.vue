<template>
  <div class="wrapper">
    <infos-card :user=user></infos-card>
    <article-list :articles=articles></article-list>
  </div>
</template>

<script>

import articleList from '@/components/myProfile/myArticles.vue'
import infosCard from '@/components/myProfile/myInfos.vue'

export default {
  data: function () {
    return {
      user: {
        id: 1, username: 'Jean-Ivre', firstName: 'Jean-Yve', lastName: 'Coco', eMail: 'coco@email.com', password: '**********'
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
</style>
