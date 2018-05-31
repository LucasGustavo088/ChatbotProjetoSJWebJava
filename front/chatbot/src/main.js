// The following line loads the standalone build of Vue instead of the runtime-only build,
// so you don't have to do: import Vue from 'vue/dist/vue'
// This is done with the browser options. For the config, see package.json
import Vue from 'vue';
import App from './App.vue';
import VueRouter from 'vue-router';
import Home from './components/Home.vue';
import Dashboard from './components/Dashboard.vue';
import Atendimento from './components/Atendimento.vue';
import Login from './components/Login.vue';

Vue.use(VueRouter);

const routes = [
  { path: '/dashboard/home', component: Dashboard},
  { path: '/dashboard/atendimento', component: Atendimento},
  { path: '/login', component: Login},
  { path: '/', component: Home},
];

const router = new VueRouter({
    routes,
    // mode: 'history'
});

new Vue({
    el: '#app',
    router,
    render: h => h(App)
});