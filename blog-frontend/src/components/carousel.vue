<template>
    <div class="block">
        <el-carousel trigger="click" height="350px">
            <el-carousel-item v-for="item in img" :key="item.id">
                <div style="position:relative;">
                    <img :src="item.url" alt="图片">
                    <div class="title">{{ item.name }}</div>
                </div>
            </el-carousel-item>
        </el-carousel>
    </div>
</template>

<script>
    export default {
        name: "carousel",
        data() {
            return {
                img: [{
                    id: 1,
                    name: '第一张图片',
                    url: 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1735688044,4235283864&fm=26&gp=0.jpg'
                }, {
                    id: 2,
                    name: '第二张图片',
                    url: 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1575435081253&di=c7c4b8d08c229a6ab77b6077c125c4a1&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fblog%2F201312%2F04%2F20131204184148_hhXUT.jpeg'
                },{
                    id:3,
                    name:'第三张图片',
                    url:'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1575435081252&di=399ace6e23d0ca7a0cdacbfd194b8195&imgtype=0&src=http%3A%2F%2Fpic22.nipic.com%2F20120620%2F9644879_220135570113_2.jpg'
                },{
                    id:4,
                    name:'第四张图片',
                    url:'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1575435081252&di=873b9ad68a50838247fc121fc5a37791&imgtype=0&src=http%3A%2F%2Fi1.sinaimg.cn%2Fent%2Fd%2F2008-06-04%2FU105P28T3D2048907F326DT20080604225106.jpg'
                }
                ]
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
