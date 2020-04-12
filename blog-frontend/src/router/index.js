import Vue from 'vue'
import VueRouter from 'vue-router'
import topnav from "../components/topnav/index";
import carousel from "../views/carousel/index";
import articlelist from "../views/article/list/index";
import sidebar from "../components/sidebar/index";
import article from "../views/article/index";
import about from "../views/about/index";
import framework from "../views/framework/index";
import main from '../views/main/index'
import archive from '../views/archive/index'
import messageBoard from '../views/messageBoard/index'

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        redirect:'main'
    },
    {
        path: '/topnav',
        name: 'topnav',
        component: topnav
    }, {
        path: '/carousel',
        name: 'carousel',
        component: carousel
    },  {
        path: '/sidebar',
        name: 'sidebar',
        component: sidebar
    }, {
        path: '/main',
        name: 'main',
        component: main
    },{
        path: '/framework',
        name: 'framework',
        component: framework,
        children:[{
            path: '/article',
            name: 'article',
            component: article
        },{
            path: '/articlelist',
            name: 'articlelist',
            component: articlelist
        },{
            path: '/about',
            name: 'about',
            component: about
        },{
                path: '/archive',
                name: 'archive',
                component: archive
            },{
            path: '/messageBoard',
            name: 'messageBoard',
            component: messageBoard
            }
        ]
    }
]

const router = new VueRouter({
    routes
})

export default router
