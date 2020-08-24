<template>
  <div>
    <div class="home">
      <chart/>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import chart from '@/components/chart.vue'

export default {
  name: 'Home',
  components: {
    chart
  }
}
</script>

<style lang="scss" scoped>

</style>
