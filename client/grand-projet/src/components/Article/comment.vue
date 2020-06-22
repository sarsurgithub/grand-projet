<template>
  <div class='wrapper'>
    <div class='title'>{{comment.title}}</div>
    <div class='text'>{{comment.content}}</div>
  </div>
</template>

<script>
export default {
  props: ['comment']
}
</script>

<style scoped>
.title{
  color: white;
  font-weight:800;
}
</style>
