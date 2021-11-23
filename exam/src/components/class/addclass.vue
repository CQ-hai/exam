<template>
  <el-form :model="classs" :rules="rules" ref="class" label-width="100px" class="demo-ruleForm">
    <el-form-item label="班级名称" prop="bankName">
      <el-input v-model="classs.name"></el-input>
    </el-form-item>
    <el-form-item label="班级描述" prop="desciption">
      <el-input v-model="classs.desciption"></el-input>
    </el-form-item>
    <el-form-item >
      <el-button @click="addClass('class')">添加</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
export default {
  name: "addclass",
  data() {
    return{
      classs:{
        name:'',
        desciption: ''
      },
      rules: {
        name: [
          {required: true, message: '请输入题库名称', trigger: 'blur'},
        ]
      }
    }
  },
  methods:{
    addClass(form){
      this.$refs[form].validate((valid) => {
        if (valid) {
          const data=this.bank
          this.$axios({
              url: '/api/class/insert',
              method: 'post',
              data: this.classs
            }
          )
            .then(res => {
              console.log(res);
              this.$router.go(-1)
            })
            .catch(error => {});
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
  }
}
</script>

<style scoped>

</style>
