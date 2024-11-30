<template>
    <div id="body">
        <div id="login">
            <div id="title">
                <span class="text-orange-50">Личный вордбук</span>
            </div>
            <div id="form">
                <el-form :rules="formRules" :model="formData" label-width="" style="margin: 5% 13%;">
                    <el-form-item label="" prop="account">
                        <el-input v-model="formData.account" placeholder="Аккаунт">
                            <template #prefix>
                                <el-icon>
                                    <User />
                                </el-icon>
                            </template>
                        </el-input>
                    </el-form-item>
                    <el-form-item label="" prop="password">
                        <el-input v-model="formData.password" placeholder="Пароль" show-password>
                            <template #prefix>
                                <el-icon>
                                    <Lock />
                                </el-icon>
                            </template>
                        </el-input>
                    </el-form-item>
                    <div id="btn">
                        <el-form-item>
                            <el-button type="success" @click="login">Войти</el-button>
                            <el-button link @click="openAdd">Зарегистрироваться...</el-button>
                        </el-form-item>
                    </div>
                </el-form>
            </div>
        </div>
    </div>
    <!-- 用户注册 对话框 -->
    <el-dialog v-model="isShowDialog" title="Регистрация пользователей" width="40%" align-center>
        <el-form :model="addFormData" style="width: 80%;margin-left: 10%">
            <el-form-item label="Ник" prop="nickName">
                <el-input v-model="addFormData.nickName"></el-input>
            </el-form-item>
            <el-form-item label="Аккаунт" prop="account">
                <el-input v-model="addFormData.account"></el-input>
            </el-form-item>
            <el-form-item label="Пароль" prop="password">
                <el-input v-model="addFormData.password" show-password></el-input>
            </el-form-item>
        </el-form>
        <template #footer>
            <div class="dialog-footer">
                <el-button @click="closeAdd">Отменять</el-button>
                <el-button type="success" @click="saveAdd">Продолжить</el-button>
            </div>
        </template>
    </el-dialog>
</template>

<script setup>
import { reactive,ref } from 'vue'
import { ElMessage } from 'element-plus'
import { getUserByAccPwd, addUser } from '@/http/api/userAPI'
import { useRouter } from 'vue-router'
import router from '@/router/index'
import { useUserStore } from '@/store/userStore'
import { storeToRefs } from 'pinia'

// 表单数据
const formData=reactive({
    account: '',
    password: ''
})

const isShowDialog = ref(false)

const addFormData = ref({
    nickName: '',
    account: '',
    password: ''
})
function resetAddFormData(){
    addFormData.value.nickName = ''
    addFormData.value.account = ''
    addFormData.value.password = ''
}

// 表单验证
const formRules={
    account:[
        {
            required: true,
            message: 'Аккаунт не может быть пустыми',
            trigger: 'blur'
        }
    ],
    password: [
        {
            required: true,
            message: 'Пароль не может быть пустыми',
            trigger: 'blur'
        }
    ]
}

// pinia 存储
const userStore = useUserStore()
// 解构为响应式数据
const { userId, account, password, nickName } = storeToRefs(userStore)

// 登录
const login=()=>{
    if(formData.account=='' || formData.password==''){
        // 账号或密码为空
        ElMessage({
            showClose: true,
            message: 'Аккаунт и пароль не могут быть пустыми',
            center: true,
            type: 'warning',
        })
    }
    else {
        // 执行登录
        let sendData={
            account:formData.account,
            password:formData.password
        }
        getUserByAccPwd(sendData)
        .then(response => {
            // 请求成功
            console.log(response)
            if(response.data.code != 200) {
                // 用户不存在或账号密码错误
                ElMessage({
                    message: 'Аккаунт не существует или пароль неверный',
                    type: 'error',
                })
            }
            else {
                // 登录成功
                ElMessage({
                    message: 'Вход в систему успешный',
                    type: 'success',
                })
                // 存入 pinia
                userId.value = response.data.data.userId
                account.value = response.data.data.account
                password.value = response.data.data.password
                nickName.value = response.data.data.nickName
                ////
                if ( userId.value!=-1 && account.value!='' ){
                    router.push('/')
                }
            }
        })
        .catch(error => {
            // 请求失败
            console.log(error)
        })
    }
}

// 打开添加窗口
const openAdd = () => {
    resetAddFormData()
    isShowDialog.value = true
}

// 添加用户
const saveAdd = () => {
    let sendData = {
        account: addFormData.value.account,
        password: addFormData.value.password,
        nickName: addFormData.value.nickName
    }
    // 请求后端
    addUser(sendData)
    .then((response) => {
        console.log(sendData)
        console.log(response)
        if (response.data.code == 200) {
            ElMessage({
                message: 'Вы успешно зарегистрировались, пожалуйста, войдите в систему',
                type: 'success',
            })
            isShowDialog.value = false
        }
        else {
            if (response.data.data == 'notOnlyAccount') {
                ElMessage({
                    message: 'Этот аккаунт уже был зарегистрирован, пожалуйста, повторите попытку с другим аккаунтом',
                    type: 'wraing',
                })
            }
            else {
                ElMessage({
                    message: 'Неудачно',
                    type: 'error',
                })
            }
        }
    })
    .catch((err) => {
        ElMessage({
            message: 'Неудачно',
            type: 'error',
        })
        console.log(err)
    })
}

// 关闭添加窗口
const closeAdd = () => {
    isShowDialog.value = false
    resetAddFormData()
}

</script>

<style scoped>
#body{
    /* border: 2px solid black; */
    /* color: aqua; */
    display: flex;
    /* 水平居中 */
    justify-content: center;
    /* 垂直居中 */
    align-items: center;
    /* 让容器高度占满视口 */
    height: 100vh;
    @apply bg-orange-100;
}

#login{
    /* border: 2px solid black; */
    height: 35%;
    width: 35%;
    @apply bg-orange-400 rounded-xl;
}

#title{
    /* border: 2px solid black; */
    padding-top: 3%;
    text-align: center;
    font-size: 30px;
    font-weight: 800;
}

#form{
    /* border: 2px solid black; */
    padding-top: 0.5%;
    padding-bottom: 0.1%;
    margin: 3% 20% 0% 20%;
    @apply bg-orange-300 rounded-xl;
}

#btn{
    /* background-color: #fff; */
    /* border: 2px solid black; */
    height: 32px;
    /* display: flex; */
    /* 水平居中 */
    /* justify-content: center; */
    /* 垂直居中 */
    /* align-items: center; */
}
</style>