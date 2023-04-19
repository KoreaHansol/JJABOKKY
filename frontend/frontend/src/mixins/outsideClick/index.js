export default {
  mounted() {
    let self = this
    this._onDocumentClick = function( event ) {
      if( !self.$refs.insideRootElement || !self.onOutsideClick ) {
        return
      }
      if( isChildElement( self.$refs.insideRootElement, event.target ) ) {
        return
      }
      self.onOutsideClick( event )
    }

    window.document.addEventListener( 'mousedown', this._onDocumentClick )
  },
  beforeDestroy() {
    window.document.removeEventListener( 'mousedown', this._onDocumentClick )
  }
}

function isChildElement( parent, child ) {
  if( !parent || !child ) {
    return false
  }
  if( parent === child ) {
    return true
  }
  return isChildElement( parent, child.parentElement )
}
