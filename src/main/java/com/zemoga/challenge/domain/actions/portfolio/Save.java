package com.zemoga.challenge.domain.actions.portfolio;

import com.zemoga.challenge.domain.model.entities.Portfolio;
import com.zemoga.challenge.domain.model.repositories.PortfolioRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Save {
  private final PortfolioRepository portfolioRepository;

  public void execute(Portfolio portfolio) {
    portfolioRepository.save(portfolio);
  }
}
