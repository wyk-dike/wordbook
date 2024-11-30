package com.wordbook.user.provider.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

// 通过在类上添加 @TableName 注解并指定表名，ORM 框架将会将该类与指定的数据库表进行映射
@TableName("user_table")
public class UserDAO {

    // 指定表的主键
    // type = IdType.AUTO 表示主键由数据库自动生成
    @TableId(type = IdType.AUTO)
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
