package com.ebay.crawler.service;

import java.util.ArrayList;
import java.util.List;

public class Page {
    private int code;
    private int depth;
    private String url;
    private List<Page> subPages;

    public Page(String url, int depth){
        this.depth = depth;
        this.url = url;
        this.subPages = new ArrayList<>();
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<Page> getSubPages() {
        return subPages;
    }

    public void addSubPage(Page page) {
        this.subPages.add(page);
    }

    public int getDepth() {
        return depth;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Page{" +
                "code=" + code +
                ", depth=" + depth +
                ", url='" + url + '\'' +
                '}';
    }
}
