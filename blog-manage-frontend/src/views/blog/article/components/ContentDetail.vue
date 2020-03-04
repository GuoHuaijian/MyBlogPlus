<template>
  <div>
    <h1 v-model="title" style="align:center">{{title}}</h1>
  <div class="author">
    <span class="au01" v-model="author">作者：{{author}}</span>
    <span class="au02" v-model="createTime">时间：{{createTime}}</span>
    <strong class="au03" v-model="tag">标签：{{tag}}</strong>
    <span class="au03" v-model="category">分类：{{category}} </span>
  </div>
    <br>
    <p v-model="content" v-html="content"></p>
  </div>
</template>

<script>
  import { ContentDetail } from '../../../../api/article'

  export default {
    name: 'ContentDetail',
    data() {
      return {
              title:"sss",
              author:"admin",
        createTime:"2015",
              tag:[],
              category:"2015",
               content:'dddddddddddddddddddddddddddddd'
        }
    },
    created() {
        this.getContentDetail();
    },
    methods:{
      getContentDetail(){
        const that = this;
        ContentDetail(this.$route.query.id).then((res)=>{
          that.title = res.data.title;
          that.author = res.data.author;
          that.createTime = res.data.createTime;
          that.tag = res.data.tag;
          that.category = res.data.category;
          that.content = res.data.content;
        })
      }
    }
  }
</script>

<style scoped>

</style>
