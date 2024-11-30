<template>
    <div id="body">
        <div id="header">
            <el-radio-group v-model="searchType" style="margin-right: 10px;">
                <el-radio-button value="1">Поиск слов</el-radio-button>
                <el-radio-button value="2">Поиск перевода</el-radio-button>
            </el-radio-group>
            <el-input v-model="searchInfo" placeholder="Поиск" style="width: 15%;">
                <template #prefix>
                    <el-icon>
                        <Search />
                    </el-icon>
                </template>
            </el-input>
            <el-button type="success" icon="Search" circle plain style="margin-left: 10px;" @click="search"></el-button>
            <el-button type="primary" icon="Plus" circle plain @click="openAddWord"></el-button>
        </div>
        <div id="main">
            <div id="table">
                <el-table :data="tableData" border>
                    <el-table-column prop="word" label="Слова" />
                    <el-table-column prop="translation" label="Перевод слов" />
                    <el-table-column prop="comment" label="Комментарий" />
                    <el-table-column prop="" label="Управление">
                        <!-- 使用 #default="scope" 把本部分数据传递给 "scope"，即可选中本行 -->
                        <template #default="scope">
                            <!-- <el-button type="primary" icon="Edit" circle plain /> -->
                            <el-button type="danger" icon="Delete" circle plain @click="deleteWord(scope.row)"/>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
        </div>
        <div id="footer">
            <el-pagination :page-size="pageSize" v-model:current-page="currentPage" :pager-count="7"
                layout="prev, pager, next" :total="total" @current-change="currentChange" />
        </div>
    </div>
    <!-- 添加单词对话框 -->
    <el-dialog v-model="isShowAddWord" title="Добавьте новое слово" width="40%" align-center>
        <el-form :model="wordFormData" style="width: 80%;margin-left: 10%">
            <el-form-item label="" prop="word">
                <el-input v-model="wordFormData.word" placeholder="Слова"></el-input>
            </el-form-item>
            <el-form-item label="" prop="translation">
                <el-input v-model="wordFormData.translation" placeholder="Перевод слов"></el-input>
            </el-form-item>
            <el-form-item label="" prop="comment">
                <el-input v-model="wordFormData.comment" placeholder="Комментарий" autosize type="textarea"></el-input>
            </el-form-item>
        </el-form>
        <template #footer>
            <div class="dialog-footer">
                <el-button @click="closeAddWord">Отменять</el-button>
                <el-button type="success" @click="saveAddWord">Сохранять</el-button>
            </div>
        </template>
    </el-dialog>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { useUserStore } from '@/store/userStore';
import { storeToRefs } from 'pinia';
import { getWordsByUserId, addWord, deleteWordById } from '@/http/api/wordsAPI';
import { ElMessage } from 'element-plus'

const tableData = ref([])

const pageSize = ref(10)

const currentPage = ref(1)

const total = ref(0)

const searchInfo = ref('')

const searchType = ref('1')

const isShowAddWord = ref(false)

const wordFormData = ref({
    word: '',
    translation: '',
    comment: ''
})
// 重置 wordFormData
function resetWordFormData(){
    wordFormData.value.word = ''
    wordFormData.value.translation = ''
    wordFormData.value.comment = ''
}

// pinia 存储
const userStore = useUserStore()
// 解构为响应式数据
const { userId, account, password } = storeToRefs(userStore)

// 获取页面单词
function getWords() {
    if (userId.value != -1 && account.value != '' && password.value != '') {
        let sendData = {
            userId: userId.value,
            account: account.value,
            password: password.value,
            currentPage: currentPage.value, // 当前页码
            pageSize: pageSize.value, // 页面大小
            searchType: parseInt(searchType.value), // 搜索类型： 0忽略搜索内容； 1匹配单词； 2匹配翻译
            searchInfo: searchInfo.value // 搜索内容
        }
        // 请求后端
        getWordsByUserId(sendData)
        .then((response) => {
            console.log(response)
            if(response.data.code == 200){
                total.value = response.data.data.total
                tableData.value = response.data.data.list
            }
        })
        .catch((error) => {
            console.log(error)
        })
    }
}
// 默认加载单词内容
getWords()

// 页码变化时执行
const currentChange = () => {
    getWords()
}

// 搜索按钮
const search = () => {
    getWords()
}

// 打开添加单词对话框
const openAddWord = () => {
    resetWordFormData()
    isShowAddWord.value = true
}

// 关闭添加单词对话框
const closeAddWord = () => {
    resetWordFormData()
    isShowAddWord.value = false
}

// 保存单词
const saveAddWord = () => {
    let sendData = {
        userId: userId.value,
        account: account.value,
        password: password.value,
        word: wordFormData.value.word,
        translation: wordFormData.value.translation,
        comment: wordFormData.value.comment
    }
    // 请求后端
    addWord(sendData)
    .then((response) => {
        if(response.data.code == 200){
            ElMessage({
                message: 'Успешно добавлено новое слово',
                type: 'success',
            })
            resetWordFormData()
            getWords()
        }
        else {
            ElMessage({
                message: 'Не удалось добавить слово',
                type: 'error',
            })
        }
    })
    .catch((error) => {
        ElMessage({
            message: 'Не удалось добавить слово',
            type: 'error',
        })
        console.log(error)
    })
}

// 删除单词
const deleteWord = (thisRowData) => {
    let sendData = {
        userId: userId.value,
        account: account.value,
        password: password.value,
        wordId: thisRowData.wordId
    }
    // 请求后端
    deleteWordById(sendData)
    .then((response) => {
        if (response.data.code == 200) {
            ElMessage({
                message: 'Успешно',
                type: 'success',
            })
            getWords()
        }
        else {
            ElMessage({
                message: 'Неудачно',
                type: 'error',
            })
        }
    })
    .catch((error) => {
        ElMessage({
            message: 'Неудачно',
            type: 'error',
        })
        console.log(error)
    })
}


</script>

<style scoped>
#body{
    /* @apply bg-orange-50; */
    /* border: 2px solid black; */
    width: 100%;
    height: 100%;
    /* display: flex; */
    /* 设置为垂直方向 */
    flex-direction: column;
    /* 子元素垂直方向从顶部开始排列 */
    justify-content: flex-start;
    /* 水平居中 */
    justify-content: center;
}

#header{
    /* border: 2px solid black; */
    height: 7%;
    display: flex;
    /* 水平居中 */
    justify-content: center;
    /* 垂直居中 */
    align-items: center;
}

#main{
    /* border: 2px solid black; */
    height: 86%;
    padding: 0.5%;
    display: flex;
    /* 水平居中 */
    justify-content: center;
}

#table{
    /* border: 2px solid black; */
    width: 80%;
}

#footer {
    /* border: 2px solid black; */
    height: 7%;
    display: flex;
    /* 水平居中 */
    justify-content: center;
    /* 垂直居中 */
    align-items: center;
}

</style>