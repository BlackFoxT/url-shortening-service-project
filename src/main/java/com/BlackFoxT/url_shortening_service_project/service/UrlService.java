package com.BlackFoxT.url_shortening_service_project.service;

import com.BlackFoxT.url_shortening_service_project.dto.UrlRequestDto;
import com.BlackFoxT.url_shortening_service_project.dto.UrlResponseDto;
import com.BlackFoxT.url_shortening_service_project.exception.UrlNotFoundException;
import com.BlackFoxT.url_shortening_service_project.model.Url;
import com.BlackFoxT.url_shortening_service_project.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public UrlResponseDto createShortUrl(UrlRequestDto requestDto) {
        String shortCode = generateShortCode();
        Url url = new Url(requestDto.getUrl(), shortCode);
        urlRepository.save(url);

        return new UrlResponseDto(url.getId(), url.getUrl(), url.getShortCode(), url.getCreatedAt(), url.getUpdatedAt());
    }

    public UrlResponseDto getOriginalUrl(String shortCode) {
        Optional<Url> urlOptional = urlRepository.findByShortCode(shortCode);
        Url url = urlOptional.orElseThrow(() -> new UrlNotFoundException("Short URL not found"));
        url.setAccessCount(url.getAccessCount() + 1);
        urlRepository.save(url);

        return new UrlResponseDto(url.getId(), url.getUrl(), url.getShortCode(), url.getCreatedAt(), url.getUpdatedAt());
    }

    public UrlResponseDto updateShortUrl(String shortCode, UrlRequestDto requestDto) {
        Optional<Url> url = urlRepository.findByShortCode(shortCode);
        if (url.isPresent()) {
            Url updatedUrl = url.get();
            updatedUrl.setUrl(requestDto.getUrl());
            urlRepository.save(updatedUrl);

            return new UrlResponseDto(updatedUrl.getId(), updatedUrl.getUrl(), updatedUrl.getShortCode(), updatedUrl.getCreatedAt(), updatedUrl.getUpdatedAt());
        } else {
            throw new UrlNotFoundException("Short URL not found");
        }
    }

    public void deleteShortUrl(String shortCode) {
        Optional<Url> url = urlRepository.findByShortCode(shortCode);
        if (url.isPresent()) {
            urlRepository.delete(url.get());
        } else {
            throw new UrlNotFoundException("Short URL not found");
        }
    }

    public UrlResponseDto getUrlStats(String shortCode) {
        Optional<Url> url = urlRepository.findByShortCode(shortCode);
        if (url.isPresent()) {
            Url urlEntity = url.get();
            return new UrlResponseDto(urlEntity.getId(), urlEntity.getUrl(), urlEntity.getShortCode(), urlEntity.getCreatedAt(), urlEntity.getUpdatedAt(), urlEntity.getAccessCount());
        } else {
            throw new UrlNotFoundException("Short URL not found");
        }
    }

    private String generateShortCode() {
        return java.util.UUID.randomUUID().toString().substring(0, 6);
    }
}
