<template>
    <div class='card'>
      <div class='form'>
        <label>Username: </label>
        <input v-model="username" type="text" id="uname" name="username" placeholder="Your username..">

        <label>First Name: </label>
        <input v-model="firstName" type="text" id="fname" name="firstName" placeholder="Your first name..">

        <label>Last Name: </label>
        <input v-model="lastName" type="text" id="lname" name="lastName" placeholder="Your last name..">

        <label>E-mail: </label>
        <input v-model="email" type="text" id="email" name="email" placeholder="Your email..">

        <label>Password: </label>
        <input v-model="password" type="password" id="pword" name="password" placeholder="Your password..">

        <button @click="formSubmit()" type="submit" >
          update data
        </button>
      </div>
    </div>
</template>

<script>
import axios from 'axios'
export default {
  props: ['user'],
  data: function () {
    return {
      username: this.user.username,
      firstName: this.user.firstName,
      lastName: this.user.lastName,
      email: this.user.email,
      password: this.user.password
    }
  },
  methods: {

    async formSubmit () {
      console.log('nous entrons dans la fonction formSubmit')

      axios.patch(`http://localhost:8081/api/users/${this.$store.getters.GET_CONNECTED_USER}`, {
        username: this.username,
        firstName: this.firstName,
        lastName: this.lastName,
        email: this.email,
        password: this.password
      })

      this.$router.push('/Auth')

      // remettre toutes les valeurs à leur origine
      this.username = null
      this.firstName = null
      this.lastName = null
      this.email = null
      this.password = null
    }
  }
}
</script>

<style scoped>
a {
  padding: 20px;
  text-decoration: none;
}
.card {
  background: #FFFFFF;
  border: 5px solid #05FFFF;
  border-radius: 20px;
  text-align: left;
  color: black;
  margin-right: 20px;
}
p {
    padding: 20px 5px;
}
.form {
  padding: 20px;
}
input, #pword {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 3px solid #05FFFF;
  border-radius: 4px;
  box-sizing: border-box;
}

button[type=submit] {
  margin: 8px 0;
  cursor: pointer;
  color: black;
  padding: 5px;
  border: 2px solid black;
  background-color: white;
  border-radius: 10px;
  font-weight: bold;
  font-size: 16px;
}

button[type=submit]:hover {
  background-color: #05FFFF;
  color: white;
  margin: 8px 0;
  cursor: pointer;
  padding: 5px;
  border: 2px solid #05FFFF;
  border-radius: 10px;
  font-weight: bold;
  font-size: 16px;
}

</style>
