import _ from 'lodash'
import axios from 'axios'
import apiUrl from '@/util/apiurl'

let checkResCode = res => {
  if( _.get( res, 'status' ) === 200 ) return _.get( res, 'data' )
}

export default {
  refreshToken( { id } ) {
    return axios.get( apiUrl( '/token/refresh' ), {
      params: {
        id
      } } ).then( x => checkResCode( x ) )
  }
}
