import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: '首页',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '首页', icon: 'home' }
    }]
  },

  {
    path: '/blog',
    component: Layout,
    redirect: '/blog/article',
    name: 'blog',
    meta: { title: '博客管理', icon: 'blog' },
    children: [
      {
        path: '/article',
        name: 'Article',
        component: () => import('@/views/blog/article/index'),
        meta: { title: '博文管理', icon: 'wblog' }
      },
      {
        path: '/edit',
        name: 'EditForm',
        component: () => import('@/views/blog/article/edit'),
        meta: { title: '修改文章', activeMenu: '/edit' },
        hidden: true
      },
      {
        path: '/detail',
        name: 'ContentDetail',
        component: () => import('@/views/blog/article/components/ContentDetail'),
        meta: { title: '文章详情', activeMenu: '/detail' },
        hidden: true
      },
      {
        path: '/create',
        name: 'CreateArticle',
        component: () => import('@/views/blog/article/create'),
        meta: { title: '添加文章', activeMenu: '/article' },
        hidden: true
      },
      {
        path: '/category',
        name: 'Category',
        component: () => import('@/views/blog/category/index'),
        meta: { title: '分类管理', icon: 'category' }
      },
      {
        path: '/tag',
        name: 'Tag',
        component: () => import('@/views/blog/tag/index'),
        meta: { title: '标签管理', icon: 'tag' }
      },
      {
        path: '/comment',
        name: 'Comment',
        component: () => import('@/views/blog/comment/index'),
        meta: { title: '评论管理', icon: 'comment' }
      }
    ]
  },

  {
    path: '/log',
    component: Layout,
    redirect: '/log/operateLog',
    name: 'Log',
    meta: { title: '日志管理', icon: 'log' },
    children: [
      {
        path: '/operateLog',
        name: 'OperateLog',
        component: () => import('@/views/log/operate/index'),
        meta: { title: '操作日志', icon: 'operate' }
      },
      {
        path: '/visitLog',
        name: 'VisitLog',
        component: () => import('@/views/log/visit/index'),
        meta: { title: '访问日志', icon: 'visit' }
      },
      {
        path: '/loginLog',
        name: 'LoginLog',
        component: () => import('@/views/log/login/index'),
        meta: { title: '登录日志', icon: 'login' }
      }
    ]
  },

  {
    path: '/monitor',
    component: Layout,
    redirect: '/monitor/online',
    name: 'Monitor',
    meta: { title: '系统监控', icon: 'monitor' },
    children: [
      {
        path: '/online',
        name: 'Online',
        component: () => import('@/views/monitor/online/index'),
        meta: { title: '在线用户', icon: 'online' }
      },
      {
        path: '/data',
        name: 'Data',
        component: () => import('@/views/monitor/data/index'),
        meta: { title: '数据监控', icon: 'data' }
      },
      {
        path: '/serve',
        name: 'Serve',
        component: () => import('@/views/monitor/serve/index'),
        meta: { title: '服务监控', icon: 'serve' }
      }
    ]
  },

  {
    path: '/system',
    component: Layout,
    redirect: '/system/user',
    name: 'System',
    meta: { title: '系统管理', icon: 'system' },
    children: [
      {
        path: '/user',
        name: 'User',
        component: () => import('@/views/system/user/index'),
        meta: { title: '用户管理', icon: 'user1' }
      },
      {
        path: '/editUser',
        name: 'EditUser',
        component: () => import('@/views/system/user/user'),
        meta: { title: '编辑用户', activeMenu: '/editUser' },
        hidden: true
      },
      {
        path: '/carousel',
        name: 'Carousel',
        component: () => import('@/views/system/carousel/index'),
        meta: { title: '轮播图', icon: 'carousel' }
      },
      {
        path: '/notice',
        name: 'Notice',
        component: () => import('@/views/system/notice/index'),
        meta: { title: '通知公告', icon: 'notice' }
      },
      {
        path: '/interface',
        name: 'Interface',
        component: () => import('@/views/system/interface/index'),
        meta: { title: '系统接口', icon: 'interface' }
      }
    ]
  },
  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
