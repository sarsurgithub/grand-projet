<template>
  <div id="app">
    <div class='header'>
      <h1 class='title'>Convergence:</h1>
      <h2 class='subtitle'> the state of converging. </h2>
    </div>
    <div :class="{
      'pink-navbar': isPink ,
      'purple-navbar': isPurple,
      'blue-navbar': isBlue,
      'yellow-navbar': isYellow,
      'green-navbar': isGreen
      }"
    id="nav">
      <router-link to="/" class="navItem">Home</router-link> |
      <router-link to="/categories" class="navItem">Categories</router-link> |
      <router-link to="/articlesByDate" class="navItem">Articles by Date</router-link> |
      <router-link to="/myProfile" v-if='$store.getters.GET_CONNECTED_USER' class="navItem">Profile</router-link>
      <router-link to="/Auth" class="navItem" v-if='$store.getters.GET_CONNECTED_USER === null'>Login</router-link>
      <button class="logout" v-if='$store.getters.GET_CONNECTED_USER' @click="$store.dispatch('setConnectedUser', null)" >Logout</button>
    </div>
    <router-view/>
  </div>
</template>

<script>
export default {
  computed: {
    isPink: function () {
      return this.$route.name === 'Categories' || this.$route.name === 'ArticlesByDate' || this.$route.name === 'Category'
    },
    isPurple: function () {
      return this.$route.name === 'Home'
    },
    isBlue: function () {
      return this.$route.name === 'MyProfile' || this.$route.name === 'TheirProfile' || this.$route.name === 'Auth' || this.$route.name === 'CreateAccount'
    },
    isYellow: function () {
      return this.$route.name === 'CreateArticle'
    },
    isGreen: function () {
      return this.$route.name === 'Article'
    }
  }
}
</script>
<style lang="scss">
button {
  outline: none;
}
.logout {
  margin-left: 5%;
}
#app {
  font-family: hack,sans-serif;
  font-weight: 400;
  font-style: normal;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: white;
}
body {
  background-color: black;
  margin: 0px;
}

.header {
  width:100%;
  background-color: white;
  height: 10%;
  padding-top: 5%;
  padding-bottom: 5%;
  margin: 0px;

}

.title {
  size: 16px;
  color: black;
  margin: 0px;
}
.subtitle {
  size: 8px;
  color: black;
  margin: 0px;
}
.purple-navbar {
  padding: 30px;
  color: #9D20FF;
  a {
    text-decoration: none;
    font-weight: bold;
    color: #9D20FF;
    padding: 5px;
    border: 3px solid #9D20FF;
    border-radius: 10px;

    &.router-link-exact-active {
      color: black;
      background-color :#9D20FF ;
    }
  }
    button {
    font-family: hack,sans-serif;
    color: #9D20FF;
    padding: 5px;
    border: 3px solid #9D20FF;
    background-color: black;
    border-radius: 10px;
    font-weight: bold;
    font-size: 16px;
  }
  button:hover {
    color: black;
    background-color: #9D20FF;
  }
}
.pink-navbar {
  padding:30px;
  color: #F51FFF;
  a {
    text-decoration: none;
    font-weight: bold;
    color: #F51FFF;
    padding: 5px;
    border: 3px solid #F51FFF;
    border-radius: 10px;

    &.router-link-exact-active {
      color: black;
      background-color :#F51FFF ;
    }
  }
    button {
    font-family: hack,sans-serif;
    color: #F51FFF;
    padding: 5px;
    border: 3px solid #F51FFF;
    background-color: black;
    border-radius: 10px;
    font-weight: bold;
    font-size: 16px;
  }
  button:hover {
    color: black;
    background-color: #F51FFF;
  }
}
.blue-navbar {
  padding:30px;
  color: #05FFFF;
  a, button {
    text-decoration: none;
    font-weight: bold;
    color: #05FFFF;
    padding: 5px;
    border: 3px solid #05FFFF;
    border-radius: 10px;
    font-size: 16px;
    background-color: black;

    &.router-link-exact-active {
      color: black;
      background-color :#05FFFF ;
    }
  }
  button {
    font-family: hack,sans-serif;
    color: #05FFFF;
    padding: 5px;
    border: 3px solid #05FFFF;
    background-color: black;
    border-radius: 10px;
    font-weight: bold;
    font-size: 16px;
  }
  button:hover {
    color: black;
    background-color: #05FFFF;
  }
}
.yellow-navbar {
  padding:30px;
  color: #FFFE00;
  a {
    text-decoration: none;
    font-weight: bold;
    color: #FFFE00;
    padding: 5px;
    border: 3px solid #FFFE00;
    border-radius: 10px;

    &.router-link-exact-active {
      color: black;
      background-color :#FFFE00 ;
    }
  }
  button {
    font-family: hack,sans-serif;
    color: #FFFE00;
    padding: 5px;
    border: 3px solid #FFFE00;
    background-color: black;
    border-radius: 10px;
    font-weight: bold;
    font-size: 16px;
  }
  button:hover {
    color: black;
    background-color: #FFFE00;
  }
}
.green-navbar {
  padding:30px;
  color: #05FFA1;
  a {
    text-decoration: none;
    font-weight: bold;
    color: #05FFA1;
    padding: 5px;
    border: 3px solid #05FFA1;
    border-radius: 10px;

    &.router-link-exact-active {
      color: black;
      background-color :#05FFA1 ;
    }
  }
  button {
    font-family: hack,sans-serif;
    color: #05FFA1;
    padding: 5px;
    border: 3px solid #05FFA1;
    background-color: black;
    border-radius: 10px;
    font-weight: bold;
    font-size: 16px;
  }
  button:hover {
    color: black;
    background-color: #05FFA1;
  }
}
</style>
