<template>
  <div id="app">
    <hello></hello>
  </div>
</template>

<script>
import Hello from './components/Hello.vue';
import Home from './components/Home.vue';

export default {
  name: 'app',
  components: {
    Hello
  }
}
</script>
