import _ from 'lodash'
import axios from 'axios'
import apiUrl from '@/util/apiurl'

let checkResCode = res => {
  if( _.get( res, 'status' ) === 200 ) return _.get( res, 'data' )
}

export default {
  joinMember( { id, email, password, name, nickname } ) {
    return axios.post( apiUrl( '/member/join' ), {
      id, email, password, name, nickname ,
    } ).then( x => checkResCode( x ) )
  }
}
