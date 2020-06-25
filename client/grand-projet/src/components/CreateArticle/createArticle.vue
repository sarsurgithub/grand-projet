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

    formSubmit () {

    this.categoriesToPost = this.tags.map(tag => ({
      name: tag.text
    }))
    // envoyer un array des catégories à potentiellement ajouter, les doublons sont gérés par le backend
    axios.post('http://localhost:8081/api/categories', {

    })
      .then(function (response) {
        console.log(response)
      })
      .catch(function (error) {
        console.log(error)
      })
    // envoyer un array de noms de catégories pour obtenir leur ids dans le query
    axios
      .get('http://localhost:8081/api/categories/getIds', {
        // comment on fait ??? 
        this.categoriesToPost
      })
      .then(response => {
        this.categories = response.data
        this.categoriesIds = this.categories.map(category => ({
          // comment on fait ???
          category.id
        }))
      })
    // envoyer un article avec son titre, contenu, autheur, et les ids de ses catégories
    axios.post('http://localhost:8081/api/articles', {
      title: this.title,
      content: this.editor.getHTML(),
      author_id: 1,
      categories_ids: this.categoriesIds
    })
      .then(function (response) {
        console.log(response)
      })
      .catch(function (error) {
        console.log(error)
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
