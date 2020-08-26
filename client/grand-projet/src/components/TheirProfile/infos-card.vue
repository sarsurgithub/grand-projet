<template>
  <div class='boite'>
    <p> {{user.username}} </p>
  </div>
</template>

<script>
export default {
  props: ['user']
}
</script>

<style scoped>
  .boite {
    border: 5px solid #05FFFF;
    border-radius: 20px;
    justify-content: space-evenly;
    padding: 10px 25px;
    margin-right: 7%;
    background-color: #05FFFF;
  }

  p {
    color: black;
    font-weight: bold;
    font-size: 32px;
  }
</style>
