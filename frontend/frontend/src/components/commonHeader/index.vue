<template>
  <div class="header-container">
    <div class="home-area" @click="goPage( '/' )">
      <img class="img-okky"  src="/static/image/okky.JPG"/>
      <div class="title">OKKY</div>
    </div>
    <div class="link-area">
      <div class="link" :class="{ current: isValidLink( 'qna' ) }" @click="goPageWithLogin('/qna')">Q&A</div>
      <div class="link" :class="{ current: isValidLink( 'knowledge' ) }" @click="goPageWithLogin('/knowledge')">지식</div>
      <div class="link" :class="{ current: isValidLink( 'community' ) }" @click="goPageWithLogin('community')">커뮤니티</div>
      <div class="link" :class="{ current: isValidLink( 'event' ) }" @click="goPageWithLogin('event')">이벤트</div>
    </div>
    <div class="login-area" v-if="!isLogin">
        <div class="menu" @click="goPage('/login')">로그인</div>
        <div class="menu registry" @click="goPage('/join')">회원가입</div>
      </div>

    <div class="status-area" v-else ref="insideRootElement">
      <img class="img-stuats" @click="openStatus = !openStatus" src="/static/image/status.JPG" >
      <div class="stauts" v-show="openStatus">
        <div class="status-link">내 프로필</div>
        <div class="status-link">내 계정</div>
        <div class="status-link">활동 내역</div>
        <div class="status-link" @click="logout">로그아웃</div>
      </div>
    </div>
  </div>
</template>
<script>

import _ from 'lodash'
import outsideClick from '@/mixins/outsideClick'

export default {
  name: 'Header',
  props: {
    link: null
  },
  mixins: [ outsideClick ],
  data() {
    return {
      openStatus: false,
      pathName: null,
    }
  },
  computed: {
    isLogin() {
      return this.$store.getters.isLogin
    },
    member() {
      return this.$store.getters.getMember
    }
  },
  created() {
    this.pathName = window.location.pathname
  },
  methods: {
    goPageWithLogin( path ) {
      if( !this.isLogin ) {
        alert( '로그인 먼저 해주세요' )
        return
      }
      this.goPage( path )
    },
    goPage( path ) {
      if( this.pathName !== path ) {
        this.$router.push( path )
        this.pathName = window.location.pathname
      }
    },
    onClickStatus() {

    },
    onOutsideClick() {
      this.openStatus = false
    },
    async logout() {
      await this.$store.dispatch( 'reqeustLogout' )
    },
    isValidLink( path ) { // 렌더링이 너무 많이된다 나중에 고치자
      return _.includes( this.pathName, path )
    }
  }
}
</script>

<style scoped lang="scss"> 
.header-container {
  display: flex;
  justify-content: space-around;
  align-items: center;
  width: 100%;
  height: 70px;
  border-bottom: 1px solid #d4cccc;
  
  .home-area {
    display: flex;
    align-items: center;

    .img-okky {
      width: 50px;
      height: 35px;
      cursor: pointer;
    }

    .title {
      font-size: 25px;
      font-weight: bold;
      margin-right: 20px;
      cursor: pointer;
    }
  }
  

  .link-area {
    display: flex;
    align-items: center;

    .link {
      font-size: 15px;
      font-weight: 600;
      margin-right: 30px;
      cursor: pointer;

      &.current {
        color: rgb(76, 76, 251);
      }
    }

  }

  .login-area {
    display: flex;
    justify-content: center;
    align-items: center;

    .menu {
      display: flex;
      justify-content: center;
      align-items: center;
      margin-right: 10px;
      border-radius: 50px;
      width: 80px;
      height: 40px;
      border: 1px solid #333333;
      cursor: pointer;

      &.registry {
        background-color: blue;
        border: none;
        color: #ffffff;
      }
    }
  }
  
  .status-area {
    position: relative;
    cursor: pointer;

    .img-stuats {
      width: 35px;
    }
    
    .stauts {
      z-index: 99999;
      display: grid;
      grid-template-rows: repeat(4, 1fr);
      position: absolute;
      border: 1px solid #ebe7e7;
      background-color: #ffffff;
      width: 250px;
      height: 250px;
      right: 0;

      .status-link {
        display: flex;
        justify-content: center;
        align-items: center;
      }
    }
  }
  
}
</style>
