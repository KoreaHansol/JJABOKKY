<template>
  <div class="new-container">
    <div class="title">기술 궁금증 해결하기</div>
    <div class="sub-title">
    <span class="nickname">{{ this.nickname }}</span>님 지식공유 플랫폼 OKKY에서 최고의 개발자들과 함께 궁금증을 해결하세요.</div>

    <select-box class="select-box" :datas="selectSortItems" v-model="selectSortItem">{{ selectSortItem.text }}</select-box>
    <input class="title-input" placeholder="제목을 입력해 주세요" v-model="title"/>

  </div>
</template>

<script>
import CommonHeader from '@/components/commonHeader.vue'
import SelectBox from '@/components/selectBox'
export default {
  name: 'QNA',
  components: { CommonHeader, SelectBox, },
  data () {
    return {
      title: '',
      selectSortItems: [
        { id: -1, text: '토픽을 선택해 주세요' },
        { id: 1, text: '기술' },
        { id: 2, text: '커리어' },
        { id: 3, text: '기타' },
      ],
      selectSortItem: { id: -1, text: '토픽을 선택해 주세요' }
    }
  },
  computed: {
    member() {
      return this.$store.getters.getMember
    },
    nickname() {
      return _.get( this.member, 'nickname', '' )
    }
  },
  created() {
    
  },
  methods: {
  }
}
</script>

<style lang="scss" scoped>
  .new-container {
    margin: 20px;

    .title {
      font-size: 30px;
      font-weight: bold;
    }

    .sub-title {
      margin-top: 20px;

      .nickname {
        font-weight: bold;
        color: #6B7280;
      }
    }

    .select-box {
      margin-top: 30px ;
      width: auto;
      height: 22px;
    }

    .title-input {
      margin-top: 20px;
      border-radius: 6px;
      width: 100%;
      height: 40px;
      border: 1px solid #D2D4D9;
    }
  }
</style>
