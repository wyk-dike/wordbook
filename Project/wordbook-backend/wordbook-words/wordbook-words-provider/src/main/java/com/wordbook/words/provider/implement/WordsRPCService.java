package com.wordbook.words.provider.implement;

import com.wordbook.words.interfaces.dto.WordsDTO;
import com.wordbook.words.interfaces.dto.WordsResDTO;
import com.wordbook.words.interfaces.interfaceFiles.IWordsRPCService;
import com.wordbook.words.provider.service.WordsService;
import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

// 注册为 dubbo 服务
@DubboService
public class WordsRPCService implements IWordsRPCService {

    @Resource
    private WordsService wordsService;

    // 根据 userId 查询单词（搜索、分页）
    @Override
    public WordsResDTO getWordsByUserId(
            int userId,
            String account,
            String password,
            int currentPage, // 页码
            int pageSize, // 页面大小
            int searchType, // 搜索类型： 0忽略搜索内容； 1匹配单词； 2匹配翻译
            String searchInfo // 搜索内容
    ) {
        return wordsService.getWordsByUserId(userId, account, password, currentPage, pageSize, searchType, searchInfo);
    }

    // 添加单词
    @Override
    public Boolean addWord(int userId, String account, String password, WordsDTO wordsDTO) {
        return wordsService.addWord(userId, account, password, wordsDTO);
    }

    // 通过 wordId 删除单词
    @Override
    public Boolean deleteWordById(int userId, String account, String password, int wordId) {
        return wordsService.deleteWordById(userId, account, password, wordId);
    }
}
