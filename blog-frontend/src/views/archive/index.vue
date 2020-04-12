<template>
    <article>
        <div class="l_box f_l">
            <div class="topnews">
                <h2><span><a href="/" target="_blank">文章归档</a></span><b>文章</b>归档</h2>
            </div>
            <el-collapse @change="handleChange" v-for="archive in archive">
                <el-collapse-item :title="archive.year+'年-'+archive.month+'月'" name="1">
                    <div v-for="item in archive.title.split(',')"> <router-link tag='a' :to="'/'" >{{item}}</router-link></div>
                </el-collapse-item>
            </el-collapse>
        </div>
    </article>
</template>

<script>
    import {getArchive} from "../../api/all";

    export default {
        name: "archive",
        data() {
            return {
                archive:[],

            };
        },
        created(){
            this.getArchive()
        },
        methods: {
            handleChange(val) {
                console.log(val);
            },
            getArchive(){
                getArchive().then(res =>{
                    const that = this;
                    that.archive =  res.data
                    console.log( that.archive)
                })
            }
        }
    }
</script>

<style lang="less" scoped>
    @import "../../assets/sidebar/base.css";
    @import "../../assets/sidebar/index.css";
    @import "../../assets/sidebar/media.css";
    @import "../../assets/sidebar/list.css";
    @import "../../assets/sidebar/view.css";
</style>
