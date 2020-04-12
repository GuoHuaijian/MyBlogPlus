<template>
    <article>
        <div class="l_box f_l">
            <div class="topnews">
                <h2>您现在的位置是：<a href="/">文章</a>><a href="/">分类</a>> 查看文章</h2>
                <div class="news_title">{{article.title}}</div>
                <div class="news_author"><span class="au01">{{article.author}}</span><span class="au02">{{article
                    .createTime}}</span><span
                        class="au03">共<b>{{article.clickNum}}</b>人围观</span>
                    <span
                            class="au03">共<b> <a href="#SOHUCS" id="changyan_count_unit"></a></b>条评论</span>
                </div>
                <div class="tags"><a href="/" target="_blank" v-for="tag in article.tag">{{tag}}</a>
                </div>

                <div class="news_about"><strong>简介</strong>
                    :{{article.summary}}。
                </div>
                <div class="news_content">
                    <ul>
                        <p v-html="article.content"></p>
                    </ul>
                </div>
                <!--打赏-->
                <div id="cyReward" role="cylabs" data-use="reward"></div>
                <div class="fenx"><label>分享：<vshare></vshare></label></div>
                <div class="nextinfo">
                    <p>上一篇：<a href="/">传微软将把入门级Surface平板价格下调150美元</a></p>
                    <p>下一篇：<a href="/">云南之行——大理洱海一日游</a></p>
                </div>
                <div class="otherlink">
                    <h2>相关文章</h2>
                    <ul>
                        <li><a href="/" title="云南之行——丽江古镇玉龙雪山">云南之行——丽江古镇玉龙雪山</a></li>
                        <li><a href="/" title="云南之行——大理洱海一日游">云南之行——大理洱海一日游</a></li>
                        <li><a href="/" target="_blank">住在手机里的朋友</a></li>
                        <li><a href="/" target="_blank">豪雅手机正式发布! 在法国全手工打造的奢侈品</a></li>
                        <li><a href="/" target="_blank">教你怎样用欠费手机拨打电话</a></li>
                        <li><a href="/" target="_blank">原来以为，一个人的勇敢是，删掉他的手机号码...</a></li>
                    </ul>
                </div>
                <div class="news_pl">
                    <h2>文章评论</h2>
                    <ul>
                        <!--PC和WAP自适应版-->
                        <div id="SOHUCS" :sid="article.id"></div>
                    </ul>
                </div>
            </div>
        </div>

    </article>
</template>

<script>
    import $ from 'jquery';
    import {getArticle, getArticleDetail} from "../../api/all";

    export default {
        name: "article",
        data() {
            return {
                article: {
                    id: '',
                    title: '',
                    author: '',
                    tag:[],
                    summary:'',
                    content:'',
                    category: '',
                    clickNum: '',
                    createTime: '',
                },
            }
        },
        mounted()
        {
            window.changyan = undefined;
            window.cyan = undefined;
            $.getScript("https://changyan.sohu.com/upload/changyan.js", () => {
                window.changyan.api.config({
                    appid: "cyuxhdmVd", // 此处换成你的畅言应用的appid,
                    conf: "prod_89edb34e0072301e8e8a752cf6acb1f2", // 此处换成你畅言应用的conf。
                });
            });
            $.getScript("https://changyan.itc.cn/js/lib/jquery.js");
            $.getScript("https://changyan.sohu.com/js/changyan.labs.https.js?appid=cyuxhdmVd");
            $.getScript("https://cy-cdn.kuaizhan.com/upload/plugins/plugins.count.js");
        },
        created(){
            this.getArticle()
        },
        methods:{
            getArticle(){
                getArticleDetail(this.$route.query.id).then(res =>{
                    const that = this
                    that.article = res.data
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
