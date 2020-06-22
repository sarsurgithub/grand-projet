<template>
  <div class='wrapper'>
    <div v-for="comment in comments" :key="comment.id">
      <comment v-bind="{comment}"></comment>
    </div>
  </div>
</template>

<script>

import comment from '@/components/Article/comment.vue'

export default {
  components: {
    comment
  },
  data: function () {
    return {
      comments: [
        { id: 1, title: 'comment 1', content: 'this article is shit' },
        { id: 2, title: 'comment 2', content: 'this article is great' },
        { id: 3, title: 'comment 3', content: 'this article is okay' }
      ]
    }
  }
}

</script>
