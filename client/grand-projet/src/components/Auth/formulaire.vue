<template>
    <div class='card'>
      <form>
        <label for="uname">Username: </label>
        <input type="text" id="uname" name="username" placeholder="Your username..">

        <label for="pword">Password: </label>
        <input type="text" id="pword" name="password" placeholder="Your password..">

        <input type="submit" value="Connexion">
        <a href='http://localhost:8080/createAccount' class='createAccount'>
        create an account
      </a>
      </form>
    </div>
</template>

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

input[type=submit] {
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

input[type=submit]:hover {
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
