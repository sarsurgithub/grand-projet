<template>
  <div class='wrapper'>
    <div v-for="comment in comments" :key="comment.id">
      <comment :comment=comment></comment>
    </div>
  </div>
</template>

<script>

import comment from '@/components/Article/comment.vue'

export default {
  components: {
    comment
  },
  props: ['comments']
}

</script>
