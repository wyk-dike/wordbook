package com.wordbook.user.provider.implement;

import com.wordbook.user.interfaces.dto.UserDTO;
import com.wordbook.user.interfaces.interfaceFiles.IUserRPCService;
import com.wordbook.user.provider.service.UserService;
import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboService;

// 声明为 dubbo 服务
@DubboService
public class UserRPCService implements IUserRPCService {

    @Resource
    private UserService userService;

    // 通过 userId 获取用户信息
    @Override
    public UserDTO getUserById(int userId) {
        return userService.getUserById(userId);
    }

    // 通过 account 和 password 获取用户信息
    @Override
    public UserDTO getUserByAccPwd(String account, String password) {
        return userService.getUserByAccPwd(account,password);
    }

    // 通过 userId 修改用户信息： nickName 和 password
    @Override
    public Boolean updateUserById(UserDTO userDTO) {
        return userService.updateUserById(userDTO);
    }

    // 添加新用户
    @Override
    public Boolean addUser(UserDTO userDTO) {
        return userService.addUser(userDTO);
    }

    // 检验账号唯一性
    @Override
    public Boolean checkAccount(String account) {
        return userService.checkAccount(account);
    }

}
