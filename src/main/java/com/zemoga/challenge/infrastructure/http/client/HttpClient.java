package com.zemoga.challenge.infrastructure.http.client;

import com.zemoga.challenge.domain.model.entities.TwitterUserTimeline;
import com.zemoga.challenge.domain.model.repositories.TwitterRepository;
import com.zemoga.challenge.infrastructure.exceptions.TwitterClientException;
import com.zemoga.challenge.infrastructure.http.dto.TwitterUserTimelineDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HttpClient implements TwitterRepository {

  private static final Logger LOGGER = LoggerFactory.getLogger(HttpClient.class);

  private final HttpClientDefinition httpClientDefinitionMap;

  public HttpClient(HttpClientDefinition httpClientDefinitionMap) {
    this.httpClientDefinitionMap = httpClientDefinitionMap;
  }

  @Override
  public List<TwitterUserTimeline> findByUsernameAndSize(String username, Integer size) {
    try {
      Response<List<TwitterUserTimelineDTO>> response = httpClientDefinitionMap.retrievePartnerStoresByEmail(username, size).execute();

      if (!response.isSuccessful()) {
        TwitterClientException.builder().message(response.message()).type(HttpStatus.valueOf(response.code())).throwIt();
      }

      return Optional.ofNullable(response.body())
              .orElseGet(Collections::emptyList)
              .stream().map(TwitterUserTimelineDTO::toTwitterUserTimeline).collect(Collectors.toList());
    } catch (Exception e) {
      LOGGER.error("[TWITTER CLIENT] error getting timeline for user {} error {}", username, e.getMessage());
      return Collections.emptyList();
    }
  }
}
