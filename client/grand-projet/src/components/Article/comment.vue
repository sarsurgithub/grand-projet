<template>
  <div class='boite'>
    <div class='title' v-html="comment.title"></div>
    <div class='text' v-html="comment.content"></div>
    <div class='author'> Written by
      <router-link class='link' :to="{ name: 'TheirProfile', params: { id : comment.author.id }}">
        {{comment.author.username}}
      </router-link>
    </div>
    <hr>
  </div>
</template>

<script>
export default {
  props: ['comment']
}
</script>

<style scoped>
.author {
  color: black;
  text-align: right;
  padding: 10px;
}
a {
  color: #05FFA1;
}
</style>
