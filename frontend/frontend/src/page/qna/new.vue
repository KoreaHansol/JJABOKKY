<template>
  <div class="new-container">
    <div class="title">기술 궁금증 해결하기</div>
    <div class="sub-title">
    <span class="nickname">{{ this.nickname }}</span>님 지식공유 플랫폼 OKKY에서 최고의 개발자들과 함께 궁금증을 해결하세요.</div>
    
    <select-box class="select-box" :datas="selectSortItems" v-model="selectSortItem">{{ selectSortItem && selectSortItem.text }}</select-box>
    <input class="title-input" placeholder="제목을 입력해 주세요" v-model="title"/>
    <editor class="editor" @change="onChangeEditor($event)"/>

    <div class="btn-area">
      <div class="button">취소</div>
      <div class="button save" @click="save">저장</div>
    </div>
  </div>
</template>

<script>
import req2svr from './req2svr'

import SelectBox from '@/components/selectBox'
import Editor from '@/components/editor'
export default {
  name: 'QNA',
  components: { SelectBox, Editor },
  data () {
    return {
      title: '',
      contents: {
        content: '',
        imageUrls: []
      },
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
    req2svr: () => req2svr,
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
    onChangeEditor( contents ) {
      this.contents = contents
    },
    async save() {
      await this.req2svr.savePost( this.title, this.contents.content )
      const imageUrls = _.get( this, 'contents.imageUrls' ) || []
      if( imageUrls.length ) {
        await this.req2svr.saveImageUrls( imageUrls )
      }
    }
  }
}
</script>

<style lang="scss" scoped>
  .new-container {
    height: 100%;
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


    .btn-area {
      display: flex;
      align-items: center;
      justify-content: flex-end;
      margin-top: 50px;
      
      .button {
        display: flex;
        justify-content: center;
        align-items: center;
        width: 80px;
        height: 40px;
        border-radius: 6px;
        border: 1px solid #D2D4D9;


        &.save {
          margin-left: 10px;
          background-color: #98D2FC;        
          color: #ffffff;
        }

      }
    }
  }
</style>
