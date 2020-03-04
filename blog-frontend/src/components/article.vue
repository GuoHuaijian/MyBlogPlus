<template>
    <article>
        <div class="l_box f_l">
            <div class="topnews">
                <h2>您现在的位置是：<a href="/">门户</a>><a href="/">新闻频道</a>> 查看内容</h2>
                <div class="news_title">2014万里户外川藏线阿里北线转山新疆看花</div>
                <div class="news_author"><span class="au01">admin</span><span class="au02">2015-1-10</span><span
                        class="au03">共<b>118</b>人围观</span><strong
                        class="au04 f_r">暂无评论</strong></div>
                <div class="tags"><a href="/" target="_blank">手机</a> <a href="/" target="_blank">电话</a> <a href="/"
                                                                                                           target="_blank">欠费</a>
                </div>

                <div class="news_about"><strong>简介</strong>
                    :公共物资：我们涉及露营的深度西藏自驾线路（川藏16天、阿里小北17天），随车会配置炉头、气罐、炊具等野炊装备、常规外伤药、高原急救药品、氧气瓶，构成公共物资。
                </div>
                <div class="news_content">
                    <ul>
                        <p><img src="../assets/images/a1.jpg"></p>
                        <p>公共物资：我们涉及露营的深度西藏自驾线路（川藏16天、阿里小北17天），随车会配置炉头、气罐、炊具等野炊装备、常规外伤药、高原急救药品、氧气瓶，构成公共物资。</p>
                        <p><img src="../assets/images/ex01.jpg"></p>
                        <p><img src="../assets/images/a1.jpg"></p>
                        <p>公共物资：我们涉及露营的深度西藏自驾线路（川藏16天、阿里小北17天），随车会配置炉头、气罐、炊具等野炊装备、常规外伤药、高原急救药品、氧气瓶，构成公共物资。</p>
                        <p><img src="../assets/images/ex01.jpg"></p>
                    </ul>
                </div>
                <!--打赏-->
                <div id="cyReward" role="cylabs" data-use="reward"></div>
                <div class="fenx">分享：</div>
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
                        <div id="SOHUCS" sid="请将此处替换为配置SourceID的语句"></div>
                    </ul>
                </div>
            </div>
        </div>

    </article>
</template>

<script>
    import $ from 'jquery';

    export default {
        name: "article",
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
        }
    }
</script>

<style lang="less" scoped>
    @import "../assets/sidebar/base.css";
    @import "../assets/sidebar/index.css";
    @import "../assets/sidebar/media.css";
    @import "../assets/sidebar/list.css";
    @import "../assets/sidebar/view.css";

</style>
