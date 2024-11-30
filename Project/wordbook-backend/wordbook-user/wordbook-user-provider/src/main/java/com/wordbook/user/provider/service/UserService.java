package com.wordbook.user.provider.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wordbook.common.util.ConvertBeanUtils;
import com.wordbook.user.interfaces.dto.UserDTO;
import com.wordbook.user.provider.dao.UserDAO;
import com.wordbook.user.provider.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    // => 通过 userId 获取用户信息
    public UserDTO getUserById(int userId) {
        // 校验参数
        if(userId < 0){
            return null;
        }

        // 查找数据库
        UserDAO userDAO = userMapper.selectById(userId);
        // 转换类型：UserDAO --> UserDTO
        UserDTO userDTO = ConvertBeanUtils.convert(userDAO, UserDTO.class);

        return userDTO;
    }

    // => 通过 account 和 password 获取用户信息
    public UserDTO getUserByAccPwd(String account, String password){
        // 校验参数
        if(account == null || password == null){
            return null;
        }
        // 查找数据库
        QueryWrapper<UserDAO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", account)
                    .eq("password", password);
        UserDAO userDAO = userMapper.selectOne(queryWrapper);
        // 转换类型：UserDAO --> UserDTO
        UserDTO userDTO = ConvertBeanUtils.convert(userDAO, UserDTO.class);
        return userDTO;
    }

    // => 通过 userId 修改用户信息： nickName 和 password
    public Boolean updateUserById(UserDTO userDTO){
        // 校验参数
        if(userDTO == null || userDTO.getUserId() < 0 || userDTO.getAccount() == null || userDTO.getPassword() == null){
            return false;
        }
        // 修改数据库
        UserDAO userDAO = new UserDAO();
        userDAO.setUserId(userDTO.getUserId());
        userDAO.setNickName(userDTO.getNickName());
        userDAO.setPassword(userDTO.getPassword());
        //
        int totalNum = userMapper.updateById(userDAO);
        if(totalNum > 0){
            return true;
        }
        return false;
    }

    // => 添加新用户
    public Boolean addUser(UserDTO userDTO){
        // 校验参数
        if(userDTO == null || userDTO.getAccount() == null || userDTO.getPassword() == null){
            return false;
        }
        // 向数据库添加信息
        UserDAO userDAO = new UserDAO();
        userDAO.setAccount(userDTO.getAccount());
        userDAO.setPassword(userDTO.getPassword());
        userDAO.setNickName(userDTO.getNickName());
        //
        int totalNum = userMapper.insert(userDAO);
        if(totalNum > 0){
            return true;
        }
        return false;
    }

    // => 检验账号唯一性
    public Boolean checkAccount(String account){
        // 校验参数
        if(account == null){
            return false;
        }
        // 全字匹配账号
        QueryWrapper<UserDAO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", account);
        UserDAO userDAO = userMapper.selectOne(queryWrapper);
        if(userDAO == null){
            return true;
        }
        return false;
    }
}
