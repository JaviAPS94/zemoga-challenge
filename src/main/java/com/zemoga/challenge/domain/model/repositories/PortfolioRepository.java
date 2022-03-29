package com.zemoga.challenge.domain.model.repositories;

import com.zemoga.challenge.domain.model.entities.Portfolio;

import java.util.Optional;

public interface PortfolioRepository {
  Optional<Portfolio> findByName(String name);
  void save(Portfolio portfolio);
}
