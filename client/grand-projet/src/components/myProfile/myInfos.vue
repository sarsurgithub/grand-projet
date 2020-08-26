<template>
  <div class='boite' id='profileInfos'>
      <p class='bold'> Username: </p>
      <p> {{user.username}} </p>
      <p class='bold'> Firstname: </p>
      <p> {{user.firstName}} </p>
      <p class='bold'> Lastname: </p>
      <p>{{user.lastName}} </p>
      <p class='bold'> e-mail: </p>
      <p> {{user.email}} </p>
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
    min-width: 300px;
    padding: 10px;
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
    padding: 20px 50px;;
    align-self: center;
    text-align: center;
  }
  p {
    color: black;
    padding: 10px;
  }
  .bold  {
    font-weight: bold;
  }
</style>
