<template>
  <div class="new-container">
    <div class="post-wrapper">
      <div class="line">
        <div class="line-title">
          <span class="board-type">{{post.boardType}}</span> / <span class="sub-board-type">{{covertSubBoartType[post.subBoardType]}}</span>
        </div>
      </div>
      <div class="nickname">{{ post.nickname }}</div>
      <div class="title">{{ post.title }}</div>
      <div class="content" v-html="post.content"></div>

      <editor class="comment-editor" :useImage="false" :initFlag="initFlag" @change="onChangeEditor( $event )"/>
      <div class="btn-comment" @click="onSaveComment">답변 쓰기</div>

      <div class="comments-wrapper" v-for="comment in commentList" :key="comment.commentId">
       
       <div class="comment-wrapper">
          <div class="recommend"></div>
          <div class="comment-content">
            <div class="name">{{ comment.nickname }}</div>
            <div class="content" v-html="comment.content"></div>
          </div>
       </div>
      </div>
    </div>
  </div>
</template>

<script>
import req2svr from './req2svr'

import SelectBox from '@/components/selectBox'
import Editor from '@/components/editor'
export default {
  name: 'QnAView',
  components: { SelectBox, Editor },
  props: {
    boardId: {
      default: () => {
        return ''
      }
    },
  },
  data () {
    return {
      post: {},
      commentList: [],
      initFlag: 0
    }
  },
  computed: {
    req2svr: () => req2svr,
    member() {
      return this.$store.getters.getMember
    },
    covertSubBoartType() {
      return {
        'tech': '기술',
        'career': '커리아',
        'etc': '기타'
      }
    }
    
  },
  async created() {
    this.post = await this.req2svr.getPost( this.$route.params.boardId )
    await this.getCommentList()
  },
  methods: {
    onChangeEditor( content ) {
      this.content = content
    },
    async onSaveComment() {
      const memberId = this.member.memberId
      await this.req2svr.saveComment( memberId, this.content.content, this.post.boardId )
      alert( '댓글이 등록되었습니다.' )
      await this.getCommentList()
      this.content = ""
      this.initFlag++
    },
    async getCommentList() {
      this.commentList = await this.req2svr.getCommentList( this.post.boardId ) 
    }
  }
}
</script>

<style lang="scss" scoped>
  .new-container {
    display: flex;
    flex-direction: column;
    justify-content: center;

    .post-wrapper {
      margin-top: 35px;
      display: flex;
      flex-direction: column;
      justify-content: center;
      padding: 0 200px;
    }
    
    .line {
      display: flex;
      flex-basis: 100%;
      align-items: center;
      justify-content: space-between;

      .line-title {
        .board-type {
          color: #9CA3AF;
        }

        .sub-board-type {
          color: #8790F9;
        }
      }
    }

    .line::before{
      content: "";
      flex-grow: 0.1;
      background: rgba(0, 0, 0, 0.35);
      height: 1px;
      font-size: 0px;
      line-height: 0px;
      margin: 0px 16px;
    }

    .line::after {
      content: "";
      flex-grow: 1;
      background: rgba(0, 0, 0, 0.35);
      height: 1px;
      font-size: 0px;
      line-height: 0px;
      margin: 0px 16px;
    }

    .nickname {
      padding-top: 30px;
    }

    .title {
      margin-top: 50px;
      font-size: 35px;
      font-weight: bold;
    }

    .content {
      margin-top: 30px;
      font-size: 18px;
      color: #717883;
    }

    .comment-editor {
      margin-top: 150px;
      max-height: 150px;
    }

    .btn-comment {
      display: flex;
      justify-content: center;
      align-items: center;
      align-self: flex-end;
      margin-top: 100px;
      width: 100px;
      height: 40px;
      border-radius: 6px;
      font-size: 14px;
      font-weight: bold;
      background-color: #0090F9;
      color: #ffffff;
      cursor: pointer;
    }

    .comments-wrapper {
      display: flex;
      flex-direction: column;

      .comment-wrapper {
        display: flex;
        padding: 50px 0;
        border-bottom: 1px dashed #D3D5D9;

        .recommend {
          flex: 0 0 50px;
        }

        .comment-content {
          flex: 1 1 auto;

          display: grid;
          grid-template-rows: minmax(0, 45px) 1fr;

          .name {

          }

          .content {

          }
        }
      }
    }
  }
</style>
