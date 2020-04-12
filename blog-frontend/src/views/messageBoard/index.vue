<template>
    <article>
        <div class="l_box f_l">
            <div class="topnews">
                <h2><span><a href="/" target="_blank">留言板</a></span><b>留言</b>板</h2>

                <div class="news_pl">
                    <ul>
                        <!--PC和WAP自适应版-->
                        <div id="SOHUCS" sid="liuyan"></div>
                    </ul>
                </div>

            </div>
        </div>

    </article>
</template>

<script>
    import $ from 'jquery';

    export default {
        name: "messageBoard",
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
    }
</script>

<style lang="less" scoped>
    @import "../../assets/sidebar/base.css";
    @import "../../assets/sidebar/index.css";
    @import "../../assets/sidebar/media.css";
    @import "../../assets/sidebar/list.css";
    @import "../../assets/sidebar/view.css";
</style>

