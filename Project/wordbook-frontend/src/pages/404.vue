<template>
    <div>
        <el-result icon="warning" title="404" sub-title="Not Found">
            <template #extra>
                <el-button type="primary" @click="toIndex">回到首页</el-button>
            </template>
        </el-result>
    </div>
</template>

<script setup>
import router from '@/router/index'

const toIndex=()=>{
    router.push('/')
}
</script>