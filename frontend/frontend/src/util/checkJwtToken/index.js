import { store } from '../../store/store'
import jwt_decode from 'jwt-decode'
import req2svr from './req2svr'

export async function checkExpireJwtToken( decodeToken ) {
  if( decodeToken ) {
    const token = sessionStorage.getItem( 'token' )
    store.commit( 'setMember', decodeToken.member )
    store.commit( 'setToken', token )
  }
  let now = new Date().getTime()
  let exp = parseInt( decodeToken.exp ) * 1000 // 자바에선 밀리세컨단위로 온다
  if( now < exp ) {
    console.log( '세션만료기간이 아직 지나지 않음', new Date(exp) )
    store.commit( 'expireToken', false )
    setTimeout( () => { checkJwtToken( decodeToken ) }, 1000 * 60 * 60 )
    return false
  }

  console.log( '세션이 만료됨 refresh token 요청' )
  const refreshToken = await req2svr.refreshToken( store.getters.getMember )
  if( !refreshToken ) {
    store.commit( 'expireToken', true )
  }

  store.commit( 'expireToken', false )
  setTimeout( () => { checkJwtToken( decodeToken ) }, 1000 * 60 * 60 )

  window.sessionStorage.setItem( 'token', refreshToken )

  // 세션만료.. logout todo
  return true
}

export default function checkJwtToken() {
  const token = sessionStorage.getItem( 'token' )
  if( !token ) {
    console.log( '토큰이 없음' )
    store.dispatch( 'setMember', {
      id: null,
      nickname: null,
      email: null
    } )
  }
  let decodeToken = null
  try {
    decodeToken = jwt_decode( token )
  } catch( err ) { } // millsecond
  store.commit( 'reqeustLogin' )
  store.commit( 'setDecodeToken', decodeToken )
  checkExpireJwtToken( decodeToken )
}
