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
    <button
      class='validate_button'
      @click="formSubmit()">
      <img src="@/assets/yellowValidation.png"/>
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
  props: ['article'],
  components: {
    VueTagsInput,
    quillEditor
  },
  data () {
    return {
      tag: '',
      tags: this.article.categories.map(tag => tag.name),
      superTags: [],
      title: this.article.title,
      categoriesToPost: [],
      categories: [],
      categoriesIds: [],
      categoriesNames: [],
      url: '',
      description: this.article.content,
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
      console.log(this.tags)
      console.log('nous entrons dans la fonction formSubmit')
      this.article.categories.forEach(element => {
        this.superTags.text = element.name
      })
      console.log(this.superTags)
      // servira à avoir un array avec seulement les noms des catégories, pour retrouver les ids des catégories
      console.log('étape1 : créer array de noms')
      this.categoriesNames = this.superTags.map(tag => tag.text)
      console.log('categ: ' + this.categoriesNames)

      // servira à avoir un array sous la forme [{ name : design }, { name: sociology}] pour pouvoir ajouter les catégories
      console.log('étape2 : créer array objets')
      this.categoriesToPost = this.tags.map(tag => ({
        name: tag.text
      }))
      console.log('important: ' + this.categoriesToPost)

      // pour chacun des tag entré, ajouter à l'url pour préparer la prochaine requête
      console.log('étape3 : créer url')
      for (let i = 0; i < this.categoriesNames.length; i++) {
        if (i === this.categoriesNames.length - 1) {
          this.url += 'name=' + this.categoriesNames[i]
          console.log('url dans boucle: ' + this.url)
        } else {
          this.url += 'name=' + this.categoriesNames[i] + '&'
          console.log('url pas index 1 :' + this.url)
        }
      }
      console.log('url: ' + this.url)

      // envoyer un array de noms de catégories pour obtenir leur ids dans le query
      console.log('étape5 : récup les ids des catégories')
      if (this.categoriesNames.length !== 0) {
        // envoyer un array des catégories à potentiellement ajouter, les doublons sont gérés par le backend
        console.log('étape4 créer les catégories')
        await axios.post(`${process.env.VUE_APP_API}/api/categories`, this.categoriesToPost, {
          headers: {
            Authorization: 'Bearer ' + this.$store.getters.GET_AUTH_TOKEN
          }
        })
        console.log('dans la boucle')
        const response = await axios
          .get(`${process.env.VUE_APP_API}/api/categories/getIds?${this.url}`)
        this.categories = response.data
        console.log(response)
      }

      // envoyer un article avec son titre, contenu, autheur, et les ids de ses catégories
      console.log('étape6: créer article')
      console.log('categ ids' + this.categories)
      await axios.patch(`${process.env.VUE_APP_API}/api/articles/${this.article.id}`, {
        title: this.title,
        content: this.description,
        categories_ids: this.categories
      },
      {
        headers: {
          Authorization: 'Bearer ' + this.$store.getters.GET_AUTH_TOKEN
        }
      })

      // remettre toutes les valeurs à leur origine, maybe rediriger vers la page de l'article ? ou page de l'auteur ?
      this.url = ''
      this.description = null
      this.categories = []
      this.tags = []
      this.title = null
      this.categoriesToPost = []
      this.categoriesIds = []
      this.categoriesNames = []
      this.$router.push({ name: 'Article', params: { id: this.article.id } })
      console.log('url au début: ' + this.url)
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
  height: 50px;
  width: 50px;
  border-radius: 50%;
  padding: 0px;
  border: none;
  background-color: transparent;
  cursor: pointer;
  position: absolute;
  left: 50%;
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
    padding-bottom: 60px;;
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
