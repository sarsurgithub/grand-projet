<template>
  <div>
    <div id="nav">
      <router-link to="/" class="navItem">Home</router-link> |
      <router-link to="/categories" class="navItem">Categories</router-link> |
      <router-link to="/articlesByDate" class="navItem">Articles by Date</router-link> |
      <router-link to="/myProfile" class="navItem">Profile</router-link> |
    </div>
    <div class="home">
      <h2> ça va être la merde de faire cette page </h2>
      <validation-button/>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import validationButton from '@/components/validation-button.vue'

export default {
  name: 'Home',
  components: {
    validationButton
  }
}
</script>

<style lang="scss" scoped>
#nav {
  padding: 30px;
  color: #9D20FF;
  a {
    font-weight: bold;
    color: #9D20FF;

    &.router-link-exact-active {
      color: black;
      background-color :#9D20FF ;
    }
  }
}

.navItem {
  padding: 5px;
  border: 3px solid #9D20FF;
  border-radius: 10px;

}
</style>
