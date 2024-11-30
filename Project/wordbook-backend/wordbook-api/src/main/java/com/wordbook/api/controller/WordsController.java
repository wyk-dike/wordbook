package com.wordbook.api.controller;

import com.wordbook.api.entity.ServerToWebDTO;
import com.wordbook.words.interfaces.dto.WordsDTO;
import com.wordbook.words.interfaces.dto.WordsResDTO;
import com.wordbook.words.interfaces.interfaceFiles.IWordsRPCService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/words")
public class WordsController {

    // 通过 @DubboReference 注解，Controller 类使用 dubbo 拿到 PRC 服务类的接口
    @DubboReference
    private IWordsRPCService iWordsRPCService;

    // 根据 userId 查询单词（搜索、分页）
    @PostMapping("/getWordsByUserId")
    public ServerToWebDTO getWordsByUserId(@RequestBody Map<String, Object> map) {
        // int userId
        // String account
        // String password
        // int currentPage // 页码
        // int pageSize // 页面大小
        // int searchType // 搜索类型： 0忽略搜索内容； 1匹配单词； 2匹配翻译
        // String searchInfo // 搜索内容
        int userId = (int) map.get("userId");
        String account = (String) map.get("account");
        String password = (String) map.get("password");
        int currentPage = (int) map.get("currentPage");
        int pageSize = (int) map.get("pageSize");
        int searchType = (int) map.get("searchType");
        String searchInfo = (String) map.get("searchInfo");
        WordsResDTO wordsResDTO = iWordsRPCService.getWordsByUserId(userId, account, password, currentPage, pageSize, searchType, searchInfo);
        if (wordsResDTO !=null) {
            return new ServerToWebDTO(ServerToWebDTO.SUCCESS_CODE, wordsResDTO);
        }
        else {
            return new ServerToWebDTO(ServerToWebDTO.ERROR_CODE,null);
        }
    }

    // 添加单词
    @PostMapping("/addWord")
    public ServerToWebDTO addWord(@RequestBody Map<String, Object> map) {
        int userId = (int) map.get("userId");
        String account = (String) map.get("account");
        String password = (String) map.get("password");
        String word = (String) map.get("word");
        String translation = (String) map.get("translation");
        String comment = (String) map.get("comment");
        //
        WordsDTO wordsDTO = new WordsDTO();
        wordsDTO.setWord(word);
        wordsDTO.setTranslation(translation);
        wordsDTO.setComment(comment);
        //
        Boolean isAdd = iWordsRPCService.addWord(userId, account, password, wordsDTO);
        if (isAdd) {
            return new ServerToWebDTO(ServerToWebDTO.SUCCESS_CODE, true);
        }
        else {
            return new ServerToWebDTO(ServerToWebDTO.ERROR_CODE,null);
        }
    }

    // 通过 wordId 删除单词
    @PostMapping("/deleteWordById")
    public ServerToWebDTO deleteWordById(@RequestBody Map<String, Object> map) {
        int userId = (int) map.get("userId");
        String account = (String) map.get("account");
        String password = (String) map.get("password");
        int wordId = (int) map.get("wordId");
        //
        Boolean isDelete = iWordsRPCService.deleteWordById(userId, account, password, wordId);
        if(isDelete) {
            return new ServerToWebDTO(ServerToWebDTO.SUCCESS_CODE, true);
        }
        else {
            return new ServerToWebDTO(ServerToWebDTO.ERROR_CODE,null);
        }
    }
}
