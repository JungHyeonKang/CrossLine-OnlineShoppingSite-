package com.green.team4.vo.community;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
@Getter
public class Criteria {
    private Long page;
    private Long pageNum;

    // search

    private String type;
    private String keyword;

    public Criteria(){
        this.page = 1L;
        this.pageNum = 10L;
    }

    public Criteria(Long page, Long pageNum){
        if(page<0){
            page=0L;
        }
        if(pageNum<=0){
            pageNum=10L;
        }

        this.page = page;
        this.pageNum = pageNum;
    }

}
