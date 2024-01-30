package com.prabhu.app.UrlShortnerService.repository;

import com.prabhu.app.UrlShortnerService.entity.UrlMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlMappingRepository extends JpaRepository<UrlMappingEntity, Long> {

    @Query("SELECT u FROM UrlMappingEntity u WHERE u.shortUrl = :shortUrl")
    UrlMappingEntity findByShortUrl(@Param("shortUrl") String shortUrl);

    UrlMappingEntity findByOriginalUrl(String originalUrl);

}
