<template>
  <div class= 'validation-button' >
    <button>
      <img class='yellow-button' src='../assets/yellowValidation.png'/>
    </button>
  </div>
</template>

<script>
export default {
  name: 'validation-button'
}
</script>

<style scoped>
  button, .yellow-button {
    height: 50px;
    width: 50px;
    border-radius: 50%;
    padding: 0px;
    border: none;
  }
</style>
