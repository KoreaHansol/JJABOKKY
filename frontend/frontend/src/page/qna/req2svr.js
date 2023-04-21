import _ from 'lodash'
import axios from 'axios'
import apiUrl from '@/util/apiurl'

let checkResCode = res => {
  if( _.get( res, 'status' ) === 200 ) return _.get( res, 'data' )
}

export default {
  getPostList( path ) {
    return axios.get( apiUrl( `/board/getPostList${ path }` ) ).then( x => checkResCode( x ) )
  },
  savePost( memberId, title, content, boardType, subBoardType ) {
    return axios.post( apiUrl( '/board/save' ), {
      memberId, title, content, boardType, subBoardType
    } ).then( x => checkResCode( x ) )
  },
  saveImageUrls( imageUrls ) {
    return axios.post( apiUrl( '/board/save/images' ), 
      imageUrls
    ).then( x => checkResCode( x ) )
  },
  getPost( boardId ) {
    return axios.get( apiUrl( `/board/getPost/${ boardId }` ) ).then( x => checkResCode( x ) )
  },
}
