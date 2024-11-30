<template>
    <div id="body">
        <div id="info" style="text-align: center; font-size: 25px;">
            <span>Ник： {{ nickName }}</span>
            <br>
            <span>Аккаунт： {{ account }}</span>
            <br>
            <span>
                <el-button type="primary" plain @click="openChange">Изменение личной информации</el-button>
            </span>
        </div>
    </div>
    <!-- 修改个人信息 对话框 -->
    <el-dialog v-model="isShowDialog" title="Изменение личной информации" width="40%" align-center>
        <el-form :model="changeFormData" style="width: 80%;margin-left: 10%">
            <el-form-item label="Ник" prop="nickName">
                <el-input v-model="changeFormData.nickName"></el-input>
            </el-form-item>
            <el-form-item label="Пароль" prop="password">
                <el-input v-model="changeFormData.newPassword" show-password></el-input>
            </el-form-item>
        </el-form>
        <template #footer>
            <div class="dialog-footer">
                <el-button @click="closeChange">Отменять</el-button>
                <el-button type="success" @click="saveChange">Сохранять</el-button>
            </div>
        </template>
    </el-dialog>
</template>

<script setup>
import { useUserStore } from '@/store/userStore';
import { storeToRefs } from 'pinia';
import { ref } from 'vue';
import { updateUserById } from '@/http/api/userAPI';
import { ElMessage } from 'element-plus'

// pinia 存储
const userStore = useUserStore()
// 解构为响应式数据
const { userId, account, password, nickName } = storeToRefs(userStore)

const isShowDialog = ref(false)

const changeFormData = ref({
    nickName: nickName.value,
    newPassword: password.value
})
function resetChangeFormData(){
    changeFormData.value.nickName = nickName.value,
    changeFormData.value.newPassword = password.value
}

// 修改信息
function changeUserInfo(){
    let sendData = {
        userId: userId.value,
        account: account.value,
        password: password.value,
        newPassword: changeFormData.value.newPassword,
        nickName: changeFormData.value.nickName
    }
    // 请求后端
    updateUserById(sendData)
    .then((response) => {
        if(response.data.code == 200){
            ElMessage({
                message: 'Вы успешно изменили свои данные, пожалуйста, войдите снова',
                type: 'success',
            })
            isShowDialog.value = false
            userStore.$reset()
            window.location.reload()
        }
        else {
            ElMessage({
                message: 'Неудачно',
                type: 'error',
            })
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

const openChange = () => {
    resetChangeFormData()
    isShowDialog.value = true
}

const saveChange = () => {
    changeUserInfo()
}

const closeChange = () => {
    isShowDialog.value = false
    resetChangeFormData()
}

</script>

<style scoped>
#body{
    /* border: 2px solid black; */
    height: 100%;
    width: 100%;
}

</style>