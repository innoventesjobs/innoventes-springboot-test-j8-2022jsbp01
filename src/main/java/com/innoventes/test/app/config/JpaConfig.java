package com.innoventes.test.app.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.innoventes.test.app.repository")
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EntityScan(basePackageClasses = {Jsr310JpaConverters.class},
    basePackages = {"com.innoventes.test.app.entity"})
public class JpaConfig {
  @Bean
  public AuditorAware<String> auditorAware() {
    return new AuditorAwareImpl();
  }
}

