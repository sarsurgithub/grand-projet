<template>
  <div class='boite'>
    <img class='profilePic' src="@/assets/yellowValidation.png"/>
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
    background: #FFFFFF;
    border: 5px solid #05FFFF;
    border-radius: 20px;
    justify-content: space-evenly;
    padding: 25px 45px;
    margin-right: 7%;
  }

  img {
    width: 100px;
    height: 100px;
    padding-top: 15px;
    padding-right:15px;
  }
  p {
    color: black;
    padding-top: 40px;
    font-weight: bold;
  }
</style>
