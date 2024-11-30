package com.wordbook.user.interfaces.dto;

import java.io.Serializable;

// implements Serializable 作用：对象的序列化和反序列化，即将对象转换成字节流并可以恢复成原来的对象
public class UserDTO implements Serializable {

    private int userId;

    private String account;

    private String password;

    private String nickName;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
