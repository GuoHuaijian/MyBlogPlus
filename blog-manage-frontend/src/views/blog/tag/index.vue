<template>
  <div>
    <vxe-grid
      border
      resizable
      keep-source
      ref="xGrid"
      height="530"
      :seq-config="{startIndex: (tablePage.currentPage - 1) * tablePage.pageSize}"
      :pager-config="tablePage"
      :data="tableData"
      :columns="tableColumn"
      :toolbar="toolbar"
      :edit-config="{trigger: 'click', mode: 'row', showStatus: true}"
      @page-change="handlePageChange">
      <template v-slot:buttons>
        <div class="input">
          <vxe-input></vxe-input>&nbsp;&nbsp;
          <vxe-button status="primary">搜索</vxe-button>
        </div>
        <vxe-button @click="create">新增</vxe-button>
        <vxe-button @click="$refs.xGrid.commitProxy('reload')">删除</vxe-button>
        <vxe-button @click="$refs.xGrid.commitProxy('mark_cancel')">标记/取消</vxe-button>
        <vxe-button @click="$refs.xGrid.exportData()">导出.csv</vxe-button>
      </template>
      <template v-slot:empty>
            <span style="color: red;">
              <img src="../../../assets/img1.gif">
              <p>不用再看了，没有更多数据了！</p>
            </span>
      </template>
      <!--自定义插槽 name_default-->
      <template v-slot:name_default="{ row, column,seq }">
            <span>
                <button class="el-button--success" @click="showDetailEvent(row, column)" >查看</button>&nbsp;&nbsp;
              <button class="el-button--primary" @click="editDetailEvent(row, column)"  style="color: blue;">修改
              </button>&nbsp;&nbsp;
                <button class="el-button--danger" @click="deleteById(row)" style="color: red;">删除</button>
            </span>
      </template>
    </vxe-grid>
    <div>
      <el-dialog
        title="详情"
        :visible.sync="centerDialogVisible"
        width="30%"
        center>
        <el-form :inline="true" :model="formInline" class="demo-form-inline">
          <el-form-item label="标签">
            <el-input v-model="formInline.tagTitle" placeholder="标签"></el-input>
          </el-form-item>
          <h2>关联的博客标题：</h2>
          <div id="app">

            <ul>
              <li v-for="(item,index) in blogTitle">
                {{item}}

              </li>
            </ul>
          </div>
        </el-form>
        <span slot="footer" class="dialog-footer">
    <el-button type="primary" @click="centerDialogVisible = false">返 回</el-button>
  </span>
      </el-dialog>
    </div>

    <!--新增-->
    <div>
      <el-dialog
        title="详情"
        :visible.sync="addDialogVisible"
        width="30%"
        center>
        <el-form :inline="true" :model="formInline" class="demo-form-inline">
          <el-form-item label="标签">
            <el-input v-model="formInline.tagTitle" placeholder="标签"></el-input>
            </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
    <el-button @click="addDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="addTag()">添 加</el-button>
  </span>
      </el-dialog>
    </div>

    <!--修改-->
    <div>
      <el-dialog
        title="详情"
        :visible.sync="editDialogVisible"
        width="30%"
        center>
        <el-form :inline="true" :model="formInline" class="demo-form-inline">
          <el-form-item label="标签">
            <el-input v-model="formInline.tagTitle" placeholder="标签"></el-input>
          </el-form-item>
          <el-input type="hidden" v-model="id" placeholder="id"></el-input>
        </el-form>
        <span slot="footer" class="dialog-footer">
    <el-button @click="editDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="editTag(id)">修 改</el-button>
  </span>
      </el-dialog>
    </div>
  </div>
</template>

<script>
  import {articleList} from '@/api/article'
  import { deleteArticle } from '../../../api/article'
  import { CategoryDetail, CategoryList, createCategory, deleteCategory, updateCategory } from '../../../api/category'
  import { createTag, deleteTag, TagDetail, TagList, updateTag } from '../../../api/tag'

  export default {
    name: 'Category',
    data () {
      return {
        centerDialogVisible: false,
        addDialogVisible: false,
        editDialogVisible:false,
        formInline: {
          tagTitle: '',
        },
        blogTitle:[],
        id:'',
        tablePage: {
          total: 0,
          currentPage: 1,
          pageSize: 10,
          align: 'left',
          pageSizes: [10, 20, 50, 100, 200, 500],
          layouts: ['Sizes', 'PrevJump', 'PrevPage', 'Number', 'NextPage', 'NextJump', 'FullJump', 'Total'],
          perfect: true
        },
        tableData:[],
        toolbar: {
          id: 'toolbar_demo_2',
          refresh: true,
          zoom: true,
          custom: {
            storage: true
          }
        },
        tableColumn: [
          { type: 'checkbox', width: 50 },
          { type: 'seq', title:'序号',width: 60 },
          { field: 'createTime', title: '日期',remoteSort: true ,},
          { field: 'tagTitle', title: '标签',showOverflow: true},
          { field: 'count', title: '关联博客数', },
          {field:'operation',title:'操作',slots: {
              // 对应自定义插槽的名称
              default: 'name_default'
            }}
        ]
      }
    },
    created() {
      this.findList()
    },
    methods: {
      findList () {
        const that = this;
        TagList(this.tablePage.currentPage,this.tablePage.pageSize).then(function(response) {
          that.tableData = response.data.records
          that.tablePage.total = response.data.total
        },function(err){
          console.log(err);
        }).catch(e => {
          this.loading = false
        })
      },
      showDetailEvent(row, column){
        TagDetail(row.id).then((res) =>{
          const that = this;
          that.formInline = res.data;
          that.blogTitle = res.data.blogTitle;
        })
        this.centerDialogVisible = true;
      },
      editDetailEvent(row,column){
        TagDetail(row.id).then((res) =>{
          const that = this;
          that.formInline = res.data;
          that.id = row.id;
        })
        this.editDialogVisible = true;
      },
      searchEvent () {
        this.tablePage.currentPage = 1
        this.findList()
      },
      handlePageChange ({ currentPage, pageSize }) {
        this.tablePage.currentPage = currentPage
        this.tablePage.pageSize = pageSize
        this.findList()
      },
      create(){
        this.addDialogVisible = true;
      },
      addTag(){
        createTag(this.formInline).then((res) =>{
          if (res.code = 20000){
            this.$message({
              type: 'success',
              message: '添加成功!'
            });
            this.$router.go(0);
            this.addDialogVisible = false;
          }else {
            this.$message({
              type: 'error',
              message: '添加失败!'
            });
            this.$router.go(0);
            this.addDialogVisible = false;
          }
        })
      },
      editTag(id){
        updateTag(this.formInline,id).then((res) =>{
          if (res.code = 20000){
            this.$message({
              type: 'success',
              message: '修改成功!'
            });
            this.$router.go(0);
            this.editDialogVisible = false;
          }else {
            this.$message({
              type: 'error',
              message: '修改失败!'
            });
            this.$router.go(0);
            this.editDialogVisible = false;
          }
        })
      },
      deleteById(row){
        this.$confirm('此操作将永久删除标签, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteTag(row.id).then((res) =>{
            if (res.code=20000){
              this.$message({
                type: 'success',
                message: '删除成功!'
              });
              this.$router.go(0);
            }else {
              this.$message({
                type: 'error',
                message: '删除失败!'
              });
              this.$router.go(0);
            }
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      }
    }
  }
</script>

<style scoped>
  .input{
    position: relative;
    float: right;
  }
</style>


