<template>
    <div class='card'>
      <div class='form'>
        <label for="uname">Username: </label>
        <input v-model="username" type="text" id="uname" name="username" placeholder="Your username..">

        <label for="pword">Password: </label>
        <input v-model="password" type="password" id="pword" name="password" placeholder="Your password..">

        <button type="submit" @click="formSubmit()">
          login
        </button>
        <a href='http://localhost:8080/createAccount' class='createAccount'>
        create an account
        </a>
      </div>
    </div>
</template>

<script>
import axios from 'axios'
import VueJwtDecode from 'vue-jwt-decode'

export default {
  data: function () {
    return {
      username: null,
      password: null,
      decoded: null,
      token: null,
      connectedUserId: null
    }
  },
  methods: {
    async formSubmit () {
      console.log('nous entrons dans la fonction formSubmit')

      axios.post('http://localhost:8081/api/login', {
        username: this.username,
        password: this.password
      })
        .then((response) => {
          this.token = response.headers.authorization
          this.token = this.token.substring(7)

          // set the connected User Id globally
          this.decoded = VueJwtDecode.decode(this.token)
          this.connectedUserId = this.decoded.sub
          this.$store.dispatch('setConnectedUser', this.connectedUserId)
          console.log(this.$store.getters.GET_CONNECTED_USER)

          // set the token globally
          this.$store.dispatch('setAuthToken', this.token)
          console.log(this.$store.getters.GET_AUTH_TOKEN)
        })

      console.log(this.username)

      // remettre toutes les valeurs à leur origine, maybe rediriger vers la page de l'article ? ou page de l'auteur ?
      this.username = null
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
