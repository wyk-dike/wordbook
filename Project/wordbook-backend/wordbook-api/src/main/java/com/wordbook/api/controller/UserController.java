package com.wordbook.api.controller;

import com.wordbook.api.entity.ServerToWebDTO;
import com.wordbook.user.interfaces.dto.UserDTO;
import com.wordbook.user.interfaces.interfaceFiles.IUserRPCService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    // 通过 @DubboReference 注解，Controller 类使用 dubbo 拿到 PRC 服务类的接口
    @DubboReference
    private IUserRPCService iUserRPCService;

    // 通过 userId 获取用户信息
    @RequestMapping("/getUserById")
    public ServerToWebDTO getUserById(int userId) {
        UserDTO userDTO = iUserRPCService.getUserById(userId);
        if(userDTO != null) {
            return new ServerToWebDTO(ServerToWebDTO.SUCCESS_CODE,userDTO);
        }
        else {
            return new ServerToWebDTO(ServerToWebDTO.ERROR_CODE,null);
        }
    }

    // 通过 account 和 password 获取用户信息
    @PostMapping("/getUserByAccPwd")
    public ServerToWebDTO getUserByAccPwd(@RequestBody Map<String, String> map) {
        String account = map.get("account");
        String password = map.get("password");
        UserDTO userDTO = iUserRPCService.getUserByAccPwd(account,password);
        if(userDTO != null) {
            return new ServerToWebDTO(ServerToWebDTO.SUCCESS_CODE,userDTO);
        }
        else {
            return new ServerToWebDTO(ServerToWebDTO.ERROR_CODE,null);
        }
    }

    // 通过 userId 修改用户信息： nickName 和 password
    @PostMapping("/updateUserById")
    public ServerToWebDTO updateUserById(@RequestBody Map<String, Object> map) {
        int userId = (int) map.get("userId");
        String account = (String) map.get("account");
        String password = (String) map.get("password");
        String newPassword = (String) map.get("newPassword");
        String nickName = (String) map.get("nickName");
        // 检验用户真实性
        UserDTO checkUserDTO  = iUserRPCService.getUserByAccPwd(account,password);
        if (checkUserDTO != null && checkUserDTO.getUserId() == userId && !newPassword.isEmpty()) {
            // 检验通过
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(userId);
            userDTO.setAccount(account);
            userDTO.setPassword(newPassword);
            userDTO.setNickName(nickName);
            //
            Boolean isUpdate = iUserRPCService.updateUserById(userDTO);
            if(isUpdate) {
                return new ServerToWebDTO(ServerToWebDTO.SUCCESS_CODE,true);
            }
            else {
                return new ServerToWebDTO(ServerToWebDTO.ERROR_CODE,null);
            }
        }
        return new ServerToWebDTO(ServerToWebDTO.ERROR_CODE,null);
    }

    // 添加新用户
    @PostMapping("/addUser")
    public ServerToWebDTO addUser(@RequestBody Map<String, Object> map) {
        String account = (String) map.get("account");
        String password = (String) map.get("password");
        String nickName = (String) map.get("nickName");
        //
        if(!account.isEmpty() && !password.isEmpty()) {
            // 检验账号唯一性
            Boolean isOnlyAccount = iUserRPCService.checkAccount(account);
            if(isOnlyAccount) {
                // 验证通过
                // 添加进用户表
                UserDTO userDTO = new UserDTO();
                userDTO.setAccount(account);
                userDTO.setPassword(password);
                userDTO.setNickName(nickName);
                //
                Boolean isAdd = iUserRPCService.addUser(userDTO);
                if(isAdd) {
                    return new ServerToWebDTO(ServerToWebDTO.SUCCESS_CODE,true);
                }
                return new ServerToWebDTO(ServerToWebDTO.ERROR_CODE,"err1");
            }
            return new ServerToWebDTO(ServerToWebDTO.ERROR_CODE,"notOnlyAccount");
        }
        return new ServerToWebDTO(ServerToWebDTO.ERROR_CODE,"err2");
    }
}
