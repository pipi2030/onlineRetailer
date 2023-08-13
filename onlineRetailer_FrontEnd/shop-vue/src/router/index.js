import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from "../views/HomeView"
import SearchView from "../views/SearchView"
import CartView from "../views/CartView"
import CenterView from "../views/CenterView"
import RegisterView from "../views/RegisterView"
import LoginView from "../views/LoginView"
import ProductView from "../views/ProductView"
import DetailView from "../views/DetailView"
Vue.use(VueRouter);

const routes = [
    {
    path: '/',
    name: 'home',
    component: HomeView
    },
    {
      path: '/search',
      name: 'search',
      component: SearchView
    },
    {
      path: '/cart',
      name: 'cart',
      component: CartView
    },
    {
      path: '/center',
      name: 'center',
      component: CenterView
    },
    {
        path: '/register',
        name: 'register',
        component: RegisterView
    },
    {
        path: '/login',
        name: 'login',
        component: LoginView
    },
    {
        path: '/product',
        name: 'product',
        component: ProductView
    },
    {
        path: '/detail',
        name: 'detail',
        component: DetailView
    },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
});

//避免重复点击造成的报错
const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location){
    return originalPush.call(this, location).catch(err => err)
};

export default router
