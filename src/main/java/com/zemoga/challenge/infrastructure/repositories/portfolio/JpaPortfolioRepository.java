package com.zemoga.challenge.infrastructure.repositories.portfolio;

import com.zemoga.challenge.infrastructure.model.PortfolioDB;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@Repository
public interface JpaPortfolioRepository extends CrudRepository<PortfolioDB, Long> {
  Optional<PortfolioDB> findByName(String name);
}
