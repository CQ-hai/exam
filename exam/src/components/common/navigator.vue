<template>
  <div id="nav">
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item><i class="el-icon-menu" @click="toggle()"></i></el-breadcrumb-item>
      <el-breadcrumb-item class="title">{{active.title}}</el-breadcrumb-item>
      <el-breadcrumb-item v-if="path!==''">{{path}}</el-breadcrumb-item>
<!--      <el-breadcrumb-item v-if="index=== 1">{{router[index].item2}}</el-breadcrumb-item>-->
<!--      <el-breadcrumb-item v-if="index=== 2">{{router[index].item3}}</el-breadcrumb-item>-->

    </el-breadcrumb>
  </div>
</template>

<script>
import store from '@/vuex/store'
import {mapState,mapMutations} from 'vuex'
export default {
  data() {
    return {
      active: [],
      index1: null,
      index: null,
      router: [{},{},{}],
      path:""
    }
  },
  computed: mapState(["menu"]),
  methods: {
    ...mapMutations(["toggle"]),
    getIndex() {
      this.path=''
      this.active = []
      this.bus.$on('sendIndex',(data)=>{
        this.index1 = data
        this.active = this.menu[data-1]
        this.router=this.active.content
        const rous = this.router;
        console.log("path"+ this.$route.path)
        console.log("rous"+ JSON.stringify(this.menu))
        for(let i=0;i<rous.length;i++){
          if(rous[i].path===this.$route.path){
            this.path=rous[i].item
          }
        }
         console.log(JSON.stringify(this.router[index]))
      })
    }
  },
  created() {
    this.getIndex()
    this.path=''
  },
  beforeDestroy() {
    // this.bus.$off('sendIndex') //销毁
  },
  store
}
</script>

<style scoped>
#nav .el-breadcrumb {
  height: 60px;
  line-height: 60px;
  padding-left: 20px;
}
#nav .el-breadcrumb .title{
  font-weight: bold;
}
</style>
