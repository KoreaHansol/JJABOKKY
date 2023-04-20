<template>
  <div class="example">
    <quill-editor
      class="editor"
      ref="myTextEditor"
      :disabled="false"
      v-model="content"
      @change="onEditorChange"
      :options="editorOption"
      @ready="onEditorReady($event)"
    />
  </div>
</template>

<script>
import req2svr from './req2svr'
import apiUrl from '@/util/apiurl'

import { quillEditor } from "vue-quill-editor";

import "quill/dist/quill.core.css";
import "quill/dist/quill.snow.css";


export default {
  components: {
    quillEditor,
  },
  data() {
    return {
      editorOption: {
        modules: {
          toolbar: {
            container: [
              ["image"],
              [{ header: [1, 2, 3, false] }],
              ["bold", "italic", "underline", "strike", "blockquote"],
            ],
          },
        },
      },
      content: '',
      imageUrls: [],
    };
  },
  methods: {
    onEditorChange() {
      this.$emit( 'change', {
        content: this.content,
        imageUrls: this.imageUrls
      } )
    },
    onEditorReady(editor) {
      this.editorOption = {
        modules: {
          toolbar: {
            container: [
              ["image"],
              [{ header: [1, 2, 3, false] }],
              ["bold", "italic", "underline", "strike", "blockquote"],
            ],
          },
        },
      },
      editor.getModule( 'toolbar' ).addHandler( 'image', this.imageHandler )
    },
    async imageHandler() {
      const input = document.createElement( 'input' )
      input.setAttribute( 'type', 'file' )
      input.setAttribute( 'accept', 'image/*' )
      input.click()

      input.addEventListener("change", async () => {
        const file = input.files[0]

        let formData = new FormData()
        formData.append("file", file)

        try {
          // 파일 업로드 api 호출
          const result = await req2svr.uplodaFile( formData )
          const imgUrl = apiUrl( `/file/getFile/${ result.storeFileName }` )
          this.imageUrls.push( imgUrl )
          const range = this.editor.getSelection()
          this.editor.insertEmbed( range.index, 'image', imgUrl )
        } catch ( err ) {
          console.error( err )
        }
      });
    },
  },
  computed: {
    editor() {
      return this.$refs.myTextEditor.quill
    },
  },
};
</script>

<style lang="scss" scoped>
.example {
  display: flex;
  flex-direction: column;

  .editor {
    height: 40rem;
    overflow: hidden;
  }

  .output {
    width: 100%;
    height: 20rem;
    margin: 0;
    border: 1px solid #ccc;
    overflow-y: auto;
    resize: vertical;

    &.code {
      padding: 1rem;
      height: 16rem;
    }

    &.ql-snow {
      border-top: none;
      height: 24rem;
    }
  }
}
</style>