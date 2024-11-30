package com.wordbook.words.interfaces.interfaceFiles;

import com.wordbook.words.interfaces.dto.WordsDTO;
import com.wordbook.words.interfaces.dto.WordsResDTO;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IWordsRPCService {

    // 根据 userId 查询单词（搜索、分页）
    WordsResDTO getWordsByUserId(
            int userId,
            String account,
            String password,
            int currentPage, // 页码
            int pageSize, // 页面大小
            int searchType, // 搜索类型： 0忽略搜索内容； 1匹配单词； 2匹配翻译
            String searchInfo // 搜索内容
    );

    // 添加单词
    Boolean addWord(
            int userId,
            String account,
            String password,
            WordsDTO wordsDTO
    );

    // 通过 wordId 删除单词
    Boolean deleteWordById(int userId, String account, String password, int wordId);
}
