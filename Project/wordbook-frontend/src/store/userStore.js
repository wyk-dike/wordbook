import { defineStore } from 'pinia'

// 用户数据存储
export const useUserStore = defineStore('userStoreId', {
    state: () => {
        return {
            // 这里是数据
            userId: -1,
            account: '',
            password: '',
            nickName: ''
        }
    },
    getters: {},
    actions: {},
    // 开启持久化存储（默认 session storage）
    persist: {
        enabled: true,
        // 改为 local storage
        strategies: [{
            key: 'userLocalStore',
            storage: localStorage
        }]
    }
})