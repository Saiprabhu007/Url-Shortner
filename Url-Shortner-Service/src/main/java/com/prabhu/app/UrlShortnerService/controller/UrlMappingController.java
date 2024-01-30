package com.prabhu.app.UrlShortnerService.controller;

import com.prabhu.app.UrlShortnerService.dto.UrlMappingDto;
import com.prabhu.app.UrlShortnerService.service.UrlMappingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/url")
public class UrlMappingController {

    private final UrlMappingService urlMappingService;

    @Autowired
    public UrlMappingController(UrlMappingService urlMappingService) {
        this.urlMappingService = urlMappingService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<UrlMappingDto> shortenUrl(@RequestBody @Valid UrlMappingDto urlMappingDto) {
        UrlMappingDto shortenedUrl = urlMappingService.shortenUrl(urlMappingDto);
        return new ResponseEntity<>(shortenedUrl, HttpStatus.CREATED);
    }


    @GetMapping("/{shortUrl}")
    public ResponseEntity<String> redirectToOriginalUrl(@PathVariable String shortUrl) {
        String originalUrl = urlMappingService.getOriginalUrl(shortUrl);

        if (originalUrl != null) {
            return new ResponseEntity<>(originalUrl, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Short URL not found", HttpStatus.NOT_FOUND);
        }
    }
}
