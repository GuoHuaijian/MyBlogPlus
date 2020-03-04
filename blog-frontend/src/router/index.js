import Vue from 'vue'
import VueRouter from 'vue-router'
import hello from "../components/hello";
import topnav from "../components/topnav";
import carousel from "../components/carousel";
import articlelist from "../components/articlelist";
import sidebar from "../components/sidebar";
import article from "../components/article";
import blank from "../components/blank";
import indextext from "../views/indextext";
import main from '../views/main'

Vue.use(VueRouter)

const routes = [
    {
        path: '/hello',
        name: 'hello',
        component: hello
    }, {
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
        path: '/indextext',
        name: 'indextext',
        component: indextext,
        children:[{
            path: '/article',
            name: 'article',
            component: article
        },{
            path: '/articlelist',
            name: 'articlelist',
            component: articlelist
        },{
            path: '/blank',
            name: 'blank',
            component: blank
        }
        ]
    }
]

const router = new VueRouter({
    routes
})

export default router
