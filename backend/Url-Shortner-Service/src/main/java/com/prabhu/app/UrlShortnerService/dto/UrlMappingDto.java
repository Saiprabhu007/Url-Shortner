package com.prabhu.app.UrlShortnerService.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UrlMappingDto {

    private Long id;
    private String originalUrl;
    private String shortUrl;
}
