<template>
  <div id="app">
    <div class='header'>
      <h1 class='title'>Convergence:</h1>
      <h2 class='subtitle'> the state of converging. </h2>
    </div>
    <router-view/>
  </div>
</template>

<style lang="scss">
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

</style>
