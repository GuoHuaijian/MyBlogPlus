<template>
  <div class="app-container">
    <el-form
      ref="form"
      :data="form"
      element-loading-text="加载中..."
      :model="form"
      label-width="120px"
    >
      <el-input v-model="form.id" type="hidden" />

      <el-form-item label="头像">
        <div class="components-container">
          <pan-thumb :image="image" v-model="form.avatar" />

          <el-button type="primary" icon="upload" style="position: absolute;bottom: 15px;margin-left: 40px;" @click="toggleShow">
            上传头像
          </el-button>

          <image-cropper
            v-model="show"
            field="file"
            :width="300"
            :height="300"
            :url="url"
            :params="params"
            :headers="headers"
            img-format="png"
            @crop-success="cropSuccess"
            @crop-upload-success="cropUploadSuccess"
            @crop-upload-fail="cropUploadFail"
          />
        </div>
      </el-form-item>


      <el-form-item label="头像">
        <img :src="form.avatar" width="60" height="60">
      </el-form-item>
      <el-form-item label="用户名">
        <el-input v-model="form.userName" />
      </el-form-item>
      <el-form-item label="邮箱">
        <el-input v-model="form.email" />
      </el-form-item>
      <el-form-item label="电话">
        <el-input v-model="form.phone" />
      </el-form-item>
      <el-form-item label="账号">
        <el-input v-model="form.account" />
      </el-form-item>
      <el-form-item label="密码">
        <el-input type="password" v-model="form.password" />
      </el-form-item>
      <el-form-item label="关于">
        <Tinymce ref="editor" v-model="form.about" :height="400" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import ImageCropper from 'vue-image-crop-upload'
  import PanThumb from '@/components/PanThumb'
  import { getToken } from '@/utils/auth'
  import Tinymce from '../../../components/Tinymce/index'
  import { addUser, detailUser, updateUser } from '../../../api/user'
  export default {
    name: 'user',
    components: { Tinymce,ImageCropper, PanThumb },
    data() {
      return {
        url: process.env.VUE_APP_BASE_API + '/file/upload',
        show: false,
        params: {
          access_token: getToken()
        },
        headers: {
          smail: '*_~'
        },
        image:'',
        formLoading: true,
        form: {
          id: undefined,
          avatar: '',
          userName: '',
          email: '',
          phone:'',
          about:'',
          account: '',
          password: '',
        }
      }
    },
    created() {
      if (this.$route.query.id != 'undefined' ) {
        this.fetchData();
      }
    },
    methods: {
      toggleShow() {
        this.show = !this.show
      },
      /**
       *
       * @param image
       * @param field
       */
      cropSuccess(image, field) {
        const self = this
        console.log('-------- crop success --------')
        this.image = image
      },
      /**
       * 上传成功
       * @param jsonData 服务器返回数据，已进行 JSON 转码
       * @param field
       */
      cropUploadSuccess(jsonData, field) {
        console.log('-------- upload success --------')
        console.log(jsonData)
        console.log('path: ', jsonData.data)
        console.log('field: ' + field)
        this.form.avatar = jsonData.data
        message: jsonData.data;
      },
      /**
       * 上传失败
       * @param status 服务器返回的失败状态码
       * @param field
       */
      cropUploadFail(status, field) {
        console.log('-------- upload fail --------')
        console.log(status)
        console.log('field: ' + field)
      },
      fetchData() {
        detailUser(this.$route.query.id).then(response => {
          const that = this
          this.form = response.data
          that.image = response.data.avatar
          this.formLoading = false
        })
      },
      onSubmit() {
        if (this.form.id != undefined){
          console.log(this.form)
        this.formLoading = true
        updateUser(this.form).then(response => {
          this.formLoading = false
          this.$message({
            message: response.msg,
            type: 'success'
          })
        }).catch(() => {
          this.formLoading = false
        })
      }else {
          this.formLoading = true
          addUser(this.form).then(response => {
            this.formLoading = false
            this.$message({
              message: response.msg,
              type: 'success'
            })
          }).catch(() => {
            this.formLoading = false
          })
        }
      }
    }
  }
</script>

<style scoped>

</style>
