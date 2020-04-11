package com.ebay.crawler.service;

public class CrawlerData {
    private String url;
    private int depth;
    private int limitPerLevel;

    public int getLimitPerLevel() {
        return limitPerLevel;
    }

    public CrawlerData() {
    }

    public CrawlerData(String url, int depth, int limitPerLevel) {
        this.url = url;
        this.depth = depth;
        this.limitPerLevel = limitPerLevel;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
}
