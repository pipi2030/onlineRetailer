<template>
  <div class="container">
    <!--顶部菜单栏-->
    <van-nav-bar
            title="乐购商城"
            left-text="注册"
            right-text="登陆"
            @click-left="onClickLeft"
            @click-right="onClickRight"
    />
    <!--主体内容-->
    <router-view/>

    <!--底部菜单栏-->
    <van-tabbar v-model="active" router>
      <van-tabbar-item  icon="home-o" to="/">主页</van-tabbar-item>
      <van-tabbar-item  icon="search" to="/search">搜索</van-tabbar-item>
      <van-tabbar-item  icon="chat-o" to="/cart">购物车</van-tabbar-item>
      <van-tabbar-item  icon="friends-o" to="/center">个人中心</van-tabbar-item>
    </van-tabbar>
  </div>
</template>
<script>
  export default {
      data(){
        return{
            active:0,
        }
      },
      methods:{
          onClickLeft(){
              this.$router.push("/register")
          },
          onClickRight(){
              this.$router.push("/login")
          },

      },
  }
</script>
<style scoped>
  /deep/ .van-ellipsis{
    color:#fc5531;
    font-weight: bolder;
    font-size: 20px;
  }
</style>