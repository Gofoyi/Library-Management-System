package com.book.entity;

import lombok.Data;

import java.util.List;

@Data
public class Pages<E> {
    int SepPageNum = 10;//一页显示的条数为10
    List<E> TotalList;
    int TotalNum;//列表的总条数
    int TotalPageNum;//一共有多少页 (总数/10)+1

    public Pages(List<E> TotalList, int SepPageNum){
        this.TotalList = TotalList;
        this.TotalNum = TotalList.size();
        this.SepPageNum = SepPageNum;
        if (TotalNum == 0){
            TotalPageNum = 1;
        } else if (this.TotalNum % this.SepPageNum !=0)
            TotalPageNum = (this.TotalNum / this.SepPageNum) + 1;
        else
            TotalPageNum = this.TotalNum / this.SepPageNum;
    }


    public List<E> getSepPage(int PageNum){
        if (PageNum == TotalPageNum)
            return TotalList.subList((PageNum-1) * SepPageNum, TotalNum);
        else
            return TotalList.subList((PageNum-1) * SepPageNum,PageNum * SepPageNum);
    }
}
