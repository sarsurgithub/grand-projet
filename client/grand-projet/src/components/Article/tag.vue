<template>
  <div class='wrapper'>
    <div class='tag'>{{tag.name}}</div>
  </div>
</template>

<script>
export default {
  props: ['tag']
}
</script>

<style scoped>
.tag{
  background-color: #05FFA1;
  border-radius: 5px;
  display: flex;
  padding: 3px 5px;
  margin: 2px;
  font-size: 16px;
  color: black;
}
</style>
