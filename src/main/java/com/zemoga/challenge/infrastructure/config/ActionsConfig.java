package com.zemoga.challenge.infrastructure.config;

import com.zemoga.challenge.domain.actions.portfolio.FindByName;
import com.zemoga.challenge.domain.actions.portfolio.Save;
import com.zemoga.challenge.domain.actions.twitter.FindByUsernameAndSize;
import com.zemoga.challenge.domain.model.repositories.PortfolioRepository;
import com.zemoga.challenge.domain.model.repositories.TwitterRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActionsConfig {
  @Bean
  public FindByUsernameAndSize findByUsernameAndSize(TwitterRepository twitterRepository) {
    return new FindByUsernameAndSize(twitterRepository);
  }

  @Bean
  public FindByName findByName(PortfolioRepository portfolioRepository) {
    return new FindByName(portfolioRepository);
  }

  @Bean
  public Save save(PortfolioRepository portfolioRepository) {
    return new Save(portfolioRepository);
  }
}
