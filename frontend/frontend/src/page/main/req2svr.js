import _ from 'lodash'
import axios from 'axios'
import apiUrl from '@/util/apiurl'

let checkResCode = res => {
  if( _.get( res, 'data.code' ) === 200 ) return _.get( res, 'data.payload' )
}

export default {
  getInitData() {
    return axios.get( apiUrl( '/getInitData' ), {
    } ).then( x => checkResCode( x ) )
  }
}
