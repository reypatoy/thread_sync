package com.threadsync.project.scrapper;

import org.springframework.web.bind.annotation.RestController;
import org.jsoup.nodes.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping(path = "api/v1/scrapper")
public class Controller {

    private final ScrapperService scrapperService;

    public Controller(ScrapperService scrapperService){
        this.scrapperService = scrapperService;
    }
    
    @PostMapping("")
    public ResponseEntity<Document> scrapper(@RequestBody SiteUrl siteUrl) {
        System.out.print("niabot diri");
        return scrapperService.scrapper(siteUrl);
    }
    
}
