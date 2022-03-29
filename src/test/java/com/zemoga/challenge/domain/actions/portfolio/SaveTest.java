package com.zemoga.challenge.domain.actions.portfolio;

import com.zemoga.challenge.domain.mock.PortfolioMock;
import com.zemoga.challenge.domain.model.entities.Portfolio;
import com.zemoga.challenge.domain.model.repositories.PortfolioRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SaveTest {
  @Mock
  private PortfolioRepository portfolioRepository;

  @InjectMocks
  private Save save;

  @Test
  public void savePortfolio_ok() {
    Portfolio portfolioMock = PortfolioMock.create();
    save.execute(portfolioMock);

    verify(portfolioRepository, times(1))
            .save(portfolioMock);
  }
}
