package com.wordbook.words.provider.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wordbook.common.util.ConvertBeanUtils;
import com.wordbook.user.interfaces.dto.UserDTO;
import com.wordbook.user.interfaces.interfaceFiles.IUserRPCService;
import com.wordbook.words.interfaces.dto.WordsDTO;
import com.wordbook.words.interfaces.dto.WordsResDTO;
import com.wordbook.words.provider.dao.WordsDAO;
import com.wordbook.words.provider.mapper.WordsMapper;
import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WordsService {

    // 通过 @DubboReference 注解，Controller 类使用 dubbo 拿到 PRC 服务类的接口
    @DubboReference
    private IUserRPCService iUserRPCService;

    @Resource
    private WordsMapper wordsMapper;

    // => 根据 userId 查询单词（搜索、分页）
    public WordsResDTO getWordsByUserId(
            int userId,
            String account,
            String password,
            int currentPage, // 页码
            int pageSize, // 页面大小
            int searchType, // 搜索类型： 0忽略搜索内容； 1匹配单词； 2匹配翻译
            String searchInfo // 搜索内容
    ) {
        // 校验参数
        if(userId<0 || account==null || password==null){
            return null;
        }
        // 检验用户真实性
        UserDTO userDTO = iUserRPCService.getUserByAccPwd(account, password);
        if(userDTO != null && userDTO.getUserId() == userId ){
            // 验证通过
            // 查单词表
            QueryWrapper<WordsDAO> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("word_user_id", userId);
            if(searchType != 0){
                if(searchType == 1){
                    // 匹配单词
                    queryWrapper.like("word", searchInfo);
                }
                else if(searchType == 2){
                    // 匹配翻译
                    queryWrapper.like("translation", searchInfo);
                }
            }
            // 设置分页查询参数
            // 紧跟着的第一个 select 方法会被分页
            // 分页时，实际返回的结果 list 类型是 Page<E>，如果想取出分页信息，需要强制转换为 Page<E>
            PageHelper.startPage(currentPage, pageSize);
            Page<WordsDAO> wordsDAOPage = (Page<WordsDAO>) wordsMapper.selectList(queryWrapper); // 只有这个被分页
            // 从 Page 中取出信息
            Long total = wordsDAOPage.getTotal();
            List<WordsDAO> wordsDAOList = wordsDAOPage.getResult();
            // 转换类型：List<WordsDAO> --> List<WordsDTO>
            List<WordsDTO> wordsDTOList = new ArrayList<>();
            for(WordsDAO wordsDAO : wordsDAOList){
                // 转换类型：WordsDAO --> WordsDTO
                WordsDTO wordsDTO = ConvertBeanUtils.convert(wordsDAO, WordsDTO.class);
                wordsDTOList.add(wordsDTO);
            }
            // 返回数据
            return new WordsResDTO(total, wordsDTOList);
        }
        return null;
    }

    // => 添加单词
    public Boolean addWord(int userId, String account, String password, WordsDTO wordInfo) {
        // 校验参数
        if(userId<0 || account==null || password==null){
            return false;
        }
        // 检验用户真实性
        UserDTO userDTO = iUserRPCService.getUserByAccPwd(account, password);
        if(userDTO != null && userDTO.getUserId() == userId ) {
            // 验证通过
            // 添加单词到单词表
            WordsDAO wordsDAO = new WordsDAO();
            wordsDAO.setWordUserId(userId);
            wordsDAO.setWord(wordInfo.getWord());
            wordsDAO.setTranslation(wordInfo.getTranslation());
            wordsDAO.setComment(wordInfo.getComment());
            //
            int addTotalNum = wordsMapper.insert(wordsDAO);
            if (addTotalNum > 0) {
                return true;
            }
            return false;
        }
        return false;
    }

    // 通过 wordId 删除单词
    public Boolean deleteWordById(int userId, String account, String password, int wordId){
        // 校验参数
        if(userId<0 || account==null || password==null || wordId<0){
            return false;
        }
        // 检验用户真实性
        UserDTO userDTO = iUserRPCService.getUserByAccPwd(account, password);
        if(userDTO != null && userDTO.getUserId() == userId ) {
            // 验证通过
            // 根据 wordId 删除单词
            int deleteTotalNum = wordsMapper.deleteById(wordId);
            if (deleteTotalNum > 0) {
                return true;
            }
            return false;
        }
        return false;
    }
}
