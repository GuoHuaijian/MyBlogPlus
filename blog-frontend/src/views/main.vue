<template>
    <div>
        <div>
            <topnav></topnav>
        </div>
        <div style="position: relative;right: 170px;margin-top: 20px">
            <sidebar></sidebar>
        </div>
        <div style="width: 700px;left:270px;position: relative ;margin-top: 0px">
            <div>
                <carousel></carousel>
            </div>
            <h1 style="margin-top: 28px;color: #00A2FF;font-size: 20px;margin-bottom: 0"><svg class="icon" aria-hidden="true">
                <use xlink:href="#icon-tuwenliebiao"></use>
            </svg>&nbsp;&nbsp;&nbsp;推荐文章</h1>
            <hr style="margin-top: 0px">
        </div>
        <div>
            <articlenav style="width: 700px;"></articlenav>
        </div>
        <div style="margin-top: 30px"><copyright style="margin-top: 30px"></copyright></div>
    </div>
</template>
<script>
    import articlenav from "../components/articlelist";
    import carousel from "../components/carousel";
    import sidebar from "../components/sidebar";
    import topnav from "../components/topnav";
    import copyright from "../components/copyright";
    export default {
        name: "main",
        components: {topnav, sidebar, carousel, articlenav,copyright}
    }
</script>

<style lang="less" scoped>
    .icon {
        width: 1.5em;
        height: 1.5em;
        vertical-align: -0.15em;
        fill: currentColor;
        overflow: hidden;
    }
</style>
