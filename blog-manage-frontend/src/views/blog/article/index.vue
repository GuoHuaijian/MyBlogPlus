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

      <template  v-slot:name_stauts="{ row, column }">
        <span v-if=" row.status=='草稿箱' " style="color:#f8ac59">{{ row.status }}</span>
        <span v-else-if=" row.status=='已发布' " style="color:#37B328">{{ row.status }}</span>
        <span v-else style="color:red">{{ row.status }}</span>
      </template>

    </vxe-grid>
  </div>
</template>

<script>
  import {articleList} from '@/api/article'
  import { deleteArticle } from '../../../api/article'

  export default {
    name: 'ComplexTable',
    data () {
      return {
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
          { field: 'title', title: '标题',showOverflow: true},
          { field: 'author', title: '作者', },
          { field: 'clickNum', title: '阅读数', },
          { field: 'status', title: '状态',slots:{default:'name_stauts' }},
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
        var that = this;
        articleList(this.tablePage.currentPage,this.tablePage.pageSize).then(function(response) {
          that.tableData = response.data.records
          that.tablePage.total = response.data.total
        },function(err){
          console.log(err);
        }).catch(e => {
           this.loading = false
         })
      },
      showDetailEvent(row, column){
        this.$router.push({path: '/detail/',query:{ id:row.id}});
      },
      editDetailEvent(row,column){
        this.$router.push({path: '/edit/',query:{ id:row.id}});
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
        this.$router.push("/create");
      },
      deleteById(row){
        this.$confirm('此操作将永久删除文章, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteArticle(row.id).then((res) =>{
            if (res.code=20000){
              this.$router.go(0);
              this.$message({
                type: 'success',
                message: '删除成功!'
              });
            }else {
              this.$message({
                type: 'error',
                message: '删除失败!'
              });
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


