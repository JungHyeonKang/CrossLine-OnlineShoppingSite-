package com.green.team4.vo.shop;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
@Setter
public class ItemPageCriteria {
    private int page;
    private int numPerPage;

    private String pCateCode;
    private int tier;
    private String keyword;
    private String sort;
    private int pno;
    public ItemPageCriteria(){
        this.page=1;
        this.numPerPage=8;
    }

    public void setPage(int page){
        if(page<=0){
            this.page=1;
            return;
        }
        this.page=page;
    }
    public void setNumPerPage(int numPerPage){
        if(numPerPage<=0 || numPerPage>100){
            this.numPerPage=8;
            return;
        }
        this.numPerPage=numPerPage;
    }

    public int getStartPage(){
      return   (this.page-1)*this.numPerPage;
    }


}
