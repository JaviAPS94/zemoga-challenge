package com.zemoga.challenge.infrastructure.repositories.portfolio;

import com.zemoga.challenge.domain.model.entities.Portfolio;
import com.zemoga.challenge.domain.model.repositories.PortfolioRepository;
import com.zemoga.challenge.infrastructure.model.PortfolioDB;

import java.util.Optional;

public class JpaPortfolio implements PortfolioRepository {

  private final JpaPortfolioRepository jpaPortfolioRepository;

  public JpaPortfolio(JpaPortfolioRepository jpaPortfolioRepository) {
    this.jpaPortfolioRepository = jpaPortfolioRepository;
  }

  @Override
  public Optional<Portfolio> findByName(String name) {
    Optional<PortfolioDB> portfolioDB = jpaPortfolioRepository.findByName(name);
    return portfolioDB.map(PortfolioDB::toDomainEntity);
  }

  @Override
  public void save(Portfolio portfolio) {
    jpaPortfolioRepository.save(new PortfolioDB(portfolio));
  }
}
