import VUE from 'vue'
import VUEX from 'vuex'

VUE.use(VUEX)

const state = {
  isPractice: false, //练习模式标志
  flag: false, //菜单栏左右滑动标志
  userInfo: null,
  menu: [
    {
      index: '1',
      title: '考试管理',
      icon: 'icon-kechengbiao',
      content:[{item:'题库管理',path:'/examBank'},{item:'试题管理',path:'/selectAnswer'},{item:'试卷管理',path:'/addAnswer'}],
    },
    // {
    //   index: '2',
    //   title: '题库管理',
    //   icon: 'icon-tiku',
    //   content:[{item1:'功能介绍',path:'/answerDescription'},{item2:'所有题库',path:'/selectAnswer'},{item3:'增加题库',path:'/addAnswer'},{path: '/addAnswerChildren'}],
    // },
    {
      index: '2',
      title: '成绩查询',
      icon: 'icon-performance',
      content:[{item:'学生成绩查询',path:'/allStudentsGrade'},{item: '成绩分段查询',path: '/selectExamToPart'}],
    },
    {
      index: '3',
      title: '学生管理',
      icon: 'icon-role',
      content:[{item:'班级管理',path:'/class'},{item:'学生管理',path:'/studentManage'}],
    }
  ],
}
const mutations = {
  practice(state,status) {
    state.isPractice = status
  },
  toggle(state) {
    state.flag = !state.flag
  },
  changeUserInfo(state,info) {
    state.userInfo = info
  }
}
const getters = {

}
const actions = {
  getUserInfo(context,info) {
    context.commit('changeUserInfo',info)
  },
  getPractice(context,status) {
    context.commit('practice',status)
  }
}
export default new VUEX.Store({
  state,
  mutations,
  getters,
  actions
})
