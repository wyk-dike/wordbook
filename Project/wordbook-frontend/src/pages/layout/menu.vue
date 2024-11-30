<template>
    <el-aside id="aside">
        <el-menu router>
            <!-- <el-menu-item index="1" class="menu-item">
                <el-icon>
                    <DataLine />
                </el-icon>
                <template #title>单词背诵</template>
            </el-menu-item> -->
            <el-menu-item index="/myWordbook" class="menu-item">
                <el-icon>
                    <Notebook />
                </el-icon>
                <template #title>Мой блокнот</template>
            </el-menu-item>
            <el-menu-item index="/setting" class="menu-item">
                <el-icon>
                    <setting />
                </el-icon>
                <template #title>Настройки</template>
            </el-menu-item>
        </el-menu>
    </el-aside>
</template>

<script setup>

// 打开我的单词本页面
// const openMyWordbook = () => {

// }
</script>

<style scoped>
#aside{
    width: 13%;
    height: 100vh;
    @apply bg-orange-400;
}

.menu-item{
    width: 100.5%;
    @apply bg-orange-400;
}

.menu-item:hover{
    @apply bg-orange-300;
}

.menu-item.is-active{
    @apply text-orange-50;
}
</style>