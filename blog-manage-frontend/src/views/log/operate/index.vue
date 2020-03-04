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

      <!--自定义插槽 name_default-->
      <template v-slot:name_default="{ row, column,seq }">
            <span>
                <button class="el-button--success" @click="showDetailEvent(row, column)" >查看</button>&nbsp;&nbsp;
            </span>
      </template>

      <template  v-slot:name_type="{ row, column }">
        <span v-if=" row.businessType=='0' " style="color:#f8ac59">其他</span>
        <span v-else-if=" row.businessType=='1' " style="color:#37B328">新增</span>
        <span v-else-if=" row.businessType=='2' " style="color:#1c84c6">修改</span>
        <span v-else style="color:red">删除</span>
      </template>

      <template  v-slot:name_stauts="{ row, column }">
        <span v-if=" row.status=='1' " style="color:#f8ac59">失败</span>
        <span v-else-if=" row.status=='0' " style="color:#37B328">成功</span>
        <span v-else style="color:red">{{ row.status }}</span>
      </template>
    </vxe-grid>


    <div>
      <el-dialog
        title="操作日志信息"
        :visible.sync="centerDialogVisible"
        width="50%"
        center>
        <el-form ref="form" :model="form" label-width="100px" size="mini">
          <el-row>
            <el-col :span="12">
              <el-form-item label="操作模块：">{{ form.title }}</el-form-item>
              <el-form-item
                label="登录信息："
              >{{ form.operName }} / {{ form.operIp }} / {{ form.operLocation }}</el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="请求地址：">{{ form.operUrl }}</el-form-item>
              <el-form-item label="请求方式：">{{ form.requestMethod }}</el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="操作方法：">{{ form.method }}</el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="请求参数：">{{ form.operParam }}</el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="返回参数：">{{ form.jsonResult }}</el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="操作状态：">
                <div v-if="form.status === 0">正常</div>
                <div v-else-if="form.status === 1">失败</div>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="操作时间：">{{ form.operTime }}</el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="异常信息：" v-if="form.status === 1">{{ form.errorMsg }}</el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <span slot="footer" class="dialog-footer">
    <el-button type="primary" @click="centerDialogVisible = false">返 回</el-button>
  </span>
      </el-dialog>
    </div>
  </div>
</template>

<script>
  import { LoginLogList, OperLogDetail, OperLogList } from '../../../api/log'

  export default {
    name: 'OperateLog',
    data () {
      return {
        form: {},
        centerDialogVisible: false,
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
          { field: 'title', title: '系统模块', },
          { field: 'businessType', title: '操作类型',slots:{default:'name_type' } },
          { field: 'operName', title: '操作人员', },
          { field: 'operIp', title: '主机地址', },
          { field: 'operLocation', title: '操作地点', },
          { field: 'status', title: '操作状态',slots:{default:'name_stauts' } },
          {field:'operTime',title:'操作时间',remoteSort: true ,},
          { field: 'operation', title: '操作', slots: {
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
        OperLogList(this.tablePage.currentPage,this.tablePage.pageSize).then(function(response) {
          that.tableData = response.data.records
          that.tablePage.total = response.data.total
        },function(err){
          console.log(err);
        }).catch(e => {
          this.loading = false
        })
      },
      showDetailEvent(row, column){
        this.centerDialogVisible = true
        OperLogDetail(row.operId).then((res) =>{
          const that = this;
          that.form = res.data;
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
