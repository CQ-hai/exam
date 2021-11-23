<template>
  <el-form :model="bank" :rules="rules" ref="bank" label-width="100px" class="demo-ruleForm">
    <el-form-item label="活动名称" prop="bankName">
      <el-input v-model="bank.bankName"></el-input>
    </el-form-item>
    <el-form-item label="题库描述" prop="desciption">
      <el-input v-model="bank.desciption"></el-input>
    </el-form-item>
    <el-form-item >
      <el-button @click="addBank('bank')">添加</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
export default {
  name: "addBank",
  data(){
    return{
      bank:{
        bankName:'',
        desciption: ''
      },
      rules: {
        bankName: [
          {required: true, message: '请输入题库名称', trigger: 'blur'},
        ]
      }
    }
  },
  methods:{
    addBank(form){
      this.$refs[form].validate((valid) => {
        if (valid) {
          const data=this.bank
          this.$axios({
              url: '/api/bank/addExamBank',
              method: 'post',
              data: this.bank
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
