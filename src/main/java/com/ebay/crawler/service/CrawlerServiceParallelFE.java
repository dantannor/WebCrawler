package com.ebay.crawler.service;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class CrawlerServiceParallelFE {
    private Set<String> visited;
    private int maxDepth;
    private int limitPerLevel;

    public CrawlerServiceParallelFE() {
        visited = new HashSet<>();
    }

    /**
     * Crawls the page and subpages
     * @param data contains the config for the crawling operation
     * @return a recursive list of pages with status codes
     */
    public Page crawl(CrawlerData data) {
        if (data.getDepth() < 1) throw new IllegalArgumentException("Depth should be a positive number");
        if (data.getLimitPerLevel() < 1) throw new IllegalArgumentException("Limit per level should be a positive number");
        if (data.getUrl() == null) throw new IllegalArgumentException("Null url");

        this.maxDepth = data.getDepth();
        this.limitPerLevel = data.getLimitPerLevel();
        visited.add(data.getUrl());

        Page page = new Page(data.getUrl(), 1);
        crawl(page);

        return page;
    }

    private void crawl(Page page){
        try {
            System.out.println("Connecting to " + page.getUrl());
            Connection.Response response = Jsoup.connect(page.getUrl()).timeout(3000).execute();
            int code = response.statusCode();
            visited.add(page.getUrl());
            page.setCode(code);
            if (code != 200) return;

            Document document = response.parse();
            Elements pageUrls = document.select("a[href]");

            if (page.getDepth() >= maxDepth){
                System.out.println("Reached max depth: " + maxDepth);
                return;
            }

            AtomicInteger limit = new AtomicInteger(0);
            pageUrls.parallelStream().forEach(url -> {
                if (visited.contains(url.attr("abs:href"))) {
                    System.out.println("Duplicate url: " + url.attr("abs:href"));
                    return;
                }

                // todo: need to find a way to break
                if (limit.get() >= limitPerLevel){
                    System.out.println("Reached limit per level: " + limit);
                    return;
                }

                Page subPage = new Page(url.attr("abs:href"), page.getDepth() + 1);
                page.addSubPage(subPage);
                crawl(subPage);

                limit.getAndIncrement();
            });
        } catch (IOException e) {
            System.err.println("URL err '" + page.getUrl() + "': " + e.getMessage());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}

    
