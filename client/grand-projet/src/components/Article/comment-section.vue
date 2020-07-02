<template>
  <div class='wrapper'>
    <h2 class='title'> Commentaires </h2>
    <hr>
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

<style scoped>
  .wrapper {
  background-color: white;
  color: black;
  border: 5px solid #05FFA1;
  border-radius: 20px;
  margin: 15px 15% 15px 15%;

}
.title {
  padding: 30px 30px 10px 30px;
}
</style>
