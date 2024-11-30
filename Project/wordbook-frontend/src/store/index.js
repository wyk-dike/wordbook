// 创建和配置 pinia
import { createPinia } from 'pinia'
// pinia 的持久化
import piniaPluginPersist from 'pinia-plugin-persist'

const store = createPinia()
// pinia 的持久化
store.use(piniaPluginPersist)

export default store