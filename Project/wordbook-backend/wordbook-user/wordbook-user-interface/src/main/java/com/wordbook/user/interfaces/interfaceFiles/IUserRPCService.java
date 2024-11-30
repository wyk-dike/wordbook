package com.wordbook.user.interfaces.interfaceFiles;

import com.wordbook.user.interfaces.dto.UserDTO;

public interface IUserRPCService {

    // 通过 userId 获取用户信息
    UserDTO getUserById(int userId);

    // 通过 account 和 password 获取用户信息
    UserDTO getUserByAccPwd(String account, String password);

    // 通过 userId 修改用户信息： nickName 和 password
    Boolean updateUserById(UserDTO userDTO);

    // 添加新用户
    Boolean addUser(UserDTO userDTO);

    // 检验账号唯一性
    Boolean checkAccount(String account);
}
