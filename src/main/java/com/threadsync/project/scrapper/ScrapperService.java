package com.threadsync.project.scrapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.io.IOException;

@Service
public class ScrapperService {
    
    public ResponseEntity<Document> scrapper(SiteUrl url) {
        Document document;
        try{
            document = Jsoup.connect(url.getSiteUrl()).get();
            return ResponseEntity.ok().body(document);
        }catch(IOException   e) {
            e.printStackTrace();
        }catch (Exception e) {
            // Handle any other exception that may occur during the process
            e.printStackTrace(); // Print the stack trace for debugging purposes
        }
        return ResponseEntity.ok().body(null);
    }
}   
