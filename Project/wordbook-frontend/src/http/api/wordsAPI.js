import request from '../request'

// 根据 userId 查询单词（搜索、分页）
export function getWordsByUserId(jsonData){
    return request({
        method: 'post',
        url: '/words/getWordsByUserId',
        data: jsonData
    })
}

// 添加单词
export function addWord(jsonData){
    return request({
        method: 'post',
        url: '/words/addWord',
        data: jsonData
    })
}

// 通过 wordId 删除单词
export function deleteWordById(jsonData){
    return request({
        method: 'post',
        url: '/words/deleteWordById',
        data: jsonData
    })
}
