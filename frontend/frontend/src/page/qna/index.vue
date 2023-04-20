<template>
  <div class="qna-container">
      <div class="contant-container">
        <img class="img-okky" src="/static/image/qna.JPG"/>
        <div class="content-header">
          <div class="btn-question" @click="onClickQuestion">질문하기</div>
          <div class="link-area">
            <div class="link">기술</div>
            <div class="link">커리어</div>
            <div class="link">기타</div>
            <div class="link">전체</div>
          </div>
          <div class="sort-area">
            <select-box :datas="selectVisibleItems" v-model="selectVisibleItem" :left="true">{{ selectVisibleItem.text }}</select-box>
            <select-box :datas="selectSortItems" v-model="selectSortItem" :right="true">{{ selectSortItem.text }}</select-box>
          </div>
        </div>
        <div class="filter"></div>
        <div class="content"></div>
      </div>
  </div>
</template>

<script>
import SelectBox from '@/components/selectBox'
export default {
  name: 'QNA',
  components: { SelectBox },
  data () {
    return {
      selectVisibleItems: [
        { id: 1, text: '전체' },
        { id: 2, text: '답변없음' }
      ],
      selectVisibleItem: { id: 1, text: '전체' },
      selectSortItems: [
        { id: 1, text: '최신순' },
        { id: 2, text: '추천순' },
        { id: 3, text: '답변순' },
        { id: 4, text: '스크랩순' },
        { id: 5, text: '조회순' }
      ],
      selectSortItem: { id: 1, text: '최신순' }
    }
  },
  computed: {
  },
  created() {
    
  },
  methods: {
    onClickQuestion() {
      this.$router.push( '/qna/new' )
    }
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

      .content-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-top: 20px;

        .btn-question {
          display: flex;
          justify-content: center;
          align-items: center;
          color: #ffffff;
          width: 100px;
          height: 40px;
          border-radius: 6px;
          font-size: 15px;
          font-weight: bold;
          background-color: #0090F9;
          cursor: pointer;
        }

        .link-area {
          display: flex;
          align-items: center;
          gap: 30px;

          .link {
            font-size: 16px;
            font-weight: bold;
          }
        }
      }

      .sort-area {
        display: flex;
        align-items: center;
      }
    }
  }
</style>
