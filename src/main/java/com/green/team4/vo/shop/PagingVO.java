package com.green.team4.vo.shop;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class PagingVO {
    private int start;
    private int end;
    private int totalData;
    private ItemPageCriteria cri;
    private boolean prev;
    private boolean next;
    private int displayPageNum=10;

    public void setCri(ItemPageCriteria cri){
        this.cri=cri;
    }
    public void setTotalProductData(int totalData){
        this.totalData=totalData;
        getPaging();

    }
    public PagingVO(ItemPageCriteria cri, int totalData){
        this.cri=cri;
        this.totalData=totalData;
        getPaging();
    }
    public void getPaging(){
        //페이지 목록 마지막
        end = (int)(Math.ceil(cri.getPage()/(double)displayPageNum) * displayPageNum);


        //페이지 목록 시작
        start = (end-displayPageNum)+1;

        //찐막 페이지
        int finalEndPage= (int)(Math.ceil(totalData/(double)cri.getNumPerPage()));
        if(end>finalEndPage) end=finalEndPage;

        // 이전 ,다음 버튼
        prev = start>1;
        next = end * cri.getNumPerPage() > totalData ? false : true;


    }


}
