import _ from 'lodash'

let baseUrl
if( process.env.NODE_ENV === 'production' ) {
  baseUrl = ''
} else if( process.env.NODE_ENV === 'devdeploy' ) {
  baseUrl = ''
} else {
  let local = {}
  try {
    local = require( '../../local' )
  } catch( err ) {
    local = {}
  }

  baseUrl = _.get( local, 'apiBaseUrl', 'http://localhost:8080' )
}

export default function( url ) {
  url = url || ''
  if( _.startsWith( url, '/' ) ) {
    return baseUrl + url
  } else {
    return baseUrl + '/' + url
  }
}
