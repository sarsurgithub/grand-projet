<template>
  <div>
    <div class='wrapper'>
      <h2 class='title'> Commentaires </h2>
      <hr>
      <div v-for="comment in comments" :key="comment.id">
        <comment :comment=comment></comment>
      </div>
    </div>
    <div class='commentEditor' v-if='commentIsActive === true'>
      <div class='commentTitle'>
        <quill-editor
            :options="titleOptions"
            v-model="title"/>
      </div>
      <div class='contentEditor'>
        <quill-editor
            :options="editorOptions"
            v-model="description"/>
      </div>
    </div>
    <div class='oops'>
      <button @click="activeSwitch()">
        <img v-if="this.commentIsActive === false" src="@/assets/croix-verte.png"/>
        <img v-if="this.commentIsActive === true" src="@/assets/yellowValidation.png"/>
      </button>
    </div>
  </div>
</template>

<script>

import comment from '@/components/Article/comment.vue'
import axios from 'axios'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
import { quillEditor } from 'vue-quill-editor'

export default {
  components: {
    comment,
    quillEditor
  },
  props: ['comments'],
  data () {
    return {
      id: this.$route.params.id,
      commentIsActive: false,
      description: null,
      title: null,
      editorOptions: {
        modules: {
          toolbar: []
        },
        theme: 'bubble',
        placeholder: 'Insérez du texte ici...'
      },
      titleOptions: {
        modules: {
          toolbar: []
        },
        theme: 'bubble',
        placeholder: 'Insérez votre titre ici...'
      }
    }
  },
  methods: {
    activeSwitch () {
      if (this.commentIsActive === false) {
        this.commentIsActive = true
      } else {
        axios.post(`${process.env.VUE_APP_API}/api/articles/${this.id}/comments`, {
          title: this.title,
          content: this.description,
          author: this.$store.getters.GET_CONNECTED_USER
        },
        {
          headers: {
            Authorization: 'Bearer ' + this.$store.getters.GET_AUTH_TOKEN
          }
        })
        this.commentIsActive = false
      }
    }
  }
}
</script>

<style scoped>
  .wrapper {
  background-color: white;
  color: black;
  border: 5px solid #05FFA1;
  border-radius: 20px;
  margin: 15px 15% 15px 15%;
  padding-bottom: 30px;
}
.title {
  padding: 30px 30px 10px 30px;
}
.commentTitle {
  background-color: transparent;
  font-weight: 800;
}
.commentEditor {
  border-radius: 20px;
  border: 5px solid #FFFE00;
  margin: 15px 15% 15px 15%;
  color: black;
  background-color: white;
}
.contentEditor {
  background-color: transparent;
}
img {
  height: 50px;
  width: 50px;
  cursor: pointer;

}
button {
  height: 50px;
  width: 50px;
  border-radius: 50%;
  padding: 0px;
  border: none;
  background-color: transparent;
  cursor: pointer;
  position: absolute;
  margin-top: 10px;
  align-self: center;
}
</style>
