<template>
  <div class="home">
    <h2> ça va être la merde de faire cette page </h2>
    <validation-button/>
  </div>
</template>

<script>
// @ is an alias to /src
import validationButton from '@/components/validation-button.vue'

export default {
  name: 'Home',
  components: {
    validationButton
  }
}
</script>
