<template>
  <div class='wrapper' id='profileInfos'>
      <img class='profilePic' src="@/assets/yellowValidation.png"/>
      <p> Username: {{user.username}} </p>
      <p> Firstname: {{user.firstName}} </p>
      <p> Lastname: {{user.lastName}} </p>
      <p> e-mail: {{user.eMail}} </p>
      <p> Password: {{user.password}} </p>
  </div>
</template>

<script>
export default {
  props: ['user']
}
</script>

<style scoped>
  .wrapper {
    top: 470px;
    left: 139px;
    width: 434px;
    background: #FFFFFF 0% 0% no-repeat padding-box;
    border: 5px solid #05FFFF;
    border-radius: 20px;
    opacity: 1;
    position:absolute;
  }

  #profileInfos {
    flex-direction: column;
    justify-items: start;
  }

  img {
    width: 100px;
    height: 100px;
    padding-top: 15px;
    padding-right:15px;
  }
  p {
    color: black;
    padding-top: 10px;
    font-weight: bold;
  }
</style>
