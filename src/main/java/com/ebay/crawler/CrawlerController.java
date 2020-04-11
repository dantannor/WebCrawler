package com.ebay.crawler;

import com.ebay.crawler.service.CrawlerService;
import com.ebay.crawler.service.CrawlerData;
import com.ebay.crawler.service.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CrawlerController {
    @Autowired
    private CrawlerService crawler;

    @PostMapping(path = "/api/crawl", consumes = "application/json", produces = "application/json")
    public Page crawl(@RequestBody CrawlerData data){
        return crawler.crawl(data);
    }

}

