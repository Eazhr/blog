package com.ezhar.domain;

/**
 * 博客查询时封装的类
 */
public class BlogQuery {
    private String title;   //标题
    private Long typeId;        //类型id
    private boolean recommend;   //是否推荐

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }
}
