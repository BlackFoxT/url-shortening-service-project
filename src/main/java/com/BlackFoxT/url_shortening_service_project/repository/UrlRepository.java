package com.BlackFoxT.url_shortening_service_project.repository;

import com.BlackFoxT.url_shortening_service_project.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, Long> {

    Optional<Url> findByShortCode(String shortCode);
}
