<template>
  <a-layout>
    <a-layout-sider width="200" style="background: #fff">
      <a-menu
          v-model:selectedKeys="selectedKeys2"
          v-model:openKeys="openKeys"
          mode="inline"
          :style="{ height: '100%', borderRight: 0 }"
          @click="handleClick"
      >
        <a-menu-item key="welcome">
          <router-link :to="'/'">
            <MailOutLined/>
            <span>欢迎</span>
          </router-link>
        </a-menu-item>
        <a-menu-sub-menu v-for="item in level1" :key="item.id">
          <template v-slot:title>
            <span>
              <user-outlined/>{{ item.name }}
            </span>
          </template>
          <a-menu-item v-for="child in item.children" :key="child.id">
            <span>
              <MailOutlined/>{{ child.name }}
            </span>
          </a-menu-item>
        </a-menu-sub-menu>
      </a-menu>
    </a-layout-sider>
    <a-layout-content
        :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
    >
      <a-list item-layout="vertical" size="large" :pagination="pagination"
              :grid="{ gutter: 16, xs: 1, sm: 2, md: 4, lg: 4, column: 3}" :data-source="books">
        <template #renderItem="{ item }">
          <a-list-item key="item.name">
            <template #actions>
          <span v-for="{ type, text } in  actions" :key="type">
            <component :is="type" style="margin-right: 8px"/>
            {{ text }}
          </span>
            </template>
            <a-list-item-meta :description="item.description">
              <template #title>
                <a :href="item.href">{{ item.name }}</a>
              </template>
              <template #avatar>
                <a-avatar :src="item.cover"/>
              </template>
            </a-list-item-meta>
            {{ item.content }}
          </a-list-item>
        </template>
      </a-list>
    </a-layout-content>
  </a-layout>
</template>

<script lang="ts">
import {defineComponent, onMounted, ref} from 'vue'
import axios from 'axios'
import {message} from "ant-design-vue";
import {Tool} from "@/util/tool";

export default defineComponent({
      name: 'Home',
      setup() {
        const books = ref()

        const level1 = ref()
        let categories: any
        /**
         * 查询分类
         **/
        const handleQueryCategory = () => {
          // 如果不清空现有数据，则编辑保存重新加载数据后，再点编辑，则列表显示的还是编辑前的数据
          axios.get("/category/all").then((response) => {
            const data = response.data

            if (data.success) {
              categories = data.content
              console.log("原始数组：", categories)

              level1.value = []
              level1.value = Tool.array2Tree(categories, 0);
              console.log("树形结构：", level1.value)
            } else {
              message.error(data.message)
            }
          })
        }

        const handleClick = () => {
          console.log("menu click")
        }

        onMounted(() => {
          handleQueryCategory()
          axios.get('/book/list', {
            params: {
              page: 1,
              size: 1000
            }
          }).then(response => {
            const data = response.data
            books.value = data.content.list
          })
        });

        const pagination = {
          onChange: (page: number) => {
            console.log(page);
          },
          pageSize: 9,
        };
        const actions: Record<string, string>[] = [
          {type: 'StarOutlined', text: '156'},
          {type: 'LikeOutlined', text: '156'},
          {type: 'MessageOutlined', text: '2'},
        ];

        return {
          books,
          pagination,
          actions,

          handleClick,
          level1
        };
      },

      components: {},
    }
)
</script>

<style scoped>
.ant-avatar {
  width: 50px;
  height: 50px;
  line-height: 50px;
  border-radius: 8%;
  max-resolution: 5px 0;
}
</style>
