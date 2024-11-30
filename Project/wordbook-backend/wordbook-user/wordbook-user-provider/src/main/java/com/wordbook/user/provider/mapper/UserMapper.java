package com.wordbook.user.provider.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wordbook.user.provider.dao.UserDAO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserDAO> {
}
