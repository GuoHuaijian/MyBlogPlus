<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="轮播图标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入轮播图标题"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="操作人员" prop="creator">
        <el-input
          v-model="queryParams.creator"
          placeholder="请输入操作人员"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="display">
        <el-select v-model="queryParams.display" placeholder="轮播图状态" clearable size="small">
          <el-option
            v-for="(val,key,index) in displays"
            :key="key"
            :label="val"
            :value="key"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
        >删除</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="carouselList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="id" width="100" />
      <el-table-column
        label="轮播图标题"
        align="center"
        prop="title"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="轮播图地址"
        align="center"
        prop="imgUrl"
        width="100"
      />
      <el-table-column
        label="状态"
        align="center"
        prop="display"
        width="100"
      />
      <el-table-column label="创建者" align="center" prop="creator" width="100" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="160">
        <template slot-scope="scope">
          <span>{{scope.row.createTime, '{y}-{m}-{d}' }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改公告对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="780px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="轮播图标题" prop="title">
              <el-input v-model="form.title" placeholder="请输入轮播图标题" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="轮播图跳转地址" prop="url">
              <el-input v-model="form.url" placeholder="请输入轮播图跳转地址" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="状态">
              <el-radio-group v-model="form.display">
                <el-radio
                  v-for="(val,key,index) in displays"
                  :key="key"
                  :label="key"
                >{{val}}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="图片">
              <Upload v-model="form.imgUrl" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer" style="padding-top:20px">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import { listCarousel, getCarousel, delCarousel, addCarousel, updateCarousel} from "@/api/carousel";
  import Upload from '@/components/Upload/SingleImage3'
  export default {
    components: {
      Upload
    },
    name: 'Carousel',
    data() {
      return {
        // 遮罩层
        loading: true,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 总条数
        total: 0,
        // 公告表格数据
        carouselList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 状态数据字典
        displays: {1:"展示",2:"不展示"},
        // 查询参数
        queryParams: Object.assign({
          pageNum: 1,
          pageSize: 10,
          title: '',
          creator: '',
          display: ''
        }),
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          title: [
            { required: true, message: "轮播图标题不能为空", trigger: "blur" }
          ],
          displays: [
            { required: true, message: "轮播图状态不能为空", trigger: "blur" }
          ]
        }
      };
    },
    created() {
      this.getList();
    },
    methods: {
      /** 查询公告列表 */
      getList() {
        this.loading = true;
        listCarousel(this.queryParams.pageNum,this.queryParams.pageSize).then(response => {
          this.carouselList = response.data.records;
          this.total = response.data.total;
          this.loading = false;
        });
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          id: undefined,
         title: undefined,
          imgUrl: undefined,
          url: undefined,
          display: "1",

        };
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.resetForm("queryForm");
        this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.id)
        this.single = selection.length!=1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加轮播图";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const id = row.id || this.ids
        getCarousel(id).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改修改轮播图";
        });
      },
      /** 提交按钮 */
      submitForm: function() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != undefined) {
              updateCarousel(this.form).then(response => {
                if (response.code === 20000) {
                  this.$message("修改成功");
                  this.open = false;
                  this.getList();
                } else {
                  this.$message(response.msg);
                }
              });
            } else {
              addCarousel(this.form).then(response => {
                if (response.code === 20000) {
                  this.$message("新增成功");
                  this.open = false;
                  this.getList();
                } else {
                  this.$message(response.msg);
                }
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const id = row.id || this.ids
        this.$confirm('是否确认删除编号为"' + id + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delCarousel(id);
        }).then(() => {
          this.getList();
          this.$message("删除成功");
        }).catch(function() {});
      }
    }
  }
</script>

<style scoped>

</style>
