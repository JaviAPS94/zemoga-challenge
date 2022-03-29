package com.zemoga.challenge.domain.actions.portfolio;

import com.zemoga.challenge.domain.model.entities.Portfolio;
import com.zemoga.challenge.domain.model.repositories.PortfolioRepository;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class FindByName {
  private final PortfolioRepository portfolioRepository;

  public Optional<Portfolio> execute(String name){
    return portfolioRepository.findByName(name);
  }
}
