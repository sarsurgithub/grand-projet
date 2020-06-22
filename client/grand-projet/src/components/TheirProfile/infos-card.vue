<template>
  <div class='wrapper'>
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
  .wrapper {
    top: 464px;
    left: 57px;
    width: 338px;
    height: 129px;
    background: #FFFFFF 0% 0% no-repeat padding-box;
    border: 5px solid #05FFFF;
    border-radius: 20px;
    opacity: 1;
    margin-left: 7%;
    margin-right: 7%;
    justify-content: space-evenly;
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
