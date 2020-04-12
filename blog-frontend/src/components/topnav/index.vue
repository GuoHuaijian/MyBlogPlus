<template>
    <div>
        <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect"
                 active-text-color="#409EFF" text-color="#303133">
            <el-menu-item class="hi" index="0"></el-menu-item>
            <el-menu-item index="1"><router-link to="/main">首页</router-link></el-menu-item>
            <el-menu-item></el-menu-item>
            <el-submenu index="2">
                <template slot="title">文章</template>
                <el-menu-item index="2-1" v-for="category in categorys"><router-link to="/articlelist">{{category.categoryTitle}}
                </router-link></el-menu-item>
            </el-submenu>
            <el-menu-item></el-menu-item>
            <el-menu-item index="3"><router-link to="/archive">归档</router-link></el-menu-item>
            <el-menu-item></el-menu-item>
            <el-menu-item index="4"><router-link to="/messageBoard">留言</router-link></el-menu-item>
            <el-menu-item></el-menu-item>
            <el-menu-item index="5"><router-link to="/about">关于</router-link></el-menu-item>
            <i style="float: right;margin-top: 15px;" class="el-icon-s-custom"><a
                    href="">进入后台</a></i>
        </el-menu>
    </div>
</template>

<script>
    import {getCategory} from "../../api/all";

    export default {
        name: "topnav",
        data() {
            return {
                categorys:[{
                    categoryTitle:''
                }],
                activeIndex: '1',
                activeIndex2: '1'
            };
        },
        created(){
          this.getCategory()
        },
        methods: {
            getCategory(){
                getCategory().then(res =>{
                  const that = this
                   that.categorys = res.data
                })
            },
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
