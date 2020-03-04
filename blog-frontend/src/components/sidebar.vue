<template>
    <div class="r_box f_r" style="width: 350px">
        <div class="tit01">
            <div style="text-align: center">
                <el-avatar :size="80"
                           src=" https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1569229355450&di=47d0cb543f240932e00b6839d007f79a&imgtype=0&src=http%3A%2F%2Fwww.jf258.com%2Fuploads%2F2015-05-15%2F014049347.jpg"></el-avatar>
                <h1 style="color: #00A2FF ;margin-top: 10px;font-size:20px;">她是故人</h1>
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
                <li class="one"><a href="#" title="响应式个人杂志WordPress博客主题">
                    <div class="overlay"></div>
                    <img class="lazy" src="../assets/images/18.jpg" alt="响应式个人杂志WordPress博客主题"/>
                    <div class="title"><span>2017-10-25</span>
                        <h4>响应式个人杂志WordPress博客主题</h4>
                    </div>
                </a></li>
                <li class="others">
                    <div class="image"><a href="#" title="Magneto响应WordPress杂志和博客主题"> <img class="lazy"
                                                                                           src="../assets/images/9.jpg"
                                                                                           alt="Magneto响应WordPress杂志和博客主题"/>
                    </a></div>
                    <div class="title">
                        <h4><a href="#" title="Magneto响应WordPress杂志和博客主题">Magneto响应WordPress杂志和博客主题</a></h4>
                        <span>2017-10-25</span>
                    </div>
                </li>
                <li class="others">
                    <div class="image"><a href="#" title="Linx - WordPress博客和杂志的主题"> <img class="lazy"
                                                                                          src="../assets/images/10.jpg"
                                                                                          alt="Linx - WordPress博客和杂志的主题"/>
                    </a></div>
                    <div class="title">
                        <h4><a href="#" title="Linx - WordPress博客和杂志的主题">Linx - WordPress博客和杂志的主题</a></h4>
                        <span>2017-10-25</span>
                    </div>
                </li>
                <li class="others">
                    <div class="image"><a href="#" title="个人生活博客和杂志的WordPress主题"> <img class="lazy"
                                                                                       src="../assets/images/11.jpg"
                                                                                       alt="个人生活博客和杂志的WordPress主题"/>
                    </a></div>
                    <div class="title">
                        <h4><a href="#" title="个人生活博客和杂志的WordPress主题">个人生活博客和杂志的WordPress主题</a></h4>
                        <span>2017-10-25</span>
                    </div>
                </li>
                <li class="others">
                    <div class="image"><a href="#" title="Penta响应博客的WordPress主题"> <img class="lazy"
                                                                                       src="../assets/images/12.jpg"
                                                                                       alt="Penta响应博客的WordPress主题"/>
                    </a></div>
                    <div class="title">
                        <h4><a href="#" title="Penta响应博客的WordPress主题">Penta响应博客的WordPress主题泄漏</a></h4>
                        <span>2017-10-25</span>
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
            <ul>
                <a href="/">手机配件</a>
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
                <li><a href="/"><img src="../assets/images/01.jpg"><b>住在手机里的朋友</b></a>
                    <p><span class="icon-list-2"><a href="/" style="color: #00A2FF">手机配件</a></span><span class="el-icon-alarm-clock">2015-02-15</span></p>
                </li>
                <li><a href="/"><img src="../assets/images/02.jpg"><b>教你怎样用欠费手机拨打电话</b></a>
                    <p><span class="icon-list-2"><a href="/" style="color: #00A2FF">手机配件</a></span><span class="el-icon-alarm-clock">2015-02-15</span></p>
                </li>
                <li><a href="/" title="手机的16个惊人小秘密，据说99.999%的人都不知"><img src="../assets/images/03.jpg"><b>手机的16个惊人小秘密，据说...</b></a>
                    <p><span class="icon-list-2"><a href="/" style="color: #00A2FF">手机配件</a></span><span class="el-icon-alarm-clock">2015-02-15</span></p>
                </li>
                <li><a href="/"><img src="../assets/images/06.jpg"><b>住在手机里的朋友</b></a>
                    <p><span class="icon-list-2"><a href="/" style="color: #00A2FF">手机配件</a></span><span class="el-icon-alarm-clock">2015-02-15</span></p>
                </li>
                <li><a href="/"><img src="../assets/images/04.jpg"><b>教你怎样用欠费手机拨打电话</b></a>
                    <p><span class="icon-list-2"><a href="/" style="color: #00A2FF">手机配件</a></span><span class="el-icon-alarm-clock">2015-02-15</span></p>
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
                <li><a href="/">手机配件</a></li>
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
    export default {
        name: "sidebar",
    }
</script>

<style lang="less" scoped>
    @import "../assets/sidebar/base.css";
    @import "../assets/sidebar/index.css";
    @import "../assets/sidebar/media.css";
    @import '../assets/article/css/fontello.css';
    @import '../assets/article/css/nicetheme.css';
    @import '../assets/article/css/owl.carousel.min.css';
    @import '../assets/article/css/reset.css';
    @import '../assets/article/css/style.css';
    @import '../assets/aliicon/iconfont.css';

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
