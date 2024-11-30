<template>
    <div class="common-layout" id="body">
        <el-container>
            <Menu></Menu>
            <el-container>
                <el-header>
                    <Header></Header>
                </el-header>
                <el-main>
                    <!-- main pages -->
                    <router-view></router-view>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script setup>
import Menu from './layout/menu.vue';
import Header from './layout/header.vue';
</script>

<style scoped>
#body{
    @apply bg-orange-100;
}
</style>