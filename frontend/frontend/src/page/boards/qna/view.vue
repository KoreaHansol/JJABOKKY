<template>
  <div class="new-container">
    <div class="post-wrapper">
      <div class="line">
        <div class="line-title">
          <span class="board-type">{{post.boardType}}</span> / <span class="sub-board-type">{{covertSubBoartType[post.subBoardType]}}</span>
        </div>
      </div>
      <div class="member-area">
        <img class="img-stuats" @click="openStatus = !openStatus" src="/static/image/status.JPG" >
        <div class="member">
          <div class="nickname">{{ post.nickname }}</div>
          <div class="diff">{{ postFromNow }}</div>
        </div>
        
      </div>
      
      <div class="title">{{ post.title }}</div>
      <div class="content" v-html="post.content"></div>

      <editor class="comment-editor" :useImage="false" :initFlag="initFlag" @change="onChangeEditor( $event )"/>
      <div class="btn-comment" @click="onSaveComment">답변 쓰기</div>

      <div class="comments-wrapper" v-for="comment in processedCommentList" :key="comment.commentId">
       <div class="comment-wrapper">
          <div class="recommend"></div>
          <div class="comment-content">
            <div class="member-area-child">
              <img class="img-stuats" @click="openStatus = !openStatus" src="/static/image/status.JPG" >
              <div class="member">
                <div class="nickname">{{ comment.nickname }}</div>
                <div class="diff">{{ comment.diff }}</div>
              </div>
            </div>
            <div class="content" v-html="comment.content"></div>


            <!-- 자식 comment -->
            <div class="child-comment-count" v-show="childCommentCountMap[comment.commentId]">댓글 {{ childCommentCountMap[comment.commentId] }}개</div>
            <div class="child-comment-list" v-for="childComment in processedChildCommentList" :key="childComment.commentId">
              <div class="child-comment" v-if="childComment.parentCommentId === comment.commentId">
                <div class="member">
                <img class="img-stuats" @click="openStatus = !openStatus" src="/static/image/status.JPG" >
                <div class="nickname">{{ childComment.nickname }}</div>
                <div class="diff">{{ childComment.diff }}</div>
                </div>
                <div class="child-comment-content" v-html="childComment.content"></div>
              </div>
            </div>
            <div class="btn-comment-child" @click="onClickChildCommetWrite( comment.commentId )" v-if="!childCommentWritingMap[comment.commentId]">댓글 쓰기</div>
            <div class="btn-comment-child" @click="onClickChildCommetWrite( comment.commentId )" v-if="childCommentWritingMap[comment.commentId]">댓글 닫기</div>

            <editor class="comment-editor child" :useImage="false" :initFlag="initFlag" @change="onChangeChildCommentEditor( $event, comment.commentId )" v-if="childCommentWritingMap[comment.commentId]"/>
            <div class="btn-comment child" @click="onSaveChildComment(comment.commentId)" v-if="childCommentWritingMap[comment.commentId]">답변 쓰기</div>
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
import moment from 'moment'


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
      initFlag: 0,
      childCommentWritingMap: {}
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
    },
    postFromNow() {
      return moment( this.post.createDate ).locale( 'ko' ).fromNow()
    },
    processedChildCommentList() {
      return _.filter( this.processedCommentList, 'parentCommentId' )
    },
    processedCommentList() {
      return _.map( this.commentList, comment => {
        const diff = moment(comment.createDate).locale( 'ko' ).fromNow()
        return {
          ...comment,
          diff
        }
      } )
    },
    childCommentCountMap() {
      return _( this.processedChildCommentList )
      .groupBy( 'parentCommentId' )
      .mapValues( list => {
        return _.get( list, 'length' )
      } )
      .value()
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
    onChangeChildCommentEditor( content, commentId ) {
      this.childCommentWritingMap = {
        ...this.childCommentWritingMap,
        [commentId]: content.content
      }
    },
    async onSaveComment() {
      const memberId = this.member.memberId
      await this.req2svr.saveComment( memberId, this.content.content, this.post.boardId )
      alert( '댓글이 등록되었습니다.' )
      await this.getCommentList()
      this.content = ""
      this.initFlag++
    },
    async onSaveChildComment( parentCommentId ) {
      const memberId = this.member.memberId
      await this.req2svr.saveComment( memberId, this.childCommentWritingMap[parentCommentId], this.post.boardId, parentCommentId )
      alert( '댓글이 등록되었습니다.' )
      await this.getCommentList()
      this.initFlag++
    },
    async getCommentList() {
      this.commentList = await this.req2svr.getCommentList( this.post.boardId ) 
    },
    onClickChildCommetWrite( commentId ) {
      if( this.childCommentWritingMap[commentId] ) {
        this.childCommentWritingMap = {
          ...this.childCommentWritingMap,
          [commentId]: false
        }
        return
      }

      this.childCommentWritingMap = {
        ...this.childCommentWritingMap,
        [commentId]: true
      }
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

    .member-area {
      display: flex;
      align-items: center;
      padding-top: 30px;
      
      .img-stuats {
        width: 35px;
        height: 40px;
      }

      .member {
        margin-left: 10px;

        .nickname {
          font-size: 22px;
          font-weight: bold;
        }

        .diff {
          margin-top: 5px;
          font-size: 13px;
        }
      }
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

      &.child {
        margin-top: 50px;
      }
    }

    .btn-comment {
      display: flex;
      justify-content: center;
      align-items: center;
      align-self: flex-end;
      margin-top: 80px;
      width: 100px;
      height: 40px;
      border-radius: 6px;
      font-size: 14px;
      font-weight: bold;
      background-color: #0090F9;
      color: #ffffff;
      margin-bottom: 30px;
      cursor: pointer;

      &.child {
        margin-top: 70px;
        align-self: flex-end;
      }
    }

    .comments-wrapper {
      display: flex;
      flex-direction: column;

      .comment-wrapper {
        display: flex;
        padding: 30px 0;
        border-top: 1px dashed #D3D5D9;

        .recommend {
          flex: 0 0 50px;
        }

        .comment-content {
          flex: 1 1 auto;

          display: flex;
          flex-direction: column;

          .member-area-child {
            display: flex;
            align-items: center;
            
            .img-stuats {
              width: 35px;
              height: 40px;
            }

            .member {
              margin-left: 10px;

              .nickname {
                font-size: 20px;
                font-weight: bold;
              }

              .diff {
                margin-top: 5px;
                font-size: 13px;
              }
            }
          }

          .content {
            margin-top: 10px;
            margin-bottom: 50px;
            color: #555E6B;
            font-size: 16px;
          }

          .btn-comment-child {
            padding: 0 20px;
            margin-top: 10px;
            font-size: 14px;
            color: #9CA5B1;
          }
        }
      }
    }

    .child-comment-count {
      padding: 0 20px;
      font-size: 14px;
    }

    .child-comment-list {
      padding: 0 20px;

      .child-comment {
        margin: 20px 0;
        border-top: 1px dashed #D3D5D9;
        display: flex;
        flex-direction: column;
        justify-content: center;

        .member {
          display: flex;
          align-items: center;
          margin: 10px 0;

          .img-stuats {
            width: 25px;
            height: 30px;
          }

          .nickname {
            margin-left: 10px;
            font-size: 16px;
            font-weight: bold;
          }

          .diff {
            margin-left: 5px;
            font-size: 12px;
          }
        }

        .child-comment-content {

        }
      }
    }
  }
</style>
