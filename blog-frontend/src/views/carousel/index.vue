<template>
    <div class="block">
        <el-carousel trigger="click" height="350px">
            <el-carousel-item v-for="item in img" :key="item.id">
                <div style="position:relative;">
                    <img :src="item.imgUrl" alt="图片">
                    <div class="title">{{ item.title }}</div>
                </div>
            </el-carousel-item>
        </el-carousel>
    </div>
</template>

<script>
    import {getCarousel} from "../../api/all";

    export default {
        name: "carousel",
        data() {
            return {
                img: []
            }
        },
        created(){
            this.getList();
        },
        methods:{
            getList(){
                getCarousel().then(res =>{
                    const that = this;
                    that.img = res.data;
                })
            }
        }
    }
</script>

<style lang="less" scoped>
    .block {
        width: 700px;
        height: 350px;
    }

    .el-carousel__item h3 {
        color: #475669;
        font-size: 14px;
        opacity: 0.75;
        margin: 0;
        text-align: center;
        position: absolute;
        float: top;
    }

    img {
        height: 100%;
        width: 100%;
    }

    .title {
        color: #475669;
        position: absolute;
        font-size: 20px;
        font-family: "Arial Black";
        z-index: 2;
        left: 10px;
        top: 300px
    }
</style>
