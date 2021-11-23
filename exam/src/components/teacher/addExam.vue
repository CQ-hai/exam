<!-- 添加考试 -->
<template>
  <div style="width: 100%">
    <el-container style="width: 100%">
      <el-aside style="width: 55%">
        <el-card shadow="always">
          <h4>当前卷面分：{{ this.form.totalScore}}</h4>
          <el-select v-model="optionValue" placeholder="请选择题库" class="w150" @change="change">
            <el-option
              v-for="item in options"
              :key="item.bankId"
              :label="item.bankName"
              :value="item | object2String">
            </el-option>
          </el-select><br>

          <div style="font-size: 10px">
            选择题：<el-input-number v-model="num1" :min="0" :max="max1" style="width: 25%"></el-input-number>
            填空题：<el-input-number v-model="num2" :min="0" :max="max2" style="width: 25%"></el-input-number>
            判断题：<el-input-number v-model="num3" :min="0" :max="max3" style="width: 25%"></el-input-number>
          </div>
          <el-button @click="addQuestion">添加题目</el-button>
          <el-table :data="questions">
            <el-table-column label="题库名" prop="bankName">
            </el-table-column>
            <el-table-column label="选择题数量" prop="multi"></el-table-column>
            <el-table-column label="填空题数量" prop="fill"></el-table-column>
            <el-table-column label="判断题数量" prop="judge"></el-table-column>
          </el-table>
        </el-card>
      </el-aside>
      <el-main>
        <section class="add">
          <el-form ref="form" :model="form" label-width="80px" :inline="true">
            <el-form-item label="试卷名称">
              <el-input v-model="form.source"></el-input>
            </el-form-item>
            <el-form-item label="介绍">
              <el-input v-model="form.description"></el-input>
            </el-form-item>
            <el-form-item label="年级">
              <el-select v-model="form.grade" placeholder="请选择">
                <el-option
                  v-for="item in classOption"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="考试日期">
              <el-col :span="11">
                <el-date-picker placeholder="选择日期" v-model="form.examDate" style="width: 100%;"></el-date-picker>
              </el-col>
            </el-form-item>
            <el-form-item label="持续时间">
              <el-input v-model="form.totalTime"></el-input>
            </el-form-item>
            <el-form-item label="总分">
              <el-input v-model="form.totalScore"></el-input>
            </el-form-item>
            <el-form-item label="考试类型">
              <el-input v-model="form.type"></el-input>
            </el-form-item>
            <el-form-item label="考生提示">
              <el-input type="textarea" v-model="form.tips"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSubmit()">立即创建</el-button>
              <el-button type="text" @click="cancel()">取消</el-button>
            </el-form-item>
          </el-form>
        </section>
      </el-main>
    </el-container>


  </div>
</template>

<script>
export default {
  filters: {
    object2String:function(obj){
      return JSON.stringify(obj)
    }
  },
  data() {
    return {
      classOption: [],
      form: { //表单数据初始化
        source: null,
        description: null,
        institute: null,
        major: null,
        grade: null,
        examDate: null,
        totalTime: null,
        totalScore: null,
        type: null,
        tips: null,
        paperId: null,
      },
      options: [{
        bankId: 1,
        bankName:'123'
      }
      ],
      optionValue:'',
      num1:0,
      num2:0,
      num3:0,
      max1:0,
      max2:0,
      max3:0,
      questions:[],
      questionItem: {}
    };
  },
  created() {
this.getBank()
    this.getClass()
  },
  methods: {
    getClass() {
      this.$axios({
        url:'/api/class/slectClass',
        method: "get",
      }).then(res=>{
        this.classOption=res.data
      })
    },
    getBank() {
        this.$axios({
          url: `/api/bank/selectExamBank`,
          method: 'get',

        }).then(res => {
          this.options = res.data
          console.log(this.options)
        })
    },
    formatTime(date) { //日期格式化
      let year = date.getFullYear()
      let month= date.getMonth()+ 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
      let day=date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
      let hours=date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
      let minutes=date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
      let seconds=date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
      // 拼接
      return year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
    },
    onSubmit() {
      let examDate = this.formatTime(this.form.examDate)
      this.form.examDate = examDate.substr(0,10)
      var question=this.questions
      this.$axios(`/api/examManagePaperId`).then(res => {
        this.form.paperId = res.data.data.paperId + 1 //实现paperId自增1
        const question2 = question;
        const data={
          exam: this.form,
          question: question2
        }
        console.log(data)
        this.$axios({
          url: '/api/exam',
          method: 'post',
          data: data
        }).then(res => {
          if(res.data.code == 200) {
            this.$message({
              message: '数据添加成功',
              type: 'success'
            })
            this.$router.push({path: '/selectExam'})
          }
        })
      })
    },
    addQuestion() {
      if (this.optionValue!=null){
        this.form.totalScore=this.form.totalScore+(this.num1+this.num2+this.num3)*2
        this.questions.push({
          bankId: JSON.parse(this.optionValue).bankId,
          bankName: JSON.parse(this.optionValue).bankName,
          multi: this.num1,
          fill: this.num2,
          judge: this.num3
        })
      }
    },
    cancel() { //取消按钮
      this.form = {}
    },
    change(val){
      this.max1=this.max2=this.max3=this.num1=this.num2=this.num3=0
      this.$axios({
        url: '/api/bank/getBankNum',
        method: 'post',
        data: {
          "bankId":JSON.parse(val).bankId
        }
      }).then(res => {
        console.log(res.data)
        this.max1=res.data.multiNum
        this.max2=res.data.fillNum
        this.max3=res.data.judgeNum
        console.log(this.max2)
      })
    }
  }
};
</script>
<style lang="scss" scoped>
.add {
  padding: 0px 0px;

}
</style>

