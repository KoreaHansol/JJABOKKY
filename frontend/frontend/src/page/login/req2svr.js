import _ from 'lodash'
import axios from 'axios'
import apiUrl from '@/util/apiurl'

let checkResCode = res => {
  if( _.get( res, 'status' ) === 200 ) return _.get( res, 'data' )
}

export default {
  login( {id, password} ) {
    return axios.get( apiUrl( '/member/login' ), {
      params: {
        id, password
      } } ).then( x => checkResCode( x ) )
  }
}
