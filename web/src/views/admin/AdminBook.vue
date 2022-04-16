<template>
  <a-layout>
    <a-layout-content
        :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
    >
      <p>
        <a-button type="primary" @click="add()" size="large">
          新增
        </a-button>
      </p>
      <a-table
          :columns="columns"
          :row-key="record => record.id"
          :data-source="books"
          :pagination="pagination"
          :loading="loading"
          @change="handleTableChange"
      >
        <template #cover="{ text: cover }">
          <img v-if="cover" :src="cover" alt="avatar" style="width:70px; height: 70px"/>
        </template>
        <template v-slot:action="{ text, record }">
          <a-space size="small">
            <a-button type="primary" @click="edit(record)">
              编辑
            </a-button>
            <a-popconfirm
                title="删除后不可回复是否删除？"
                ok-text="是"
                cancel-text="否"
                @confirm="handleDelete(record.id)"
            >
              <a-button type="danger">
                删除
              </a-button>
            </a-popconfirm>
          </a-space>
        </template>
      </a-table>
    </a-layout-content>
  </a-layout>
  <a-modal
      title="电子书表单"
      v-model:visible="modalVisible"
      :confirm-loading="modalLoading"
      @ok="handleModalOk"
  >
    <a-form
        :model="book"
        :label-col="{ span: 6 }"
    >
      <a-form-item
          label="封面"
      >
        <a-input v-model:value="book.cover"/>
      </a-form-item>
      <a-form-item
          label="名称"
      >
        <a-input v-model:value="book.name"/>
      </a-form-item>
      <a-form-item
          label="分类一"
      >
        <a-input v-model:value="book.category1Id"/>
      </a-form-item>
      <a-form-item
          label="分类二"
      >
        <a-input v-model:value="book.category2Id"/>
      </a-form-item>
      <a-form-item
          label="描述"
      >
        <a-input v-model:value="book.description" type="text"/>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script lang='ts'>
import {defineComponent, onMounted, ref} from 'vue'
import axios from 'axios'
import {message} from "ant-design-vue";

export default defineComponent({
  name: 'AdminBook',
  setup() {
    const books = ref()
    const pagination = ref({
      current: 1,
      pageSize: 4,
      total: 0
    })
    const loading = ref(false)

    const columns = [
      {
        title: '封面',
        dataIndex: 'cover',
        slots: {customRender: 'cover'}
      },
      {
        title: '名称',
        dataIndex: 'name'
      },
      {
        title: '分类一',
        key: 'category1Id',
        dataIndex: 'category1Id'
      },
      {
        title: '分类二',
        dataIndex: 'category2Id'
      },
      {
        title: '文档数',
        dataIndex: 'docCount'
      },
      {
        title: '阅读数',
        dataIndex: 'viewCount'
      },
      {
        title: '点赞数',
        dataIndex: 'voteCount'
      },
      {
        title: 'Action',
        key: 'action',
        slots: {customRender: 'action'}
      }
    ]

    /**
     * 数据查询
     **/
    const handleQuery = (params: any) => {
      loading.value = true
      // 如果不清空现有数据，则编辑保存重新加载数据后，再点编辑，则列表显示的还是编辑前的数据
      axios.get("/book/list", {
        params: {
          page: params.page,
          size: params.size
        }
      }).then((response) => {
        loading.value = false
        const data = response.data

        if (data.success) {
          books.value = data.content.list

          // 重置分页按钮
          pagination.value.current = params.page
          pagination.value.total = data.content.total
        } else {
          message.error(data.message)
        }
      })
    }

    /**
     * 表格点击页码时触发
     */
    const handleTableChange = (pagination: any) => {
      console.log("看看自带的分页参数都有啥：" + pagination)
      handleQuery({
        page: pagination.current,
        size: pagination.pageSize
      })
    }

    /*---------------表单--------------*/
    const book = ref({})
    const modalVisible = ref<boolean>(false)
    const modalLoading = ref<boolean>(false)

    const handleModalOk = () => {
      modalLoading.value = true

      axios.post("/book/save", book.value).then((response) => {
        const data = response.data

        if (data.success) {
          modalVisible.value = false
          modalLoading.value = false
        }

        // 重新加载列表
        handleQuery({
          page: pagination.value.current,
          size: pagination.value.pageSize
        })
      })
    }

    /**
     * 编辑
     */
    const edit = (record: any) => {
      modalVisible.value = true
      book.value = record
    }

    /**
     * 新增
     */
    const add = () => {
      modalVisible.value = true
      book.value = {}
    }

    /**
     * 删除
     */
    const handleDelete = (id: string) => {
      axios.delete("/book/delete/" + id).then((response) => {
        const data = response.data

        if (data.success) {
          // 重新加载列表
          handleQuery({
            page: pagination.value.current,
            size: pagination.value.pageSize
          })
        }
      })
    }

    onMounted(() => {
      handleQuery({
        page: 1,
        size: pagination.value.pageSize
      })
    })

    return {
      books,
      pagination,
      columns,
      loading,
      handleTableChange,

      edit,
      add,
      handleDelete,

      book,
      modalVisible,
      modalLoading,
      handleModalOk,
    }
  }
})
</script>