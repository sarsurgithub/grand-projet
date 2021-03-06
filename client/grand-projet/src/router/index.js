import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Article from '../views/Article.vue'
import Categories from '../views/Categories.vue'
import ArticlesByDate from '../views/ArticlesByDate.vue'
import TheirProfile from '../views/TheirProfile.vue'
import MyProfile from '../views/MyProfile.vue'
import CreateArticle from '../views/CreateArticle'
import Category from '../views/Category.vue'
import Auth from '../views/Auth.vue'
import CreateAccount from '../views/CreateAccount.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/articles/:id',
    name: 'Article',
    component: Article
  },
  {
    path: '/categories',
    name: 'Categories',
    component: Categories
  },
  {
    path: '/user/:id/createArticle',
    name: 'CreateArticle',
    component: CreateArticle
  },
  {
    path: '/articlesByDate',
    name: 'ArticlesByDate',
    component: ArticlesByDate
  },
  {
    path: '/theirProfile/:id',
    name: 'TheirProfile',
    component: TheirProfile
  },
  {
    path: '/myProfile',
    name: 'MyProfile',
    component: MyProfile
  },
  {
    path: '/category/:id',
    name: 'Category',
    component: Category
  },
  {
    path: '/auth',
    name: 'Auth',
    component: Auth
  },
  {
    path: '/createAccount',
    name: 'CreateAccount',
    component: CreateAccount
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
