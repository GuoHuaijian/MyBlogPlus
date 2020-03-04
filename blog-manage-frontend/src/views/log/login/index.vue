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

      <template  v-slot:name_stauts="{ row, column }">
        <span v-if=" row.status=='1' " style="color:#f8ac59">失败</span>
        <span v-else-if=" row.status=='0' " style="color:#37B328">成功</span>
        <span v-else style="color:red">{{ row.status }}</span>
      </template>

    </vxe-grid>
  </div>
</template>
<script>
  import { LoginLogList } from '../../../api/log'

  export default {
    name: 'LoginLog',
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
          { field: 'userName', title: '登录账号', },
          { field: 'ipaddr', title: '登录IP', },
          { field: 'loginLocation', title: '登录地点',},
          { field: 'browser', title: '浏览器'},
          { field: 'os', title: '操作系统', },
          { field: 'status', title: '登录状态', slots:{default:'name_stauts' }},
          { field: 'msg', title: '操作信息',},
          {field:'loginTime',title:'登录时间',remoteSort: true ,}
        ]
      }
    },
    created() {
      this.findList()
    },
    methods: {
      findList () {
        var that = this;
        LoginLogList(this.tablePage.currentPage,this.tablePage.pageSize).then(function(response) {
          that.tableData = response.data.records
          that.tablePage.total = response.data.total
        },function(err){
          console.log(err);
        }).catch(e => {
          this.loading = false
        })
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
      deleteById(row){
        this.$confirm('此操作将永久删除文章, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deletelog(row.id).then((res) =>{
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
