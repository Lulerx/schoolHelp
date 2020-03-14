package com.itle.schoolhelp.dto;

import java.util.List;

/**
 * 分页对象
 * @auther Luler
 * @date 2020/2/28
 */
public class Page<T> {

    //数据
    private List<T> dataList;

    //总条数
    private int totalSize;

    //当前页码数
    private int pageNo;

    //每页条数
    private int pageSize;

    //总页码
    private int totalNo;

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalNo() {
        return totalNo;
    }

    public void setTotalNo(int totalNo) {
        this.totalNo = totalNo;
    }

    @Override
    public String toString() {
        return "Page{" +
                "dataList=" + dataList +
                ", totalSize=" + totalSize +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", totalNo=" + totalNo +
                '}';
    }
}
