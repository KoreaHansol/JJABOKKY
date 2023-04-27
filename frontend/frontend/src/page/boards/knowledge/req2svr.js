import _ from 'lodash'
import axios from 'axios'
import apiUrl from '@/util/apiurl'

let checkResCode = res => {
  if( _.get( res, 'status' ) === 200 ) return _.get( res, 'data' )
}

export default {
  getPostList( boardType, selectBoardType ) {
    return axios.get( apiUrl( `/board/getPostList/${boardType}/${ selectBoardType }` ) ).then( x => checkResCode( x ) )
  },
  getAllPostList( boardType ) {
    return axios.get( apiUrl( `/board/getAllPostList` ), { params: { boardType } } ).then( x => checkResCode( x ) )
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
  saveComment( memberId, content, boardId, parentCommentId = null ) {
    return axios.post( apiUrl( '/comment/saveComment' ), {
      memberId, content, boardId, parentCommentId
    } ).then( x => checkResCode( x ) )
  },
  getCommentList( boardId ) {
    return axios.get( apiUrl( '/comment/getCommentList' ), { params: { boardId } } ).then( x => checkResCode( x ) )
  }
}
