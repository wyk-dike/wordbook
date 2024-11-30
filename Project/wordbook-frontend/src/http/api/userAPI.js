import request from '../request'

// 通过账号密码获取用户信息
export function getUserByAccPwd(jsonData){
    return request({
        method: 'post',
        url: '/user/getUserByAccPwd',
        data: jsonData
    })
}

// 通过 userId 修改用户信息： nickName 和 password
export function updateUserById(jsonData){
    return request({
        method: 'post',
        url: '/user/updateUserById',
        data: jsonData
    })
}

// 添加新用户
export function addUser(jsonData){
    return request({
        method: 'post',
        url: '/user/addUser',
        data: jsonData
    })
}