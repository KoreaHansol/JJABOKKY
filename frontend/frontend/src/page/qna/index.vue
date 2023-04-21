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

        <div class="contents">
          <div class="content" v-for="post in processedPostList" :key="post.boardId">
            <div class="reply-area">
              <div class="reply">답변</div>
              <div class="reply-count">0</div>
            </div>
            <div class="info">
              <div class="member-info">
                {{ post.nickname }}
              </div>
              <div class="post-info">
                <div class="title" @click="onEnterPost( post.boardId )">{{ post.title }}</div>
                <div class="post-preview">{{ post.content }}</div>
              </div>
              <div class="tag-area">조회수: 4 추천수: 3</div>
              
            </div>
          </div>
          
        </div>
      </div>
  </div>
</template>

<script>
import SelectBox from '@/components/selectBox'
import req2svr from './req2svr'
import htmlToFormattedText from 'html-to-formatted-text'

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
      selectSortItem: { id: 1, text: '최신순' },
      postList: []
    }
  },
  computed: {
    req2svr: () => req2svr,
    processedPostList() {
      return _.map( this.postList, post => {
        let content = post.content
        content = htmlToFormattedText(content)
        return {
          ...post,
          content
        }
      } )
    }
  },
  async created() {
    const path = window.location.pathname
    try {
      this.postList = await this.req2svr.getPostList( path )
    } catch( err ) {
      alert( '게시물을 가져오는데 오류가 생겼습니다.' )
      this.postList = []
    }
  },
  methods: {
    onClickQuestion() {
      this.$router.push( '/qna/new' )
    },
    onEnterPost( boardId ) {
      this.$router.push( { name: 'QnaPost', params: { boardId } } )
    },
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
      display: flex;
      flex-direction: column;
      justify-content: center;
      padding: 0 120px;

      .img-okky {
        width: 906px;
        height:83px;
        align-self: center;
      }

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

      .contents {
        display: grid;
        grid-auto-rows: minmax(0, 160px);
        margin-top: 20px;

        .content {
          display: grid;
          grid-template-columns: minmax(0, 75px) 1fr;
          gap: 20px;
          overflow: hidden;

          .reply-area {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            border-radius: 6px;
            height: 70px;
            border: 1px solid #D1D5DB;

            .reply {
              font-size: 12px;
            }

            .reply-count {
              margin-top: 5px;
              color: #D1D5DB;

            }
          }

          .info {
            display: grid;
            grid-template-rows: minmax(0, 40px) minmax(0, 74px) minmax(0, 46px);

            .member-info {

            }

            .post-info {
              overflow: hidden;

              .title {
                font-weight: bold;
                font-size: 21px;
                cursor: pointer;
              }

              .post-preview {
                margin-top: 20px;
                text-overflow: ellipsis;
                display: -webkit-box;
                -webkit-line-clamp: 2;
                -webkit-box-orient: vertical;
              }
            }

            .tag-area {
              display: flex;
              justify-content: flex-end;
              align-items: flex-end;
            }
          }
        }
      }
    }
  }
</style>
