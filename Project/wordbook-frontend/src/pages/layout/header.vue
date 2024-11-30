<template>
    <el-header id="header">
        <div id="title">
            <span>Личный вордбук</span>
        </div>
        <div id="user">
            <el-button type="success" plain v-show="!isShow" @click="toLogin">Войти</el-button>
            <div v-show="isShow" id="showUser">
                <el-icon size="40px" style="float: left; margin-right: 10px;">
                    <UserFilled />
                    <CaretBottom />
                </el-icon>
                <el-button type="danger" plain @click="logout" style="float: right;" size="small">Выйти</el-button>
            </div>
        </div>
    </el-header>
</template>

<script setup>
import router from '@/router/index';
import { ref } from 'vue'
import { useUserStore } from '@/store/userStore'; 
import { storeToRefs } from 'pinia';

const isShow=ref(false);

const userStore = useUserStore()
const { userId, account, nickName } = storeToRefs(userStore)

// 检查用户是否登录
function userIsLogin() {
    if (userId.value != -1 && account.value != '') {
        // 用户已登录
        return true
    }
    else return false
}

isShow.value = userIsLogin()

// 跳转到登录页
const toLogin=()=>{
    router.push("/login")
}

// 退出登录
const logout = () => {
    userStore.$reset()
    isShow.value = userIsLogin()
    // 刷新页面
    window.location.reload()
}
</script>

<style scoped>
#header{
    border-bottom: 1px solid gray;
}

#title{
    height: 100%;
    display: flex;
    align-items: center;
    float: left;
    font-size: 25px;
    @apply text-orange-400;
}

#user{
    /* border: 2px solid black; */
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    float: right;
}

#showUser{
    /* border: 2px solid black; */
    display: flex;
    align-items: center;
    justify-content: center;
}
</style>