<template>
    <div class='card'>

        <p>Username: </p>
        <input v-model="username" type="text" id="uname" name="username" placeholder="Your username..">

        <p >First Name: </p>
        <input v-model="firstName" type="text" id="fname" name="firstName" placeholder="Your first name..">

        <p>Last Name: </p>
        <input v-model="lastName" type="text" id="lname" name="lastName" placeholder="Your last name..">

        <p>E-mail: </p>
        <input v-model="email" type="text" id="email" name="email" placeholder="Your email..">

        <p>Password: </p>
        <input v-model="password" type="text" id="pword" name="password" placeholder="Your password..">

        <button @click="formSubmit()" type="submit" >
          Create Account
        </button>

    </div>
</template>

<script>
import axios from 'axios'
export default {
  data: function () {
    return {
      username: null,
      firstName: null,
      lastName: null,
      email: null,
      password: null
    }
  },
  methods: {

    async formSubmit () {
      console.log('nous entrons dans la fonction formSubmit')

      axios.post('http://localhost:8081/api/users', {
        username: this.username,
        firstName: this.firstName,
        lastName: this.lastName,
        email: this.email,
        password: this.password
      })
      console.log(this.username)

      // remettre toutes les valeurs à leur origine, maybe rediriger vers la page de l'article ? ou page de l'auteur ?
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
.createAccount {
  font-size: 12px;
  font-style: italic;
  color: lightslategray;
  padding: 15px;
  text-align: center ;
}
a {
  padding: 20px;
  text-decoration: none;
}
.card {
  background: #FFFFFF;
  border: 5px solid #05FFFF;
  border-radius: 20px;
  text-align: left;
  margin: 5% 30%;
  color: black;
}
p {
    padding: 20px;
}
form {
  padding: 20px;
}
input[type=text] {
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
