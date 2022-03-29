package com.zemoga.challenge.infrastructure.config;

import com.zemoga.challenge.infrastructure.repositories.portfolio.JpaPortfolio;
import com.zemoga.challenge.infrastructure.repositories.portfolio.JpaPortfolioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoriesConfig {
  @Bean
  public JpaPortfolio jpaNotification(JpaPortfolioRepository jpaPortfolioRepository) {
    return new JpaPortfolio(jpaPortfolioRepository);
  }
}
