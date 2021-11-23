<template>
  <div>
    <el-card>
      <el-button type="primary" @click="$router.push({ path: '/addClass' });">添加题库</el-button>
      <el-table :data="classList">
        <el-table-column label="班级名" prop="name"> </el-table-column>
        <el-table-column label="班级描述" prop="desciption"> </el-table-column>
        <el-table-column label="操作"></el-table-column>
      </el-table>
    </el-card>

  </div>

</template>

<script>
export default {
  name: "class",
  data(){
    return {
      classList: [],
      pageIndex: 1,
      pageSize: 5
    }
  },
  created() {
    this.getClass()
  },
  methods:{
    getClass() {
      this.$axios({
        url:'/api/class/getClass',
        method: "post",
        data:{
          pageIndex: this.pageIndex,
          pageSize:  this.pageSize,
          name: ''
        }
      }).then(res=>{
        this.classList=res.data.records
      })
    }
  }
}
</script>

<style scoped>

</style>
