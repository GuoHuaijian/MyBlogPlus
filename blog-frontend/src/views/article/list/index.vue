<template>
    <div class="article col-xs-12 col-sm-8 col-md-8" style="width: 700px;left:270px;position: relative ;">
        <div class="ajax-load-box posts-con" v-for="record in records " :key="record.id">
            <div class="ajax-load-con content wow fadeInUp">
                <div class="content-box posts-gallery-box">
                    <div class="posts-gallery-img"><a href="#" :title="record.title" target="_blank"> <img
                            class="lazy" :src="record.summaryImg" width="231" height="173" :alt="record.title"/> </a>
                    </div>
                    <div class="posts-gallery-content">
                        <h2><a href="#"
                               :title="record.title">
                            <router-link :to="{path: '/article',query:{ id: record.id,}}">{{record
                                .title}}
                            </router-link>
                        </a></h2>
                        <div class="posts-gallery-text">{{record.summary}}</div>
                        <div class="posts-default-info posts-gallery-info">
                            <ul>
                                <li class="post-author hidden-xs">
                                    <div class="avatar"><img alt='maolai' :src="record.authorImg" height='96'
                                                             width='96'/></div>
                                    <a href="#" target="_blank">{{record.author}}</a>
                                </li>
                                <li class="ico-cat"><i class="icon-list-2"></i> <a href="#">{{record.category}}</a></li>
                                <li class="ico-time"><i class="icon-clock-1"></i> {{record.createTime}}</li>
                                <li class="ico-eye hidden-xs"><i class="icon-eye-4"></i> {{record.clickNum}}</li>
                                <li class="ico-like hidden-xs"><i class="icon-commenting"></i>
                                    <span :id = "'sourceId::'+record.id" class = "cy_cmt_count" ></span></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="block">
            <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page.sync="tablePage.currentPage"
                    :page-sizes="tablePage.pageSizes"
                    :page-size.sync="tablePage.pageSize"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="tablePage.total">
            </el-pagination>
        </div>

    </div>
</template>

<script>
    import $ from 'jquery';
    import {getArticle} from "../../../api/all";

    export default {
        name: "article",
        data() {
            return {
                records: [{
                    id: '',
                    title: '',
                    summary: '',
                    summaryImg: '',
                    author: '',
                    authorImg: '',
                    category: '',
                    clickNum: '',
                    createTime: '',
                }],
                tablePage: {
                    total: 0,
                    currentPage: 1,
                    pageSize: 10,
                    align: 'left',
                    pageSizes: [10, 20, 50, 100, 200, 500],
                    perfect: true
                },
            }
        },
        mounted()
        {
            $.getScript("https://cy-cdn.kuaizhan.com/upload/plugins/plugins.list.count.js?clientId=cyuxhdmVd");
        },
        created() {
            this.findList()
        },
        methods: {
            findList() {
                getArticle(this.tablePage.currentPage, this.tablePage.pageSize).then(res => {
                    const that = this;
                    that.tablePage.total = res.data.total
                    that.records = res.data.records
                })
            },
            handleCurrentChange(val) {
                this.tablePage.currentPage = val
                this.findList()
            },
            handleSizeChange(val) {
                this.tablePage.pageSize = val
                this.findList()
            }
        }
    }
</script>

<style scoped>
    @import '../../../assets/article/css/fontello.css';
    @import '../../../assets/article/css/nicetheme.css';
    @import '../../../assets/article/css/owl.carousel.min.css';
    @import '../../../assets/article/css/reset.css';
    @import '../../../assets/article/css/style.css';
</style>
