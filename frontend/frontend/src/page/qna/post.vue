<template>
  <div class="new-container">
    <div v-html="post.content"></div>
  </div>
</template>

<script>
import req2svr from './req2svr'

import SelectBox from '@/components/selectBox'
import Editor from '@/components/editor'
export default {
  name: 'POST',
  components: { SelectBox, Editor },
  props: {
    boardId: {
      
      default: () => {
        return ''
      }
    },
  },
  data () {
    return {
      post: {}
    }
  },
  computed: {
    req2svr: () => req2svr,
    
  },
  async created() {
    console.log( this.$route.params.boardId ) 
    this.post = await this.req2svr.getPost( this.$route.params.boardId )
  },
  methods: {
    
  }
}
</script>

<style lang="scss" scoped>
  .post-container {
    
  }
</style>
