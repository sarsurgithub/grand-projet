<template>
  <div class="editor">
    <editor-menu-bar :editor="editor" v-slot="{ commands, isActive }">
      <div class="menubar">

        <button
          class="menubar__button"
          :class="{ 'is-active': isActive.bold() }"
          @click="commands.bold"
        >
          <icon name="bold" />
        </button>

        <button
          class="menubar__button"
          :class="{ 'is-active': isActive.italic() }"
          @click="commands.italic"
        >
          <icon name="italic" />
        </button>

        <button
          class="menubar__button"
          :class="{ 'is-active': isActive.strike() }"
          @click="commands.strike"
        >
          <icon name="strike" />
        </button>

        <button
          class="menubar__button"
          :class="{ 'is-active': isActive.underline() }"
          @click="commands.underline"
        >
          <icon name="underline" />
        </button>

        <button
          class="menubar__button"
          :class="{ 'is-active': isActive.code() }"
          @click="commands.code"
        >
          <icon name="code" />
        </button>

        <button
          class="menubar__button"
          :class="{ 'is-active': isActive.paragraph() }"
          @click="commands.paragraph"
        >
          <icon name="paragraph" />
        </button>

        <button
          class="menubar__button"
          :class="{ 'is-active': isActive.heading({ level: 1 }) }"
          @click="commands.heading({ level: 1 })"
        >
          H1
        </button>

        <button
          class="menubar__button"
          :class="{ 'is-active': isActive.heading({ level: 2 }) }"
          @click="commands.heading({ level: 2 })"
        >
          H2
        </button>

        <button
          class="menubar__button"
          :class="{ 'is-active': isActive.heading({ level: 3 }) }"
          @click="commands.heading({ level: 3 })"
        >
          H3
        </button>

        <button
          class="menubar__button"
          :class="{ 'is-active': isActive.bullet_list() }"
          @click="commands.bullet_list"
        >
          <icon name="ul" />
        </button>

        <button
          class="menubar__button"
          :class="{ 'is-active': isActive.ordered_list() }"
          @click="commands.ordered_list"
        >
          <icon name="ol" />
        </button>

        <button
          class="menubar__button"
          :class="{ 'is-active': isActive.blockquote() }"
          @click="commands.blockquote"
        >
          <icon name="quote" />
        </button>

        <button
          class="menubar__button"
          :class="{ 'is-active': isActive.code_block() }"
          @click="commands.code_block"
        >
          <icon name="code" />
        </button>

        <button
          class="menubar__button"
          @click="commands.horizontal_rule"
        >
          <icon name="hr" />
        </button>

        <button
          class="menubar__button"
          @click="commands.undo"
        >
          <icon name="undo" />
        </button>

        <button
          class="menubar__button"
          @click="commands.redo"
        >
          <icon name="redo" />
        </button>

      </div>
    </editor-menu-bar>
    <div class="wrapper-editor">
      <input v-model="title"/>
      <editor-content class="editor__content" :editor="editor" />
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
      I'm done </button>
  </div>
</template>

<script>
import VueTagsInput from '@johmun/vue-tags-input'
import axios from 'axios'
import { Editor, EditorContent, EditorMenuBar } from 'tiptap'
import {
  Blockquote,
  CodeBlock,
  HardBreak,
  Heading,
  HorizontalRule,
  OrderedList,
  BulletList,
  ListItem,
  TodoItem,
  TodoList,
  Bold,
  Code,
  Italic,
  Link,
  Strike,
  Underline,
  History
} from 'tiptap-extensions'
export default {
  components: {
    EditorContent,
    EditorMenuBar,
    VueTagsInput
  },
  data () {
    return {
      tag: '',
      tags: [],
      tagsChecking: [],
      title: 'Mon super Article',
      categoriesToPost: [],
      categories: [],
      categoriesIds: [],
      categoriesNames: [],
      url: '',
      editor: new Editor({
        extensions: [
          new Blockquote(),
          new BulletList(),
          new CodeBlock(),
          new HardBreak(),
          new Heading({ levels: [1, 2, 3] }),
          new HorizontalRule(),
          new ListItem(),
          new OrderedList(),
          new TodoItem(),
          new TodoList(),
          new Link(),
          new Bold(),
          new Code(),
          new Italic(),
          new Strike(),
          new Underline(),
          new History()
        ],
        content: `
          <h2>
            Write your article here,
          </h2>
          <blockquote>
            It's amazing 
            <br />
            – mom
          </blockquote>
        `,
        onUpdate: ({ getHTML }) => {
          this.html = getHTML()
        },
        html: 'Update content to see changes'
      })
    }
  },
  beforeDestroy () {
    this.editor.destroy()
  },
  methods: {

    afficherHtml: function () {
      console.log(this.editor.getHTML())
    },

    async formSubmit () {
      console.log('nous entrons dans la fonction formSubmit')

      // servira à avoir un array avec seulement les noms des catégories, pour retrouver les ids des catégories
      console.log('étape1 : créer array de noms')
      this.categoriesNames = this.tags.map(tag => tag.text)
      console.log(this.categoriesNames)

      // servira à avoir un array sous la forme [{ name : design }, { name: sociology}] pour pouvoir ajouter les catégories
      console.log('étape2 : créer array objets')
      this.categoriesToPost = this.tags.map(tag => ({
        name: tag.text
      }))
      console.log(this.categoriesToPost)

      // pour chacun des tag entré, ajouter à l'url pour préparer la prochaine requête
      console.log('étape3 : créer url')
      for (let i = 0; i < this.categoriesNames.length; i++) {
        if (i === this.categoriesNames.length - 1) {
          this.url += 'name=' + this.categoriesNames[i]
        } else {
          this.url += 'name=' + this.categoriesNames[i] + '&'
        }
      }
      console.log('url: ' + this.url)

      // envoyer un array des catégories à potentiellement ajouter, les doublons sont gérés par le backend
      console.log('étape4 créer les catégories')
      await axios.post('http://localhost:8081/api/categories', this.categoriesToPost)

      // envoyer un array de noms de catégories pour obtenir leur ids dans le query
      console.log('étape5 : récup les ids des catégories')
      const response = await axios
        .get(`http://localhost:8081/api/categories/getIds?${this.url}`)
      this.categories = response.data
      // .then(response => {
      //   console.log('entrée dans la requête des ids')
      //   this.categories = response.data
      //   console.log(this.categories)
      // })
      console.log(response)

      // envoyer un article avec son titre, contenu, autheur, et les ids de ses catégories
      console.log('étape6: créer article')
      await axios.post('http://localhost:8081/api/articles', {
        title: this.title,
        content: this.editor.getHTML(),
        author_id: 1,
        categories_ids: this.categories
      })
    }
  }
}
</script>

<style scoped>
  .validate_button {
    color: #FFFE00;
    padding: 5px;
    border: 2px solid #FFFE00;
    background-color: black;
    border-radius: 5px;
    font-weight: bold;
    font-size: 16px;
  }
  .validate_button:hover {
    color: black;
    background-color: #FFFE00;
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
  }
  .vue-tags-input {
    max-width: none !important;
    margin-bottom: 20px;
  }
</style>
