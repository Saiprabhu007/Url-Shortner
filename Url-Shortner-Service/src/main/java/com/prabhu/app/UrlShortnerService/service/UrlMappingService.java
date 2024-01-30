package com.prabhu.app.UrlShortnerService.service;

import com.prabhu.app.UrlShortnerService.dto.UrlMappingDto;

public interface UrlMappingService {

    UrlMappingDto shortenUrl(UrlMappingDto urlMappingDto);

    String getOriginalUrl(String shortUrl);
}
