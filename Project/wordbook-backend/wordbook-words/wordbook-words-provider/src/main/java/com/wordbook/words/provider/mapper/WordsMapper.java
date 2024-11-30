package com.wordbook.words.provider.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wordbook.words.provider.dao.WordsDAO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WordsMapper extends BaseMapper<WordsDAO> {
}
