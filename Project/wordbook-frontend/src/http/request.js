// 在此文件中配置 axios 请求

import axios from 'axios'

const service = axios.create({
    baseURL: '/serverURL', // 后端服务器被 '/serverURL' 代理
    // timeout: timeout
    // headers: headers
})

export default service