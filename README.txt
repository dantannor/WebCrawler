WebCrawler

The input is a URL and the depth level in which to crawl pages.
For depth 3 this means that if on the page there are the following links (1st
 level crawling depth):
1. http://from-root-1.com
2. http://from-root-2.com
3. http://from-root-3.com
The application should also crawl all the above URLs. Assuming that for each link
there are 2 links of the pages:
1. http://from-root-1.com has the following links
    a. http://from-root-1-1.com
        i. http://from-root-1-1-1.com
            ii. http://from-root-1-1-2.com
    b. http://from-root-1-2.com
        i. http://from-root-1-2-1.com
            ii. http://from-root-1-2-2.com
2. And http://from-root-2.com has the following URLs
    a. http://from-root-2-1.com
        i. http://from-root-2-1-1.com
            ii. http://from-root-2-1-2.com
    b. http://from-root-2-2.com
        i. http://from-root-2-2-1.com
            ii. http://from-root-2-2-2.com
The list contains all 14 links with their availability status (HTTP code)

You MUST also provide a limit of URLs per level

EXECUTING THE CODE
Download the code and simply run CrawlerApplication

example request:
POST http://localhost:8080/api/crawl
{
	"url": "https://www.walla.co.il/",
	"depth": 3,
	"limitPerLevel": 3
}

PERFORMANCE
leumi.co.il, depth 5, limit 3
parallel foreach with no break for url limit 1:24 minutes (150 urls in response?)
V1 simple with no parallel but with a break, 32s!

depth 5, limit 5
with completable futures: 46 secs


NOTES
- The ParallelFE class was a different approach I tried, you can look it over, but task #3 is basically the 'CrawlerService'
- My guess is the parallelization does not maintain the order of urls for task #3.
- CrawlerServiceV1 is Task #2 (Simply point CrawlerController to that class instead if you'd like to run it)
- The results don't seem to always be perfect, there might be an issue with starting and waiting for all tasks to complete
- To further support scale can add a DB
- The Java ecosystem was a little bit challenging to work with (multithreading/async/spring), this absorbed a good amount of my time on these projects.


