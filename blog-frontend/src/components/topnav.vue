<template>
    <div>
        <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect"
                 active-text-color="#409EFF" text-color="#303133">
            <el-menu-item class="hi" index="0"></el-menu-item>
            <el-menu-item index="1"><router-link to="/main">首页</router-link></el-menu-item>
            <el-menu-item></el-menu-item>
            <el-submenu index="2">
                <template slot="title">文章</template>
                <el-menu-item index="2-1"><router-link to="/articlelist">Java</router-link></el-menu-item>
                <el-menu-item index="2-2"><router-link to="/articlelist">Spring</router-link></el-menu-item>
                <el-menu-item index="2-3"><router-link to="/articlelist">Linux</router-link></el-menu-item>
                <el-menu-item index="2-4"><router-link to="/articlelist">常用demo</router-link></el-menu-item>
            </el-submenu>
            <el-menu-item></el-menu-item>
            <el-menu-item index="3"><router-link to="/blank">归档</router-link></el-menu-item>
            <el-menu-item></el-menu-item>
            <el-menu-item index="4"><router-link to="/blank">留言</router-link></el-menu-item>
            <el-menu-item></el-menu-item>
            <el-menu-item index="5"><router-link to="/blank">关于</router-link></el-menu-item>
            <el-menu-item index="6"><a href="#">订单管理</a></el-menu-item>
            <i style="float: right;margin-top: 15px;" class="el-icon-s-custom"><a
                    href="">进入后台</a></i>
        </el-menu>
    </div>
</template>

<script>
    export default {
        name: "topnav",
        data() {
            return {
                activeIndex: '1',
                activeIndex2: '1'
            };
        },
        methods: {
            handleSelect(key, keyPath) {
                console.log(key, keyPath);
            }
        }
    }
</script>

<style lang="less" scoped>
    .hi {
        width: 400px;
    }

    .el-icon-s-custom a:hover {
        color: #00A2FF;
    }

</style>
