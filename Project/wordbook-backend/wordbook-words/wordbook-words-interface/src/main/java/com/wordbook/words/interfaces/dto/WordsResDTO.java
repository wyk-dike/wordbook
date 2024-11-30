package com.wordbook.words.interfaces.dto;

import java.io.Serializable;
import java.util.List;

public class WordsResDTO implements Serializable {

    private Long total;

    private List<WordsDTO> list;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<WordsDTO> getList() {
        return list;
    }

    public void setList(List<WordsDTO> list) {
        this.list = list;
    }

    public WordsResDTO(Long total, List<WordsDTO> list) {
        this.total = total;
        this.list = list;
    }
}
