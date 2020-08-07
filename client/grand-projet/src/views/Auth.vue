<template>
  <div>
    <div id="nav">
        <router-link to="/" class="navItem">Home</router-link> |
        <router-link to="/categories" class="navItem">Categories</router-link> |
        <router-link to="/articlesByDate" class="navItem">Articles by Date</router-link> |
        <router-link to="/myProfile" class="navItem">Profile</router-link>
      <router-link to="/Auth" class="navItem" v-if='this.$store.getters.GET_CONNECTED_USER === false'> | Login</router-link>
    </div>
    <formulaire></formulaire>
  </div>
</template>

<script>
import formulaire from '@/components/Auth/formulaire.vue'

export default {
  components: {
    formulaire

  }
}
</script>

<style lang="scss" scoped>
#nav {
  padding: 30px;
  color: #05FFFF;
  a {
    font-weight: bold;
    color: #05FFFF;

    &.router-link-exact-active {
      color: black;
      background-color :#05FFFF ;
    }
  }
}

.navItem {
  padding: 5px;
  border: 3px solid #05FFFF;
  border-radius: 10px;

}

.wrapper {
  margin-bottom: 10%;
}

</style>
