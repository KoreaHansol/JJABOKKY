<template>
  <div class="login-page-container">
      <div class="welcome">
        <img class="img-okky" src="/static/image/okky.JPG"/>
        <div class="welcome-meg title">OKKY에 오신것을 환영합니다.</div>
        <div class="welcome-meg description">OKKY는 소프트웨어 개발자를 위한 지식공유 플랫폼 입니다.</div>
      </div>

      <div class="description">OKKY 아이디로 로그인</div>

      <div class="form">
        <div class="area">
          <div class="title">아이디</div>
          <normal-input class="info-input" v-model="member.id"/>
        </div>
        <div class="area">
          <div class="title">비밀번호</div>
          <normal-input class="info-input" v-model="member.password"/>
        </div>
      </div>


      <div class="login-button" :class="{ 'none-validation': noneValidation }" @click="login">로그인</div>

      <div class="register-question">아직 회원이 아니신가요? <span class="register" @click="onRegister">회원가입</span></div>
  </div>
</template>

<script>
import req2svr from './req2svr'

import NormalInput from '@/components/normalInput'

export default {
  name: 'Login',
  components: { NormalInput },
  data () {
    return {
      member: {
        id: '',
        password: '',
      }
    }
  },
  computed: {
    req2svr: () => req2svr,
    noneValidation() {
      // 개발땐 간단하게 잡는다
      if( !this.member.id || !this.member.password ) {
        return true
      }

      return false
    }
  },
  created() {
    
  },
  methods: {
    async login() {
      try {
        let token = await this.req2svr.login( this.member )
        if( token ) {
          alert( "로그인에 성공하였습니다." )
          this.$store.dispatch( 'reqeustLogin', token )
          this.$router.push( '/' )
        }
      } catch( err ) {
        if( _.get( err, 'response.status' ) == 404 ) {
          alert( "잘못된 회원정보 입니다." )
        }
      }
    },
    onRegister() {
      this.$router.push( '/join' )
    }
  }

}
</script>

<style lang="scss" scoped>
  .login-page-container {
    display: flex;
    height: 100%;
    flex-direction: column;
    align-items: center;
    margin: 150px 200px 50px 200px;

    .welcome {
      display: flex;
      flex-direction: column;
      align-items: center;

      .img-okky {
        width: 100px;
        height: 80px;
        margin-bottom: 20px;
      }

      .welcome-meg {

        &.title {
          font-weight: bold;
          font-size: 30px;
          margin-bottom: 15px;
        }

        &.description {
          font-size: 15px;
          margin-bottom: 25px;
        }
      }
    }

    .description {
      font-size: 15px;
    }

    .form {
      width: 500px;
      margin-top: 50px;

      .area {
        display: flex;
        flex-direction: column;
        margin-bottom: 30px;

        .title {
          margin-bottom: 10px;
        }
        
        .info-input {
          width: 100%;
          height: 50px;
        }
      }
    }

    .login-button {
      display: flex;
      justify-content: center;
      align-items: center;
      color: #ffffff;
      font-weight: bold;
      width: 500px;
      height: 50px;
      border-radius: 6px;
      background-color: #0090F9;
      cursor: pointer;

      &.none-validation {
        pointer-events: none;
        background-color: #98D2FC;
      }
    }

    .register-question {
      font-size: 15px;
      margin-top: 30px;

      .register {
        cursor: pointer;
        color: #3F96F9;
        text-decoration: underline;
      }
    }
  }
  

  
</style>
