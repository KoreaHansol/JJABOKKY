<template>
  <div class="qna-container">
      <div class="contant-container">
        <img class="img-okky" src="/static/image/qna.JPG"/>
        <div class="header"></div>
        <div class="filter"></div>
        <div class="content"></div>
      </div>
  </div>
</template>

<script>
import CommonHeader from '@/components/commonHeader.vue'

export default {
  name: 'Event',
  components: { CommonHeader },
  data () {
    return {
    }
  },
  computed: {
  },
  created() {
    
  },
  methods: {
    
  }

}
</script>

<style lang="scss" scoped>
  .qna-container {
    display: flex;
    height: 100%;
    flex-direction: column;
    align-items: center;

    .contant-container {
      margin-top: 35px;
    }
  }
</style>
