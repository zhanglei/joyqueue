<template>
  <div>
    <div class="ml20 mt10">
      <d-input v-model="searchData.keyword" placeholder="请输入要查询的IP/ID/标签" class="left"
               style="width: 300px" @on-enter="getList">
        <span slot="prepend">关键词</span>
        <icon name="search" size="14" color="#CACACA" slot="suffix" @click="getList"></icon>
      </d-input>
    </div>
    <my-table :optional="true" :data="tableData" :showPin="showTablePin" :page="page" @on-size-change="handleSizeChange"
              @on-current-change="handleCurrentChange" @on-selection-change="handleSelectionChange">
    </my-table>
  </div>
</template>

<script>
import myTable from '../../components/common/myTable.vue'
import crud from '../../mixins/crud.js'
import apiRequest from '../../utils/apiRequest.js'

export default {
  name: 'add-broker',
  components: {
    myTable
  },
  props: {
    // data: {
    //   type: Object,
    //   default () {
    //     return {}
    //   }
    // },
    urls: {
      type: Object
    },
    colData: {
      type: Array
    },
    btns: {
      type: Array
    }
  },
  mixins: [ crud ],
  data () {
    return {
      searchData: {
        keyword: '',
        brokerGroupId: -1
      },
      tableData: {
        rowData: [{}],
        colData: this.colData,
        btns: this.btns
      },
      multipleSelection: [],
      selectedBrokers:[]
    }
  },
  methods: {
    handleSelectionChange (val) {
      this.multipleSelection = val
      this.$emit('on-choosed-broker', val)
    },
    getListByGroup (brokerGroupId) {
      this.getListByGroupAndbrokers(brokerGroupId, undefined)
    },
    onBrokerLoadComplete(val){
      this.$emit('on-broker-load-complete',val,tag=>{
        for (let index=0; index<tag.length; index++) {
          let row = {};
          Object.assign(row, tag[index])
          this.$set(this.tableData.rowData, index, row)
        }
      })
    },
    getListByGroupAndbrokers (brokerGroupId, brokers) {
      let query = {}
      if (!brokerGroupId) {
        query = {
          keyword: this.searchData.keyword
        }
      } else {
        this.searchData.brokerGroupId = brokerGroupId
        this.selectedBrokers = brokers
        query = {
          keyword: this.searchData.keyword,
          brokerGroupId: this.searchData.brokerGroupId
        }
      }

      let data = {
        pagination: {
          page: this.page.page,
          size: this.page.size
        },
        query: query
      }
      apiRequest.post(this.urls.search, {}, data).then((data) => {
        if (data === '') {
          return
        }
        data.data = data.data || []
        data.pagination = data.pagination || {
          totalRecord: data.data.length
        }
        this.page.total = data.pagination.totalRecord
        this.page.page = data.pagination.page
        this.page.size = data.pagination.size
        this.tableData.rowData = data.data
        this.tableData.rowData.forEach(element => {
          element['isChecked'] = false
          if (brokers && brokers.length > 0) {
            for (let broker of brokers) {
              if (broker.id === element.id) {
                element['isChecked'] = true
                break
              }
            }
          }
        })
        this.onBrokerLoadComplete(this.tableData.rowData)
      })
    },
    getList(){
      this.getListByGroupAndbrokers(this.searchData.brokerGroupId,this.selectedBrokers)
    }
  },
  mounted () {
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .label{text-align: right; line-height: 32px;}
  .val{}
</style>
