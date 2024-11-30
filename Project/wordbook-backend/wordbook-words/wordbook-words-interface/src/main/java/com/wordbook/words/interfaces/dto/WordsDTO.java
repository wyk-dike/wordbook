package com.wordbook.words.interfaces.dto;

import java.io.Serializable;

// implements Serializable 作用：对象的序列化和反序列化，即将对象转换成字节流并可以恢复成原来的对象
public class WordsDTO implements Serializable {

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
