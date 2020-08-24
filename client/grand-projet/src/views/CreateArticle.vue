
<template>
  <div>
    <div>
      <articleEditor></articleEditor>
    </div>
  </div>
</template>

<script>

import articleEditor from '@/components/CreateArticle/createArticle.vue'
export default {
  components: {
    articleEditor
  }
}
</script>

<style lang="scss" scoped>

tags {
  align-self: center;
}
</style>
