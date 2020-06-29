<template>
  <div class='wrapper'>

    <div class='author'> Written by
    <router-link class='link' :to="{ name: 'TheirProfile', params: { id : article.author.id }}">
      {{article.author.username}}
    </router-link>
    </div>
    <h1 class='title'>{{article.title}}</h1>
    <div class='text'>{{article.content}}</div>
  </div>
</template>

<script>
export default {
  props: ['article']
}
</script>

<style scoped>
.title {
  color: white;
  size: 16px;
  font-weight: bold;
}
</style>
