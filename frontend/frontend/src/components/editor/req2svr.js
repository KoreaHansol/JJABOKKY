import _ from 'lodash'
import axios from 'axios'
import apiUrl from '@/util/apiurl'

let checkResCode = res => {
  if( _.get( res, 'status' ) === 200 ) return _.get( res, 'data' )
}

export default {
  uplodaFile( formData ) {
    return axios.post( apiUrl( '/file/upload' ), 
      formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      }
    } ).then( x => checkResCode( x ) )
  }
}
