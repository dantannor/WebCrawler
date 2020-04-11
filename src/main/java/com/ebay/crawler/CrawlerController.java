package com.ebay.crawler;

import com.ebay.crawler.service.Crawler;
import com.ebay.crawler.service.CrawlerData;
import com.ebay.crawler.service.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class CrawlerController {
    @Autowired
    private Crawler crawler;

    @PostMapping(path = "/api/crawl", consumes = "application/json", produces = "application/json")
    public Page crawl(@RequestBody CrawlerData data){
        return crawler.crawl(data);
    }

}

