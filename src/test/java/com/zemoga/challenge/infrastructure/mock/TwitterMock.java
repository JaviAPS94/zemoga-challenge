package com.zemoga.challenge.infrastructure.mock;

import com.zemoga.challenge.infrastructure.http.dto.EntityDTO;
import com.zemoga.challenge.infrastructure.http.dto.MediaDTO;
import com.zemoga.challenge.infrastructure.http.dto.PortfolioDTO;
import com.zemoga.challenge.infrastructure.http.dto.PortfolioUserWithTwitterTimelineDTO;
import com.zemoga.challenge.infrastructure.http.dto.TwitterUserTimelineDTO;

import java.util.ArrayList;
import java.util.List;

public class TwitterMock {
  public static PortfolioUserWithTwitterTimelineDTO createPortfolioUserWithTwitterTimeline() {
    return PortfolioUserWithTwitterTimelineDTO.builder()
            .portfolio(createPortfolio())
            .tweets(createTwitterUserTimeline())
            .build();
  }

  public static PortfolioDTO createPortfolio() {
    return PortfolioDTO.builder()
            .id(1L)
            .twitterUser("twitterUser")
            .phone("09956398751")
            .zipCode("5893")
            .imagePath("https://www.zemoga.com/images/user/1/profile.jpg")
            .email("test@test.com")
            .name("test name")
            .address("test address")
            .experience("test experience")
            .build();
  }

  public static List<TwitterUserTimelineDTO> createTwitterUserTimeline() {
    List<MediaDTO> mediaDTOList = new ArrayList<>();
    List<TwitterUserTimelineDTO> twitterUserTimelineDTOList = new ArrayList<>();

    mediaDTOList.add(MediaDTO.builder()
            .mediaUrlHttps("https://pbs.twimg.com/media/Dy_7-zUU0AAZQ-q.jpg")
            .build());

    twitterUserTimelineDTOList.add(TwitterUserTimelineDTO
            .builder()
            .createdAt("2022-02-02T02:02:02Z")
            .id(1L)
            .text("Lorem")
            .entities(EntityDTO.builder()
                    .media(mediaDTOList)
                    .build())
            .build());

    return twitterUserTimelineDTOList;
  }
}
