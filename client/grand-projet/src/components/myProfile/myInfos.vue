<template>
  <div class='boite' id='profileInfos'>
      <img class='profilePic' src="@/assets/yellowValidation.png"/>
      <p> Username: {{user.username}} </p>
      <p> Firstname: {{user.firstName}} </p>
      <p> Lastname: {{user.lastName}} </p>
      <p> e-mail: {{user.email}} </p>
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
    text-align: left;
    margin-right: 5%;
  }

  #profileInfos {
    flex-direction: column;
    justify-items: start;
    justify-content: start;
    align-items: start;
  }

  img {
    width: 100px;
    height: 100px;
    padding-top: 15px;
    padding-right:15px;
    align-self: center;
    text-align: center;
  }
  p {
    color: black;
    padding: 20px;
    font-weight: bold;
  }
</style>
