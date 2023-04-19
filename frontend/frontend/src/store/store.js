import Vue from 'vue'
import Vuex from 'vuex'
import jwt_decode from 'jwt-decode'
import { checkExpireTokenDetail } from '@/util/checkExpireToken'
import checkExpireToken from '@/util/checkExpireToken'

Vue.use(Vuex)

export const store = new Vuex.Store( {
  state: {
    isLogin: false,
    token: null,
    decodeToken: null,
    isExpireToken: true,
    member: {
      id: null,
      nickname: null,
      email: null
    }
  },
  getters: {
    isLogin( state ) {
      return state.isLogin
    },
    isExpireToken() {
      return state.isExpireToken
    },
    getCurrentMember( state ) {
      return state.member
    },
    getToken() {
      return sessionStorage.getItem( 'token' )
    },
    getMember( state ) {
      return state.member
    }
  },
  mutations: {
    reqeustLogin( state ) {
      state.isLogin = true
    },
    reqeustLogout( state ) {
      state.isLogin = false
    },
    setToken( state, token ) {
      state.token = token
    },
    setDecodeToken( state, decodeToken ) {
      state.decodeToken = decodeToken
    },
    setMember( state, member ) {
      if( !member ) {
        let decodeToken = null
        const token = sessionStorage.getItem( 'token' )
        try {
          decodeToken = jwt_decode( token )
          state.member = _.get( decodeToken, 'member' )
          return
        } catch( err ) {  } // millsecond
      }
      state.member = member
    },
    expireToken( state, expired ) {
      state.isExpireToken = expired
    }
  },
  actions: {
    setDecodeToken( context, decodeToken ) {
      console.log( 'decodeToken', decodeToken )
      context.commit( 'setDecodeToken', decodeToken )
    },
    async reqeustLogin( context, token ) {
      let decodeToken = null
      try {
        decodeToken = jwt_decode( token )
      } catch( err ) { } // millsecond
      context.commit( 'reqeustLogin' )
      await sessionStorage.setItem( 'token', token )
      checkExpireToken()
      context.commit( 'setToken', token )
      context.commit( 'setMember' )
      context.commit( 'expireToken', false )
    },
    async reqeustLogout( context ) {
      context.commit( 'reqeustLogout' )
      await sessionStorage.removeItem( 'token' )
      checkExpireToken()
      context.commit( 'setToken', null )
      context.commit( 'setDecodeToken', null )
      context.commit( 'expireToken', true )
    },
    setMember( context ) {
      context.commit( 'setMember' )
    },
    expireToken( context, expired ) {
      context.commit( 'expireToken', expired )
    }
  }
} )