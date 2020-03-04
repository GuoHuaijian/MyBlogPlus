<template>
  <article-update :is-edit="true" />
</template>

<script>
import ArticleUpdate from './components/ArticleUpdate'

export default {
  name: 'EditForm',
  components: { ArticleUpdate }
}
</script>

