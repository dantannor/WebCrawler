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

example request:
POST http://localhost:8080/api/crawl
{
	"url": "https://www.walla.co.il/",
	"depth": 3,
	"limitPerLevel": 3
}