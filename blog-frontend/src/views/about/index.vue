<template>
    <article>
        <div class="l_box f_l">
            <div class="topnews">
                <h2><span><a href="/" target="_blank">About Me</a></span><b>About</b>Me</h2>

            </div>

            <p v-html="about"></p>
        </div>

    </article>
</template>

<script>
    import {getAbout} from "../../api/all";

    export default {
        name: "about",
        data(){
            return{
                about:''
            }
        },
        created() {
            this.getAbout()
        },
        methods:{
            getAbout(){
                getAbout().then(res =>{
                    const that = this
                    that.about = res.data
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
