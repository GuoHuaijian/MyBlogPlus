<template>
  <div class="createPost-container">
    <el-form ref="postForm" :model="postForm" :rules="rules" class="form-container">

      <sticky :z-index="10" :class-name="'sub-navbar '+postForm.status" style="float: right; margin-top: 15px">
        <el-button v-loading="loading" style="margin-left: 10px;" type="success" @click="submitForm">
          发布
        </el-button>
        <el-button v-loading="loading" type="warning" @click="draftForm">
          草稿箱
        </el-button>
      </sticky>

      <div class="createPost-main-container">
        <el-row>
          <el-col :span="24">
            <el-form-item style="margin-bottom: 40px;" prop="title">
              <MDinput v-model="postForm.title" :maxlength="100" name="name" required>
                标题
              </MDinput>
            </el-form-item>

            <div class="postInfo-container">
              <el-row>
                <el-col :span="8">
                  <el-form-item label-width="60px" label="分类:" class="postInfo-container-item">
                    <el-select  v-model="postForm.category" placeholder="博客类别">
                      <el-option v-for="item in category"  :value="item.name">{{ item.name }}</el-option>
                    </el-select>
                  </el-form-item>
                </el-col>

                <el-col :span="10">
                  <el-form-item label-width="120px" label="标签:" class="postInfo-container-item">
                    <el-select
                      v-model="postForm.tag"
                      multiple
                      filterable
                      allow-create
                      default-first-option
                      placeholder="请选择文章标签">
                      <el-option
                        v-for="item in tag"
                        :key="item.id"
                        :label="item.label"
                        :value="item.name">
                      </el-option>
                    </el-select>
                  </el-form-item>
                </el-col>

                <el-col :span="6">
                  <el-form-item label-width="90px" label="作者:" class="postInfo-container-item">
                    <el-input v-model="postForm.author" placeholder="请输入内容"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
            </div>
          </el-col>
        </el-row>

        <el-form-item style="margin-bottom: 40px;" label-width="70px" label="摘要:">
          <el-input v-model="postForm.summary" :rows="1" type="textarea" class="article-textarea" autosize placeholder="请入摘要" />
          <span v-show="contentShortLength" class="word-counter">{{ contentShortLength }}words</span>
        </el-form-item>

        <el-form-item prop="image_uri" style="margin-bottom: 30px;" label="封面:">
          <Upload v-model="postForm.summary_img" />
        </el-form-item>

        <el-form-item prop="content" style="margin-bottom: 30px;">
          <Tinymce ref="editor" v-model="postForm.content" :height="400" />
        </el-form-item>

      </div>
    </el-form>
  </div>
</template>

<script>
  import {listCategory} from '@/api/category'
  import {listTag} from '@/api/tag'
  import Tinymce from '@/components/Tinymce'
  import Upload from '@/components/Upload/SingleImage3'
  import MDinput from '@/components/MDinput'
  import Sticky from '@/components/Sticky' // 粘性header组件
  import { validURL } from '@/utils/validate'
  import { fetchArticle } from '@/api/article'
  import {createArticle} from '@/api/article'

  import {  SourceUrlDropdown } from './Dropdown'
  import { updateArticle } from '../../../../api/article'

  const defaultForm = {
    status: 'draft',
    title: '', // 文章题目
    category:'', // 文章分类
    tag:[],  // 文章标题
    author:'',  // 作者
    summary:'',  // 摘要
    summary_img:'', // 封面
    content: '', // 文章内容
  }

  export default {
    name: 'ArticleUpdate',
    components: { Tinymce, MDinput, Upload, Sticky, SourceUrlDropdown },
    props: {
      isEdit: {
        type: Boolean,
        default: false
      }
    },
    data() {
      const validateRequire = (rule, value, callback) => {
        if (value === '') {
          this.$message({
            message: rule.field + '为必传项',
            type: 'error'
          })
          callback(new Error(rule.field + '为必传项'))
        } else {
          callback()
        }
      }
      const validateSourceUri = (rule, value, callback) => {
        if (value) {
          if (validURL(value)) {
            callback()
          } else {
            this.$message({
              message: '外链url填写不正确',
              type: 'error'
            })
            callback(new Error('外链url填写不正确'))
          }
        } else {
          callback()
        }
      }
      return {
        postForm: Object.assign({}, defaultForm),
        loading: false,
        rules: {
          summary_img: [{ validator: validateRequire }],
          title: [{ validator: validateRequire }],
          content: [{ validator: validateRequire }],
          source_uri: [{ validator: validateSourceUri, trigger: 'blur' }]
        },
        tag: [],
        category:[],
      }
    },
    computed: {
      contentShortLength() {
        return this.postForm.summary.length
      }
    },
    created() {
      this.listTag();
      this.listCategory();
      this. getname();
      if (this.isEdit) {
        const id = this.$route.query.id
        this.fetchData(id)
      }

      // Why need to make a copy of this.$route here?
      // Because if you enter this page and quickly switch tag, may be in the execution of the setTagsViewTitle function, this.$route is no longer pointing to the current page
      // https://github.com/PanJiaChen/vue-element-admin/issues/1221
      this.tempRoute = Object.assign({}, this.$route)
    },
    methods: {
      listTag(){
        var that = this;
        listTag().then(response =>{
          that.tag = response.data
        })
      },
      listCategory(){
        var that = this;
        listCategory().then(response =>{
          that.category = response.data
        })
      },
      getname(){
        var that = this;
        that.postForm.author = this.$store.getters.name;
      },
      fetchData(id) {
        fetchArticle(id).then(response => {
          this.postForm = response.data
        }).catch(err => {
          console.log(err)
        })
      },
      submitForm() {
        this.$refs.postForm.validate(valid => {
          if (valid) {
            this.loading = true
            this.postForm.status = 'published'
            const id = this.$route.query.id
            updateArticle(this.postForm,id).then(response =>{
              if (response.code = 20000){
                this.$notify({
                  title: '成功',
                  message: '修改文章成功',
                  type: 'success',
                  duration: 2000
                })
                this.postForm.status = 'published'
                this.loading = false
              }
            })
          } else {
            console.log('error submit!!')
            return false
          }
        })
      },
      draftForm() {
        this.$refs.postForm.validate(valid => {
          if (valid) {
            this.loading = true
            this.postForm.status = 'draft'
            const id = this.$route.query.id
            updateArticle(this.postForm,id).then(response =>{
              if (response.code = 20000){
                this.$notify({
                  title: '成功',
                  message: '保存文章成功',
                  type: 'success',
                  duration: 2000
                })
                this.postForm.status = 'draft'
                this.loading = false
              }
            })
          } else {
            console.log('error submit!!')
            return false
          }
        })
      },
    }
  }
</script>

<style lang="scss" scoped>
  @import "~@/styles/mixin.scss";

  .createPost-container {
    position: relative;

    .createPost-main-container {
      padding: 40px 45px 20px 50px;

      .postInfo-container {
        position: relative;
        @include clearfix;
        margin-bottom: 10px;

        .postInfo-container-item {
          float: left;
        }
      }
    }

    .word-counter {
      width: 40px;
      position: absolute;
      right: 10px;
      top: 0px;
    }
  }

  .article-textarea /deep/ {
    textarea {
      padding-right: 40px;
      resize: none;
      border: none;
      border-radius: 0px;
      border-bottom: 1px solid #bfcbd9;
    }
  }
</style>
