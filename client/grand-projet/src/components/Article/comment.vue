<template>
  <div class='boite'>
    <div class='title'>{{comment.title}}</div>
    <div class='text'>{{comment.content}}</div>
    <hr>
  </div>
</template>

<script>
export default {
  props: ['comment']
}
</script>

<style scoped>
</style>
