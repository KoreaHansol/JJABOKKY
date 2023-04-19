<template>
  <div class="main-page-container">
    <common-header></common-header>
    <div class="content">
      <div class="side">
      <div class="ranker-title">Top Writer</div>
      <div class="ranker">
        <div class="name">김한솔</div>
        <div class="score">80</div>
      </div><div class="ranker">
        <div class="name">김한솔</div>
        <div class="score">80</div>
      </div><div class="ranker">
        <div class="name">김한솔</div>
        <div class="score">80</div>
      </div><div class="ranker">
        <div class="name">김한솔</div>
        <div class="score">80</div>
      </div><div class="ranker">
        <div class="name">김한솔</div>
        <div class="score">80</div>
      </div>
    </div>
    <div class="quaters-content">
      <div class="qna-container">
        <div class="title">Q&A</div>
      </div>
      <div class="community-container">
        <div class="title">커뮤니티</div>
      </div>
      <div class="weekly-container">
        <div class="title">WEEKLY BEST</div>
      </div>
      <div class="choice-container">
        <div class="title">EDITOR CHOICE</div>
      </div>
    </div>
    </div>
  </div>
</template>

<script>
import req2svr from './req2svr'
import CommonHeader from '@/components/commonHeader'

export default {
  name: 'MainPage',
  components: { CommonHeader },
  data () {
    return {
    }
  },
  computed: {
    req2svr: () => req2svr,
    token() {
      return this.$store.getToken
    }
  },
  created() {
    this.req2svr.getInitData()
  },
  methods: {
    onRegister() {
      this.$router.push( '/join' )
    },
    onLogin() {
      this.$router.push( '/login' )
    },
    joinQNA() {
      this.$router.push( 'qna' )
    }
  }

}
</script>

<style lang="scss" scoped>
  .main-page-container {
    display: flex;
    flex-direction: column;
    height: 100%;
  }

  .content {
    display: flex;
    flex-direction: row;
    padding: 0 100px;
    
    .side {
      flex: 0 1 200px;

      .ranker-title {
        font-size: 18px;
        font-weight: bold;
        padding-bottom: 30px;
        border-bottom: 1px solid #333333;
      }
      

      .ranker {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin: 10px;
      }
    }

    .quaters-content {
      flex: 1 1 300px;

      display: grid;
      grid-template-columns: repeat(2, 1fr);
      grid-template-rows: repeat(2, 1fr);
    }
  }

  
</style>
