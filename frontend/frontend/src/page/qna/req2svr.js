import _ from 'lodash'
import axios from 'axios'
import apiUrl from '@/util/apiurl'

let checkResCode = res => {
  if( _.get( res, 'status' ) === 200 ) return _.get( res, 'data' )
}

export default {
  savePost( title, content ) {
    return axios.post( apiUrl( '/board/save' ), {
      title, content
    } ).then( x => checkResCode( x ) )
  },
  saveImageUrls( imageUrls ) {
    return axios.post( apiUrl( '/board/save/images' ), 
      imageUrls
    ).then( x => checkResCode( x ) )
  }
}
