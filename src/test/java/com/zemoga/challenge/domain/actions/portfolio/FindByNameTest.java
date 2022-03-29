package com.zemoga.challenge.domain.actions.portfolio;

import com.zemoga.challenge.domain.mock.PortfolioMock;
import com.zemoga.challenge.domain.model.entities.Portfolio;
import com.zemoga.challenge.domain.model.repositories.PortfolioRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class FindByNameTest {
  @Mock
  private PortfolioRepository portfolioRepository;

  @InjectMocks
  private FindByName findByName;

  @Test
  public void findPortfolioByName_ok() {
    Optional<Portfolio> optionalPortfolioMock = PortfolioMock.createOptional();
    String userName = "userName";

    doReturn(optionalPortfolioMock)
            .when(portfolioRepository)
            .findByName(userName);

    Optional<Portfolio> result = findByName.execute(userName);

    assertThat(result.isPresent()).isEqualTo(true);
    assertThat(result.get().getName()).isEqualTo("test name");
    assertThat(result.get().getTwitterUser()).isEqualTo("twitterUser");
    assertThat(result.get().getId()).isEqualTo(1L);
    assertThat(result.get().getImagePath()).isEqualTo("https://www.zemoga.com/images/user/1/profile.jpg");
    assertThat(result.get().getExperience()).isEqualTo("test experience");
    assertThat(result.get().getPhone()).isEqualTo("09956398751");
    assertThat(result.get().getZipCode()).isEqualTo("5893");
    assertThat(result.get().getAddress()).isEqualTo("test address");

    verify(portfolioRepository, times(1))
            .findByName(userName);
  }
}
