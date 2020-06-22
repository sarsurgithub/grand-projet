<template>
  <div class='wrapper'>
    <div class='title'>{{article.title}}</div>
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
