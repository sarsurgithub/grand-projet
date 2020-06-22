<template>
  <div class="wrapper">
    <myArticle v-bind='{article}'></myArticle>
    <comments v-bind='[comments]'></comments>
  </div>
</template>

<script>

import myArticle from '@/components/Article/article.vue'
import comments from '@/components/Article/comment-section'

export default {
  data: function () {
    return {
      article: {
        id: 1, title: 'ACAB', content: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed interdum, neque vitae porta auctor, sapien nisi malesuada ex, id volutpat mauris felis condimentum massa. Quisque ultrices rhoncus dui et consectetur. Aliquam erat volutpat. Donec viverra metus ac diam lacinia dictum. Sed quis arcu vitae urna euismod auctor. Cras mollis, odio id vulputate tincidunt, purus velit scelerisque eros, sit amet ultricies lorem magna vitae mauris. Vestibulum dignissim vulputate sem, vitae vestibulum massa fermentum id. Curabitur sed nisi in dui pellentesque mattis congue eu sapien. In venenatis est nulla, non sagittis risus consectetur id. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.Aenean sodales justo id gravida eleifend. Etiam venenatis sapien mi, facilisis dictum lorem suscipit non. In scelerisque est ac varius pulvinar. Ut dolor ligula, mattis et semper molestie, placerat sit amet risus. Ut tincidunt venenatis viverra. Nunc tincidunt enim magna, ac tristique mauris bibendum ut. Phasellus vel lacinia mauris.'
      }
    }
  },
  components: {
    myArticle,
    comments

  }
}

</script>

<style scoped>
.wrapper {
  padding: 15px 20% 15px 20%;
}
</style>
