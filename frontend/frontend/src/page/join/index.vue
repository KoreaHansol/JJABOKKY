<template>
  <div class="join-page-container">
      <div class="welcome">
        <img class="img-okky" src="/static/image/okky.JPG"/>
        <div class="welcome-meg title">OKKY에 오신것을 환영합니다.</div>
        <div class="welcome-meg description">OKKY는 소프트웨어 개발자를 위한 지식공유 플랫폼 입니다.</div>
      </div>

      <div class="description">화원가입에 필요한 기본정보를 입력해주세요.</div>

      <div class="form">
        <div class="area">
          <div class="title">아이디</div>
          <normal-input class="info-input" v-model="member.id" placeholder="4~15자 이내로 입력해주세요"/>
        </div>
        <div class="area">
          <div class="title">비밀번호</div>
          <normal-input class="info-input" v-model="member.password" placeholder="최소 6자 이상(알파벳, 숫자 필수)"/>
        </div>
        <div class="area">
          <div class="title">이메일</div>
          <normal-input class="info-input" v-model="member.email" placeholder="hskimaa@naver.com"/>
        </div>
        <div class="area">
          <div class="title">실명</div>
          <normal-input class="info-input" v-model="member.name" placeholder="홍길동"/>
        </div>
        <div class="area">
          <div class="title">닉네임</div>
          <normal-input class="info-input" v-model="member.nickname" placeholder="별명을 알파벳, 한글, 숫자를 20자 이하로 입력해주세요."/>
        </div>
      </div>

      <div class="join-button" :class="{ 'none-validation': noneValidation }" @click="joinMember">회원가입</div>
  </div>
</template>

<script>
import NormalInput from '@/components/normalInput'
import req2svr from './req2svr'

export default {
  name: 'Join',
  components: { NormalInput },
  data () {
    return {
      member: {
        id: '',
        password: '',
        email: '',
        name: '',
        nickname: '',
      }
    }
  },
  computed: {
    req2svr: () => req2svr,
    noneValidation() {
      // 개발땐 간단하게 잡는다
      if( !this.member.id || !this.member.password || !this.member.email || !this.member.name || !this.member.nickname ) {
        return true
      }

      return false
    }
  },
  created() {
    
  },
  methods: {
    async joinMember() {
      try {
        const result = await this.req2svr.joinMember( this.member )
        if( result ) {
          await alert( "회원가입에 성공하셨습니다." );
          this.$router.push( '/login' )
        }
      } catch( e ) {
        console.log(e)
      }
    }
  }

}
</script>

<style lang="scss" scoped>
  .join-page-container {
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

    .join-button {
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
  }
  

  
</style>
