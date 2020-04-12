<template>
    <div>
        <div>
            <topnav></topnav>
        </div>
        <div style="position: relative;right: 170px;margin-top: 20px">
            <sidebar></sidebar>
        </div>
        <router-view></router-view>
        <div style="margin-top: 30px"><copyright style="margin-top: 30px"></copyright></div>
    </div>
</template>

<script>
    import sidebar from "../../components/sidebar/index";
    import topnav from "../../components/topnav/index";
    import copyright from "../../components/copyright/index";
    export default {
        name: "framework",
        components: {topnav, sidebar, copyright}
    }
</script>

<style scoped>

</style>
