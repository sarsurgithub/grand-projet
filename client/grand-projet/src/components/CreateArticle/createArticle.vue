<template>
  <div class="editor">
    <div class="wrapper-editor">
      <div class='title'>
        <quill-editor
            :options="titleOptions"
            v-model="title"/>
      </div>
    </div>
    <div class='quilleditor'>
        <quill-editor
            :options="editorOptions"
            v-model="description"/>
    </div>
    <template>
      <div>
        <vue-tags-input
          v-model="tag"
          :tags="tags"
          @tags-changed="newTags => tags = newTags"
        />
      </div>
    </template>
    <button class='validate_button' @click="formSubmit()">
          <img src='@/assets/yellowValidation.png'>
      </button>
  </div>
</template>

<script>
import VueTagsInput from '@johmun/vue-tags-input'
import axios from 'axios'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
import { quillEditor } from 'vue-quill-editor'

export default {
  components: {
    VueTagsInput,
    quillEditor
  },
  data () {
    return {
      tag: '',
      tags: [],
      title: null,
      categoriesToPost: [],
      categories: [],
      categoriesIds: [],
      categoriesNames: [],
      url: '',
      articleId: null,
      description: null,
      // Options de l'éditeur (quels boutons sont actifs - tu peux trouver la liste ici : https://quilljs.com/docs/modules/toolbar/)
      editorOptions: {
        modules: {
          toolbar: [
            ['bold', 'italic', 'underline', 'strike'],
            [{ list: 'ordered' }, { list: 'bullet' }, { list: 'check' }],
            [{ indent: '-1' }, { indent: '+1' }], // outdent/indent
            [{ color: [] }, { background: [] }], // dropdown with defaults from theme
            [{ align: [] }],
            ['image'],
            [{ script: 'sub' }, { script: 'super' }], // exposant
            ['clean'] // "reset formatting" button
          ]
        },
        theme: 'snow',
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

    async formSubmit () {
      // servira à avoir un array avec seulement les noms des catégories, pour retrouver les ids des catégories
      this.categoriesNames = this.tags.map(tag => tag.text)
      console.log(this.categoriesNames)

      // servira à avoir un array sous la forme [{ name : design }, { name: sociology}] pour pouvoir ajouter les catégories
      this.categoriesToPost = this.tags.map(tag => ({
        name: tag.text
      }))

      // pour chacun des tag entré, ajouter à l'url pour préparer la prochaine requête
      for (let i = 0; i < this.categoriesNames.length; i++) {
        if (i === this.categoriesNames.length - 1) {
          this.url += 'name=' + this.categoriesNames[i]
        } else {
          this.url += 'name=' + this.categoriesNames[i] + '&'
        }
      }

      // envoyer un array de noms de catégories pour obtenir leur ids dans le query
      if (this.categoriesNames.length !== 0) {
        // envoyer un array des catégories à potentiellement ajouter, les doublons sont gérés par le backend
        await axios.post(`${process.env.VUE_APP_API}/api/categories`, this.categoriesToPost, {
          headers: {
            Authorization: 'Bearer ' + this.$store.getters.GET_AUTH_TOKEN
          }
        })
        const response = await axios
          .get(`${process.env.VUE_APP_API}/api/categories/getIds?${this.url}`)
        this.categories = response.data
      }

      // envoyer un article avec son titre, contenu, autheur, et les ids de ses catégories
      await axios.post(`${process.env.VUE_APP_API}/api/articles`, {
        title: this.title,
        content: this.description,
        author_id: this.$store.getters.GET_CONNECTED_USER,
        categories_ids: this.categories,
        views: 0
      },
      {
        headers: {
          Authorization: 'Bearer ' + this.$store.getters.GET_AUTH_TOKEN
        }
      })
        .then((response) => {
          this.articleId = response.headers.location.substring(35)
          console.log(this.articleId)
          console.log(response)
          this.$router.push({ name: 'Article', params: { id: this.articleId } })
        })
    }
  }
}
</script>

<style scoped>
img {
  height: 50px;
  width: 50px;
  cursor: pointer;;
}
button {
    margin-top: 10px;
    height: 50px;
    width: 50px;
    border-radius: 50%;
    padding: 0px;
    border: none;
    background-color: transparent;
    cursor: pointer;
  }
  .wrapper-editor {
    background-color: white;
    color: black;
    border: 5px solid #FFFE00;
    border-radius: 20px;
    margin-bottom: 30px;
  }
  .menubar {
    border: 5px solid #FFFE00;
    border-radius: 20px;
    padding: 30px 0px 30px 0px;
    margin-bottom: 20px;
  }
  .editor {
    margin: 15px 15% 15px 15%;
  }

</style>

<style lang="scss" >
  .ti-input {
    border: 5px solid yellow !important;
    border-radius: 20px !important;
    display: flex;
    padding: 20px 45px !important;
    flex-wrap: wrap;
  }
  .vue-tags-input {
    border-radius: 20px !important;
  }
  .ti-tag {
    color:black !important;
    background-color: yellow !important;
    border-radius: 5px !important;
  }
  .vue-tags-input {
    max-width: none !important;
    margin-bottom: 20px;
  }
  .ql-toolbar {
    border: none;
    margin-bottom: 20px !important;
  }
  .ql-toolbar.ql-snow {
    border: none;
    border-bottom:  5px solid #FFFE00 !important;
  }
  .ql-container.ql-snow {
    border: none;
  }
  .title {
    padding: 10px;
    font-weight: 800;
  }
  .quilleditor {
    background-color: white;
    margin-bottom: 30px;
    color: black;
    border-radius: 20px;
    border: 5px solid #FFFE00 !important;
  }
  .quill-editor {
    border-radius: 20px !important;
  }
</style>
