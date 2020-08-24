<template>
  <div>
    <div class="wrapper">
      <articles></articles>
    </div>
  </div>
</template>
<script>
import articles from '@/components/Category/articles-byCategory'

export default {
  data: function () {
    return {
      article: {},
      id: this.$route.params.id,
      comments: []
    }
  },
  components: {
    articles
  }
}

</script>

<style lang="scss" scoped>
</style>
