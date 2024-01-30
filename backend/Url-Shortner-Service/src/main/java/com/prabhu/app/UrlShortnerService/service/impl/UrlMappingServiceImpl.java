package com.prabhu.app.UrlShortnerService.service.impl;

import com.prabhu.app.UrlShortnerService.dto.UrlMappingDto;
import com.prabhu.app.UrlShortnerService.entity.UrlMappingEntity;
import com.prabhu.app.UrlShortnerService.exception.UrlMappingNotFoundException;
import com.prabhu.app.UrlShortnerService.repository.UrlMappingRepository;
import com.prabhu.app.UrlShortnerService.service.UrlMappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@Slf4j
public class UrlMappingServiceImpl implements UrlMappingService {

    private final UrlMappingRepository urlMappingRepository;

    @Autowired
    public UrlMappingServiceImpl(UrlMappingRepository urlMappingRepository) {
        this.urlMappingRepository = urlMappingRepository;
    }

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int CODE_LENGTH = 15;

    @Override
    public UrlMappingDto shortenUrl(UrlMappingDto urlMappingDto) {
        if (urlMappingDto == null || urlMappingDto.getOriginalUrl() == null || urlMappingDto.getOriginalUrl().trim().isEmpty()) {
            throw new IllegalArgumentException("Original URL cannot be blank");
        }

        UrlMappingEntity entity = new UrlMappingEntity();
        entity.setOriginalUrl(urlMappingDto.getOriginalUrl());
        entity.setShortUrl(generateShortUrl());
        urlMappingRepository.save(entity);

        return new UrlMappingDto(entity.getId(), entity.getOriginalUrl(), entity.getShortUrl());
    }


    @Override
    public String getOriginalUrl(String shortUrl) {
        if (shortUrl == null || shortUrl.trim().isEmpty()) {
            throw new IllegalArgumentException("Short URL cannot be blank");
        }

        UrlMappingEntity entity = urlMappingRepository.findByShortUrl(shortUrl);
        if (entity != null) {
            return entity.getOriginalUrl();
        } else {
            log.error("Short URL not found: {}", shortUrl);
            throw new UrlMappingNotFoundException(shortUrl);
        }
    }


    private String generateShortUrl() {
        StringBuilder shortUrl = new StringBuilder(CODE_LENGTH);
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < CODE_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            shortUrl.append(CHARACTERS.charAt(randomIndex));
        }
        return shortUrl.toString();
    }
}
