import Vue from 'vue'
import Router from 'vue-router'
import MainPage from '@/page/main'
import JoinPage from '@/page/join'
import LoginPage from '@/page/login'
import QnA from '@/page/boards/qna'
import QnAWrite from '@/page/boards/qna/write'
import QNAView from '@/page/boards/qna/view'

import Knowledge from '@/page/boards/knowledge'
import KnowledgeWrite from '@/page/boards/knowledge/write'
import KnowledgeView from '@/page/boards/knowledge/view'

import Community from '@/page/boards/community'

import Event from '@/page/boards/event'

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
      component: QnA
    },
    {
      path: '/qna/write',
      name: 'QnaWrite',
      component: QnAWrite
    },
    {
      path: '/qna/post/:boardId',
      name: 'QnaView',
      component: QNAView
    },
    {
      path: '/qna',
      name: 'Qna',
      component: QnA
    },
    {
      path: '/knowledge',
      name: 'Knowledge',
      component: Knowledge
    },
    {
      path: '/knowledge/write',
      name: 'KnowledgeWrite',
      component: KnowledgeWrite
    },
    {
      path: '/knowledge/post/:boardId',
      name: 'KnowledgeView',
      component: KnowledgeView
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
