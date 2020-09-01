<template>
  <div class="chartdiv" ref="chartdiv">
  </div>
</template>

<style lang="scss" scoped>
.chartdiv {
  width: 100%;
height:550px;
max-width:100%;
}
</style>
<script>
import * as am4core from '@amcharts/amcharts4/core'
// import * as am4charts from '@amcharts/amcharts4/charts'
import * as am4pluginsForceDirected from '@amcharts/amcharts4/plugins/forceDirected'
import am4themesAnimated from '@amcharts/amcharts4/themes/animated'
import am4themesDark from '@amcharts/amcharts4/themes/dark'
import axios from 'axios'

am4core.useTheme(am4themesAnimated)
am4core.useTheme(am4themesDark)

export default {
  name: 'HelloWorld',
  mounted () {
    const chart = am4core.create(this.$refs.chartdiv, am4pluginsForceDirected.ForceDirectedTree)
    const networkSeries = chart.series.push(new am4pluginsForceDirected.ForceDirectedSeries())
    const categories = []
    const mesPromesses = []
    let articlesFormated = []

    let articles

    const promise = axios.get('http://localhost:8081/api/articles')
      .then(function (response) {
        articles = response.data
        articles.sort(function (a, b) {
          if (a.id > b.id) return 1
          if (b.id > a.id) return -1
          return 0
        })
        if (articles.length >= 10) {
          articles = articles.slice(1).slice(-10)
        }
      })
    mesPromesses.push(promise)

    Promise.all(mesPromesses).then((values) => {
      articlesFormated = articles.map(article => ({
        id: article.id,
        name: article.title,
        views: article.views,
        color: '#F51FFF',
        config: {
          label: {
            disabled: true
          },
          circle: {
            radius: 20,
            stroke: 'black',
            strokeWidth: 3
          }
        },
        category: 'article'
      }))

      articles.forEach(element => {
        const id = element.id
        element.categories.forEach(category => {
          category.linkWith = []
          category.linkWith.push(id)
          if (categories.findIndex(x => x.id === category.id) === -1) {
            categories.push(category)
          } else {
            categories.find(x => x.id === category.id).linkWith.push(id)
          }
        })
      })

      const categoriesFormated = categories.map(category => ({
        id: category.id,
        name: category.name,
        linkWith: category.linkWith,
        configLink: {
          strokeColor: '#F51FFF'
        },
        color: '#9D20FF',
        textColor: 'white',
        config: {
          label: {
            fill: 'white'
          },
          circle: {
            radius: 40,
            stroke: '#F51FFF',
            strokeWidth: 2
          }
        },
        category: 'category'
      }))
      const data = categoriesFormated.concat(articlesFormated)

      chart.data = data

      networkSeries.tooltip.keepTargetHover = true
      networkSeries.tooltip.label.interactionsEnabled = true
      networkSeries.minRadius = 40
      networkSeries.maxRadius = 60
      networkSeries.fontSize = 10

      const dataFields = networkSeries.dataFields

      dataFields.id = 'id'
      dataFields.linkWith = 'linkWith'
      dataFields.value = 'views'
      dataFields.name = 'name'
      dataFields.children = 'children'
      dataFields.color = 'color'
      dataFields.hidden = 'hidden'
      dataFields.hiddenInLegend = 'hiddenInLegend'
      dataFields.categories = 'category'

      const nodeTemplate = networkSeries.nodes.template

      nodeTemplate.tooltipHTML = '{name}'
      nodeTemplate.fillOpacity = 1
      nodeTemplate.configField = 'config'
      nodeTemplate.label.text = '{name}'

      const linkTemplate = networkSeries.links.template

      linkTemplate.configField = 'configLink'
      linkTemplate.strokeColor = 'white'
      linkTemplate.strokeWidth = 4

      const hoverState = networkSeries.links.template.states.create('hover')

      hoverState.properties.strokeWidth = 3
      hoverState.properties.strokeOpacity = 1

      networkSeries.nodes.template.events.on('over', function (event) {
        event.target.dataItem.childLinks.each(function (link) {
          link.isHover = true
        })
        if (event.target.dataItem.parentLink) {
          event.target.dataItem.parentLink.isHover = true
        }
      })

      networkSeries.nodes.template.events.on('out', function (event) {
        event.target.dataItem.childLinks.each(function (link) {
          link.isHover = false
        })
        if (event.target.dataItem.parentLink) {
          event.target.dataItem.parentLink.isHover = false
        }
      })

      networkSeries.nodes.template.events.on('hit', function (event) {
        if (event.target.dataItem.categories === 'article') {
          window.location = `http://localhost:8080/articles/${event.target.dataItem.id}`
        } else if (event.target.dataItem.categories === 'category') {
          window.location = `http://localhost:8080/category/${event.target.dataItem.id}`
        }
      })
    })
  },
  beforeDestroy () {
    if (this.chart) {
      this.chart.dispose()
    }
  }
}
</script>
