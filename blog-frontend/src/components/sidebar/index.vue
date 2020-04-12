<template>
    <div class="r_box f_r" style="width: 350px">
        <div class="tit01">
            <div style="text-align: center">
                <el-avatar :size="80"
                           :src="avatar"></el-avatar>
                <h1 style="color: #00A2FF ;margin-top: 10px;font-size:20px;">{{userName}}</h1>
            </div>
            <h3 style="color: #00A2FF">
                <svg class="icon" aria-hidden="true">
                    <use xlink:href="#icon-guanzhu"></use>
                </svg>&nbsp;&nbsp;&nbsp;关注我
            </h3>
            <div class="gzwm">
                <ul>
                    <li><a class="xlwb" href="https://github.com/GuoHuaijian" target="_blank">GitHub</a></li>
                    <li><a class="rss" href="564559079@qq.com" target="_blank">邮箱</a></li>
                    <li><a class="wx" href="mailto:admin@admin.com" onmouseover="tip.start(this)"
                           tips="<font color='#fff'><b><img src='images/weixintop.jpg'></b>">微信</a>
                    </li>
                    <div id="mjs:tip" class="tip" style="position:absolute;left:0;top:0;display:none;"></div>
                </ul>
            </div>
        </div>
        <!--tit01 end-->
        <div class="search">
            <el-input placeholder="请输入内容"></el-input>
            <el-button type="primary" icon="el-icon-search">搜索</el-button>
        </div>
        <div class="moreSelect" id="lp_right_select">
            <h3 style=" line-height: 40px; color: #00A2FF; font-size: 18px; border-bottom: 1px solid #E6E6E6; height: 40px;">
                <svg class="icon" aria-hidden="true">
                    <use xlink:href="#icon-zuixin"></use>
                </svg>&nbsp;&nbsp;&nbsp;
                最新文章
            </h3>

            <ul class="recent-posts-widget" style="width: 300px">
                <li class="one"><a href="#" :title="title">
                    <div class="overlay"></div>
                    <img class="lazy" :src="summaryImg" :alt="title"/>
                    <div class="title"><span>{{createTime}}</span>
                        <h4>{{title}}</h4>
                    </div>
                </a></li>
                <li class="others" v-for="record in records">
                    <div class="image"><a href="#" :title="record.title"> <img class="lazy"
                                                                               :src="record.summaryImg"
                                                                               :alt="record.title"/>
                    </a></div>
                    <div class="title">
                        <h4><a href="#" :title="record.title">{{record.title}}</a></h4>
                        <span>{{record.createTime}}</span>
                    </div>
                </li>
            </ul>

            <!--ms-main end -->
        </div>
        <!--切换卡 moreSelect end -->
        <div class="cloud">
            <h3 style="color: #00A2FF">
                <svg class="icon" aria-hidden="true">
                    <use xlink:href="#icon-biaoqian"></use>
                </svg>&nbsp;&nbsp;&nbsp;标签云
            </h3>
            <ul  v-for="item in tagTitles">
                <a href="/">{{item.tagTitle}}</a>
                <a href="/">手机</a>
                <a href="/">苹果Apple</a>
                <a href="/">SumSung</a>
                <a href="/">Sony</a>
                <a href="/">华维荣耀</a>
                <a href="/">手机</a>
                <a href="/">三星</a><a href="/">索尼</a>
                <a href="/">华维荣耀</a>
                <a href="/">三星</a>
                <a href="/">索尼</a>

            </ul>
        </div>
        <div class="tuwen">
            <h3 style="color: #00A2FF">
                <svg class="icon" aria-hidden="true">
                    <use xlink:href="#icon-fenlei"></use>
                </svg>&nbsp;&nbsp;&nbsp;图文推荐
            </h3>
            <ul>
                <li v-for="record in records"><a href="/"><img :src="record.summaryImg"><b>{{record.title}}</b></a>
                    <p><span class="icon-list-2"><a href="/" style="color: #00A2FF">{{record.category}}</a></span><span
                            class="el-icon-alarm-clock">{{record.createTime}}</span></p>
                </li>
            </ul>
        </div>
        <div class="links">
            <h3 style="color: #00A2FF"><span>[<a href="/">申请友情链接</a>]</span>
                <svg class="icon" aria-hidden="true">
                    <use xlink:href="#icon-lianjie"></use>
                </svg>&nbsp;&nbsp;&nbsp;友情链接
            </h3>
            <ul>
                <li><a href="/">小米</a></li>
                <li><a href="/">苹果Apple</a></li>
                <li><a href="/">SumSung</a></li>
                <li><a href="/">Sony</a></li>
                <li><a href="/">三星</a></li>
                <li><a href="/">索尼</a></li>
                <li><a href="/">华维荣耀</a></li>
            </ul>
        </div>
    </div>
</template>

<script>
    import {getArticle, getTag, getUser} from "../../api/all";

    export default {
        name: "sidebar",
        data() {
            return {
                userName:'',
                avatar:'',
                tagTitles:[{
                    tagTitle:''
                }],
                id: '',
                title: '',
                summaryImg: '',
                createTime: '',
                records: [{
                    id: '',
                    title: '',
                    summaryImg: '',
                    category: '',
                    createTime: '',
                }],
                tablePage: {
                    total: 0,
                    currentPage: 1,
                    pageSize: 4,
                },
            }
        },
        created() {
            this.findList()
            this.getArticle()
            this.getTag()
            this.getUser()
        },
        methods:{
            findList(){
                getArticle(this.tablePage.currentPage,this.tablePage.pageSize).then(res =>{
                    const that = this;
                    that.records = res.data.records
                })
            },
            getArticle(){
                getArticle(this.tablePage.currentPage,1).then(res =>{
                    const that = this;
                    that.id = res.data.records[0].id
                    that.title = res.data.records[0].title
                    that.summaryImg = res.data.records[0].summaryImg
                    that.createTime = res.data.records[0].createTime
                })
            },
            getTag(){
                getTag().then(res =>{
                    const that = this
                    that.tagTitles = res.data
                })
            },
            getUser(){
                getUser().then(res =>{
                    const that = this
                    that.userName = res.data.userName
                    that.avatar = res.data.avatar
                })
            }
        }
    }
</script>

<style lang="less" scoped>
    @import "../../assets/sidebar/base.css";
    @import "../../assets/sidebar/index.css";
    @import "../../assets/sidebar/media.css";
    @import '../../assets/article/css/fontello.css';
    @import '../../assets/article/css/nicetheme.css';
    @import '../../assets/article/css/owl.carousel.min.css';
    @import '../../assets/article/css/reset.css';
    @import '../../assets/article/css/style.css';
    @import '../../assets/aliicon/iconfont.css';

    .el-input {
        width: 261px;
    }

    .el-button {
        float: right;
    }

    .icon {
        width: 1.5em;
        height: 1.5em;
        vertical-align: -0.15em;
        fill: currentColor;
        overflow: hidden;
    }
</style>
