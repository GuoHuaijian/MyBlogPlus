<template>
  <div class="dashboard-container">
    <div class="dashboard-text">博文概览</div>
    <PanelGroup></PanelGroup>
    <div>
      <el-row :gutter="12">
        <el-col :span="16">
          <el-card shadow="always" :span="16">
            <LineChart></LineChart>
          </el-card>
        </el-col>
        <el-col :span="8">
          <el-card shadow="always">
              <BoxCard></BoxCard>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import PanelGroup from './component/PanelGroup'
import LineChart from './component/LineChart'
import BoxCard from './component/BoxCard'


export default {
  name: 'Dashboard',
  components: { LineChart, PanelGroup,BoxCard },
  computed: {
    ...mapGetters([
      'name'
    ])
  },
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
