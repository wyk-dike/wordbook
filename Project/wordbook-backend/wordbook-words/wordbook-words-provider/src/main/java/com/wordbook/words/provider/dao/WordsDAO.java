package com.wordbook.words.provider.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

// 通过在类上添加 @TableName 注解并指定表名，ORM 框架将会将该类与指定的数据库表进行映射
@TableName("words_table")
public class WordsDAO {

    // 指定表的主键
    // type = IdType.AUTO 表示主键由数据库自动生成
    @TableId(type = IdType.AUTO)
    private int wordId;

    private int wordUserId;

    private String word;

    private String translation;

    private String comment;

    public int getWordId() {
        return wordId;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
    }

    public int getWordUserId() {
        return wordUserId;
    }

    public void setWordUserId(int wordUserId) {
        this.wordUserId = wordUserId;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
