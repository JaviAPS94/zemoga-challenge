package com.zemoga.challenge.infrastructure.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zemoga.challenge.domain.actions.portfolio.FindByName;
import com.zemoga.challenge.domain.actions.portfolio.Save;
import com.zemoga.challenge.domain.actions.twitter.FindByUsernameAndSize;
import com.zemoga.challenge.domain.mock.PortfolioMock;
import com.zemoga.challenge.domain.mock.TwitterMock;
import com.zemoga.challenge.domain.model.entities.Portfolio;
import com.zemoga.challenge.domain.model.entities.TwitterUserTimeline;
import com.zemoga.challenge.infrastructure.http.dto.PortfolioDTO;
import com.zemoga.challenge.infrastructure.http.dto.PortfolioUserWithTwitterTimelineDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(value = MockitoJUnitRunner.class)
public class PortfolioControllerTest {
  private static final String PORTFOLIO_BASE_URL = "/portfolio";
  private static final String RETRIEVE_USER_DATA_SOURCE = "/user/{username}";
  private static final String POST_USER_DATA_SOURCE = "/user";

  private MockMvc mvc;
  private ObjectMapper objectMapper = new ObjectMapper();

  private JacksonTester<PortfolioUserWithTwitterTimelineDTO> portfolioUserWithTwitterTimelineJacksonTester;
  private JacksonTester<PortfolioDTO> portfolioJacksonTester;

  @Mock
  private FindByName findByName;
  @Mock
  private FindByUsernameAndSize findByUsernameAndSize;
  @Mock
  private Save save;

  @InjectMocks
  private PortfolioController portfolioController;

  @Before
  public void setUp() {
    JacksonTester.initFields(this, new ObjectMapper());
    mvc = MockMvcBuilders.standaloneSetup(portfolioController).build();
  }

  @Test
  public void getUserPortfolio_ok() throws Exception {
    String userName = "userName";
    Integer size = 5;
    Optional<Portfolio> optionalPortfolioMock = PortfolioMock.createOptional();
    List<TwitterUserTimeline> twitterUserTimelineList = TwitterMock.createList();
    PortfolioUserWithTwitterTimelineDTO portfolioUserWithTwitterTimelineDTO =
            com.zemoga.challenge.infrastructure.mock.TwitterMock.createPortfolioUserWithTwitterTimeline();

    doReturn(optionalPortfolioMock)
            .when(findByName)
            .execute(userName);

    doReturn(twitterUserTimelineList)
            .when(findByUsernameAndSize)
            .execute(anyString(), anyInt());

    mvc.perform(get(PORTFOLIO_BASE_URL + RETRIEVE_USER_DATA_SOURCE
                    .replace("{username}", userName)
                    .replace("{size}", size.toString()))
                    .accept(MediaType.APPLICATION_JSON)
                    .param("tweets", size.toString()))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().json(portfolioUserWithTwitterTimelineJacksonTester.write(portfolioUserWithTwitterTimelineDTO).getJson()));
  }

  @Test
  public void getUserPortfolio_not_found() throws Exception {
    String userName = "userName";
    Integer size = 5;

    doReturn(Optional.empty())
            .when(findByName)
            .execute(userName);

    mvc.perform(get(PORTFOLIO_BASE_URL + RETRIEVE_USER_DATA_SOURCE
                    .replace("{username}", userName)
                    .replace("{size}", size.toString()))
                    .accept(MediaType.APPLICATION_JSON)
                    .param("tweets", size.toString()))
            .andDo(print())
            .andExpect(status().isNotFound());
  }

  @Test
  public void saveUserPortfolio_ok() throws Exception {
    PortfolioDTO portfolioDTO = com.zemoga.challenge.infrastructure.mock.TwitterMock.createPortfolio();

    mvc.perform(post(PORTFOLIO_BASE_URL + POST_USER_DATA_SOURCE)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(portfolioJacksonTester.write(portfolioDTO).getJson())
            )
            .andDo(print())
            .andExpect(status().isCreated());
  }
}
