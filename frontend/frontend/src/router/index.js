import Vue from 'vue'
import Router from 'vue-router'
import MainPage from '@/page/main'
import JoinPage from '@/page/join'
import LoginPage from '@/page/login'
import QNA from '@/page/qna'
import QNANEW from '@/page/qna/new'
import QNAPOST from '@/page/qna/post'
import Knowledge from '@/page/knowledge'
import Community from '@/page/community'
import Event from '@/page/event'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'MainPage',
      component: MainPage
    },
    {
      path: '/join',
      name: 'JoinPage',
      component: JoinPage
    },
    {
      path: '/login',
      name: 'LoginPage',
      component: LoginPage
    },
    {
      path: '/qna',
      name: 'Qna',
      component: QNA
    },
    {
      path: '/qna/new',
      name: 'QnaNew',
      component: QNANEW
    },
    {
      path: '/qna/post/:boardId',
      name: 'QnaPost',
      component: QNAPOST
    },
    {
      path: '/knowledge',
      name: 'Knowledge',
      component: Knowledge
    },
    {
      path: '/community',
      name: 'Community',
      component: Community
    },
    {
      path: '/event',
      name: 'Event',
      component: Event
    }
  ]
})
