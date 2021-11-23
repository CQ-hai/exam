<template>
  <div>
    <el-input v-model="title" placeholder="搜索题库名称" style="width: 200px;margin: 20px" class="filter-item" /><el-button @click="search">查询</el-button>
  <el-card style="margin: 20px">
    <div>
      <el-button type="primary" @click="$router.push({ path: '/addBank' });">添加题库</el-button>
    </div>
  <el-table :data="banks" style="width: 90%;padding: 20px" >
    <!--  <el-table-column-->
    <!--    label="题库名称"-->
    <!--  >-->
    <!--    <template slot-scope="scope">-->

    <!--      &lt;!&ndash;        <router-link :to="{ name: 'UpdateRepo', params:{id: data.row.id}}">&ndash;&gt;-->
    <!--      &lt;!&ndash;          {{ data.row.title }}&ndash;&gt;-->
    <!--      &lt;!&ndash;        </router-link>&ndash;&gt;-->
    <!--      {{ scope.row.bankName }}-->
    <!--    </template>-->

    <!--  </el-table-column>-->
    <el-table-column
      prop="bankName"
      label="题库名称"
      width="300">
    </el-table-column>
    <el-table-column
      prop="desciption"
      label="题库描述"
      width="300">
    </el-table-column>
    <el-table-column
      label="操作"
      width="300">
      <template slot-scope="scope">
        <el-button @click="$router.push({path:'/addAnswerChildren',query:{ bank_id:scope.row.bankId,bank_name:scope.row.bankName} })">添加题目</el-button>
      </template>
    </el-table-column>
  </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="pageIndex"
      :page-sizes="[5, 10]"
      :page-size="pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total">
    </el-pagination>
</el-card>

  </div>

    <!--    <el-table-column-->
    <!--      label="单选题数量"-->
  <!--      prop="radioCount"-->
    <!--      align="center"-->
    <!--    />-->

    <!--    <el-table-column-->
    <!--      label="多选题数量"-->
    <!--      prop="multiCount"-->
    <!--      align="center"-->
    <!--    />-->

    <!--    <el-table-column-->
    <!--      label="判断题数量"-->
    <!--      prop="judgeCount"-->
    <!--      align="center"-->
    <!--    />-->

    <!--    <el-table-column-->
    <!--      label="创建时间"-->
    <!--      align="center"-->
    <!--      prop="createTime"-->
    <!--    />-->


</template>

<script>
export default {
  name: "vue",
  data() {
    return {
      title:"",
      banks:[],
      pageIndex:1,
      pageSize: 5,
      total:0
    }
  },
  created() {
    this.getBank()
  },
  methods:{
    handleSizeChange(val) {
      this.pageSize=val
      this.search()
    },
    handleCurrentChange(val) {
      this.pageIndex=val
      this.search()
    },
    search() {
      console.log(this.pageIndex)
      this.$axios({
          url: '/api/bank/getExamBank',
          method: 'post',
          data:{
            pageSize: this.pageSize,
            pageIndex: this.pageIndex,
            bankName: this.title
          }
        }
      )
        .then(res => {
          this.banks = res.data.records;
          this.total = res.data.total;
          console.log(this.total);
        })
        .catch(error => {});
    },
    getBank() {
      //分页查询所有试卷信息
      this.$axios({
          url: '/api/bank/getExamBank',
          method: 'post',
          data:{
            pageSize: 5,
            pageIndex: 1,
            bankName: ''
          }
        }
      )
        .then(res => {
          this.banks = res.data.records;
          this.total = res.data.total;
          console.log(res);
        })
        .catch(error => {});
    },

  }
}
</script>

<style scoped>

</style>

