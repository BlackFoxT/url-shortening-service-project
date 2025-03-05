package com.BlackFoxT.url_shortening_service_project.controller;

import com.BlackFoxT.url_shortening_service_project.dto.UrlRequestDto;
import com.BlackFoxT.url_shortening_service_project.dto.UrlResponseDto;
import com.BlackFoxT.url_shortening_service_project.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shorten")
public class UrlController {

    @Autowired
    private UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UrlResponseDto createShortUrl(@RequestBody UrlRequestDto requestDto) {
        return urlService.createShortUrl(requestDto);
    }

    @GetMapping("/{shortCode}")
    public UrlResponseDto getOriginalUrl(@PathVariable String shortCode) {
        return urlService.getOriginalUrl(shortCode);
    }

    @PutMapping("/{shortCode}")
    public UrlResponseDto updateShortUrl(@PathVariable String shortCode, @RequestBody UrlRequestDto requestDto) {
        return urlService.updateShortUrl(shortCode, requestDto);
    }

    @DeleteMapping("/{shortCode}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteShortUrl(@PathVariable String shortCode) {
        urlService.deleteShortUrl(shortCode);
    }

    @GetMapping("/{shortCode}/stats")
    public UrlResponseDto getUrlStats(@PathVariable String shortCode) {
        return urlService.getUrlStats(shortCode);
    }

}
