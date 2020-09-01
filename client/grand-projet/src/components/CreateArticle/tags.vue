<template>
  <div>
    <vue-tags-input
      v-model="tag"
      :tags="tags"
      @tags-changed="newTags => tags = newTags"
    />
    <button
    @click="createCategories()"
    > add those tags </button>
    {{tags}}
  </div>
</template>

<script>
import axios from 'axios'
import VueTagsInput from '@johmun/vue-tags-input'

export default {
  components: {
    VueTagsInput
  },
  data () {
    return {
      tag: '',
      tags: []
    }
  },
  methods: {
    createCategories: function () {
      for (let i = 0; i < this.tags.length; i++) {
        console.log(this.tags[i].text)
        axios.post(`${process.env.VUE_APP_API}/api/categories`, {
          name: this.tags[i].text
        })
      }
    }
  }
}
</script>

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
