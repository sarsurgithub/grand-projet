import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Article from '../views/Article.vue'
import Categories from '../views/Categories.vue'
import ArticlesByDate from '../views/ArticlesByDate.vue'
import TheirProfile from '../views/TheirProfile.vue'
import MyProfile from '../views/MyProfile.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/article',
    name: 'Article',
    component: Article
  },
  {
    path: '/categories',
    name: 'Categories',
    component: Categories
  },
  {
    path: '/articlesByDate',
    name: 'ArticlesByDate',
    component: ArticlesByDate
  },
  {
    path: '/theirProfile',
    name: 'TheirProfile',
    component: TheirProfile
  },
  {
    path: '/myProfile',
    name: 'MyProfile',
    component: MyProfile
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
